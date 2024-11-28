/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.suppliers;

import model.transport.Transport;

import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author Dell
 */
public class Popups {

    // loadpopupmenu on road_name
    public static void loadPopupTextField5(JPopupMenu popupMenu, JTextField jTextField5, JTextField textField7, HashMap<String, String> list,HashMap<String, Transport> transportMap) {
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
                    String transportId = list.get(name);

                    // Fetch the corresponding Transport object from transportMap using the transportId
                    Transport selectedTransport = transportMap.get(transportId);

                    if (selectedTransport != null) {
                        textField7.setText(selectedTransport.getTransport_rate());
                    }
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(jTextField5, 0, jTextField5.getHeight());
        
    }

    public static void loadPopupTextField8(JPopupMenu popupMenu, JTextField textField, HashMap<String, Integer> list) {
        popupMenu.setVisible(false);
        popupMenu.removeAll();

        for (String doc_rate : list.keySet()) {
            JMenuItem jMenuItem = new JMenuItem();
            jMenuItem.setFont(new java.awt.Font("Iskoola Pota", 0, 20));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));  // Increase top, left, bottom, right padding
            jMenuItem.setText(doc_rate);

            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    textField.setText(jMenuItem.getText());
                }
            });

            popupMenu.add(jMenuItem);
        }
        popupMenu.show(textField, 0, textField.getHeight());

    }




}
