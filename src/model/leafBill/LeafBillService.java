package model.leafBill;


import model.Mysql;
import model.suppliers.SuppliersService;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class LeafBillService {

    SuppliersService suppliersService = new SuppliersService();


    public List<LeafBillModel> findAll(int page, int pageSize) {
        List<String> supplierIdList = suppliersService.getAllSupplierIds();
        List<LeafBillModel> listLeafBill = new ArrayList<>();

        // Get the current date
        LocalDate now = LocalDate.now();

        // Calculate the first and last day of the previous month
        LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = now.minusMonths(1).withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());

        // Create a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String supplierId : supplierIdList) {
            try {
                int offset = pageSize * (page - 1);

                // Query for advances
                String sqlAdvance = String.format(
                        "SELECT supplier_id, SUM(price) as advance_price " +
                                "FROM advance WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter), // Format the date
                        lastDayOfLastMonth.format(formatter),   // Format the date
                        offset,
                        pageSize
                );
                ResultSet rsAdvance = Mysql.execute(sqlAdvance);

                // Get advance price
                double advancePrice = 0.0;
                if (rsAdvance != null && rsAdvance.next()) {
                    advancePrice = rsAdvance.getDouble("advance_price");
                }

                // Query for debits
                String sqlDebit = String.format(
                        "SELECT supplier_id, SUM(price) as debit_price " +
                                "FROM debits WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter), // Format the date
                        lastDayOfLastMonth.format(formatter),   // Format the date
                        offset,
                        pageSize
                );
                ResultSet rsDebit = Mysql.execute(sqlDebit);

                // Get debit price
                double debitPrice = 0.0; // Default to 0
                if (rsDebit != null && rsDebit.next()) {
                    debitPrice = rsDebit.getDouble("debit_price");
                }

                // Query for debits
                String sqlDailyLeaf = String.format(
                        "SELECT supplier_id, SUM(gross_qty) as gross_tqty, SUM(net_qty) as net_tqty, transport_rate " +
                                "FROM daily_leaf WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter), // Format the date
                        lastDayOfLastMonth.format(formatter),   // Format the date
                        offset,
                        pageSize
                );
                ResultSet rsDailyLeaf = Mysql.execute(sqlDailyLeaf);

                // Get debit price
                double dailyLeafGrossQty = 0.0; // Default to 0
                double dailyLeafNetQty = 0.0; // Default to 0
                double dailyLeafTransportRate = 0.0; // Default to 0
                if (rsDailyLeaf != null && rsDailyLeaf.next()) {
                    dailyLeafGrossQty = rsDailyLeaf.getDouble("gross_tqty");
                    dailyLeafNetQty = rsDailyLeaf.getDouble("net_tqty");
                    dailyLeafTransportRate = rsDailyLeaf.getDouble("transport_rate");
                }

                // Create LeafBillModel and set values
                LeafBillModel p = new LeafBillModel();
                p.setSupplier_id(supplierId);
                p.setAdvance_price(String.valueOf(advancePrice)); // Assuming you have a setter for advance_price
                p.setDebit_price(String.valueOf(debitPrice));     // Set the debit price
                p.setGross_tqty(String.valueOf(dailyLeafGrossQty));     // Set the debit price
                p.setNet_tqty(String.valueOf(dailyLeafNetQty));     // Set the debit price
                p.setTransport_rate(String.valueOf(dailyLeafTransportRate));     // Set the debit price
                listLeafBill.add(p);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "Advance_Service", ex);
            }
        }
        return listLeafBill;
    }




}
