/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.month;

import java.time.Year;

/**
 *
 * @author Cover Star
 */
public class MonthModal {
    private String id;
    private String month;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "MonthModal{" +
                "id='" + id + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
