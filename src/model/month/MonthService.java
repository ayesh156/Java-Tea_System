package model.month;


import model.Mysql;

import java.sql.ResultSet;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MonthService {

    public List<MonthModal> findAll() {
        List<MonthModal> listTransport = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM month");
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                MonthModal p = new MonthModal();
                p.setId(rs.getString("id"));
                p.setMonth(rs.getString("month"));
                listTransport.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTransport;
    }

}
