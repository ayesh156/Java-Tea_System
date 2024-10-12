package model.advance; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.advance.AdvanceModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class AdvanceTableModel extends AbstractTableModel {

    List<AdvanceModel> listAdvance = new ArrayList<AdvanceModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "uqo,"};

    public void setList(List<AdvanceModel> listAdvance) {
        this.listAdvance = listAdvance;
    }

    public void save(AdvanceModel advance) {
        listAdvance.add(advance);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, AdvanceModel advance) {
        listAdvance.set(index, advance);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listAdvance.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public AdvanceModel findOne(int index) {
        return listAdvance.get(index);
    }

    public int getRowCount() {
        return listAdvance.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        AdvanceModel advance = listAdvance.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return advance.getId();
            case 1:
                return advance.getSupplier_id();
            case 2:
                return advance.getSupplier_name();

            case 3:
                return advance.getDate();
                
            case 4:
                return advance.getPrice();

            default:
                return null;
        }
    }
}
