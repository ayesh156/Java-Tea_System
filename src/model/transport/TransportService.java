package model.transport;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import model.Mysql;

import static gui.Home.logger;

public class TransportService {

    public void update(Transport transport) {
//        System.out.println(transport.getRoad_name() + " "+ transport.getTransport_rate() + " "+ transport.getId());
        try {
            String sql = String.format(
                    "UPDATE transport SET road_name = '%s', transport_rate = '%s' WHERE id = '%s'",
                    transport.getRoad_name(), transport.getTransport_rate(), transport.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
    }

    public void save(Transport transport) {
        try {
            String sql = String.format("INSERT INTO transport (id, road_name, transport_rate) VALUES ('%s', '%s', '%s')",
                    transport.getId(), transport.getRoad_name(), transport.getTransport_rate());
            Mysql.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
    }

    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM transport WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Home", ex);
        }
    }

    public List<Transport> findAll(int page, int pageSize) {
        List<Transport> listTransport = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM transport ORDER BY id ASC LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Transport p = new Transport();
                p.setId(rs.getString("id"));
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
                p.setId(rs.getString("id"));
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

    public int findById(String id) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM transport WHERE id = '%s'",
                    id
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


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(id) AS total FROM transport";
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
