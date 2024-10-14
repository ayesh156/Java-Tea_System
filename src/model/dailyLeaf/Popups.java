/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dailyLeaf;

import model.suppliers.SuppliersModel;
import model.transport.Transport;

import javax.swing.*;
import java.util.HashMap;

/**
 *
 * @author Dell
 */
public class Popups {

    // loadpopupmenu on road_name
    public static void loadPopupTextField5(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, JTextField jTextField10, HashMap<String, String> list,HashMap<String, SuppliersModel> suppliersMap) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();

        for (String name : list.keySet()) {
            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setFont(new java.awt.Font("Iskoola Pota", 0, 20));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));  // Increase top, left, bottom, right padding
            jMenuItem.setText(name);

            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField5.setText(jMenuItem.getText());
                    // Fetch the transportId (value) associated with the selected road_name (key)
                    String supplierDetails = list.get(name);

                    if (supplierDetails != null) {
                        // Split the supplier details to get ID and transport rate
                        String[] parts = supplierDetails.split("\\|"); // Use "|" as a delimiter
                        String suppliersId = parts[0]; // Get the supplier ID
                        String transportRate = parts[1]; // Get the transport rate

                        // Set the supplier ID and transport rate in the respective text fields
                        jTextField4.setText(suppliersId); // Set ID in jTextField4
                        jTextField10.setText(transportRate); // Set transport rate in jTextField10
                    }
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(jTextField5, 0, jTextField5.getHeight());

    }

    public static void loadPopupTextField4(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, JTextField jTextField10, HashMap<String, String> list,HashMap<String, SuppliersModel> suppliersMap) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();

        for (String id : list.keySet()) {
            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setFont(new java.awt.Font("Iskoola Pota", 0, 20));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));  // Increase top, left, bottom, right padding
            jMenuItem.setText(id);

            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField4.setText(jMenuItem.getText());  // Set the selected ID in jTextField4

                    // Fetch the corresponding supplier details from suppliersNameMap using the ID
                    String details = list.get(jMenuItem.getText()); // Assuming 'list' contains supplier details
                    if (details != null) {
                        String[] parts = details.split("\\|"); // Split the details string
                        String supplierName = parts[0]; // Get the name
                        String transportRate = parts[1]; // Get the transport rate

                        // Set the name of the supplier in jTextField5
                        jTextField5.setText(supplierName);  // Set name in jTextField5
                        // You can also store the transport rate if needed
                        jTextField10.setText(transportRate);
                        // For example, you can set it in another text field if you have one
                        // jTextField6.setText(transportRate); // Assuming you have jTextField6 for transport rate
                    }
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(jTextField4, 0, jTextField4.getHeight());

    }


}
