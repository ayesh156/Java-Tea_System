/**
 * Please Visit:
 * Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261
 * Youtube: https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ
 * Instagram: coverstarstory
 */

package model.year;

import java.time.Year;

/**
 *
 * @author Cover Star
 */
public class YearModal {
    private String id;
    private Year year;

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

    @Override
    public String toString() {
        return "Year{" +
                "id='" + id + '\'' +
                ", year=" + year +
                '}';
    }
}
