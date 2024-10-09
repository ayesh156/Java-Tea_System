/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.transport;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Cover Star
 */
public class Transport {
    private String id;

    private String root_id;
    private String road_name;
    private String transport_rate;

    public String getRoot_id() {
        return root_id;
    }

    public void setRoot_id(String root_id) {
        this.root_id = root_id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id='" + id + '\'' +
                ", root_id='" + root_id + '\'' +
                ", road_name='" + road_name + '\'' +
                ", transport_rate='" + transport_rate + '\'' +
                '}';
    }
}
