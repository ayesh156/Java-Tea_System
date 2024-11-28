/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.suppliers.SuppliersModel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class SupNameIdPopups {

//     loadpopupmenu on road_name
    public static void loadPopupTextField5(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, HashMap<Integer, String> list,HashMap<Integer, SuppliersModel> suppliersMap) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();

        for (Map.Entry<Integer, String> entry : list.entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();

            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setFont(new java.awt.Font("Iskoola Pota", 0, 20));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0)); // Padding
            jMenuItem.setText(name); // Set the name as the menu item text

            // Add action listener to handle menu item selection
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    // Set the selected name in jTextField5
                    jTextField5.setText(name);

                    // Use the ID to fetch the corresponding SuppliersModel
                    SuppliersModel selectedSuppliers = suppliersMap.get(id);

                    // If found, set the supplier's ID in jTextField4
                    if (selectedSuppliers != null) {
                        jTextField4.setText(String.valueOf(selectedSuppliers.getId()));
                    }
                }
            });

            // Add the menu item to the popup menu
            popupMenu.add(jMenuItem);
        }

        // Show the popup menu relative to jTextField5
        popupMenu.show(jTextField5, 0, jTextField5.getHeight());

    }

    public static void loadPopupTextField4(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, HashMap<Integer, String> list,HashMap<Integer, SuppliersModel> suppliersMap) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();

        for (int id : list.keySet()) {
            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setFont(new java.awt.Font("Iskoola Pota", 0, 20));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));  // Increase top, left, bottom, right padding
            jMenuItem.setText(String.valueOf(id));

            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField4.setText(jMenuItem.getText());  // Set the selected ID in jTextField4

                    // Fetch the corresponding Supplier object using the ID
                    SuppliersModel selectedSuppliers = suppliersMap.get(Integer.parseInt(jMenuItem.getText()));

                    // Set the name of the supplier in jTextField5
                    if (selectedSuppliers != null) {
                        jTextField5.setText(selectedSuppliers.getName());  // Set name in jTextField5
                    }
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(jTextField4, 0, jTextField4.getHeight());

    }


}
