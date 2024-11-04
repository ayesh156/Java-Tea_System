package model.leafBill;


import model.Mysql;
import model.suppliers.SupplierDetails;
import model.suppliers.SuppliersService;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static gui.Home.logger;

public class LeafBillService {

    SuppliersService suppliersService = new SuppliersService();


    public List<LeafBillModel> findAll(int page, int pageSize) {
        // Use getAllSuppliers to get a map of supplier details (name and doc_rate)
        Map<String, SupplierDetails> suppliersMap = suppliersService.getAllSuppliers(page, pageSize);
        List<LeafBillModel> listLeafBill = new ArrayList<>();

        // Get the current date
        LocalDate now = LocalDate.now();

        // Calculate the first and last day of the previous month
        LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = now.minusMonths(1).withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());

        // Extract year and month for the query
        String year = String.valueOf(firstDayOfLastMonth.getYear());
        String month = String.valueOf(firstDayOfLastMonth.getMonthValue());

        String previousMonthLeafRate = "";

        try {
            // Query for leaf rates
            String sqlLeafRate = String.format(
                    "SELECT lr.leaf_rate " +
                            "FROM leaf_rate lr " +
                            "JOIN year y ON lr.year_id = y.id " +
                            "JOIN month m ON lr.month_id = m.id " +
                            "WHERE y.year = '%s' AND m.id = '%s'",
                    year,
                    month
            );

            ResultSet rsLeafRate = Mysql.execute(sqlLeafRate);

            // Get leaf rate
            if (rsLeafRate != null && rsLeafRate.next()) {
                previousMonthLeafRate = rsLeafRate.getString("leaf_rate");
            } else {
                previousMonthLeafRate = "0";
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            logger.log(Level.WARNING, "Leaf_Bill_Service", e);
        }

        // Create a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Map.Entry<String, SupplierDetails> supplierEntry : suppliersMap.entrySet()) {
            String supplierId = supplierEntry.getKey();
            SupplierDetails supplierDetails = supplierEntry.getValue();
            String supplierName = supplierDetails.getName(); // Get supplier name
            String transportRate = supplierDetails.getTransportRate();
            String docRate = supplierDetails.getDocRate(); // Get doc_rate
            String arrears = supplierDetails.getArrears(); // Get doc_rate

            try {
                int offset = pageSize * (page - 1);

                // Query for advances
                String sqlAdvance = String.format(
                        "SELECT supplier_id, SUM(price) as advance_price " +
                                "FROM advance WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
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
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDebit = Mysql.execute(sqlDebit);

                // Get debit price
                double debitPrice = 0.0; // Default to 0
                if (rsDebit != null && rsDebit.next()) {
                    debitPrice = rsDebit.getDouble("debit_price");
                }

                // Query for daily leaf data
                String sqlDailyLeaf = String.format(
                        "SELECT supplier_id, SUM(gross_qty) as gross_tqty, SUM(net_qty) as net_tqty, transport_rate " +
                                "FROM daily_leaf WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDailyLeaf = Mysql.execute(sqlDailyLeaf);

                double dailyLeafGrossQty = 0.0;
                double dailyLeafNetQty = 0.0;
                if (rsDailyLeaf != null && rsDailyLeaf.next()) {
                    dailyLeafGrossQty = rsDailyLeaf.getDouble("gross_tqty");
                    dailyLeafNetQty = rsDailyLeaf.getDouble("net_tqty");
                }

                // Query for tea table data
                String sqlTea = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_tea " +
                                "FROM tea WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsTea = Mysql.execute(sqlTea);

                // Variables to store tea quantity and total revenue
                double totalTea = 0.0;

                if (rsTea != null && rsTea.next()) {
                    totalTea = rsTea.getDouble("total_tea");
                }

                // Query for manure table data
                String sqlManure = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_manure " +
                                "FROM manure WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsManure = Mysql.execute(sqlManure);

                // Variables to store tea quantity and total revenue
                double totalManure = 0.0;

                if (rsManure != null && rsManure.next()) {
                    totalManure = rsManure.getDouble("total_manure");
                }

                // Query for dolomite table data
                String sqlDolomite = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_dolomite " +
                                "FROM dolomite WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDolomite = Mysql.execute(sqlDolomite);

                double totalDolomite = 0.0;

                if (rsDolomite != null && rsDolomite.next()) {
                    totalDolomite = rsDolomite.getDouble("total_dolomite");
                }

                double leafRateValue = Double.parseDouble(previousMonthLeafRate); // Convert leaf rate to double

                // Calculate total price
                double totalPrice = leafRateValue * dailyLeafNetQty;

                double transportRateValue = Double.parseDouble(transportRate);

                double transportPrice = dailyLeafNetQty * transportRateValue;

                // Format the transport price to two decimal places
                DecimalFormat df = new DecimalFormat("#.00");
                String formattedTransportPrice;
                String formattedTotalPrice;
                String formattedTotalDeductionse;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (transportPrice == 0) {
                    formattedTransportPrice = "0.00";
                } else {
                    formattedTransportPrice = df.format(transportPrice);
                }

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalPrice == 0) {
                    formattedTotalPrice = "0.00";
                } else {
                    formattedTotalPrice = df.format(totalPrice);
                }

                double docRateValue = Double.parseDouble(docRate);
                double debitPriceValue = Double.parseDouble(String.valueOf(debitPrice));
                double advancePriceValue = Double.parseDouble(String.valueOf(advancePrice));
                double transportPriceValue = Double.parseDouble(formattedTransportPrice); // Already formatted
                double totalTeaValue = Double.parseDouble(String.valueOf(totalTea));
                double totalManureValue = Double.parseDouble(String.valueOf(totalManure));
                double totalDolomiteValue = Double.parseDouble(String.valueOf(totalDolomite));

                Double arrearsDouble = null;

                try {
                    arrearsDouble = Double.parseDouble(arrears);
                    // Add 2.5% interest if the conversion is successful
                    arrearsDouble += arrearsDouble * 0.025;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Handle the case where the string is not a valid double, possibly assign a default value
                    arrearsDouble = 0.0; // Default value if conversion fails
                    logger.log(Level.WARNING, "Leaf_Bill_Service", e);
                }

                // Sum the values
                double totalDeductions = docRateValue + advancePriceValue + debitPriceValue +
                        transportPriceValue + totalTeaValue + totalManureValue +
                        totalDolomiteValue + arrearsDouble;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalDeductions == 0) {
                    formattedTotalDeductionse = "0.00";
                } else {
                    formattedTotalDeductionse = df.format(totalDeductions);
                }

                // Calculate finalAmount as formattedTotalPrice - totalDeductions
                double formattedTotalPriceValue = Double.parseDouble(formattedTotalPrice);


                // Fetch supplier's current arrears from the suppliers table using supplierId
                double supplierArrears = Double.parseDouble(arrears);

                double finalAmount = formattedTotalPriceValue - totalDeductions;

                boolean arrearsSetZero = false;
                double newArrears = 0.0;

                if (finalAmount < 0) {
                    // If finalAmount is negative, insert its absolute value as arrears into suppliers table

                    if (formattedTotalPriceValue != 0) {
//                        suppliersService.updateSupplierArrears(supplierId, String.valueOf(newArrears));
                        newArrears = Math.abs(finalAmount);
                    }

                } else {
                    // If finalAmount is positive, deduct the supplier's arrears from it
                    if (supplierArrears > 0) {
                        if (finalAmount >= supplierArrears) {

                            arrearsSetZero = true;
                            // Reset supplier's arrears to 0
//                            suppliersService.updateSupplierArrears(supplierId, "0");
                        } else {
                            // Deduct partial arrears, and remaining arrears stay in supplier's record
                            double remainingArrears = supplierArrears - finalAmount;

                            if (formattedTotalPriceValue != 0) {
                                // Update the remaining arrears in the suppliers table
                                suppliersService.updateSupplierArrears(supplierId, String.valueOf(remainingArrears));
                            }

                        }
                    }
                }

                String formattedFinalAmount;
                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (finalAmount == 0) {
                    formattedFinalAmount = "0.00";
                } else {
                    formattedFinalAmount = df.format(finalAmount);
                }

                String formattedArreas;
                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (arrearsDouble == 0) {
                    formattedArreas = "0.00";
                } else {
                    formattedArreas = df.format(arrearsDouble);
                }

                String billNumber = generateBillNumber(supplierId, year, month);

                // Create LeafBillModel and set values
                LeafBillModel p = new LeafBillModel();
                p.setSupplier_id(supplierId);
                p.setSupplier_name(supplierName); // Set the supplier name
                p.setDoc_rate(docRate); // Set the doc_rate from SupplierDetails
                p.setAdvance_price(String.valueOf(advancePrice));
                p.setDebit_price(String.valueOf(debitPrice));
                p.setGross_tqty(String.valueOf(dailyLeafGrossQty));
                p.setNet_tqty(String.valueOf(dailyLeafNetQty));
                p.setTransport_rate(formattedTransportPrice);
                p.setTea(String.valueOf(totalTea));
                p.setManure(String.valueOf(totalManure));
                p.setDolomite(String.valueOf(totalDolomite)); // Set the dolomite revenue
                p.setLeafRate(previousMonthLeafRate);
                p.setTotalLeafPrice(formattedTotalPrice);
                p.setArrears(formattedArreas);
                p.setTotalDeductions(String.valueOf(formattedTotalDeductionse));
                p.setFinalAmount(formattedFinalAmount);
                p.setArrearsSetZero(arrearsSetZero);
                p.setNewArrears(String.valueOf(newArrears));
                p.setBillNumber(billNumber);

                listLeafBill.add(p);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "Leaf_Bill_Service", ex);
            }
        }
        return listLeafBill;
    }

    public List<LeafBillModel> find(String searchText, int page, int pageSize) {
        // Use getAllSuppliers to get a map of supplier details (name and doc_rate)
        Map<String, SupplierDetails> suppliersMap = suppliersService.searchLeafBillSuppliers(searchText, page, pageSize);
        List<LeafBillModel> listLeafBill = new ArrayList<>();

        // Get the current date
        LocalDate now = LocalDate.now();

        // Calculate the first and last day of the previous month
        LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = now.minusMonths(1).withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());

        // Extract year and month for the query
        String year = String.valueOf(firstDayOfLastMonth.getYear());
        String month = String.valueOf(firstDayOfLastMonth.getMonthValue());

        String previousMonthLeafRate = "";

        try {
            // Query for leaf rates
            String sqlLeafRate = String.format(
                    "SELECT lr.leaf_rate " +
                            "FROM leaf_rate lr " +
                            "JOIN year y ON lr.year_id = y.id " +
                            "JOIN month m ON lr.month_id = m.id " +
                            "WHERE y.year = '%s' AND m.id = '%s'",
                    year,
                    month
            );

            ResultSet rsLeafRate = Mysql.execute(sqlLeafRate);

            // Get leaf rate
            if (rsLeafRate != null && rsLeafRate.next()) {
                previousMonthLeafRate = rsLeafRate.getString("leaf_rate");
            } else {
                previousMonthLeafRate = "0";
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            logger.log(Level.WARNING, "Leaf_Bill_Service", e);
        }

        // Create a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Map.Entry<String, SupplierDetails> supplierEntry : suppliersMap.entrySet()) {
            String supplierId = supplierEntry.getKey();
            SupplierDetails supplierDetails = supplierEntry.getValue();
            String supplierName = supplierDetails.getName(); // Get supplier name
            String transportRate = supplierDetails.getTransportRate();
            String docRate = supplierDetails.getDocRate(); // Get doc_rate
            String arrears = supplierDetails.getArrears(); // Get doc_rate

            try {
                int offset = pageSize * (page - 1);

                // Query for advances
                String sqlAdvance = String.format(
                        "SELECT supplier_id, SUM(price) as advance_price " +
                                "FROM advance WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
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
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDebit = Mysql.execute(sqlDebit);

                // Get debit price
                double debitPrice = 0.0; // Default to 0
                if (rsDebit != null && rsDebit.next()) {
                    debitPrice = rsDebit.getDouble("debit_price");
                }

                // Query for daily leaf data
                String sqlDailyLeaf = String.format(
                        "SELECT supplier_id, SUM(gross_qty) as gross_tqty, SUM(net_qty) as net_tqty, transport_rate " +
                                "FROM daily_leaf WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDailyLeaf = Mysql.execute(sqlDailyLeaf);

                double dailyLeafGrossQty = 0.0;
                double dailyLeafNetQty = 0.0;
                if (rsDailyLeaf != null && rsDailyLeaf.next()) {
                    dailyLeafGrossQty = rsDailyLeaf.getDouble("gross_tqty");
                    dailyLeafNetQty = rsDailyLeaf.getDouble("net_tqty");
                }

                // Query for tea table data
                String sqlTea = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_tea " +
                                "FROM tea WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsTea = Mysql.execute(sqlTea);

                // Variables to store tea quantity and total revenue
                double totalTea = 0.0;

                if (rsTea != null && rsTea.next()) {
                    totalTea = rsTea.getDouble("total_tea");
                }

                // Query for manure table data
                String sqlManure = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_manure " +
                                "FROM manure WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsManure = Mysql.execute(sqlManure);

                // Variables to store tea quantity and total revenue
                double totalManure = 0.0;

                if (rsManure != null && rsManure.next()) {
                    totalManure = rsManure.getDouble("total_manure");
                }

                // Query for dolomite table data
                String sqlDolomite = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_dolomite " +
                                "FROM dolomite WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDolomite = Mysql.execute(sqlDolomite);

                double totalDolomite = 0.0;

                if (rsDolomite != null && rsDolomite.next()) {
                    totalDolomite = rsDolomite.getDouble("total_dolomite");
                }

                double leafRateValue = Double.parseDouble(previousMonthLeafRate); // Convert leaf rate to double

                // Calculate total price
                double totalPrice = leafRateValue * dailyLeafNetQty;

                double transportRateValue = Double.parseDouble(transportRate);

                double transportPrice = dailyLeafNetQty * transportRateValue;

                // Format the transport price to two decimal places
                DecimalFormat df = new DecimalFormat("#.00");
                String formattedTransportPrice;
                String formattedTotalPrice;
                String formattedTotalDeductionse;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (transportPrice == 0) {
                    formattedTransportPrice = "0.00";
                } else {
                    formattedTransportPrice = df.format(transportPrice);
                }

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalPrice == 0) {
                    formattedTotalPrice = "0.00";
                } else {
                    formattedTotalPrice = df.format(totalPrice);
                }

                double docRateValue = Double.parseDouble(docRate);
                double debitPriceValue = Double.parseDouble(String.valueOf(debitPrice));
                double advancePriceValue = Double.parseDouble(String.valueOf(advancePrice));
                double transportPriceValue = Double.parseDouble(formattedTransportPrice); // Already formatted
                double totalTeaValue = Double.parseDouble(String.valueOf(totalTea));
                double totalManureValue = Double.parseDouble(String.valueOf(totalManure));
                double totalDolomiteValue = Double.parseDouble(String.valueOf(totalDolomite));

                Double arrearsDouble = null;

                try {
                    arrearsDouble = Double.parseDouble(arrears);
                    // Add 2.5% interest if the conversion is successful
                    arrearsDouble += arrearsDouble * 0.025;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Handle the case where the string is not a valid double, possibly assign a default value
                    arrearsDouble = 0.0; // Default value if conversion fails
                    logger.log(Level.WARNING, "Leaf_Bill_Service", e);
                }

                // Sum the values
                double totalDeductions = docRateValue + advancePriceValue + debitPriceValue +
                        transportPriceValue + totalTeaValue + totalManureValue +
                        totalDolomiteValue + arrearsDouble;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalDeductions == 0) {
                    formattedTotalDeductionse = "0.00";
                } else {
                    formattedTotalDeductionse = df.format(totalDeductions);
                }

                // Calculate finalAmount as formattedTotalPrice - totalDeductions
                double formattedTotalPriceValue = Double.parseDouble(formattedTotalPrice);


                // Fetch supplier's current arrears from the suppliers table using supplierId
                double supplierArrears = Double.parseDouble(arrears);

                double finalAmount = formattedTotalPriceValue - totalDeductions;

                boolean arrearsSetZero = false;
                double newArrears = 0.0;

                if (finalAmount < 0) {
                    // If finalAmount is negative, insert its absolute value as arrears into suppliers table

                    if (formattedTotalPriceValue != 0) {
//                        suppliersService.updateSupplierArrears(supplierId, String.valueOf(newArrears));
                        newArrears = Math.abs(finalAmount);
                    }

                } else {
                    // If finalAmount is positive, deduct the supplier's arrears from it
                    if (supplierArrears > 0) {
                        if (finalAmount >= supplierArrears) {
                            arrearsSetZero = true;
                            // Reset supplier's arrears to 0
//                            suppliersService.updateSupplierArrears(supplierId, "0");
                        } else {
                            // Deduct partial arrears, and remaining arrears stay in supplier's record
                            double remainingArrears = supplierArrears - finalAmount;

                            if (formattedTotalPriceValue != 0) {
                                // Update the remaining arrears in the suppliers table
                                suppliersService.updateSupplierArrears(supplierId, String.valueOf(remainingArrears));
                            }

                        }
                    }
                }

                String formattedFinalAmount;
                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (finalAmount == 0) {
                    formattedFinalAmount = "0.00";
                } else {
                    formattedFinalAmount = df.format(finalAmount);
                }

                String formattedArreas;
                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (arrearsDouble == 0) {
                    formattedArreas = "0.00";
                } else {
                    formattedArreas = df.format(arrearsDouble);
                }

                String billNumber = generateBillNumber(supplierId, year, month);

                // Create LeafBillModel and set values
                LeafBillModel p = new LeafBillModel();
                p.setSupplier_id(supplierId);
                p.setSupplier_name(supplierName); // Set the supplier name
                p.setDoc_rate(docRate); // Set the doc_rate from SupplierDetails
                p.setAdvance_price(String.valueOf(advancePrice));
                p.setDebit_price(String.valueOf(debitPrice));
                p.setGross_tqty(String.valueOf(dailyLeafGrossQty));
                p.setNet_tqty(String.valueOf(dailyLeafNetQty));
                p.setTransport_rate(formattedTransportPrice);
                p.setTea(String.valueOf(totalTea));
                p.setManure(String.valueOf(totalManure));
                p.setDolomite(String.valueOf(totalDolomite)); // Set the dolomite revenue
                p.setLeafRate(previousMonthLeafRate);
                p.setTotalLeafPrice(formattedTotalPrice);
                p.setArrears(formattedArreas);
                p.setTotalDeductions(String.valueOf(formattedTotalDeductionse));
                p.setFinalAmount(formattedFinalAmount);
                p.setArrearsSetZero(arrearsSetZero);
                p.setNewArrears(String.valueOf(newArrears));
                p.setBillNumber(billNumber);

                listLeafBill.add(p);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "Leaf_Bill_Service", ex);
            }
        }
        return listLeafBill;
    }

    public List<LeafBillModel> findByYearMonth(String searchText, Object searchYear, Object searchMonth, int page, int pageSize) {

        // Use getAllSuppliers to get a map of supplier details (name and doc_rate)
        Map<String, SupplierDetails> suppliersMap = suppliersService.searchLeafBillSuppliers(searchText, page, pageSize);
        List<LeafBillModel> listLeafBill = new ArrayList<>();

        // Get the current date
        LocalDate now = LocalDate.now();

        // Calculate the first and last day of the previous month
        LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = now.minusMonths(1).withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());

        // Extract year and month for the query
        String year = String.valueOf(firstDayOfLastMonth.getYear());
        String month = String.valueOf(firstDayOfLastMonth.getMonthValue());

        // Variables to hold year and month, initialized with default values
        int parsedYear = firstDayOfLastMonth.getYear();
        int parsedMonth = firstDayOfLastMonth.getMonthValue();

        // If searchYear is provided, override the year
        if (!"අවුරුද්ද".equals(searchYear)) {
            parsedYear = Integer.parseInt(searchYear.toString());
            year = searchYear.toString();
        }

        // If searchMonth is provided, override the month
        if (!"මාසය".equals(searchMonth)) {
            // Extract the numeric month from the format "1 - month name"
            String[] monthParts = searchMonth.toString().split(" - ");
            parsedMonth = Integer.parseInt(monthParts[0]);  // Get the first part which is the numeric month
            month = monthParts[0];
        }

        // Update firstDayOfLastMonth and lastDayOfLastMonth using the potentially updated year and month
        firstDayOfLastMonth = LocalDate.of(parsedYear, parsedMonth, 1);
        lastDayOfLastMonth = firstDayOfLastMonth.withDayOfMonth(firstDayOfLastMonth.lengthOfMonth());


        String previousMonthLeafRate = "";

        try {
            // Query for leaf rates
            String sqlLeafRate = String.format(
                    "SELECT lr.leaf_rate " +
                            "FROM leaf_rate lr " +
                            "JOIN year y ON lr.year_id = y.id " +
                            "JOIN month m ON lr.month_id = m.id " +
                            "WHERE y.year = '%s' AND m.id = '%s'",
                    year,
                    month
            );

            ResultSet rsLeafRate = Mysql.execute(sqlLeafRate);

            // Get leaf rate
            if (rsLeafRate != null && rsLeafRate.next()) {
                previousMonthLeafRate = rsLeafRate.getString("leaf_rate");
            } else {
                previousMonthLeafRate = "0";
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            logger.log(Level.WARNING, "Leaf_Bill_Service", e);
        }

        // Create a DateTimeFormatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Map.Entry<String, SupplierDetails> supplierEntry : suppliersMap.entrySet()) {
            String supplierId = supplierEntry.getKey();
            SupplierDetails supplierDetails = supplierEntry.getValue();
            String supplierName = supplierDetails.getName(); // Get supplier name
            String transportRate = supplierDetails.getTransportRate();
            String docRate = supplierDetails.getDocRate(); // Get doc_rate
            String arrears = supplierDetails.getArrears();

            try {
                int offset = pageSize * (page - 1);

                // Query for advances
                String sqlAdvance = String.format(
                        "SELECT supplier_id, SUM(price) as advance_price " +
                                "FROM advance WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
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
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDebit = Mysql.execute(sqlDebit);

                // Get debit price
                double debitPrice = 0.0; // Default to 0
                if (rsDebit != null && rsDebit.next()) {
                    debitPrice = rsDebit.getDouble("debit_price");
                }

                // Query for daily leaf data
                String sqlDailyLeaf = String.format(
                        "SELECT supplier_id, SUM(gross_qty) as gross_tqty, SUM(net_qty) as net_tqty, transport_rate " +
                                "FROM daily_leaf WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDailyLeaf = Mysql.execute(sqlDailyLeaf);

                double dailyLeafGrossQty = 0.0;
                double dailyLeafNetQty = 0.0;
                if (rsDailyLeaf != null && rsDailyLeaf.next()) {
                    dailyLeafGrossQty = rsDailyLeaf.getDouble("gross_tqty");
                    dailyLeafNetQty = rsDailyLeaf.getDouble("net_tqty");
                }

                // Query for tea table data
                String sqlTea = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_tea " +
                                "FROM tea WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsTea = Mysql.execute(sqlTea);

                // Variables to store tea quantity and total revenue
                double totalTea = 0.0;

                if (rsTea != null && rsTea.next()) {
                    totalTea = rsTea.getDouble("total_tea");
                }

                // Query for manure table data
                String sqlManure = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_manure " +
                                "FROM manure WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsManure = Mysql.execute(sqlManure);

                // Variables to store tea quantity and total revenue
                double totalManure = 0.0;

                if (rsManure != null && rsManure.next()) {
                    totalManure = rsManure.getDouble("total_manure");
                }

                // Query for dolomite table data
                String sqlDolomite = String.format(
                        "SELECT supplier_id, SUM(qty * price) as total_dolomite " +
                                "FROM dolomite WHERE supplier_id = '%s' AND date >= '%s' AND date <= '%s' " +
                                "GROUP BY supplier_id LIMIT %d, %d",
                        supplierId,
                        firstDayOfLastMonth.format(formatter),
                        lastDayOfLastMonth.format(formatter),
                        offset,
                        pageSize
                );
                ResultSet rsDolomite = Mysql.execute(sqlDolomite);

                double totalDolomite = 0.0;

                if (rsDolomite != null && rsDolomite.next()) {
                    totalDolomite = rsDolomite.getDouble("total_dolomite");
                }

                double leafRateValue = Double.parseDouble(previousMonthLeafRate); // Convert leaf rate to double

                // Calculate total price
                double totalPrice = leafRateValue * dailyLeafNetQty;

                double transportRateValue = Double.parseDouble(transportRate);

                double transportPrice = dailyLeafNetQty * transportRateValue;

                // Format the transport price to two decimal places
                DecimalFormat df = new DecimalFormat("#.00");
                String formattedTransportPrice;
                String formattedTotalPrice;
                String formattedTotalDeductionse;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (transportPrice == 0) {
                    formattedTransportPrice = "0.00";
                } else {
                    formattedTransportPrice = df.format(transportPrice);
                }

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalPrice == 0) {
                    formattedTotalPrice = "0.00";
                } else {
                    formattedTotalPrice = df.format(totalPrice);
                }

                double docRateValue = Double.parseDouble(docRate);
                double debitPriceValue = Double.parseDouble(String.valueOf(debitPrice));
                double advancePriceValue = Double.parseDouble(String.valueOf(advancePrice));
                double transportPriceValue = Double.parseDouble(formattedTransportPrice); // Already formatted
                double totalTeaValue = Double.parseDouble(String.valueOf(totalTea));
                double totalManureValue = Double.parseDouble(String.valueOf(totalManure));
                double totalDolomiteValue = Double.parseDouble(String.valueOf(totalDolomite));

                Double arrearsDouble = null;

                try {
                    arrearsDouble = Double.parseDouble(arrears);
                    // Add 2.5% interest if the conversion is successful
                    arrearsDouble += arrearsDouble * 0.025;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Handle the case where the string is not a valid double, possibly assign a default value
                    arrearsDouble = 0.0; // Default value if conversion fails
                    logger.log(Level.WARNING, "Leaf_Bill_Service", e);
                }

                // Sum the values
                double totalDeductions = docRateValue + advancePriceValue + debitPriceValue +
                        transportPriceValue + totalTeaValue + totalManureValue +
                        totalDolomiteValue + arrearsDouble;

                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (totalDeductions == 0) {
                    formattedTotalDeductionse = "0.00";
                } else {
                    formattedTotalDeductionse = df.format(totalDeductions);
                }

                // Calculate finalAmount as formattedTotalPrice - totalDeductions
                double formattedTotalPriceValue = Double.parseDouble(formattedTotalPrice);


                double finalAmount = formattedTotalPriceValue - totalDeductions;

                boolean arrearsSetZero = false;

                String formattedFinalAmount;
                // Check if transportPrice is 0, set formattedTransportPrice accordingly
                if (finalAmount == 0) {
                    formattedFinalAmount = "0.00";
                } else {
                    formattedFinalAmount = df.format(finalAmount);
                }


                String billNumber = generateBillNumber(supplierId, year, month);

                // Create LeafBillModel and set values
                LeafBillModel p = new LeafBillModel();
                p.setSupplier_id(supplierId);
                p.setSupplier_name(supplierName); // Set the supplier name
                p.setDoc_rate(docRate); // Set the doc_rate from SupplierDetails
                p.setAdvance_price(String.valueOf(advancePrice));
                p.setDebit_price(String.valueOf(debitPrice));
                p.setGross_tqty(String.valueOf(dailyLeafGrossQty));
                p.setNet_tqty(String.valueOf(dailyLeafNetQty));
                p.setTransport_rate(formattedTransportPrice);
                p.setTea(String.valueOf(totalTea));
                p.setManure(String.valueOf(totalManure));
                p.setDolomite(String.valueOf(totalDolomite)); // Set the dolomite revenue
                p.setLeafRate(previousMonthLeafRate);
                p.setTotalLeafPrice(formattedTotalPrice);
                p.setArrears(String.valueOf(arrearsDouble));
                p.setTotalDeductions(String.valueOf(formattedTotalDeductionse));
                p.setFinalAmount(formattedFinalAmount);
                p.setArrearsSetZero(arrearsSetZero);
                p.setNewArrears(String.valueOf(arrears));
                p.setBillNumber(billNumber);

                listLeafBill.add(p);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "Leaf_Bill_Service", ex);
            }
        }
        return listLeafBill;
    }

    private String generateBillNumber(String billNo, String year, String month) {
        // Ensure billNo, year, and month are formatted to have at least 2 digits
        String formattedBillNo = String.format("%02d", Integer.parseInt(billNo));
        String formattedMonth = String.format("%02d", Integer.parseInt(month));

        // Concatenate bill number, year, and month to create the bill number
        return formattedBillNo + year + formattedMonth;
    }


}
