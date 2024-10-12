package model.dolomite; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.dolomite.DolomiteModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class DolomiteTableModel extends AbstractTableModel {

    List<DolomiteModel> listDolomite = new ArrayList<DolomiteModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "m%udKh", "uqo,"};

    public void setList(List<DolomiteModel> listDolomite) {
        this.listDolomite = listDolomite;
    }

    public void save(DolomiteModel dolomite) {
        listDolomite.add(dolomite);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, DolomiteModel dolomite) {
        listDolomite.set(index, dolomite);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listDolomite.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public DolomiteModel findOne(int index) {
        return listDolomite.get(index);
    }

    public int getRowCount() {
        return listDolomite.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DolomiteModel dolomite = listDolomite.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dolomite.getId();
            case 1:
                return dolomite.getSupplier_id();
            case 2:
                return dolomite.getSupplier_name();

            case 3:
                return dolomite.getDate();
                
            case 4:
                return dolomite.getQty();
                
            case 5:
                return dolomite.getPrice();

            default:
                return null;
        }
    }
}
