package model.advance;


import model.Mysql;
import model.advance.AdvanceModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class AdvanceService {

    public void update(AdvanceModel advance) {
        try {
            String sql = String.format(
                    "UPDATE advance SET supplier_name = '%s', supplier_id = '%s', date = '%s', price = '%s' WHERE id = '%s'",
                    advance.getSupplier_name(), advance.getSupplier_id(), advance.getDate(), advance.getPrice(), advance.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
    }

    public void save(AdvanceModel advanceModel) {
        try {
            String sql = String.format("INSERT INTO advance (supplier_name, supplier_id, date, price) VALUES ('%s','%s','%s','%s')",
                    advanceModel.getSupplier_name(), advanceModel.getSupplier_id(), advanceModel.getDate(), advanceModel.getPrice());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
    }
    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM advance WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
    }

    public int findByDataExist(String s_id, Date date, String price) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM advance WHERE supplier_id = '%s' AND date = '%s' AND price = '%s'",
                    s_id, date, price
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return total;
    }


    public List<AdvanceModel> findAll(int page, int pageSize) {
        List<AdvanceModel> listAdvance = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM advance LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                AdvanceModel p = new AdvanceModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setPrice(rs.getString("price"));
                listAdvance.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return listAdvance;
    }

    public AdvanceModel findByDataById(String fId) {
        AdvanceModel advance = null;
        try {
            String sql = String.format(
                    "SELECT * FROM advance WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                advance = new AdvanceModel();
                advance.setId(rs.getInt("id"));
                advance.setSupplier_name(rs.getString("supplier_name"));
                advance.setSupplier_id(rs.getString("supplier_id"));
                advance.setDate(rs.getDate("date"));
                advance.setPrice(rs.getString("price"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return advance;
    }

    public List<AdvanceModel> find(String searchText, int page, int pageSize) {
        List<AdvanceModel> listAdvance = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM advance WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                AdvanceModel p = new AdvanceModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setPrice(rs.getString("price"));
                listAdvance.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return listAdvance;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM advance WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%'",
                    searchText, searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM advance";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return totalCount;
    }


}
