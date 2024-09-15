/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.leaf;

import java.time.Year;

/**
 *
 * @author Cover Star
 */
public class Leaf {
    private String id;
    private Year year;
    private String month;
    private String leaf_rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLeaf_rate() {
        return leaf_rate;
    }

    public void setLeaf_rate(String leaf_rate) {
        this.leaf_rate = leaf_rate;
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "id='" + id + '\'' +
                ", year=" + year +
                ", month='" + month + '\'' +
                ", leaf_rate='" + leaf_rate + '\'' +
                '}';
    }
}
