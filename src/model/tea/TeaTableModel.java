package model.tea; /**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */


import model.tea.TeaModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class TeaTableModel extends AbstractTableModel {

    List<TeaModel> listTea = new ArrayList<TeaModel>();
    private final String HEADER[] = {"wx'","ie' wx'","ie' ku","Èkh", "m%udKh", "uqo,"};

    public void setList(List<TeaModel> listTea) {
        this.listTea = listTea;
    }

    public void save(TeaModel tea) {
        listTea.add(tea);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, TeaModel tea) {
        listTea.set(index, tea);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listTea.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TeaModel findOne(int index) {
        return listTea.get(index);
    }

    public int getRowCount() {
        return listTea.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TeaModel tea = listTea.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tea.getId();
            case 1:
                return tea.getSupplier_id();
            case 2:
                return tea.getSupplier_name();

            case 3:
                return tea.getDate();
                
            case 4:
                return tea.getQty();
                
            case 5:
                return tea.getPrice();

            default:
                return null;
        }
    }
}
