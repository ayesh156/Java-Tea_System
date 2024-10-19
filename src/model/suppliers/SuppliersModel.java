/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.suppliers;

/**
 *
 * @author Cover Star
 */
public class SuppliersModel {
    private String id;
    private String name;

    private String address;
    private String doc_rate;

    private int transport_id;

    private String road_name;
    private String transport_rate;
    private String arrears;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoc_rate() {
        return doc_rate;
    }

    public void setDoc_rate(String doc_rate) {
        this.doc_rate = doc_rate;
    }

    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
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

    public String getArrears() {
        return arrears;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    @Override
    public String toString() {
        return "SuppliersModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", doc_rate='" + doc_rate + '\'' +
                ", transport_id=" + transport_id +
                ", road_name='" + road_name + '\'' +
                ", transport_rate='" + transport_rate + '\'' +
                ", arrears='" + arrears + '\'' +
                '}';
    }
}
