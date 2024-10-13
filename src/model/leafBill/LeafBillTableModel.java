package model.leafBill; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.leafBill.LeafBillModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class LeafBillTableModel extends AbstractTableModel {

    List<LeafBillModel> listLeafBill = new ArrayList<LeafBillModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "uqo,","ie' wx'","ie' ku","Èkh", "uqo,"};

    public void setList(List<LeafBillModel> listLeafBill) {
        this.listLeafBill = listLeafBill;
    }

    public void save(LeafBillModel listLeaf) {
        listLeafBill.add(listLeaf);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, LeafBillModel listLeaf) {
        listLeafBill.set(index, listLeaf);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listLeafBill.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public LeafBillModel findOne(int index) {
        return listLeafBill.get(index);
    }

    public int getRowCount() {
        return listLeafBill.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        LeafBillModel listLeaf = listLeafBill.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return listLeaf.getId();
            case 1:
                return listLeaf.getSupplier_id();


            default:
                return null;
        }
    }
}
