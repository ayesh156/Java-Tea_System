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

    List<Suppliers> listSuppliers = new ArrayList<Suppliers>();
    private final String HEADER[] = {"ie' wx'","ie' ku", ",smskh", "ud¾.fha ku"};

    public void setList(List<Suppliers> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

    public void save(Suppliers suppliers) {
        listSuppliers.add(suppliers);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, Suppliers suppliers) {
        listSuppliers.set(index, suppliers);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listSuppliers.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Suppliers findOne(int index) {
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
        Suppliers suppliers = listSuppliers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return suppliers.getId();
                
            case 1:
                return suppliers.getName();
                
            case 2:
                return suppliers.getAddress();

            case 3:
                return suppliers.getRoad_name();

            default:
                return null;
        }
    }
}
