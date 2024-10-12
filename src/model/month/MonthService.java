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

}
