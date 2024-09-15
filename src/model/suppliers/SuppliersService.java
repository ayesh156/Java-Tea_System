package model.suppliers;


import model.Mysql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class SuppliersService {

//    public void update(Suppliers suppliers) {
////        System.out.println(suppliers.getRoad_name() + " "+ suppliers.getTransport_rate() + " "+ suppliers.getId());
//        try {
//            String sql = String.format(
//                    "UPDATE suppliers SET road_name = '%s', transport_rate = '%s' WHERE id = '%s'",
//                    suppliers.getRoad_name(), suppliers.getTransport_rate(), suppliers.getId());
//            Mysql.execute(sql); // Execute the update query
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            logger .log(Level.WARNING, "Suppliers", ex);
//        }
//    }

//    public void save(Suppliers suppliers) {
//        try {
//            String sql = String.format("INSERT INTO suppliers (id, road_name, transport_rate) VALUES ('%s', '%s', '%s')",
//                    suppliers.getId(), suppliers.getRoad_name(), suppliers.getTransport_rate());
//            Mysql.execute(sql);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            logger .log(Level.WARNING, "Suppliers", ex);
//        }
//    }

    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM suppliers WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
    }

    public List<Suppliers> findAll(int page, int pageSize) {
        List<Suppliers> listSuppliers = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM suppliers ORDER BY id ASC LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Suppliers p = new Suppliers();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setRoad_name(rs.getString("road_name"));
                listSuppliers.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
        return listSuppliers;
    }

    public List<Suppliers> find(String searchText, int page, int pageSize) {
        List<Suppliers> listSuppliers = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM suppliers WHERE name LIKE '%%%s%%' OR address LIKE '%%%s%%' OR road_name LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Suppliers p = new Suppliers();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setRoad_name(rs.getString("road_name"));
                listSuppliers.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
        return listSuppliers;
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

    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM suppliers WHERE name LIKE '%%%s%%' OR address LIKE '%%%s%%' OR road_name LIKE '%%%s%%'",
                    searchText, searchText, searchText
                    );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger .log(Level.WARNING, "Suppliers", ex);
        }
        return total;
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
            logger .log(Level.WARNING, "Suppliers", ex);
        }
        return totalCount;
    }


}
