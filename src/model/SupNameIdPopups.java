/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.suppliers.SuppliersModel;

import javax.swing.*;
import java.util.HashMap;

/**
 *
 * @author Dell
 */
public class SupNameIdPopups {

    // loadpopupmenu on road_name
    public static void loadPopupTextField5(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, HashMap<String, String> list,HashMap<String, SuppliersModel> suppliersMap) {
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
                    String suppliersId = list.get(name);

                    // Fetch the corresponding Transport object from transportMap using the transportId
                    SuppliersModel selectedSuppliers = suppliersMap.get(suppliersId);

                    if (selectedSuppliers != null) {
                        jTextField4.setText(selectedSuppliers.getId());
                    }
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(jTextField5, 0, jTextField5.getHeight());

    }

    public static void loadPopupTextField4(JPopupMenu popupMenu, JTextField jTextField5, JTextField jTextField4, HashMap<String, String> list,HashMap<String, SuppliersModel> suppliersMap) {
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

                    // Fetch the corresponding Supplier object using the ID
                    SuppliersModel selectedSuppliers = suppliersMap.get(jMenuItem.getText());

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
