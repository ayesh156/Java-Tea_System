/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.transport;

import model.product.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Cover Star
 */
public class Transport {
    private int id;
    private String road_name;
    private String transport_rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public String getTransport_rate() {
        return transport_rate;
    }

    public void setTransport_rate(String transport_rate) {
        this.transport_rate = transport_rate;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", road_name=" + road_name + ", transport_rate=" + transport_rate + '}';
    }
    
}
