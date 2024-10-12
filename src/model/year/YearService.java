package model.year;


import model.Mysql;

import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;
import java.util.logging.Level;

import static gui.Home.logger;

public class YearService {

    public List<YearModal> findAll() {
        List<YearModal> listYear = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM year");
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                YearModal p = new YearModal();
                p.setId(rs.getString("id"));
                p.setYear(Year.of(rs.getInt("year")));
                listYear.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Year_Service", ex);
        }
        return listYear;
    }

}
