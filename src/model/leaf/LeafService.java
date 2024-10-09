package model.leaf;


import gui.Home;
import model.Mysql;
import model.transport.Transport;

import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import static gui.Home.logger;

public class LeafService {

    public boolean update(Leaf leaf) {
        try {

            int fleaf = findByDataId(String.valueOf(leaf.getYear()), leaf.getMonth(), leaf.getLeaf_rate(), leaf.getId());

            System.out.println(fleaf);

            if (fleaf < 1) {

                // Ensure the year is in the year table and retrieve its ID
                String yearId = getOrCreateYearId(leaf.getYear());

                // Ensure the month is in the month table and retrieve its ID
                String monthId = getOrCreateMonthId(leaf.getMonth());

                String sql = String.format(
                        "UPDATE leaf_rate SET year_id = '%s', month_id = '%s', leaf_rate = '%s' WHERE id = '%s'",
                        yearId, monthId, leaf.getLeaf_rate(), leaf.getId()
                );
                Mysql.execute(sql);// Execute the update query

                System.out.println( yearId+" "+ monthId + " "+ leaf.getLeaf_rate()+ " "+ leaf.getId());
                return true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);

        }
        return false;
    }

    public int findByDataId(String year, String month, String rate, String idToExclude) {
        int total = 0;
        try {
            // SQL query to find if another record exists with the same year, month, and rate, excluding the current record
            String sql = String.format(
                    "SELECT COUNT(*) AS total " +
                            "FROM leaf_rate lr " +
                            "INNER JOIN month m ON lr.month_id = m.id " +
                            "INNER JOIN year y ON lr.year_id = y.id " +
                            "WHERE lr.leaf_rate = '%s' " +             // Exact match in leaf_rate table
                            "AND m.month = '%s' " +                    // Exact match in month table
                            "AND y.year = '%s' " +                     // Exact match in year table
                            "AND lr.id != '%s'",                       // Exclude current record by id
                    rate, month, year, idToExclude
            );

            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return total;
    }

    public void save(Leaf leaf) {
        try {
            // Ensure the year is in the year table and retrieve its ID
            String yearId = getOrCreateYearId(leaf.getYear());

            // Ensure the month is in the month table and retrieve its ID
            String monthId = getOrCreateMonthId(leaf.getMonth());

            // Insert the leaf data into the leaf_rate table
            String sql = String.format(
                    "INSERT INTO leaf_rate (year_id, month_id, leaf_rate) VALUES ('%s', '%s', '%s')",
                    yearId, monthId, leaf.getLeaf_rate()
            );
            Mysql.execute(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
    }

    // Helper method to ensure year exists and retrieve its ID
    private String getOrCreateYearId(Year year) {
        String yearId = null;
        try {
            // Check if the year already exists in the database
            String checkSql = String.format("SELECT id FROM year WHERE year = '%s'", year.toString());
            ResultSet rs = Mysql.execute(checkSql);

            if (rs != null && rs.next()) {
                // Year exists, get the ID
                yearId = rs.getString("id");
            } else {
                // Year does not exist, insert it and retrieve the new ID
                String insertSql = String.format("INSERT INTO year (year) VALUES ('%s')", year.toString());
                Mysql.execute(insertSql);

                // Retrieve the newly inserted ID
                rs = Mysql.execute(checkSql);
                if (rs != null && rs.next()) {
                    yearId = rs.getString("id");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return yearId;
    }

    // Helper method to ensure month exists and retrieve its ID
    private String getOrCreateMonthId(String month) {
        String monthId = null;
        try {
            // Check if the month already exists in the database
            String checkSql = String.format("SELECT id FROM month WHERE month = '%s'", month);
            ResultSet rs = Mysql.execute(checkSql);

            if (rs != null && rs.next()) {
                // Month exists, get the ID
                monthId = rs.getString("id");
            } else {
                // Month does not exist, insert it and retrieve the new ID
                String insertSql = String.format("INSERT INTO month (month) VALUES ('%s')", month);
                Mysql.execute(insertSql);

                // Retrieve the newly inserted ID
                rs = Mysql.execute(checkSql);
                if (rs != null && rs.next()) {
                    monthId = rs.getString("id");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return monthId;
    }


    public void delete(String id) {
        try {
            String sql = String.format("DELETE FROM leaf_rate WHERE id = '%s'", id);
            Mysql.execute(sql); // Execute the delete query
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
    }

    public List<Leaf> findAll(int page, int pageSize) {
        List<Leaf> listLeaf = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            // SQL query with JOINs to fetch year and month details
            String sql = String.format(
                    "SELECT l.*, y.year, m.month " +
                            "FROM leaf_rate l " +
                            "JOIN year y ON l.year_id = y.id " +
                            "JOIN month m ON l.month_id = m.id " +
                            "ORDER BY l.id ASC " +     // Order by id in ascending order
                            "LIMIT %d, %d",
                    offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Leaf p = new Leaf();
                p.setId(rs.getString("id"));
                p.setYear(Year.of(rs.getInt("year"))); // Extract year from the joined table
                p.setMonth(rs.getString("month")); // Extract month from the joined table
                p.setLeaf_rate(rs.getString("leaf_rate"));
                listLeaf.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return listLeaf;
    }

    public int findByData(String year, String month, String rate) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total " +
                            "FROM leaf_rate lr " +
                            "INNER JOIN month m ON lr.month_id = m.id " +
                            "INNER JOIN year y ON lr.year_id = y.id " +
                            "WHERE lr.leaf_rate = '%s' " +    // Exact match for leaf_rate
                            "AND m.month = '%s' " +           // Exact match for month
                            "AND y.year = '%s'",              // Exact match for year
                    rate, month, year
            );

            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return total;
    }

    public List<Leaf> find(String searchText, int page, int pageSize) {
        List<Leaf> listTransport = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format(
                    "SELECT * " +
                            "FROM leaf_rate lr " +
                            "INNER JOIN month m ON lr.month_id = m.id " +    // Assuming 'month_id' links the two tables
                            "INNER JOIN year y ON lr.year_id = y.id " +      // Assuming 'year_id' links the two tables
                            "WHERE lr.leaf_rate LIKE '%%%s%%' " +          // Search in leaf_rate table
                            "OR m.month LIKE '%%%s%%' " +               // Search in month table
                            "OR y.year LIKE '%%%s%%' LIMIT %d, %d", // Search in year table
                    searchText, searchText, searchText, offset, pageSize
            );
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Leaf leaf = new Leaf();
                leaf.setId(rs.getString("id"));
                leaf.setYear(Year.of(rs.getInt("year")));
                leaf.setMonth(rs.getString("month"));
                leaf.setLeaf_rate(rs.getString("leaf_rate"));
                listTransport.add(leaf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return listTransport;
    }

    public int findCount(String searchText) {
        int total = 0;
        try {
            String sql = String.format(
                    "SELECT COUNT(*) AS total " +
                            "FROM leaf_rate lr " +
                            "INNER JOIN month m ON lr.month_id = m.id " +    // Assuming 'month_id' links the two tables
                            "INNER JOIN year y ON lr.year_id = y.id " +      // Assuming 'year_id' links the two tables
                            "WHERE lr.leaf_rate LIKE '%%%s%%' " +          // Search in leaf_rate table
                            "OR m.month LIKE '%%%s%%' " +               // Search in month table
                            "OR y.year LIKE '%%%s%%'",                  // Search in year table
                    searchText, searchText, searchText
            );
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return total;
    }


    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(id) AS total FROM leaf_rate";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "LeafRate", ex);
        }
        return totalCount;
    }


}
