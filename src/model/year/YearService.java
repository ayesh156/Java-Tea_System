package model.year;


import model.Mysql;

import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;

public class YearService {

    public List<YearModal> findAll() {
        List<YearModal> listTransport = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM year");
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                YearModal p = new YearModal();
                p.setId(rs.getString("id"));
                p.setYear(Year.of(rs.getInt("year")));
                listTransport.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTransport;
    }

}
