package model.suppliers;

import model.leafBill.LeafBillModel;
import model.leafBill.LeafBillService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

/**
 * Handles automatic monthly arrears processing.
 * This class checks if we're in a new month and automatically saves
 * the previous month's arrears for all suppliers.
 */
public class MonthlyArrearsProcessor {

    private final LeafBillService leafBillService;
    private final SupplierArrearsService arrearsService;
    private final SuppliersService suppliersService;

    public MonthlyArrearsProcessor() {
        this.leafBillService = new LeafBillService();
        this.arrearsService = new SupplierArrearsService();
        this.suppliersService = new SuppliersService();
    }

    /**
     * Check if arrears need to be processed for the previous month.
     * This should be called when the application starts.
     * 
     * @return true if arrears were processed, false otherwise
     */
    public boolean checkAndProcessMonthlyArrears() {
        try {
            LocalDate today = LocalDate.now();
            LocalDate previousMonth = today.minusMonths(1);
            
            int prevYear = previousMonth.getYear();
            int prevMonth = previousMonth.getMonthValue();

            // Check if leaf rate exists for previous month
            if (!hasLeafRateForMonth(prevYear, prevMonth)) {
                logger.log(Level.WARNING, String.format(
                    "Leaf rate not found for %d-%02d. Cannot process arrears without leaf rate. Skipping.",
                    prevYear, prevMonth
                ));
                return false;
            }

            // Check if arrears have already been saved for the previous month
            if (arrearsAlreadyProcessed(prevYear, prevMonth)) {
                logger.log(Level.INFO, String.format(
                    "Arrears for %d-%02d already processed. Skipping.",
                    prevYear, prevMonth
                ));
                return false;
            }

            // Check if we should process (on or after the 1st of current month)
            if (shouldProcessArrears(today, previousMonth)) {
                logger.log(Level.INFO, String.format(
                    "Processing arrears for %d-%02d...",
                    prevYear, prevMonth
                ));
                
                processArrearsForMonth(prevYear, prevMonth);
                return true;
            }

            return false;

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error in monthly arrears check", ex);
            return false;
        }
    }

    /**
     * Check if leaf rate exists for a specific month.
     * Arrears cannot be calculated without leaf rate data.
     * 
     * @param year Year to check
     * @param month Month to check
     * @return true if leaf rate exists, false otherwise
     */
    private boolean hasLeafRateForMonth(int year, int month) {
        try {
            String sql = String.format(
                "SELECT COUNT(*) as count FROM leaf_rate lr " +
                "JOIN year y ON lr.year_id = y.id " +
                "JOIN month m ON lr.month_id = m.id " +
                "WHERE y.year = %d AND m.id = %d",
                year, month
            );
            
            java.sql.ResultSet rs = model.Mysql.execute(sql);
            if (rs != null && rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
            return false;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error checking leaf rate existence", ex);
            return false; // Assume no leaf rate on error (safe default)
        }
    }

    /**
     * Check if we should process arrears based on the current date.
     * 
     * @param today Current date
     * @param previousMonth Previous month date
     * @return true if we should process
     */
    private boolean shouldProcessArrears(LocalDate today, LocalDate previousMonth) {
        // Process if:
        // 1. We're in a month after the previous month (e.g., in November, process October)
        // 2. We're on or after the 1st of the current month
        return today.isAfter(previousMonth.withDayOfMonth(previousMonth.lengthOfMonth()));
    }

    /**
     * Check if arrears have already been saved for a specific month.
     * Now checks if ALL suppliers with data have been processed, not just if ANY records exist.
     * 
     * @param year Year to check
     * @param month Month to check
     * @return true if all suppliers have been processed for that month
     */
    private boolean arrearsAlreadyProcessed(int year, int month) {
        try {
            // Count how many suppliers have daily_leaf data for this month
            String sqlSuppliersWithData = String.format(
                "SELECT COUNT(DISTINCT s.id) as count " +
                "FROM suppliers s " +
                "WHERE EXISTS (" +
                "  SELECT 1 FROM daily_leaf dl " +
                "  JOIN year y ON dl.year_id = y.id " +
                "  JOIN month m ON dl.month_id = m.id " +
                "  WHERE dl.supplier_id = s.id " +
                "  AND y.year = %d AND m.id = %d" +
                ")",
                year, month
            );
            
            java.sql.ResultSet rsTotal = model.Mysql.execute(sqlSuppliersWithData);
            int totalSuppliersWithData = 0;
            if (rsTotal != null && rsTotal.next()) {
                totalSuppliersWithData = rsTotal.getInt("count");
            }
            
            // If no suppliers have data for this month, don't process
            if (totalSuppliersWithData == 0) {
                logger.log(Level.INFO, String.format(
                    "No suppliers with data found for %d-%02d. Skipping.",
                    year, month
                ));
                return true; // Consider it "processed" (nothing to process)
            }
            
            // Count how many arrears records exist for this month
            String sqlArrearsRecords = String.format(
                "SELECT COUNT(*) as count FROM supplier_arrears WHERE year = %d AND month = %d",
                year, month
            );
            
            java.sql.ResultSet rsArrears = model.Mysql.execute(sqlArrearsRecords);
            int arrearsRecordCount = 0;
            if (rsArrears != null && rsArrears.next()) {
                arrearsRecordCount = rsArrears.getInt("count");
            }
            
            // Check if processing is complete
            // Processing is complete if we have at least as many arrears records as suppliers with data
            boolean isComplete = arrearsRecordCount >= totalSuppliersWithData;
            
            if (arrearsRecordCount > 0) {
                logger.log(Level.INFO, String.format(
                    "Arrears status for %d-%02d: %d records exist, %d suppliers with data. %s",
                    year, month, arrearsRecordCount, totalSuppliersWithData,
                    isComplete ? "Processing complete." : "Incomplete - will reprocess."
                ));
            }
            
            return isComplete;
            
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error checking if arrears processed", ex);
            return false; // Changed: Allow reprocessing on error instead of blocking
        }
    }

    /**
     * Process and save arrears for all suppliers for a specific month.
     * 
     * @param year Year to process
     * @param month Month to process (1-12)
     */
    private void processArrearsForMonth(int year, int month) {
        try {
            // Generate bills for the previous month to get final amounts
            // We need to use a large page size to get all suppliers
            int pageSize = 10000; // Large enough to get all suppliers
            List<LeafBillModel> bills = leafBillService.findAll(1, pageSize);

            int processedCount = 0;
            int skippedCount = 0;
            int zeroArrearsSkipped = 0;

            // Format the last day of the month for last_modify update
            LocalDate lastDayOfMonth = LocalDate.of(year, month, 1)
                .withDayOfMonth(LocalDate.of(year, month, 1).lengthOfMonth());
            String lastDayString = lastDayOfMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            for (LeafBillModel bill : bills) {
                try {
                    int supplierId = bill.getSupplier_id();
                    String finalAmountStr = bill.getFinalAmount();
                    
                    // Parse final amount
                    double finalAmount = Double.parseDouble(finalAmountStr);
                    double arrearsToSave = 0.0;

                    /**
                     * ARREARS LOGIC:
                     * - If finalAmount < 0: Supplier owes money (save absolute value)
                     * - If finalAmount >= 0: No arrears (skip - don't save zero records)
                     * 
                     * OPTIMIZATION: Only save records for suppliers with actual arrears.
                     * This prevents database clutter with unnecessary zero-value records.
                     */
                    if (finalAmount < 0) {
                        arrearsToSave = Math.abs(finalAmount);
                        
                        // Only save if there's actual arrears amount
                        arrearsService.saveOrUpdateArrears(supplierId, year, month, arrearsToSave);
                        
                        // Update last_modify date only for suppliers with arrears
                        suppliersService.updateLastModify(supplierId, lastDayString);
                        
                        processedCount++;
                        
                        logger.log(Level.FINE, String.format(
                            "Saved arrears for supplier %d: %.2f", supplierId, arrearsToSave
                        ));
                    } else {
                        // Skip suppliers with zero or positive final amount (no arrears)
                        zeroArrearsSkipped++;
                        
                        logger.log(Level.FINEST, String.format(
                            "Skipped supplier %d (no arrears, finalAmount: %.2f)", 
                            supplierId, finalAmount
                        ));
                    }

                } catch (Exception ex) {
                    logger.log(Level.WARNING, 
                        String.format("Error processing arrears for supplier %d", 
                            bill.getSupplier_id()), ex);
                    skippedCount++;
                }
            }

            logger.log(Level.INFO, String.format(
                "Arrears processing complete for %d-%02d: %d with arrears saved, %d with zero arrears skipped, %d errors",
                year, month, processedCount, zeroArrearsSkipped, skippedCount
            ));

        } catch (Exception ex) {
            logger.log(Level.SEVERE, 
                String.format("Error processing arrears for %d-%02d", year, month), ex);
        }
    }

    /**
     * Force process arrears for a specific month, even if already processed.
     * Use with caution - this will overwrite existing records.
     * 
     * @param year Year to process
     * @param month Month to process (1-12)
     */
    public void forceProcessArrears(int year, int month) {
        logger.log(Level.WARNING, String.format(
            "Force processing arrears for %d-%02d (overwriting existing records)",
            year, month
        ));
        processArrearsForMonth(year, month);
    }

    /**
     * Check what months have been processed and provide diagnostic information.
     * 
     * @return Diagnostic message
     */
    public String getDiagnosticInfo() {
        try {
            StringBuilder info = new StringBuilder();
            LocalDate today = LocalDate.now();
            LocalDate previousMonth = today.minusMonths(1);
            int prevYear = previousMonth.getYear();
            int prevMonth = previousMonth.getMonthValue();
            
            info.append(String.format("=== ARREARS DIAGNOSTIC INFO ===\n"));
            info.append(String.format("Current Date: %s\n", today));
            info.append(String.format("Previous Month: %d-%02d\n\n", prevYear, prevMonth));
            
            // Check leaf rate
            boolean hasLeafRate = hasLeafRateForMonth(prevYear, prevMonth);
            info.append(String.format("Leaf Rate Exists for %d-%02d: %s\n", 
                prevYear, prevMonth, hasLeafRate ? "YES" : "NO"));
            
            // Check arrears records
            String sql = String.format(
                "SELECT COUNT(*) as count FROM supplier_arrears WHERE year = %d AND month = %d",
                prevYear, prevMonth
            );
            java.sql.ResultSet rs = model.Mysql.execute(sql);
            if (rs != null && rs.next()) {
                int count = rs.getInt("count");
                info.append(String.format("Arrears Records for %d-%02d: %d\n\n", 
                    prevYear, prevMonth, count));
            }
            
            // Get last 3 processed months
            sql = "SELECT year, month, COUNT(*) as count, SUM(arrears) as total " +
                  "FROM supplier_arrears " +
                  "GROUP BY year, month " +
                  "ORDER BY year DESC, month DESC LIMIT 3";
            rs = model.Mysql.execute(sql);
            info.append("Last 3 Processed Months:\n");
            while (rs != null && rs.next()) {
                info.append(String.format("  %d-%02d: %d suppliers, Total: %.2f\n",
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("count"),
                    rs.getDouble("total")
                ));
            }
            
            return info.toString();
        } catch (Exception ex) {
            return "Error getting diagnostic info: " + ex.getMessage();
        }
    }

    /**
     * Get status information about the last processed month.
     * 
     * @return Status message string
     */
    public String getProcessingStatus() {
        try {
            String sql = "SELECT year, month, COUNT(*) as supplier_count, SUM(arrears) as total_arrears " +
                        "FROM supplier_arrears " +
                        "GROUP BY year, month " +
                        "ORDER BY year DESC, month DESC LIMIT 1";
            
            java.sql.ResultSet rs = model.Mysql.execute(sql);
            if (rs != null && rs.next()) {
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                int count = rs.getInt("supplier_count");
                double total = rs.getDouble("total_arrears");
                
                return String.format(
                    "Last processed: %d-%02d | Suppliers: %d | Total arrears: %.2f",
                    year, month, count, total
                );
            }
            return "No arrears processed yet";
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error getting processing status", ex);
            return "Status unavailable";
        }
    }
}
