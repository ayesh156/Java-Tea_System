package model.suppliers;


import model.Mysql;
import model.transport.Transport;
import model.transport.TransportService;

import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static gui.Home.logger;

public class SuppliersService {

    private TransportService transportService = new TransportService();

    public void update(SuppliersModel supplier) {

        try {

            // Search for transport ID using road_name and transportRate
            int transportId = findTransportId(supplier.getRoad_name(), supplier.getTransport_rate());

            // If transport ID is found, update the supplier
            if (transportId != -1) {
                String sql = String.format(
                        "UPDATE suppliers SET name = '%s', address = '%s', doc_rate = '%s', arrears = '%s', transport_id = %d WHERE id = '%s'",
                        supplier.getName(), supplier.getAddress(), supplier.getDoc_rate(), supplier.getArrears(),transportId, supplier.getId()
                );

                Mysql.execute(sql); // Execute the update query

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }


    }

    public void updateLastModify(int id, String mDate) {

        try {
                String sql = String.format(
                        "UPDATE suppliers SET last_modify = '%s' WHERE id = '%s'",
                        mDate, id
                );

                Mysql.execute(sql); // Execute the update query


        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }


    }

    public void save(SuppliersModel supplier) {

        try {
            // Search for transport ID using road_name and transportRate
            int transportId = findTransportId(supplier.getRoad_name(), supplier.getTransport_rate());

            // If transport ID is found, save the supplier
            if (transportId != -1) {
                String sql = String.format(
                        "INSERT INTO suppliers (id,name, address, doc_rate, new_arrears, arrears, transport_id, last_modify) VALUES ('%s', '%s', '%s', '%s', '0', '0', %d, '2020-01-30')",
                        supplier.getId() ,supplier.getName(), supplier.getAddress(), supplier.getDoc_rate(), transportId
                );

                Mysql.execute(sql); // Execute the insert query
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }

    }

    private int findTransportId(String roadName, String transportRate) {
        // Implement your logic to query the transport table and retrieve the transport ID
        // Return the transport ID or -1 if not found
        String query = String.format(
                "SELECT id FROM transport WHERE road_name = '%s' AND transport_rate = '%s'",
                roadName, transportRate
        );

        try {
            // Execute the query and process the result to get the transport ID
            // Example:
            ResultSet resultSet = Mysql.execute(query);
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }

        return -1; // Indicate that the transport ID was not found
    }

    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM suppliers WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
    }

    public HashMap<Integer, SuppliersModel> getSuppliersByDocRate(String docRate, int limit) {
        HashMap<Integer, SuppliersModel> suppliersMap = new HashMap<>();

        try {
            String queryLimit = limit > 0 ? " LIMIT " + limit : "";
            String sql = "SELECT * FROM `suppliers` WHERE `doc_rate` LIKE '%" + docRate + "%'" + queryLimit;

            ResultSet results = Mysql.execute(sql);

            while (results.next()) {
                int id = results.getInt("id");

                // Check if the supplier is already in the map
                if (!suppliersMap.containsKey(id)) {
                    SuppliersModel suppliersModel = new SuppliersModel();
                    suppliersModel.setId(id);
                    suppliersModel.setDoc_rate(results.getString("doc_rate"));

                    // Add the supplier to the map
                    suppliersMap.put(id, suppliersModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", e);
        }

        return suppliersMap;
    }

    public List<SuppliersModel> findAll(int page, int pageSize) {
        List<SuppliersModel> listSuppliers = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM suppliers ORDER BY id ASC LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                SuppliersModel p = new SuppliersModel();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setTransport_id(rs.getInt("transport_id"));

                // Fetch transport data using transport_id
                Transport transport = transportService.getTransportById(p.getTransport_id());
                if (transport != null) {
                    p.setRoad_name(transport.getRoad_name());
                }
                listSuppliers.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return listSuppliers;
    }

    public List<SuppliersModel> find(String searchText, int page, int pageSize) {
        List<SuppliersModel> listSuppliers = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM suppliers s INNER JOIN transport t ON s.transport_id = t.id  " +
                            "WHERE s.name LIKE '%%%s%%' OR s.address LIKE '%%%s%%' OR t.road_name LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                SuppliersModel p = new SuppliersModel();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setTransport_id(rs.getInt("transport_id"));

                // Fetch transport data using transport_id
                Transport transport = transportService.getTransportById(p.getTransport_id());
                if (transport != null) {
                    p.setRoad_name(transport.getRoad_name());
                }
                listSuppliers.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return listSuppliers;
    }

    public Map<Integer, SupplierDetails> getAllSuppliers(int page, int pageSize) {
        Map<Integer, SupplierDetails> suppliersMap = new HashMap<>();

        // Calculate the offset for pagination
        int offset = (page - 1) * pageSize; // Assuming page is 1-based

        // Use try-with-resources for ResultSet to ensure it is closed
        try {
            // SQL query to fetch suppliers ordered by id ASC and apply pagination
            String sql = String.format(
                    "SELECT id, name, doc_rate, transport_id, new_arrears, arrears, last_modify FROM suppliers ORDER BY id ASC LIMIT %d, %d",
                    offset,
                    pageSize
            );

            try (ResultSet rs = Mysql.execute(sql)) {  // Automatically closes ResultSet
                while (rs != null && rs.next()) {
                    int supplierId = rs.getInt("id");
                    String supplierName = rs.getString("name");
                    String docRate = rs.getString("doc_rate");
                    int transportId = rs.getInt("transport_id"); // Fetch transport_id
                    String lastArrears = rs.getString("new_arrears");
                    String arrears = rs.getString("arrears");
                    Date lastModify = rs.getDate("last_modify");

                    // Fetch transport data using transport_id
                    Transport transport = transportService.getTransportById(transportId);
                    String transportRate = (transport != null) ? transport.getTransport_rate() : null;

                    // Create SupplierDetails instance with name, docRate, and transportRate
                    SupplierDetails supplierDetails = new SupplierDetails(supplierName, docRate, transportRate, lastArrears,arrears, lastModify);
                    suppliersMap.put(supplierId, supplierDetails);
                }
            }

            return suppliersMap; // Return the suppliersMap directly, as it is already ordered by SQL
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }

        return suppliersMap; // Return an empty map if an exception occurs
    }

    public Map<Integer, SupplierDetails> searchLeafBillSuppliers(String searchText, int page, int pageSize) {
        Map<Integer, SupplierDetails> suppliersMap = new HashMap<>();

        // Calculate the offset for pagination
        int offset = (page - 1) * pageSize; // Assuming page is 1-based

        // Use try-with-resources for ResultSet to ensure it is closed
        try {
            // SQL query to fetch suppliers ordered by id ASC and apply pagination
            String sql = String.format(
                    "SELECT id, name, doc_rate, transport_id, new_arrears, arrears, last_modify FROM suppliers WHERE name LIKE '%%%s%%' ORDER BY id ASC LIMIT %d, %d",
                    searchText,
                    offset,
                    pageSize
            );

            try (ResultSet rs = Mysql.execute(sql)) {  // Automatically closes ResultSet
                while (rs != null && rs.next()) {
                    int supplierId = rs.getInt("id");
                    String supplierName = rs.getString("name");
                    String docRate = rs.getString("doc_rate");
                    int transportId = rs.getInt("transport_id"); // Fetch transport_id
                    String lastArrears = rs.getString("new_arrears");
                    String arrears = rs.getString("arrears");
                    Date lastModify = rs.getDate("last_modify");

                    // Fetch transport data using transport_id
                    Transport transport = transportService.getTransportById(transportId);
                    String transportRate = (transport != null) ? transport.getTransport_rate() : null;

                    // Create SupplierDetails instance with name, docRate, and transportRate
                    SupplierDetails supplierDetails = new SupplierDetails(supplierName, docRate, transportRate, lastArrears, arrears, lastModify);
                    suppliersMap.put(supplierId, supplierDetails);
                }
            }

            return suppliersMap; // Return the suppliersMap directly, as it is already ordered by SQL
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }

        return suppliersMap; // Return an empty map if an exception occurs
    }


    public int findById(String id) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers WHERE id = '%s'",
                    id
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return total;
    }

    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers s INNER JOIN transport t ON s.transport_id = t.id  " +
                            "WHERE s.name LIKE '%%%s%%' OR s.address LIKE '%%%s%%' OR t.road_name LIKE '%%%s%%'",
                    searchText, searchText, searchText
                    );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return total;
    }

    public HashMap<Integer, SuppliersModel> getSuppliersName(String name, int limit) {
        HashMap<Integer, SuppliersModel> suppliersMap = new HashMap<>();

        try {
            String queryLimit = limit > 0 ? " LIMIT " + limit : "";
            String sql = "SELECT * FROM `suppliers` WHERE `name` LIKE '%" + name + "%'" + queryLimit;

            ResultSet results = Mysql.execute(sql);

            while (results.next()) {
                SuppliersModel suppliers = new SuppliersModel();
                suppliers.setId(results.getInt("id"));
                suppliers.setName(results.getString("name"));
                suppliers.setAddress(results.getString("address"));
                suppliers.setDoc_rate(results.getString("doc_rate"));
                suppliers.setTransport_id(results.getInt("transport_id"));

                // Fetch transport data using transport_id
                Transport transport = transportService.getTransportById(suppliers.getTransport_id());
                if (transport != null) {
                    suppliers.setTransport_rate(transport.getTransport_rate());
                }

                // Add the transport data to the map
                suppliersMap.put(suppliers.getId(), suppliers);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", e);
        }

        return suppliersMap;
    }

    public HashMap<Integer, SuppliersModel> getSuppliersId(String id, int limit) {
        HashMap<Integer, SuppliersModel> suppliersMap = new HashMap<>();

        try {
            String queryLimit = limit > 0 ? " LIMIT " + limit : "";
            String sql = "SELECT * FROM `suppliers` WHERE `id` LIKE '%" + id + "%'" + queryLimit;

            ResultSet results = Mysql.execute(sql);

            while (results.next()) {
                SuppliersModel suppliers = new SuppliersModel();
                suppliers.setId(results.getInt("id"));
                suppliers.setName(results.getString("name"));
                suppliers.setAddress(results.getString("address"));
                suppliers.setDoc_rate(results.getString("doc_rate"));
                suppliers.setTransport_id(results.getInt("transport_id"));

                // Fetch transport data using transport_id
                Transport transport = transportService.getTransportById(suppliers.getTransport_id());
                if (transport != null) {
                    suppliers.setTransport_rate(transport.getTransport_rate());
                }

                // Add the transport data to the map
                suppliersMap.put(suppliers.getId(), suppliers);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", e);
        }

        return suppliersMap;
    }

    public int findByName(String name) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers WHERE name = '%s'",
                    name
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return total;
    }

    public int findByDocRate(String doc_rate) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers WHERE doc_rate = '%s'",
                    doc_rate
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return total;
    }

    public void updateSupplierNewArrears(int supplierId, String newArrears) {
        try {
            // SQL query to update arrears for the supplier using supplierId
            String sql = String.format(
                    "UPDATE suppliers SET new_arrears = '%s' WHERE id = '%s'",
                    newArrears,
                    supplierId
            );
            Mysql.execute(sql);  // Assuming executeUpdate() for running update queries

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
    }

    public void updateSupplierArrears(int supplierId, String newArrears) {
        try {
            // SQL query to update arrears for the supplier using supplierId
            String sql = String.format(
                    "UPDATE suppliers SET arrears = '%s' WHERE id = '%s'",
                    newArrears,
                    supplierId
            );
            Mysql.execute(sql);  // Assuming executeUpdate() for running update queries

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
    }


    public SuppliersModel findByDataById(String fId) {
        SuppliersModel supplier = null;
        try {
            // SQL query to find if another record exists with the same year, month, and rate, excluding the current record
            String sql = String.format(
                    "SELECT * FROM suppliers WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                supplier = new SuppliersModel();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setTransport_id(rs.getInt("transport_id"));
                supplier.setDoc_rate(rs.getString("doc_rate"));
                supplier.setArrears(rs.getString("arrears"));

                // Fetch transport data using transport_id
                Transport transport = transportService.getTransportById(supplier.getTransport_id());
                if (transport != null) {
                    supplier.setRoad_name(transport.getRoad_name());
                    supplier.setTransport_rate(transport.getTransport_rate());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return supplier;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(id) AS total FROM suppliers";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return totalCount;
    }

    public int findCountName(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers WHERE name LIKE '%%%s%%'",
                    searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Suppliers_Service", ex);
        }
        return total;
    }


}
