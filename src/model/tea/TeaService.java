package model.tea;


import model.Mysql;
import model.tea.TeaModel;
import model.transport.Transport;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class TeaService {

    public void update(TeaModel tea) {
        try {
            String sql = String.format(
                    "UPDATE tea SET supplier_name = '%s', supplier_id = '%s', date = '%s', qty = '%s', price = '%s' WHERE id = '%s'",
                    tea.getSupplier_name(), tea.getSupplier_id(), tea.getDate(), tea.getQty(), tea.getPrice(), tea.getId());
            Mysql.execute(sql); // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
    }

    public void save(TeaModel teaModel) {
        try {
            String sql = String.format("INSERT INTO tea (supplier_name, supplier_id, date, qty, price) VALUES ('%s','%s','%s','%s', '%s')",
                    teaModel.getSupplier_name(), teaModel.getSupplier_id(), teaModel.getDate(), teaModel.getQty(), teaModel.getPrice());

            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
    }
    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM tea WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
    }

    public int findByDataExist(String s_id, Date date, String qty, String price) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total FROM tea WHERE supplier_id = '%s' AND date = '%s' AND qty = '%s' AND price = '%s'",
                    s_id, date, qty, price
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return total;
    }


    public List<TeaModel> findAll(int page, int pageSize) {
        List<TeaModel> listTea = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM tea LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                TeaModel p = new TeaModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listTea.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return listTea;
    }

    public TeaModel findByDataById(String fId) {
        TeaModel tea = null;
        try {
            String sql = String.format(
                    "SELECT * FROM tea WHERE id = '%s'",
                    fId
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                tea = new TeaModel();
                tea.setId(rs.getInt("id"));
                tea.setSupplier_name(rs.getString("supplier_name"));
                tea.setSupplier_id(rs.getString("supplier_id"));
                tea.setDate(rs.getDate("date"));
                tea.setQty(rs.getString("qty"));
                tea.setPrice(rs.getString("price"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return tea;
    }

    public List<TeaModel> find(String searchText, int page, int pageSize) {
        List<TeaModel> listTea = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            // Check if searchText is numeric for exact ID match
            boolean isNumeric = searchText.matches("\\d+");
            // Check if searchText is a valid date format (YYYY-MM-DD)
            boolean isDate = searchText.matches("\\d{4}-\\d{2}-\\d{2}");
            String sql;
            if (isNumeric) {
                sql = String.format(
                        "SELECT * FROM tea WHERE supplier_id = '%s' OR supplier_name LIKE '%%%s%%' LIMIT %d, %d",
                        searchText, searchText, offset, pageSize
                );
            } else if (isDate) {
                sql = String.format(
                        "SELECT * FROM tea WHERE date = '%s' OR supplier_name LIKE '%%%s%%' LIMIT %d, %d",
                        searchText, searchText, offset, pageSize
                );
            } else {
                sql = String.format(
                        "SELECT * FROM tea WHERE supplier_name LIKE '%%%s%%' LIMIT %d, %d",
                        searchText, offset, pageSize
                );
            }
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                TeaModel p = new TeaModel();
                p.setId(rs.getInt("id"));
                p.setSupplier_id(rs.getString("supplier_id"));
                p.setSupplier_name(rs.getString("supplier_name"));
                p.setDate(rs.getDate("date"));
                p.setQty(rs.getString("qty"));
                p.setPrice(rs.getString("price"));
                listTea.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return listTea;
    }


    public int findCount(String searchText) {
        int total = 0;
        try {
            // Check if searchText is numeric for exact ID match
            boolean isNumeric = searchText.matches("\\d+");
            // Check if searchText is a valid date format (YYYY-MM-DD)
            boolean isDate = searchText.matches("\\d{4}-\\d{2}-\\d{2}");
            String sql;
            if (isNumeric) {
                sql = String.format(
                        "SELECT COUNT(*) AS total FROM tea WHERE supplier_id = '%s' OR supplier_name LIKE '%%%s%%'",
                        searchText, searchText
                );
            } else if (isDate) {
                sql = String.format(
                        "SELECT COUNT(*) AS total FROM tea WHERE date = '%s' OR supplier_name LIKE '%%%s%%'",
                        searchText, searchText
                );
            } else {
                sql = String.format(
                        "SELECT COUNT(*) AS total FROM tea WHERE supplier_name LIKE '%%%s%%'",
                        searchText
                );
            }
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM tea";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Tea_Service", ex);
        }
        return totalCount;
    }


}
