/**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */
package model.suppliers;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cover Star
 */
public class SuppliersTableModel extends AbstractTableModel {

    List<SuppliersModel> listSuppliers = new ArrayList<SuppliersModel>();
    private final String HEADER[] = {"ie' wx'","ie' ku", ",smskh", "ud¾.fha ku"};

    public void setList(List<SuppliersModel> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

    public void save(SuppliersModel suppliersModel) {
        listSuppliers.add(suppliersModel);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, SuppliersModel suppliersModel) {
        listSuppliers.set(index, suppliersModel);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listSuppliers.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public SuppliersModel findOne(int index) {
        return listSuppliers.get(index);
    }

    public int getRowCount() {
        return listSuppliers.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        SuppliersModel suppliersModel = listSuppliers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return suppliersModel.getId();
                
            case 1:
                return suppliersModel.getName();
                
            case 2:
                return suppliersModel.getAddress();

            case 3:
                return suppliersModel.getRoad_name();

            default:
                return null;
        }
    }
}
