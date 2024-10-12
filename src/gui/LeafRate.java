/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import model.leaf.Leaf;
import model.leaf.LeafService;
import model.leaf.LeafTableModel;
import model.month.MonthModal;
import model.month.MonthService;
import model.year.YearModal;
import model.year.YearService;
import static gui.Home.logger;

/**
 * @author ECOTEC
 */
public class LeafRate extends javax.swing.JPanel {

    private java.util.HashMap<String, JButton> buttonMap = new HashMap<>();

    LeafTableModel leafTableModel;

    Integer page = 1;
    Integer rowCountPerPage = 5;
    Integer totalPage = 1;
    Integer totalData = 0;

    private static LeafService dailyLeafService;
    


    /**
     * Creates new form Suppliers
     */
    public LeafRate() {

        dailyLeafService = new LeafService();

        initComponents();

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        // Check if screen height is less than 720
        if (screenHeight < 1000) {
            jPanel17.setLayout(null);
            jPanel17.setBounds(50, 50, 200, 100);  // Set bounds for jPanel17
            jPanel20.setBounds(0, 60, 1011, 240);  // Set width (300) and new height
            int newHeight = screenHeight - 450;
            jPanel22.setBounds(0, 290, 1011, newHeight);  // Set width (300) and new height
        }

        // Customize the table header
        customizeTableHeader(jTable);

        jComboBoxPage.addItem("7");
        jComboBoxPage.addItem("15");
        jComboBoxPage.addItem("30");
        jComboBoxPage.addItem("50");
        jComboBoxPage.addItem("100");
        jComboBoxPage.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                loadTable();
            }
        });
        loadTable();

        jTable.setSelectionBackground(new Color(57, 117, 104));
        jTable.setSelectionForeground(new Color(255, 255, 255));

        jComboBoxPage.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Create a button for the arrow
                JButton arrowButton = new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        super.paint(g);
                        Graphics2D g2d = (Graphics2D) g;

                        // Create a gradient from green to dark green
                        GradientPaint gradient = new GradientPaint(0, 0, Color.GREEN, getWidth(), getHeight(), new Color(57, 117, 104));
                        g2d.setPaint(gradient);

                        // Adjusted space between the two arrows
                        int arrowSpacing = 2; // Space between the two arrows

                        // Draw upper arrow (pointing up)
                        int[] xPointsUp = {getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2};
                        int[] yPointsUp = {getHeight() / 2 - arrowSpacing, getHeight() / 2 - arrowSpacing, getHeight() / 2 - 10};
                        g2d.fillPolygon(xPointsUp, yPointsUp, 3);

                        // Draw lower arrow (pointing down)
                        int[] xPointsDown = {getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2};
                        int[] yPointsDown = {getHeight() / 2 + arrowSpacing, getHeight() / 2 + arrowSpacing, getHeight() / 2 + 10};
                        g2d.fillPolygon(xPointsDown, yPointsDown, 3);
                    }
                };
                arrowButton.setBorder(null);
                arrowButton.setBackground(Color.WHITE); // Set background color for the button
                return arrowButton;
            }
        });

        jTextField2.setText("ටයිප් කරන්න...");
        jTextField2.setForeground(Color.GRAY);

        loadYearsCombobox();
        loadMonthsCombobox();
    }

    private void loadMonthsCombobox() {
        // Instantiate the monthService
        MonthService monthService = new MonthService();

        // Retrieve the list of MonthModal from the database
        List<MonthModal> monthList = monthService.findAll();

        // Create a list to hold the month strings
        List<String> monthStrings = new ArrayList<>();

        monthStrings.add("මාසය");

        // Loop through the monthList and add each month to the list
        for (MonthModal monthModal : monthList) {
            monthStrings.add(monthModal.getMonth());  // Convert Year object to string
        }

        // Set the combo box model with the years dynamically
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(monthStrings.toArray(new String[0])));
    }

    private void loadYearsCombobox() {
        // Instantiate the YearService
        YearService yearService = new YearService();

        // Retrieve the list of YearModal from the database
        List<YearModal> yearList = yearService.findAll();

        // Create a list to hold the year strings
        List<String> yearStrings = new ArrayList<>();

        yearStrings.add("අවුරුද්ද");
        // Loop through the yearList and add each year to the list
        for (YearModal yearModal : yearList) {
            yearStrings.add(yearModal.getYear().toString());  // Convert Year object to string
        }

        // Set the combo box model with the years dynamically
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(yearStrings.toArray(new String[0])));
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel23 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jEntriesLabel = new javax.swing.JLabel();
        jComboBoxPage = new javax.swing.JComboBox();
        jShowLabel = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabelTotalData2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jButtonFirst = new javax.swing.JButton();
        jButtonPrevious = new javax.swing.JButton();
        jButtonNum = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonLast = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabelStatusHalaman = new javax.swing.JLabel();
        jLabelTotalData = new javax.swing.JLabel();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(991, 986));
        setPreferredSize(new java.awt.Dimension(991, 986));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        cardPanel.setLayout(new javax.swing.BoxLayout(cardPanel, javax.swing.BoxLayout.LINE_AXIS));

        card1.setMinimumSize(new java.awt.Dimension(991, 986));
        card1.setPreferredSize(new java.awt.Dimension(991, 986));
        card1.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(245, 245, 245));
        jPanel17.setMinimumSize(new java.awt.Dimension(991, 936));
        jPanel17.setPreferredSize(new java.awt.Dimension(991, 936));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 23, 1, 23, new java.awt.Color(245, 245, 245)), javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        jPanel20.setMaximumSize(new java.awt.Dimension(32767, 240));
        jPanel20.setMinimumSize(new java.awt.Dimension(991, 209));
        jPanel20.setPreferredSize(new java.awt.Dimension(991, 209));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setMaximumSize(new java.awt.Dimension(2147483647, 92));
        jPanel24.setMinimumSize(new java.awt.Dimension(900, 52));
        jPanel24.setPreferredSize(new java.awt.Dimension(900, 52));
        jPanel24.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setMinimumSize(new java.awt.Dimension(50, 52));
        jPanel28.setPreferredSize(new java.awt.Dimension(50, 52));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setMinimumSize(new java.awt.Dimension(50, 52));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel28.add(jPanel33, java.awt.BorderLayout.CENTER);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setMaximumSize(new java.awt.Dimension(400, 52));
        jPanel32.setMinimumSize(new java.awt.Dimension(400, 52));
        jPanel32.setPreferredSize(new java.awt.Dimension(400, 52));
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
        jPanel32.add(jButton15, java.awt.BorderLayout.CENTER);

        jButton16.setBackground(new java.awt.Color(213, 60, 60));
        jButton16.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        jButton16.setText("bj;a lrkak");
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

        jPanel24.add(jPanel28, java.awt.BorderLayout.PAGE_END);

        jPanel20.add(jPanel24, java.awt.BorderLayout.PAGE_END);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setMaximumSize(new java.awt.Dimension(2147483647, 92));
        jPanel29.setMinimumSize(new java.awt.Dimension(900, 92));
        jPanel29.setPreferredSize(new java.awt.Dimension(900, 92));
        jPanel29.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel30.setMinimumSize(new java.awt.Dimension(200, 92));
        jPanel30.setPreferredSize(new java.awt.Dimension(200, 92));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(15, 15, 18));
        jLabel19.setText("wjqreoao");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel19.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel19.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel19.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel19.setVerifyInputWhenFocusTarget(false);
        jPanel30.add(jLabel19, java.awt.BorderLayout.PAGE_START);

        jComboBox2.setBackground(new java.awt.Color(245, 245, 245));
        jComboBox2.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(15, 15, 18));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setMaximumSize(new java.awt.Dimension(32767, 52));
        jComboBox2.setMinimumSize(new java.awt.Dimension(296, 52));
        jComboBox2.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel30.add(jComboBox2, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel30, java.awt.BorderLayout.LINE_START);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setMaximumSize(new java.awt.Dimension(32767, 92));
        jPanel31.setMinimumSize(new java.awt.Dimension(296, 92));
        jPanel31.setPreferredSize(new java.awt.Dimension(296, 92));
        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 15, 18));
        jLabel20.setText("udih");
        jLabel20.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel20.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel20.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel20.setPreferredSize(new java.awt.Dimension(296, 40));
        jPanel31.add(jLabel20, java.awt.BorderLayout.CENTER);

        jComboBox1.setBackground(new java.awt.Color(245, 245, 245));
        jComboBox1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(15, 15, 18));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(32767, 52));
        jComboBox1.setMinimumSize(new java.awt.Dimension(296, 52));
        jComboBox1.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel31.add(jComboBox1, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel31, java.awt.BorderLayout.CENTER);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 15, 18));
        jLabel16.setText("o¿ ñ<");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel16.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel16.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel16.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel16.setVerifyInputWhenFocusTarget(false);
        jPanel23.add(jLabel16, java.awt.BorderLayout.PAGE_START);

        jTextField1.setBackground(new java.awt.Color(245, 245, 245));
        jTextField1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(15, 15, 18));
        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField1.setMinimumSize(new java.awt.Dimension(296, 52));
        jTextField1.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel23.add(jTextField1, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel23, java.awt.BorderLayout.LINE_END);

        jPanel20.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        jPanel17.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel21.setBackground(new java.awt.Color(245, 245, 245));
        jPanel21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 22, 1, 22));
        jPanel21.setToolTipText("");
        jPanel21.setMaximumSize(new java.awt.Dimension(32767, 64));
        jPanel21.setMinimumSize(new java.awt.Dimension(991, 64));
        jPanel21.setPreferredSize(new java.awt.Dimension(991, 64));
        jPanel21.setLayout(new javax.swing.BoxLayout(jPanel21, javax.swing.BoxLayout.LINE_AXIS));

        jLabel15.setFont(new java.awt.Font("FMEmanee", 0, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(73, 80, 87));
        jLabel15.setText("o¿ ñ<");
        jPanel21.add(jLabel15);

        jPanel17.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(23, 23, 23, 23, new java.awt.Color(245, 245, 245)));
        jPanel22.setMinimumSize(new java.awt.Dimension(1011, 578));
        jPanel22.setPreferredSize(new java.awt.Dimension(1011, 578));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.LINE_AXIS));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setMinimumSize(new java.awt.Dimension(950, 557));
        jPanel37.setPreferredSize(new java.awt.Dimension(950, 568));
        jPanel37.setLayout(new java.awt.BorderLayout());

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setMaximumSize(new java.awt.Dimension(32767, 60));
        jPanel38.setMinimumSize(new java.awt.Dimension(950, 60));
        jPanel38.setPreferredSize(new java.awt.Dimension(950, 60));
        jPanel38.setLayout(new java.awt.BorderLayout());

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setMinimumSize(new java.awt.Dimension(325, 40));
        jPanel35.setPreferredSize(new java.awt.Dimension(325, 54));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jEntriesLabel.setFont(new java.awt.Font("FMMalithi", 0, 25)); // NOI18N
        jEntriesLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEntriesLabel.setText("jd¾;d j,ska");
        jPanel35.add(jEntriesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jComboBoxPage.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPage.setFont(new java.awt.Font("Inter", 0, 20)); // NOI18N
        jComboBoxPage.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel35.add(jComboBoxPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jShowLabel.setFont(new java.awt.Font("FMMalithi", 0, 25)); // NOI18N
        jShowLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jShowLabel.setText("fmkaj'");
        jPanel35.add(jShowLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel38.add(jPanel35, java.awt.BorderLayout.LINE_START);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setMinimumSize(new java.awt.Dimension(365, 52));
        jPanel36.setPreferredSize(new java.awt.Dimension(365, 60));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTotalData2.setFont(new java.awt.Font("FMMalithi", 0, 25)); // NOI18N
        jLabelTotalData2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalData2.setText("fidhkak (");
        jLabelTotalData2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel36.add(jLabelTotalData2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 7, -1, -1));

        jTextField2.setFont(new java.awt.Font("Iskoola Pota", 0, 20)); // NOI18N
        jTextField2.setToolTipText("Search Here...");
        jTextField2.setMinimumSize(new java.awt.Dimension(229, 40));
        jTextField2.setPreferredSize(new java.awt.Dimension(229, 40));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel36.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 12, -1, -1));

        jPanel38.add(jPanel36, java.awt.BorderLayout.LINE_END);

        jPanel37.add(jPanel38, java.awt.BorderLayout.PAGE_START);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setMaximumSize(new java.awt.Dimension(32767, 60));
        jPanel40.setMinimumSize(new java.awt.Dimension(950, 60));
        jPanel40.setPreferredSize(new java.awt.Dimension(950, 60));
        jPanel40.setLayout(new java.awt.BorderLayout());

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 1, 7, 1));
        jPanel27.setMaximumSize(new java.awt.Dimension(218, 46));
        jPanel27.setMinimumSize(new java.awt.Dimension(218, 46));
        jPanel27.setPreferredSize(new java.awt.Dimension(218, 46));
        jPanel27.setLayout(new java.awt.GridLayout(1, 0));

        jButtonFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first_arrow.png"))); // NOI18N
        jButtonFirst.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonFirst.setMaximumSize(new java.awt.Dimension(46, 46));
        jButtonFirst.setMinimumSize(new java.awt.Dimension(46, 46));
        jButtonFirst.setPreferredSize(new java.awt.Dimension(46, 46));
        jButtonFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFirstActionPerformed(evt);
            }
        });
        jPanel27.add(jButtonFirst);

        jButtonPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev_arrow.png"))); // NOI18N
        jButtonPrevious.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonPrevious.setMaximumSize(new java.awt.Dimension(46, 46));
        jButtonPrevious.setMinimumSize(new java.awt.Dimension(46, 46));
        jButtonPrevious.setPreferredSize(new java.awt.Dimension(46, 46));
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });
        jPanel27.add(jButtonPrevious);

        jButtonNum.setBackground(new java.awt.Color(57, 117, 104));
        jButtonNum.setFont(new java.awt.Font("Inter", 0, 22)); // NOI18N
        jButtonNum.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNum.setText("1");
        jButtonNum.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonNum.setMaximumSize(new java.awt.Dimension(46, 46));
        jButtonNum.setMinimumSize(new java.awt.Dimension(46, 46));
        jButtonNum.setPreferredSize(new java.awt.Dimension(46, 46));
        jButtonNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNumActionPerformed(evt);
            }
        });
        jPanel27.add(jButtonNum);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next_arrow.png"))); // NOI18N
        jButtonNext.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonNext.setMaximumSize(new java.awt.Dimension(46, 46));
        jButtonNext.setMinimumSize(new java.awt.Dimension(46, 46));
        jButtonNext.setPreferredSize(new java.awt.Dimension(46, 46));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        jPanel27.add(jButtonNext);

        jButtonLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last_arrow.png"))); // NOI18N
        jButtonLast.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonLast.setMaximumSize(new java.awt.Dimension(46, 46));
        jButtonLast.setMinimumSize(new java.awt.Dimension(46, 46));
        jButtonLast.setPreferredSize(new java.awt.Dimension(46, 46));
        jButtonLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLastActionPerformed(evt);
            }
        });
        jPanel27.add(jButtonLast);

        jPanel40.add(jPanel27, java.awt.BorderLayout.LINE_END);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 1, 7, 1));
        jPanel34.setMinimumSize(new java.awt.Dimension(600, 46));
        jPanel34.setPreferredSize(new java.awt.Dimension(600, 46));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelStatusHalaman.setFont(new java.awt.Font("FMMalithi", 0, 25)); // NOI18N
        jLabelStatusHalaman.setText("msgq 1 isg 25 olajd");
        jPanel34.add(jLabelStatusHalaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 19, 270, -1));

        jLabelTotalData.setFont(new java.awt.Font("FMMalithi", 0, 25)); // NOI18N
        jLabelTotalData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalData.setText("uq¿ jd¾;d .Kk");
        jPanel34.add(jLabelTotalData, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 250, -1));

        jPanel40.add(jPanel34, java.awt.BorderLayout.LINE_START);

        jPanel37.add(jPanel40, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(950, 417));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(950, 417));

        jTable.setFont(new java.awt.Font("Iskoola Pota", 0, 22)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(52);
        jTable.setRowMargin(5);
        jTable.setShowGrid(true);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel37.add(tableScrollButton1, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel37);

        jPanel17.add(jPanel22, java.awt.BorderLayout.PAGE_END);

        card1.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel18.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel18.setMinimumSize(new java.awt.Dimension(991, 50));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("FMMalithi", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(73, 80, 87));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("o¿ ñ<");
        jLabel10.setMaximumSize(new java.awt.Dimension(32767, 50));
        jLabel10.setMinimumSize(new java.awt.Dimension(1011, 50));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(1011, 50));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        card1.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 22, 1, 22));
        jPanel19.setMaximumSize(new java.awt.Dimension(32767, 47));
        jPanel19.setMinimumSize(new java.awt.Dimension(991, 47));
        jPanel19.setPreferredSize(new java.awt.Dimension(991, 47));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.LINE_AXIS));

        jLabel13.setFont(new java.awt.Font("Inter", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(152, 166, 173));
        jLabel13.setText("2024");
        jPanel19.add(jLabel13);

        jLabel11.setFont(new java.awt.Font("Inter", 0, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(152, 166, 173));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copyright.png"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 6, 1, 6));
        jPanel19.add(jLabel11);

        jLabel14.setFont(new java.awt.Font("Inter", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(152, 166, 173));
        jLabel14.setText("Nebulainfinite");
        jPanel19.add(jLabel14);

        jLabel12.setFont(new java.awt.Font("Inter", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(152, 166, 173));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Design & Develop by Ayesh");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel12.setMaximumSize(new java.awt.Dimension(32767, 25));
        jLabel12.setMinimumSize(new java.awt.Dimension(734, 25));
        jLabel12.setName(""); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(734, 25));
        jPanel19.add(jLabel12);

        card1.add(jPanel19, java.awt.BorderLayout.PAGE_END);

        cardPanel.add(card1);

        add(cardPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void clear() {

        jComboBox2.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
        jTextField1.setText("");

    }

    private void customizeTableHeader(JTable table) {

        JTableHeader header = table.getTableHeader();

        // Set font family, style, and size
        Font headerFont = new Font("FMMalithi", Font.PLAIN, 26);  // Change "Arial" and size as needed
        header.setFont(headerFont);

        // Set the header height
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));  // Adjust the height as needed

        // Center align the header names
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the custom header renderer to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }

    private void loadTable() {

        totalData = dailyLeafService.count();
        rowCountPerPage = Integer.valueOf(jComboBoxPage.getSelectedItem().toString());
        Double totalPageD = Math.ceil(totalData.doubleValue() / rowCountPerPage.doubleValue());
        totalPage = totalPageD.intValue();

        if (page.equals(1)) {
            jButtonFirst.setEnabled(false);
            jButtonPrevious.setEnabled(false);
        } else {
            jButtonFirst.setEnabled(true);
            jButtonPrevious.setEnabled(true);
        }

        if (page.equals(totalPage)) {
            jButtonNext.setEnabled(false);
            jButtonLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dis_last_arrow.png")));
            jButtonLast.setBackground(new Color(249, 249,249));
            jButtonLast.setFocusPainted(false);

        } else {
            jButtonNext.setEnabled(true);
            jButtonLast.setBackground(new Color(255, 255,255));
            jButtonLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last_arrow.png")));
        }

        if (page > totalPage) {
            page = 1;
        }

        leafTableModel = new LeafTableModel();
        leafTableModel.setList(dailyLeafService.findAll(page, rowCountPerPage));
        jTable.setModel(leafTableModel);

        jLabelStatusHalaman.setText("msgq " + page + " isg " + totalPage + " olajd");
        jLabelTotalData.setText(("uq¿ jd¾;d .Kk " + totalData));
        autoResizeColumn(jTable);

        jTable.getColumnModel().getColumn(0).setPreferredWidth(20);

        jButtonNum.setText(page.toString());

    }

    private void searchTable(String searchText) {

        totalData = dailyLeafService.findCount(searchText);
        rowCountPerPage = Integer.valueOf(jComboBoxPage.getSelectedItem().toString());
        Double totalPageD = Math.ceil(totalData.doubleValue() / rowCountPerPage.doubleValue());
        totalPage = totalPageD.intValue();

        if (page.equals(1)) {
            jButtonFirst.setEnabled(false);
            jButtonPrevious.setEnabled(false);
        } else {
            jButtonFirst.setEnabled(true);
            jButtonPrevious.setEnabled(true);
        }

        if (page.equals(totalPage)) {
            jButtonLast.setEnabled(false);
            jButtonNext.setEnabled(false);
        } else {
            jButtonLast.setEnabled(true);
            jButtonNext.setEnabled(true);
        }

        if (page > totalPage) {
            page = 1;
        }

        leafTableModel = new LeafTableModel();
        leafTableModel.setList(dailyLeafService.find(searchText, page, rowCountPerPage));
        jTable.setModel(leafTableModel);

        jLabelStatusHalaman.setText("msgq " + page + " isg " + totalPage + " olajd");
        jLabelTotalData.setText(("uq¿ jd¾;d .Kk " + totalData));
        autoResizeColumn(jTable);
        jButtonNum.setText(page.toString());

    }

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        if (jTextField2.getText().equals("ටයිප් කරන්න...")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);  // Change color to black when focused
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        if (jTextField2.getText().isEmpty()) {
            jTextField2.setForeground(Color.GRAY);  // Change color to gray when empty
            jTextField2.setText("ටයිප් කරන්න...");
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        searchTable(jTextField2.getText().trim());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButtonFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFirstActionPerformed
        page = 1;
        loadTable();
    }//GEN-LAST:event_jButtonFirstActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        if (page > 1) {
            page--;
            loadTable();
        }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNumActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (page < totalPage) {
            page++;
            loadTable();
            ;
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLastActionPerformed
        page = totalPage;
        loadTable();
    }//GEN-LAST:event_jButtonLastActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        int row = jTable.getSelectedRow();

        // Check if the row index is valid
        if (row >= 0 && row < jTable.getRowCount()) {
            // Safely retrieve the values from the selected row
            jComboBox2.setSelectedItem(jTable.getValueAt(row, 1));
            jComboBox1.setSelectedItem(jTable.getValueAt(row, 2));
            jTextField1.setText(String.valueOf(jTable.getValueAt(row, 3)));

            setUpdateButton();
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void setUpdateButton(){
        // Update button properties
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png")));
        jButton15.setText("fjkia'"); // Assuming you meant "Update" instead of "fjkia"
        jButton15.setBackground(new Color(30, 30, 30));
    }

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableKeyReleased

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:

        int row = jTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "lreKdlr bj;a lsÍug wjYH o;a;h f;darkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String id = (String) jTable.getValueAt(row, 0);



            // Show a confirmation dialog
            int response = JOptionPane.showConfirmDialog(null,
                    "Tng fuu o;a; uelSug wjYH nj úYajdio@",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            // If the user confirms (YES option)
            if (response == JOptionPane.YES_OPTION) {

                try {

                    // Call the delete method in transportService
                    LeafService dailyLeafService = new LeafService();
                    dailyLeafService.delete(id);

                } catch (Exception e) {
                    logger.log(Level.WARNING, "Leaf_Rate", e);
                    e.printStackTrace();

                }

                clear();
                if (row != -1) {
                    // Unselect the row
                    jTable.clearSelection();
                }
                setSaveButton();
                String searchText = jTextField2.getText();
                if (searchText.equals("ටයිප් කරන්න...")) {
                    loadTable();
                } else {
                    searchTable(searchText);
                }

            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void setSaveButton(){
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png")));
        jButton15.setText("iqrlskak");
        jButton15.setBackground(new Color(57, 117, 104));
    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        String yearString = String.valueOf(jComboBox2.getSelectedItem());
        String month = String.valueOf(jComboBox1.getSelectedItem());
        String rate = jTextField1.getText().trim();

        if (yearString.equals("අවුරුද්ද") || yearString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "wjqreoao f;darkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("මාසය") || month.isEmpty()) {
            JOptionPane.showMessageDialog(this, "udih f;darkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (rate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "o¿ ñ< we;=,;a lrkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Validate if rate is a valid number (either integer or float)
                Float.parseFloat(rate); // Attempt to parse rate as a float

                // Convert yearString to Year object
                Year year = Year.parse(yearString);

                // First check if the ID already exists

                if ("iqrlskak".equals(jButton15.getText())) {
                    try {

                        LeafService dailyLeafService = new LeafService();
                        int fleaf = dailyLeafService.findByData(yearString, month, rate);

                        if (fleaf > 0) {
                            // ID exists, show error message
                            JOptionPane.showMessageDialog(this, "fuu o;a;h oekgu;a mj;S' lreKdlr fjk;a o;a;h we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            // Create a Transport object and set the values
                            Leaf leaf = new Leaf();
                            leaf.setYear(year);
                            leaf.setMonth(month);
                            leaf.setLeaf_rate(rate);

                            // Call the save method in transportService
                            dailyLeafService.save(leaf);
                            String searchText = jTextField2.getText();
                            if(searchText.equals("ටයිප් කරන්න...")){
                                loadTable();
                            } else{
                                searchTable(searchText);
                            }
                            clear();

                        }

                    } catch (Exception e) {
                        logger.log(Level.WARNING, "Leaf_Rate", e);
                        e.printStackTrace();

                    }
                } else {

                    int row = jTable.getSelectedRow();

                    if (row != -1) {  // Ensure a row is selected


                        try {
                            // Retrieve the ID from the selected row
                            String id = (String) jTable.getValueAt(row, 0);  // Assuming 'Id' is in the first column (index 0)

                            // Create a Leaf object and set the values
                            Leaf leaf = new Leaf();
                            leaf.setId(id);  // Set the selected Id
                            leaf.setYear(year);  // Set the selected year
                            leaf.setMonth(month);  // Set the selected month
                            leaf.setLeaf_rate(rate);  // Set the leaf rate

                            // Call the update method in dailyLeafService
                            LeafService dailyLeafService = new LeafService();
                            boolean response = dailyLeafService.update(leaf);

                            if(!response){
                                JOptionPane.showMessageDialog(this, "fuu o;a;h oekgu;a mj;S' lreKdlr fjk;a o;a;h we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        } catch (Exception e) {
                            // Log the error
                            e.printStackTrace();
                            logger.log(Level.WARNING, "Leaf_Rate", e);
                        }

                        clear();

                        // Unselect the row if any was selected
                        jTable.clearSelection();
                        String searchText = jTextField2.getText();
                        if(searchText.equals("ටයිප් කරන්න...")){
                            loadTable();
                        } else{
                            searchTable(searchText);
                        }

                        setSaveButton();
                    }
                }

            } catch (NumberFormatException e) {
                // If the rate is not a valid number, show an error message
                JOptionPane.showMessageDialog(this, "lreKdlr o¿ ñ< wxlhla f,i we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        int row = jTable.getSelectedRow();
        clear();
        if (row != -1) {
            // Unselect the row
            jTable.clearSelection();
        }
        loadTable();
        jTextField2.setText("ටයිප් කරන්න...");
        setSaveButton();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void autoResizeColumn(JTable jTable1) {

        JTableHeader header = jTable1.getTableHeader();
        int rowCount = jTable1.getRowCount();

        final Enumeration columns = jTable1.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) jTable1.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(jTable1, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) jTable1.getCellRenderer(row, col).getTableCellRendererComponent(jTable1,
                        jTable1.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            // Account for cell spacing
            width += jTable1.getIntercellSpacing().width;

            // Set the column width
            column.setPreferredWidth(width);

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card1;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButtonFirst;
    private javax.swing.JButton jButtonLast;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonNum;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox jComboBoxPage;
    private javax.swing.JLabel jEntriesLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabelStatusHalaman;
    private javax.swing.JLabel jLabelTotalData;
    private javax.swing.JLabel jLabelTotalData2;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jShowLabel;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
