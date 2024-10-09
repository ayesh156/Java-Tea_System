/**
 * Please Visit: Fans Page Facebook: https://web.facebook.com/coverstarcomunity
 * Group Facebook: https://web.facebook.com/groups/454664331926261 Youtube:
 * https://www.youtube.com/channel/UClcEDDeJvnJ69p-n7XsRqHQ Instagram:
 * coverstarstory
 */
package model.transport;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cover Star
 */
public class TransportTableModel extends AbstractTableModel {

    List<Transport> listTransport = new ArrayList<Transport>();
    private final String HEADER[] = {"ud' wx'", "ud¾.fha ku", "m%jdyk wkqmd;h"};

    public void setList(List<Transport> listTransport) {
        this.listTransport = listTransport;
    }

    public void save(Transport transport) {
        listTransport.add(transport);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, Transport transport) {
        listTransport.set(index, transport);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        listTransport.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public Transport findOne(int index) {
        return listTransport.get(index);
    }

    public int getRowCount() {
        return listTransport.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Transport transport = listTransport.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return transport.getRoot_id();
                
            case 1:
                return transport.getRoad_name();
                
            case 2:
                return transport.getTransport_rate();

            default:
                return null;
        }
    }
}
