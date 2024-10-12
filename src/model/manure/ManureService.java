package model.manure;


import model.Mysql;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class ManureService {

    public void update(ManureModel manure) {
        try {
            String sql = String.format(
                    "UPDATE manure SET supplier_name = '%s', supplier_id = '%s', date = '%s', qty = '%s', price = '%s' WHERE id = '%s'",
                    manure.getSupplier_name(), manure.getSupplier_id(), manure.getDate(), manure.getQty(), manure.getPrice(), manure.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
    }

    public void save(ManureModel manureModel) {
        try {
            String sql = String.format("INSERT INTO manure (supplier_name, supplier_id, date, qty, price) VALUES ('%s','%s','%s','%s', '%s')",
                    manureModel.getSupplier_name(), manureModel.getSupplier_id(), manureModel.getDate(), manureModel.getQty(), manureModel.getPrice());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
    }
    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM manure WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
    }

    public int findByDataExist(String s_id, Date date, String qty, String price) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM manure WHERE supplier_id = '%s' AND date = '%s' AND qty = '%s' AND price = '%s'",
                    s_id, date, qty, price
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return total;
    }


    public List<ManureModel> findAll(int page, int pageSize) {
        List<ManureModel> listManure = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM manure LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                ManureModel p = new ManureModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listManure.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return listManure;
    }

    public ManureModel findByDataById(String fId) {
        ManureModel manure = null;
        try {
            String sql = String.format(
                    "SELECT * FROM manure WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                manure = new ManureModel();
                manure.setId(rs.getInt("id"));
                manure.setSupplier_name(rs.getString("supplier_name"));
                manure.setSupplier_id(rs.getString("supplier_id"));
                manure.setDate(rs.getDate("date"));
                manure.setQty(rs.getString("qty"));
                manure.setPrice(rs.getString("price"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return manure;
    }

    public List<ManureModel> find(String searchText, int page, int pageSize) {
        List<ManureModel> listManure = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM manure WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                ManureModel p = new ManureModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listManure.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return listManure;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM manure WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%'",
                    searchText, searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM manure";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Manure_Service", ex);
        }
        return totalCount;
    }


}
