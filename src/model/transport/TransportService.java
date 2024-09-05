package model.transport;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Mysql;

public class TransportService {

    public void init() {
        try {
            String sqlTemplate = "INSERT INTO transport (id, road_name, transport_rate) VALUES ('%s', '%d', '%d')";
            for (int i = 1; i <= 1000; i++) {
                String sql = String.format(sqlTemplate, generateCode(), i, i * 1000, i);
                Mysql.execute(sql);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void save(Transport transport) {
        try {
            String sql = String.format("INSERT INTO transport (id, road_name, transport_rate) VALUES ('%s', '%d', '%d')",
                    transport.getId(), transport.getRoad_name(),transport.getTransport_rate());
            Mysql.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Transport> findAll(int page, int pageSize) {
        List<Transport> listTransport = new ArrayList<>();
        try {
            int offset = pageSize * (page - 1);
            String sql = String.format("SELECT * FROM transport LIMIT %d, %d", offset, pageSize);
            ResultSet rs = Mysql.execute(sql);

            while (rs != null && rs.next()) {
                Transport p = new Transport();
                p.setId(rs.getInt("id"));
                p.setRoad_name(rs.getString("road_name"));
                p.setTransport_rate(rs.getString("transport_rate"));
                listTransport.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTransport;
    }

    public int count() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(id) AS total FROM transport";
            ResultSet rs = Mysql.execute(sql);
            if (rs != null && rs.next()) {
                totalCount = rs.getInt("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return totalCount;
    }

    public String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString().toUpperCase();
    }
}
