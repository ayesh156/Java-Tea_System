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
    private final String HEADER[] = {"ie' wx'", "ì,a wxlh", "ie' ku", "o< o¿ m%'", "Y=oaO o¿ m%'", "yr uqo,a", "w;a;sldrï", ",s' ø' .dia;=", "m%' .dia;=", "úh<s f;a", "fmdfydr", "fvd,uhsÜ", "lsf,da 1 ñ,", "uq¿ uqo,", "ysÕ uqo,a", "uq¿ wvqlsÍï", "wjika uqo,"};

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
                return String.valueOf(listLeaf.getSupplier_id());
            case 1:
                return listLeaf.getBillNumber();
            case 2:
                return listLeaf.getSupplier_name();
            case 3:
                return checkZero(listLeaf.getGross_tqty());
            case 4:
                return checkZero(listLeaf.getNet_tqty());
            case 5:
                return checkZero(listLeaf.getDebit_price());
            case 6:
                return checkZero(listLeaf.getAdvance_price());
            case 7:
                return checkZero(listLeaf.getDoc_rate());
            case 8:
                return checkZero(listLeaf.getTransport_rate());
            case 9:
                return checkZero(listLeaf.getTea());
            case 10:
                return checkZero(listLeaf.getManure());
            case 11:
                return checkZero(listLeaf.getDolomite());
            case 12:
                return checkZero(listLeaf.getLeafRate());
            case 13:
                return checkZero(listLeaf.getTotalLeafPrice());
            case 14:
                return checkZero(listLeaf.getArrears());
            case 15:
                return checkZero(listLeaf.getTotalDeductions());
            case 16:
                return checkZero(listLeaf.getFinalAmount());
            default:
                return null;
        }
    }

    private String checkZero(String value) {
        // Convert value to double to check against 0
        try {
            if (value == null || Double.parseDouble(value) == 0.0) {
                return "";
            }

            // Format the value to 2 decimal places if it is numeric
            double numericValue = Double.parseDouble(value);
            return String.format("%.2f", numericValue);
        } catch (NumberFormatException e) {
            // In case of non-numeric values, just return the value as it is
            return value;
        }
    }
}
