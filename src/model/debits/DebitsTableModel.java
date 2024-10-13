package model.debits; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.debits.DebitsModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class DebitsTableModel extends AbstractTableModel {

    List<DebitsModel> listDebits = new ArrayList<DebitsModel>();
    private final String HEADER[] = {"ie' wx'","ie' ku","Èkh", "uqo,"};

    public void setList(List<DebitsModel> listDebits) {
        this.listDebits = listDebits;
    }

    public void save(DebitsModel debits) {
        listDebits.add(debits);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, DebitsModel debits) {
        listDebits.set(index, debits);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listDebits.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public DebitsModel findOne(int index) {
        return listDebits.get(index);
    }

    public int getRowCount() {
        return listDebits.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DebitsModel debits = listDebits.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return debits.getId();
            case 1:
                return debits.getSupplier_id();
            case 2:
                return debits.getSupplier_name();

            case 3:
                return debits.getDate();
                
            case 4:
                return debits.getPrice();

            default:
                return null;
        }
    }
}
