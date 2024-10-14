package model.month;


import model.Mysql;

import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static gui.Home.logger;

public class MonthService {

    public List<MonthModal> findAll() {
        List<MonthModal> listMonth = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM month");
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                MonthModal p = new MonthModal();
                p.setId(rs.getString("id"));
                p.setMonth(rs.getString("month"));
                listMonth.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Month_Service", ex);
        }
        return listMonth;
    }

    public String findMonthById(String id) {
        String monthValue = null; // Variable to store the month value
        try {
            // SQL query to find the month by id
            String sql = String.format("SELECT month FROM month WHERE id = '%s'", id);
            ResultSet rs = Mysql.execute(sql);

            if (rs != null && rs.next()) {
                String monthString = rs.getString("month"); // Get the month string
                // Split the month string by " - " and get the second value
                String[] monthParts = monthString.split(" - ");
                if (monthParts.length > 1) {
                    monthValue = monthParts[1]; // Get the second value
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Month_Service", ex);
        }
        return monthValue; // Return the extracted month value or null if not found
    }

}
