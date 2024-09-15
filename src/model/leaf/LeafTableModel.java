/**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */
package model.leaf;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class LeafTableModel extends AbstractTableModel {

    List<Leaf> listLeaf = new ArrayList<Leaf>();
    private final String HEADER[] = {"wxlh","wjqreoao", "udih", "o¿ ñ<"};

    public void setList(List<Leaf> listLeaf) {
        this.listLeaf = listLeaf;
    }

    public void save(Leaf leaf) {
        listLeaf.add(leaf);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, Leaf leaf) {
        listLeaf.set(index, leaf);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listLeaf.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Leaf findOne(int index) {
        return listLeaf.get(index);
    }

    public int getRowCount() {
        return listLeaf.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Leaf leaf = listLeaf.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return leaf.getId();
            case 1:
                return leaf.getYear().toString();
                
            case 2:
                return leaf.getMonth();
                
            case 3:
                return leaf.getLeaf_rate();

            default:
                return null;
        }
    }
}
