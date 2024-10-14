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
    private final String HEADER[] = {"ie' wx'", "ie' ku", "o< o¿ m%'", "Y=oaO o¿ m%'", "yr uqo,a", "w;a;sldrï", ",s' ø' .dia;=", "m%' .dia;=", "úh<s f;a", "fmdfydr", "fvd,uhsÜ", "lsf,da 1 ñ,", "uq¿ uqo,", "ysÕ uqo,a", "uq¿ wvqlsÍï", "wjika uqo,"};

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
                return listLeaf.getSupplier_id();
            case 1:
                return listLeaf.getSupplier_name();
            case 2:
                return listLeaf.getGross_tqty();
            case 3:
                return listLeaf.getNet_tqty();
            case 4:
                return listLeaf.getDebit_price();
            case 5:
                return listLeaf.getAdvance_price();
            case 6:
                return listLeaf.getDoc_rate();
            case 7:
                return listLeaf.getTransport_rate();
            case 8:
                return listLeaf.getTea();
            case 9:
                return listLeaf.getManure();
            case 10:
                return listLeaf.getDolomite();
            case 11:
                return listLeaf.getLeafRate();
            case 12:
                return listLeaf.getTotalLeafPrice();
            case 13:
                return listLeaf.getArrears();
            case 14:
                return listLeaf.getTotalDeductions();
            case 15:
                return listLeaf.getFinalAmount();
            default:
                return null;
        }
    }
}
