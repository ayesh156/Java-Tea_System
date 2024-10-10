package model.dailyLeaf; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.dailyLeaf.DailyLeafModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class DailyLeafTableModel extends AbstractTableModel {

    List<DailyLeafModel> listLeaf = new ArrayList<DailyLeafModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "Y=oaO m%udKh", "m%jdyk .dia;=j"};

    public void setList(List<DailyLeafModel> listLeaf) {
        this.listLeaf = listLeaf;
    }

    public void save(DailyLeafModel leaf) {
        listLeaf.add(leaf);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, DailyLeafModel leaf) {
        listLeaf.set(index, leaf);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listLeaf.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public DailyLeafModel findOne(int index) {
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
        DailyLeafModel leaf = listLeaf.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return leaf.getId();
            case 1:
                return leaf.getSupplier_id();
            case 2:
                return leaf.getSupplier_name();

            case 3:
                return leaf.getDate();
                
            case 4:
                return leaf.getNet_qty();
                
            case 5:
                return leaf.getTransport_rate();

            default:
                return null;
        }
    }
}
