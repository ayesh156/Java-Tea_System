/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import model.suppliers.SuppliersModel;
import model.suppliers.SuppliersService;

import static gui.Home.logger;

import javax.swing.*;

import model.suppliers.Popups;
import model.transport.Transport;
import model.transport.TransportService;

/**
 * @author Dell
 */
public class AddSupplier extends javax.swing.JDialog {

    /**
     * Creates new form customer
     */

    private HashMap<String, Transport> transportMap = new HashMap<>(); //to keep all products
    private HashMap<String, String> transportNameMap = new HashMap<>(); //to keep product names with IDss

    private HashMap<Integer, SuppliersModel> suppliersMap = new HashMap<>(); //to keep all products
    private HashMap<String, Integer> suppliersDocMap = new HashMap<>(); //to keep product names with IDss
    private static AddSupplier instance; // Static instance to ensure only one instance is created
    private String supplierNo; // Use instance variable instead of static

    // Constructor
    private AddSupplier(String sNo) {
        initComponents();

        supplierNo = sNo;

        if (supplierNo != null && !supplierNo.trim().isEmpty()) {

            setUpdateButton();
            loadSupplierData(supplierNo); // Load supplier data if `supplierNo` is valid
            jTextField6.grabFocus();

        } else {
            
            setSaveButton();
            newSupplier(); // Clear fields if `supplierNo` is empty or null
            jTextField4.grabFocus();
            
        }
    }
    
    private void mannualTab(KeyEvent evt, int order) {
//        System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (order) {
                case 1:
                    jTextField6.grabFocus();
                    break;
                case 2:
                    jTextArea1.grabFocus();
                    break;
                case 3:
                    jTextField5.grabFocus();
                    break;
                case 4:
                    jTextField8.grabFocus();
                    break;
                case 5:
                    jButton15.grabFocus();
                    break;

            }
        }
        
         if (evt.getKeyCode() == KeyEvent.VK_UP) {
            switch (order) {
                case 2:
                    jTextField4.grabFocus();
                    break;
                case 3:
                    jTextField6.grabFocus();
                    break;
                case 4:
                    jTextArea1.grabFocus();
                    break;
                case 5:
                    jTextField5.grabFocus();
                    break;
                case 6:
                    jTextField8.grabFocus();
                    break;
                
            }
        }
    }

    private void setUpdateButton() {
        // Update button properties
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png")));
        jButton15.setText("fjkia'"); // Assuming you meant "Update" instead of "fjkia"
        jButton15.setBackground(new Color(30, 30, 30));
    }

    private void setSaveButton() {
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png")));
        jButton15.setText("iqrlskak");
        jButton15.setBackground(new Color(57, 117, 104));
    }

    // Method to get the single instance of AddSupplier
    public static AddSupplier getInstance(String supplierNo) {
        if (instance == null) {
            instance = new AddSupplier(supplierNo);
        } else {
            if (supplierNo.isEmpty()) {
                instance.newSupplier(); // Clear fields if `supplierNo` is empty
            } else {
                instance.loadSupplierData(supplierNo); // Load data if supplier number is provided
            }
            instance.toFront(); // Bring the existing instance to the front if it exists
            instance.requestFocus();
        }
        return instance;
    }

    // Method to clear all fields
    private void newSupplier() {
        // Example of how you might clear fields:
        jTextField4.setText("");
        jTextField4.setEditable(true);
        jTextField6.setText("");
        jTextField9.setText("0");
        jTextArea1.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jLabel15.setText("kj iemhqïlrejka");
        jTextField9.setEditable(false);
        // Add code to clear any other input fields
    }

    private void loadSupplierData(String supplierNo) {
        jTextField4.setText(supplierNo);
        jTextField9.setEditable(true);
        jTextField4.setEditable(false);
        SuppliersService ss = new SuppliersService();

        SuppliersModel s = ss.findByDataById(supplierNo);
        jTextField6.setText(s.getName());
        jTextArea1.setText(s.getAddress());
        jTextField5.setText(s.getRoad_name());
        jTextField7.setText(s.getTransport_rate());
        jTextField8.setText(s.getDoc_rate());
        jTextField9.setText(s.getArrears());

        jLabel15.setText("iemhqïlrejka fjkia lsÍu");
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(975, 856));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setMinimumSize(new java.awt.Dimension(975, 856));
        jPanel1.setPreferredSize(new java.awt.Dimension(975, 856));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 25, 1, 25));
        jPanel21.setToolTipText("");
        jPanel21.setMaximumSize(new java.awt.Dimension(32767, 64));
        jPanel21.setMinimumSize(new java.awt.Dimension(975, 57));
        jPanel21.setPreferredSize(new java.awt.Dimension(975, 57));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("FMEmanee", 0, 35)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(73, 80, 87));
        jLabel15.setText("kj iemhqïlrejka");
        jPanel21.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 17, -1, -1));

        jPanel1.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(975, 792));
        jPanel2.setPreferredSize(new java.awt.Dimension(975, 792));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 1, 46, new java.awt.Color(255, 255, 255)));
        jPanel3.setMinimumSize(new java.awt.Dimension(975, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(975, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(430, 95));
        jPanel10.setPreferredSize(new java.awt.Dimension(430, 98));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jTextField4.setBackground(new java.awt.Color(245, 245, 245));
        jTextField4.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(15, 15, 18));
        jTextField4.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField4.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField4.setPreferredSize(new java.awt.Dimension(882, 55));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jPanel10.add(jTextField4, java.awt.BorderLayout.PAGE_END);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(15, 15, 18));
        jLabel19.setText("iemhqïlref.a wxlh");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel19.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel19.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel19.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel19.setVerifyInputWhenFocusTarget(false);
        jPanel10.add(jLabel19, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel10, java.awt.BorderLayout.LINE_START);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMinimumSize(new java.awt.Dimension(430, 95));
        jPanel11.setPreferredSize(new java.awt.Dimension(430, 98));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jTextField9.setBackground(new java.awt.Color(245, 245, 245));
        jTextField9.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(15, 15, 18));
        jTextField9.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField9.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField9.setPreferredSize(new java.awt.Dimension(882, 55));
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });
        jPanel11.add(jTextField9, java.awt.BorderLayout.PAGE_END);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(15, 15, 18));
        jLabel25.setText("ysÕ uqo,");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel25.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel25.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel25.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel25.setVerifyInputWhenFocusTarget(false);
        jPanel11.add(jLabel25, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel11, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 1, 46, new java.awt.Color(255, 255, 255)));
        jPanel5.setMinimumSize(new java.awt.Dimension(975, 100));
        jPanel5.setPreferredSize(new java.awt.Dimension(975, 100));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(15, 15, 18));
        jLabel21.setText("iemhqïlref.a ku");
        jLabel21.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel21.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel21.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel21.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel21.setVerifyInputWhenFocusTarget(false);
        jPanel5.add(jLabel21, java.awt.BorderLayout.PAGE_START);

        jTextField6.setBackground(new java.awt.Color(245, 245, 245));
        jTextField6.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(15, 15, 18));
        jTextField6.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField6.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField6.setPreferredSize(new java.awt.Dimension(882, 55));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        jPanel5.add(jTextField6, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 1, 46, new java.awt.Color(255, 255, 255)));
        jPanel4.setMinimumSize(new java.awt.Dimension(975, 191));
        jPanel4.setPreferredSize(new java.awt.Dimension(975, 191));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 15, 18));
        jLabel20.setText("iemhqïlref.a ,smskh");
        jLabel20.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel20.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel20.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel20.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel20.setVerifyInputWhenFocusTarget(false);
        jPanel4.add(jLabel20, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(882, 140));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(882, 140));

        jTextArea1.setBackground(new java.awt.Color(245, 245, 245));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(15, 15, 18));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(875, 135));
        jTextArea1.setPreferredSize(new java.awt.Dimension(875, 135));
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 1, 46, new java.awt.Color(255, 255, 255)));
        jPanel6.setMinimumSize(new java.awt.Dimension(975, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(975, 100));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(15, 15, 18));
        jLabel22.setText("ud¾.fha ku ");
        jLabel22.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel22.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel22.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel22.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel22.setVerifyInputWhenFocusTarget(false);
        jPanel6.add(jLabel22, java.awt.BorderLayout.PAGE_START);

        jTextField5.setBackground(new java.awt.Color(245, 245, 245));
        jTextField5.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(15, 15, 18));
        jTextField5.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField5.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField5.setPreferredSize(new java.awt.Dimension(882, 55));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        jPanel6.add(jTextField5, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 1, 46, new java.awt.Color(255, 255, 255)));
        jPanel7.setMinimumSize(new java.awt.Dimension(975, 100));
        jPanel7.setPreferredSize(new java.awt.Dimension(975, 100));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMinimumSize(new java.awt.Dimension(430, 95));
        jPanel8.setPreferredSize(new java.awt.Dimension(430, 98));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(15, 15, 18));
        jLabel23.setText("m%jdyk wkqmd;h");
        jLabel23.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel23.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel23.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel23.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel23.setVerifyInputWhenFocusTarget(false);
        jPanel8.add(jLabel23, java.awt.BorderLayout.PAGE_START);

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(245, 245, 245));
        jTextField7.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(15, 15, 18));
        jTextField7.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField7.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField7.setPreferredSize(new java.awt.Dimension(882, 55));
        jPanel8.add(jTextField7, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMinimumSize(new java.awt.Dimension(430, 95));
        jPanel9.setPreferredSize(new java.awt.Dimension(430, 98));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("FMMalithi", 0, 31)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(15, 15, 18));
        jLabel24.setText(",sms øjH .dia;=");
        jLabel24.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel24.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel24.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel24.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel24.setVerifyInputWhenFocusTarget(false);
        jPanel9.add(jLabel24, java.awt.BorderLayout.PAGE_START);

        jTextField8.setBackground(new java.awt.Color(245, 245, 245));
        jTextField8.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(15, 15, 18));
        jTextField8.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField8.setMinimumSize(new java.awt.Dimension(882, 55));
        jTextField8.setPreferredSize(new java.awt.Dimension(882, 55));
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel9.add(jTextField8, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel9, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel7);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 46, 20, 46, new java.awt.Color(255, 255, 255)));
        jPanel28.setMinimumSize(new java.awt.Dimension(975, 72));
        jPanel28.setPreferredSize(new java.awt.Dimension(975, 72));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setMaximumSize(new java.awt.Dimension(400, 77));
        jPanel32.setMinimumSize(new java.awt.Dimension(400, 77));
        jPanel32.setPreferredSize(new java.awt.Dimension(400, 77));
        jPanel32.setLayout(new java.awt.BorderLayout(15, 0));

        jButton15.setBackground(new java.awt.Color(57, 117, 104));
        jButton15.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        jButton15.setText("iqrlskak");
        jButton15.setIconTextGap(8);
        jButton15.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton15.setMaximumSize(new java.awt.Dimension(135, 52));
        jButton15.setMinimumSize(new java.awt.Dimension(135, 52));
        jButton15.setOpaque(true);
        jButton15.setPreferredSize(new java.awt.Dimension(135, 52));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jButton15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton15KeyPressed(evt);
            }
        });
        jPanel32.add(jButton15, java.awt.BorderLayout.CENTER);

        jButton16.setBackground(new java.awt.Color(243, 156, 18));
        jButton16.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        jButton16.setText("bj;a jkak");
        jButton16.setIconTextGap(8);
        jButton16.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton16.setMaximumSize(new java.awt.Dimension(175, 52));
        jButton16.setMinimumSize(new java.awt.Dimension(175, 52));
        jButton16.setOpaque(true);
        jButton16.setPreferredSize(new java.awt.Dimension(175, 52));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel32.add(jButton16, java.awt.BorderLayout.LINE_END);

        jButton18.setBackground(new java.awt.Color(68, 150, 207));
        jButton18.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loading.png"))); // NOI18N
        jButton18.setIconTextGap(8);
        jButton18.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton18.setMaximumSize(new java.awt.Dimension(55, 52));
        jButton18.setMinimumSize(new java.awt.Dimension(55, 52));
        jButton18.setOpaque(true);
        jButton18.setPreferredSize(new java.awt.Dimension(55, 52));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel32.add(jButton18, java.awt.BorderLayout.LINE_START);

        jPanel28.add(jPanel32, java.awt.BorderLayout.LINE_END);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setMinimumSize(new java.awt.Dimension(50, 52));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel28.add(jPanel33, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel28);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clear() {

        jTextField4.setText("");
        jTextField6.setText("");
        jTextArea1.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTextField8.setText("");

    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        String sNo = jTextField4.getText().trim();
        String supplierName = jTextField6.getText().trim();
        String arrears = jTextField9.getText().trim();
        String supplierAddress = jTextArea1.getText().trim();
        String rodeName = jTextField5.getText().trim();
        String transportRate = jTextField7.getText().trim();
        String docRate = jTextField8.getText().trim();

        if (sNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "iemhqïlref.a wxlh we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if (!isNumeric(sNo)) {
            JOptionPane.showMessageDialog(this, "iemhqïlref.a wxlh wxlhla f,i we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (supplierName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "iemhqïlref.a ku we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!isNumeric(arrears)) {
            JOptionPane.showMessageDialog(this, "ysÕ uqo, wxlhla f,i we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (supplierAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "iemhqïlref.a ,smskh we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (rodeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ud¾.fha ku we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            // First check if the ID already exists
            TransportService roadNameAvailability = new TransportService();
            int fRoadNameAvailability = roadNameAvailability.findByName(rodeName);

            if (fRoadNameAvailability <= 0) {
                JOptionPane.showMessageDialog(this, "oekgu;a mj;sk ud¾.hla we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (transportRate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "lreKdlr oekgu;a mj;sk ud¾. kï muKla we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (docRate.isEmpty()) {
                JOptionPane.showMessageDialog(this, ",sms øjH .dia;= we;=,;a lrkak'", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    // Validate if docRate is a valid number (either integer or float)
                    Float.parseFloat(docRate); // Attempt to parse rate as a float

                    // First check if the ID already exists
                    SuppliersService docRateAvailability = new SuppliersService();
                    int fDocRateAvailability = docRateAvailability.findByDocRate(docRate);

                    if (fDocRateAvailability <= 0) {
                        JOptionPane.showMessageDialog(this, "oekgu;a mj;sk ,sms øjH .dia;=jla we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        int sNoInt = Integer.parseInt(sNo);

                        // First check if the ID already exists
                        if ("iqrlskak".equals(jButton15.getText())) {

                            try {

                                SuppliersService supplierService = new SuppliersService();
                                int fsupplier = supplierService.findById(sNo);

                                if (fsupplier > 0) {
                                    // ID exists, show error message
                                    JOptionPane.showMessageDialog(this, "fuu o;a;h oekgu;a mj;S' lreKdlr fjk;a o;a;h we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {

                                    // Create a Transport object and set the values
                                    SuppliersModel supplier = new SuppliersModel();
                                    supplier.setId(sNoInt);
                                    supplier.setName(supplierName);
                                    supplier.setAddress(supplierAddress);
                                    supplier.setRoad_name(rodeName);
                                    supplier.setTransport_rate(transportRate);
                                    supplier.setDoc_rate(docRate);

                                    // Call the save method in transportService
                                    supplierService.save(supplier);
                                    clear();

                                }

                            } catch (Exception e) {
                                logger.log(Level.WARNING, "Add_Supplier", e);
                                e.printStackTrace();
                            }

                        } else {

                            try {

                                int supplierNoInt = Integer.parseInt(supplierNo);

                                // Create a Transport object and set the values
                                SuppliersModel supplier = new SuppliersModel();
                                supplier.setId(supplierNoInt);
                                supplier.setName(supplierName);
                                supplier.setArrears(arrears);
                                supplier.setAddress(supplierAddress);
                                supplier.setRoad_name(rodeName);
                                supplier.setTransport_rate(transportRate);
                                supplier.setDoc_rate(docRate);

                                // Call the save method in transportService
                                SuppliersService supplierService = new SuppliersService();
                                supplierService.update(supplier);


                            } catch (Exception e) {
                                logger.log(Level.WARNING, "Add_Supplier", e);
                                e.printStackTrace();
                            }

                        }
                    }

                } catch (NumberFormatException e) {
                    // If the rate is not a valid number, show an error message
                    JOptionPane.showMessageDialog(this, "lreKdlr ,sms øjH .dia;= wxlhla f,i we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    // Utility method to check if a string is a valid number
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false; // Return false for empty strings
        }
        try {
            Double.parseDouble(str); // Attempt to parse the string as a double
            return true; // If parsing succeeds, it's a valid number
        } catch (NumberFormatException e) {
            return false; // If an exception occurs, it's not a valid number
        }
    }


    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        supplierNo = ""; // Reset supplierNo
        this.dispose();

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        instance = null;
        System.out.println(supplierNo);
        if (!supplierNo.isEmpty()) {
            loadSupplierData(supplierNo); // Load supplier data if `supplierNo` is provided
        } else {
            newSupplier();
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private HashMap<String, String> loadTransport() {
        transportMap.clear();
        transportNameMap.clear();

        String name = jTextField5.getText().trim();
        int limit = name.isEmpty() ? 0 : 5;  // Define the limit if there's input text

        // Set jTextField7 to empty if limit is 0
        if (limit == 0) {
            jTextField7.setText("");  // Clear the text field
        }

        // Initialize the service class
        TransportService transportService = new TransportService();

        // Fetch transport data using SupplierService
        HashMap<String, Transport> tMap = transportService.getTransportByRoadName(name, limit);

        // Populate tMap and transportNameMap with the fetched data
        for (Map.Entry<String, Transport> entry : tMap.entrySet()) {
            Transport transport = entry.getValue();
            transportMap.put(transport.getId(), transport); // Adding the transport objects to the transportMap
            transportNameMap.put(transport.getRoad_name(), transport.getId()); // Adding the Product names and IDs to the transportNameMap
        }

        return transportNameMap;
    }

    private HashMap<String, Integer> loadDoc() {
        suppliersMap.clear();
        suppliersDocMap.clear();

        String doc = jTextField8.getText().trim();
        int limit = doc.isEmpty() ? 0 : 5;  // Define the limit if there's input text

        // Initialize the service class
        SuppliersService suppliersService = new SuppliersService();

        // Fetch transport data using SupplierService
        HashMap<Integer, SuppliersModel> suMap = suppliersService.getSuppliersByDocRate(doc, limit);

        // Populate suMap and transportNameMap with the fetched data
        for (Map.Entry<Integer, SuppliersModel> entry : suMap.entrySet()) {
            SuppliersModel suppliersModel = entry.getValue();
            suppliersMap.put(suppliersModel.getId(), suppliersModel); // Adding the transport objects to the suppliersMap
            suppliersDocMap.put(suppliersModel.getDoc_rate(), suppliersModel.getId()); // Adding the Product names and IDs to the transportNameMap
        }

        return suppliersDocMap;
    }


    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() != KeyEvent.VK_ESCAPE) {
                Popups.loadPopupTextField5(jPopupMenu1, jTextField5, jTextField7, loadTransport(), transportMap);
                if (jTextField5.getText().equals("")) {
                    jPopupMenu1.setVisible(false);
                }
            } else {
                jPopupMenu1.setVisible(false);
            }

            if (evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_UP) {
                jTextField5.grabFocus();
            }
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() != KeyEvent.VK_ESCAPE) {
                Popups.loadPopupTextField8(jPopupMenu2, jTextField8, loadDoc());
                if (jTextField8.getText().equals("")) {
                    jPopupMenu2.setVisible(false);
                }
            } else {
                jPopupMenu2.setVisible(false);
            }

            if (evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_UP) {
                jTextField8.grabFocus();
            }
        }
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        // TODO add your handling code here:
        if (!supplierNo.isEmpty()) {
            jTextField5.setText("");
        }
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        // TODO add your handling code here:
        if (!supplierNo.isEmpty()) {
            jTextField8.setText("");
        }
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        String value9 = jTextField9.getText().trim();
        // If jTextField6 contains "0", clear it before entering a new value
        if (value9.equals("0")) {
            jTextField9.setText(""); // Clear jTextField6 if it is "0"
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 1);
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 2);
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 3);
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 4);
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 5);
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jButton15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton15KeyPressed
        // TODO add your handling code here:
        mannualTab(evt, 6);
    }//GEN-LAST:event_jButton15KeyPressed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
     *//* Set the Nimbus look and feel *//*
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        *//* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        *//* Create and display the dialog *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddSupplier dialog = new AddSupplier("");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
