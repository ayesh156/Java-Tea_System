package model.dolomite;


import model.Mysql;
import model.dolomite.DolomiteModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class DolomiteService {

    public void update(DolomiteModel dolomite) {
        try {
            String sql = String.format(
                    "UPDATE dolomite SET supplier_name = '%s', supplier_id = '%s', date = '%s', qty = '%s', price = '%s' WHERE id = '%s'",
                    dolomite.getSupplier_name(), dolomite.getSupplier_id(), dolomite.getDate(), dolomite.getQty(), dolomite.getPrice(), dolomite.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
    }

    public void save(DolomiteModel dolomiteModel) {
        try {
            String sql = String.format("INSERT INTO dolomite (supplier_name, supplier_id, date, qty, price) VALUES ('%s','%s','%s','%s', '%s')",
                    dolomiteModel.getSupplier_name(), dolomiteModel.getSupplier_id(), dolomiteModel.getDate(), dolomiteModel.getQty(), dolomiteModel.getPrice());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
    }
    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM dolomite WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
    }

    public int findByDataExist(String s_id, Date date, String qty, String price) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM dolomite WHERE supplier_id = '%s' AND date = '%s' AND qty = '%s' AND price = '%s'",
                    s_id, date, qty, price
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return total;
    }


    public List<DolomiteModel> findAll(int page, int pageSize) {
        List<DolomiteModel> listDolomite = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM dolomite LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DolomiteModel p = new DolomiteModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listDolomite.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return listDolomite;
    }

    public DolomiteModel findByDataById(String fId) {
        DolomiteModel dolomite = null;
        try {
            String sql = String.format(
                    "SELECT * FROM dolomite WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                dolomite = new DolomiteModel();
                dolomite.setId(rs.getInt("id"));
                dolomite.setSupplier_name(rs.getString("supplier_name"));
                dolomite.setSupplier_id(rs.getString("supplier_id"));
                dolomite.setDate(rs.getDate("date"));
                dolomite.setQty(rs.getString("qty"));
                dolomite.setPrice(rs.getString("price"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return dolomite;
    }

    public List<DolomiteModel> find(String searchText, int page, int pageSize) {
        List<DolomiteModel> listDolomite = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM dolomite WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DolomiteModel p = new DolomiteModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listDolomite.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return listDolomite;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM dolomite WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%'",
                    searchText, searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM dolomite";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Dolomite_Service", ex);
        }
        return totalCount;
    }


}
