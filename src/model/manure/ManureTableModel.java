package model.manure; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.manure.ManureModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class ManureTableModel extends AbstractTableModel {

    List<ManureModel> listManure = new ArrayList<ManureModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "m%udKh", "uqo,"};

    public void setList(List<ManureModel> listManure) {
        this.listManure = listManure;
    }

    public void save(ManureModel manure) {
        listManure.add(manure);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, ManureModel manure) {
        listManure.set(index, manure);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listManure.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public ManureModel findOne(int index) {
        return listManure.get(index);
    }

    public int getRowCount() {
        return listManure.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ManureModel manure = listManure.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return manure.getId();
            case 1:
                return manure.getSupplier_id();
            case 2:
                return manure.getSupplier_name();

            case 3:
                return manure.getDate();
                
            case 4:
                return manure.getQty();
                
            case 5:
                return manure.getPrice();

            default:
                return null;
        }
    }
}
