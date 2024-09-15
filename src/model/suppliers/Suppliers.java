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
public class Suppliers {
    private String id;
    private String name;

    private String address;
    private String road_name;

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

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    @Override
    public String toString() {
        return "Suppliers{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", road_name='" + road_name + '\'' +
                '}';
    }
}
