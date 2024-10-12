package model.dailyLeaf;


import model.Mysql;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class DailyLeafService {

    public void update(DailyLeafModel dailyLeafModel) {
        try {
            String sql = String.format(
                    "UPDATE daily_leaf SET supplier_name = '%s', supplier_id = '%s', date = '%s', gross_qty = '%s', net_qty = '%s', transport_rate = '%s' WHERE id = '%s'",
                    dailyLeafModel.getSupplier_name(), dailyLeafModel.getSupplier_id(), dailyLeafModel.getDate(), dailyLeafModel.getGross_qty(), dailyLeafModel.getNet_qty(), dailyLeafModel.getTransport_rate(), dailyLeafModel.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
    }

    public void save(DailyLeafModel dailyLeafModel) {
        try {
            String sql = String.format("INSERT INTO daily_leaf (supplier_name, supplier_id, date, gross_qty, net_qty, transport_rate) VALUES ('%s','%s','%s','%s', '%s', '%s')",
                    dailyLeafModel.getSupplier_name(), dailyLeafModel.getSupplier_id(), dailyLeafModel.getDate(), dailyLeafModel.getGross_qty(), dailyLeafModel.getNet_qty(), dailyLeafModel.getTransport_rate());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
    }

    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM daily_leaf WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
    }

    public int findByDataExist(String s_id, Date date, String gross_qty, String net_qty, String trans_rate) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM daily_leaf WHERE supplier_id = '%s' AND date = '%s' AND gross_qty = '%s' AND net_qty = '%s' AND  transport_rate = '%s'",
                    s_id, date,gross_qty, net_qty, trans_rate
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return total;
    }


    public List<DailyLeafModel> findAll(int page, int pageSize) {
        List<DailyLeafModel> listDailyLeaf = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM daily_leaf LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DailyLeafModel p = new DailyLeafModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setNet_qty(rs.getString("net_qty"));
                p.setTransport_rate(rs.getString("transport_rate"));
                listDailyLeaf.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return listDailyLeaf;
    }

    public DailyLeafModel findByDataById(String fId) {
        DailyLeafModel dailyLeaf = null;
        try {
            // SQL query to find if another record exists with the same year, month, and rate, excluding the current record
            String sql = String.format(
                    "SELECT * FROM daily_leaf WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                dailyLeaf = new DailyLeafModel();
                dailyLeaf.setId(rs.getInt("id"));
                dailyLeaf.setSupplier_name(rs.getString("supplier_name"));
                dailyLeaf.setSupplier_id(rs.getString("supplier_id"));
                dailyLeaf.setDate(rs.getDate("date"));
                dailyLeaf.setGross_qty(rs.getString("gross_qty"));
                dailyLeaf.setNet_qty(rs.getString("net_qty"));
                dailyLeaf.setTransport_rate(rs.getString("transport_rate"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return dailyLeaf;
    }

    public List<DailyLeafModel> find(String searchText, int page, int pageSize) {
        List<DailyLeafModel> listDailyLeaf = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * FROM daily_leaf WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%' LIMIT %d, %d",
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                DailyLeafModel p = new DailyLeafModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setNet_qty(rs.getString("net_qty"));
                p.setTransport_rate(rs.getString("transport_rate"));
                listDailyLeaf.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return listDailyLeaf;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM daily_leaf WHERE supplier_name LIKE '%%%s%%' OR supplier_id LIKE '%%%s%%' OR date LIKE '%%%s%%'",
                    searchText, searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM daily_leaf";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Daily_Leaf_Service", ex);
        }
        return totalCount;
    }


}
