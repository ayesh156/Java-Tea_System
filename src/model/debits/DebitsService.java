package model.debits;


import model.Mysql;
import model.debits.DebitsModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class DebitsService {

    public void update(DebitsModel debits) {
        try {
            String sql = String.format(
                    "UPDATE debits SET supplier_name = '%s', supplier_id = '%s', date = '%s', price = '%s' WHERE id = '%s'",
                    debits.getSupplier_name(), debits.getSupplier_id(), debits.getDate(), debits.getPrice(), debits.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
    }

    public void save(DebitsModel advanceModel) {
        try {
            String sql = String.format("INSERT INTO debits (supplier_name, supplier_id, date, price) VALUES ('%s','%s','%s','%s')",
                    advanceModel.getSupplier_name(), advanceModel.getSupplier_id(), advanceModel.getDate(), advanceModel.getPrice());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
    }
    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM debits WHERE id = '%s'", id);
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
                    "SELECT COUNT(*) AS total FROM debits WHERE supplier_id = '%s' AND date = '%s' AND price = '%s'",
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


    public List<DebitsModel> findAll(int page, int pageSize) {
        List<DebitsModel> listDebits = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM debits LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DebitsModel p = new DebitsModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setPrice(rs.getString("price"));
                listDebits.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return listDebits;
    }

    public DebitsModel findByDataById(String fId) {
        DebitsModel debits = null;
        try {
            String sql = String.format(
                    "SELECT * FROM debits WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                debits = new DebitsModel();
                debits.setId(rs.getInt("id"));
                debits.setSupplier_name(rs.getString("supplier_name"));
                debits.setSupplier_id(rs.getString("supplier_id"));
                debits.setDate(rs.getDate("date"));
                debits.setPrice(rs.getString("price"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return debits;
    }

    public List<DebitsModel> find(String searchText, int page, int pageSize) {
        List<DebitsModel> listDebits = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM debits WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DebitsModel p = new DebitsModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setPrice(rs.getString("price"));
                listDebits.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Advance_Service", ex);
        }
        return listDebits;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM debits WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%'",
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
            String sql = "SELECT COUNT(*) AS total FROM debits";
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
