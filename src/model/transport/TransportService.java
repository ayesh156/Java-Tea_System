package model.transport;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import model.Mysql;
import model.suppliers.SuppliersModel;

import static gui.Home.logger;

public class TransportService {

    public void update(Transport transport) {
//        System.out.println(transport.getRoad_name() + " "+ transport.getTransport_rate() + " "+ transport.getRoot_id());
        try {
            String sql = String.format(
                    "UPDATE transport SET road_name = '%s', transport_rate = '%s' WHERE root_id = '%s'",
                    transport.getRoad_name(), transport.getTransport_rate(), transport.getRoot_id());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
    }

    public void save(Transport transport) {
        try {
            String sql = String.format("INSERT INTO transport (root_id, road_name, transport_rate) VALUES ('%s', '%s', '%s')",
                    transport.getRoot_id(), transport.getRoad_name(), transport.getTransport_rate());
            Mysql.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
    }

    public void delete(String root_id) {
        try {
            String sql = String.format("DELETE FROM transport WHERE root_id = '%s'", root_id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
    }

    // Method to fetch transport data based on the road name
    public HashMap<String, Transport> getTransportByRoadName(String roadName, int limit) {
        HashMap<String, Transport> transportMap = new HashMap<>();

        try {
            String queryLimit = limit > 0 ? " LIMIT " + limit : "";
            String sql = "SELECT * FROM `transport` WHERE `road_name` LIKE '%" + roadName + "%'" + queryLimit;

            ResultSet results = Mysql.execute(sql);

            while (results.next()) {
                Transport transport = new Transport();
                transport.setId(results.getString("id"));
                transport.setRoot_id(results.getString("root_id"));
                transport.setRoad_name(results.getString("road_name"));
                transport.setTransport_rate(results.getString("transport_rate"));

                // Add the transport data to the map
                transportMap.put(transport.getId(), transport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transportMap;
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
            logger .log(Level.WARNING, "Suppliers", ex);
        }
        return total;
    }

    public int findByName(String road_name) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM transport WHERE road_name = '%s'",
                    road_name
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return total;
    }

    public HashMap<String, Transport> getSuppliersByTransRate(String tranRate, int limit) {
        HashMap<String, Transport> TransportMap = new HashMap<>();

        try {
            String queryLimit = limit > 0 ? " LIMIT " + limit : "";
            String sql = "SELECT * FROM `transport` WHERE `transport_rate` LIKE '%" + tranRate + "%'" + queryLimit;

            ResultSet results = Mysql.execute(sql);

            while (results.next()) {
                String id = results.getString("id");

                // Check if the supplier is already in the map
                if (!TransportMap.containsKey(id)) {
                    Transport transport = new Transport();
                    transport.setId(id);
                    transport.setTransport_rate(results.getString("transport_rate"));

                    // Add the supplier to the map
                    TransportMap.put(id, transport);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TransportMap;
    }

    public List<Transport> findAll(int page, int pageSize) {
        List<Transport> listTransport = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM transport ORDER BY root_id ASC LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Transport p = new Transport();
                p.setRoot_id(rs.getString("root_id"));
                p.setRoad_name(rs.getString("road_name"));
                p.setTransport_rate(rs.getString("transport_rate"));
                listTransport.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return listTransport;
    }

    public List<Transport> find(String searchText, int page, int pageSize) {
        List<Transport> listTransport = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM transport WHERE road_name LIKE '%%%s%%' OR transport_rate LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Transport p = new Transport();
                p.setRoot_id(rs.getString("root_id"));
                p.setRoad_name(rs.getString("road_name"));
                p.setTransport_rate(rs.getString("transport_rate"));
                listTransport.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return listTransport;
    }

    public int findByRate(String rate) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM transport WHERE transport_rate = '%s'",
                    rate
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return total;
    }

    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM transport WHERE road_name LIKE '%%%s%%' OR transport_rate LIKE '%%%s%%'",
                    searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return total;
    }

    public Transport getTransportById(int transportId) {
        Transport transport = null;
        try {

            String sql = String.format(
                    "SELECT * FROM transport WHERE id = %d",
                    transportId
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                transport = new Transport();
                transport.setId(rs.getString("id"));
                transport.setRoot_id(rs.getString("root_id"));
                transport.setRoad_name(rs.getString("road_name"));
                transport.setTransport_rate(rs.getString("transport_rate"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Transport", ex);
        }
        return transport;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM transport";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
        return totalCount;
    }


}
