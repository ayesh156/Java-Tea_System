package model.suppliers;

import model.Mysql;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

/**
 * Service class for managing supplier arrears in the new supplier_arrears table.
 * This replaces the old arrears and new_arrears columns in the suppliers table.
 */
public class SupplierArrearsService {

    private static final DecimalFormat df = new DecimalFormat("#.00");

    /**
     * Save or update arrears for a supplier for a specific month.
     * If arrears already exist for that month, it updates the value.
     * If arrears is 0, it still saves the record for tracking purposes.
     *
     * @param supplierId Supplier ID
     * @param year Year
     * @param month Month (1-12)
     * @param arrears Arrears amount
     */
    public void saveOrUpdateArrears(int supplierId, int year, int month, double arrears) {
        try {
            // Format arrears to 2 decimal places
            String formattedArrears = df.format(arrears);
            
            // Use INSERT ... ON DUPLICATE KEY UPDATE to handle both insert and update
            String sql = String.format(
                    "INSERT INTO supplier_arrears (supplier_id, year, month, arrears) " +
                    "VALUES (%d, %d, %d, %s) " +
                    "ON DUPLICATE KEY UPDATE arrears = %s",
                    supplierId, year, month, formattedArrears, formattedArrears
            );

            Mysql.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - saveOrUpdateArrears", ex);
        }
    }

    /**
     * Get the latest arrears for a supplier (most recent month).
     *
     * @param supplierId Supplier ID
     * @return SupplierArrearsModel or null if no arrears found
     */
    public SupplierArrearsModel getLatestArrears(int supplierId) {
        SupplierArrearsModel arrearsModel = null;
        try {
            String sql = String.format(
                    "SELECT * FROM supplier_arrears " +
                    "WHERE supplier_id = %d " +
                    "ORDER BY year DESC, month DESC LIMIT 1",
                    supplierId
            );

            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                arrearsModel = new SupplierArrearsModel();
                arrearsModel.setId(rs.getInt("id"));
                arrearsModel.setSupplierId(rs.getInt("supplier_id"));
                arrearsModel.setYear(rs.getInt("year"));
                arrearsModel.setMonth(rs.getInt("month"));
                arrearsModel.setArrears(rs.getDouble("arrears"));
                arrearsModel.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - getLatestArrears", ex);
        }
        return arrearsModel;
    }

    /**
     * Get arrears for a specific supplier and month.
     *
     * @param supplierId Supplier ID
     * @param year Year
     * @param month Month (1-12)
     * @return SupplierArrearsModel or null if not found
     */
    public SupplierArrearsModel getArrearsByMonth(int supplierId, int year, int month) {
        SupplierArrearsModel arrearsModel = null;
        try {
            String sql = String.format(
                    "SELECT * FROM supplier_arrears " +
                    "WHERE supplier_id = %d AND year = %d AND month = %d",
                    supplierId, year, month
            );

            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                arrearsModel = new SupplierArrearsModel();
                arrearsModel.setId(rs.getInt("id"));
                arrearsModel.setSupplierId(rs.getInt("supplier_id"));
                arrearsModel.setYear(rs.getInt("year"));
                arrearsModel.setMonth(rs.getInt("month"));
                arrearsModel.setArrears(rs.getDouble("arrears"));
                arrearsModel.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - getArrearsByMonth", ex);
        }
        return arrearsModel;
    }

    /**
     * Get all arrears history for a supplier.
     *
     * @param supplierId Supplier ID
     * @return List of SupplierArrearsModel
     */
    public List<SupplierArrearsModel> getArrearsHistory(int supplierId) {
        List<SupplierArrearsModel> arrearsList = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT * FROM supplier_arrears " +
                    "WHERE supplier_id = %d " +
                    "ORDER BY year DESC, month DESC",
                    supplierId
            );

            ResultSet rs = Mysql.execute(sql);
            while (rs != null && rs.next()) {
                SupplierArrearsModel arrearsModel = new SupplierArrearsModel();
                arrearsModel.setId(rs.getInt("id"));
                arrearsModel.setSupplierId(rs.getInt("supplier_id"));
                arrearsModel.setYear(rs.getInt("year"));
                arrearsModel.setMonth(rs.getInt("month"));
                arrearsModel.setArrears(rs.getDouble("arrears"));
                arrearsModel.setCreatedAt(rs.getTimestamp("created_at"));
                arrearsList.add(arrearsModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - getArrearsHistory", ex);
        }
        return arrearsList;
    }

    /**
     * Calculate arrears with 2.5% interest.
     *
     * @param arrears Original arrears amount
     * @return Arrears with interest added
     */
    public double calculateArrearsWithInterest(double arrears) {
        return arrears + (arrears * 0.025);
    }

    /**
     * Delete arrears record for a specific month.
     * Use with caution - generally you should keep historical records.
     *
     * @param supplierId Supplier ID
     * @param year Year
     * @param month Month (1-12)
     */
    public void deleteArrears(int supplierId, int year, int month) {
        try {
            String sql = String.format(
                    "DELETE FROM supplier_arrears " +
                    "WHERE supplier_id = %d AND year = %d AND month = %d",
                    supplierId, year, month
            );

            Mysql.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - deleteArrears", ex);
        }
    }

    /**
     * Get the previous month's arrears for a given year and month.
     * This is useful when calculating the current month's bill.
     *
     * @param supplierId Supplier ID
     * @param currentYear Current year
     * @param currentMonth Current month (1-12)
     * @return Previous month's arrears amount, or 0.0 if not found
     */
    public double getPreviousMonthArrears(int supplierId, int currentYear, int currentMonth) {
        try {
            // Calculate previous month and year
            int prevMonth = currentMonth - 1;
            int prevYear = currentYear;
            
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear = currentYear - 1;
            }

            SupplierArrearsModel prevArrears = getArrearsByMonth(supplierId, prevYear, prevMonth);
            if (prevArrears != null) {
                return prevArrears.getArrears();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Supplier_Arrears_Service - getPreviousMonthArrears", ex);
        }
        return 0.0;
    }
}
