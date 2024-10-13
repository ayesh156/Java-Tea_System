/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.advance.*;
import model.leafBill.LeafBillModel;
import model.leafBill.LeafBillService;
import model.leafBill.LeafBillTableModel;
import model.month.MonthModal;
import model.month.MonthService;
import model.SupNameIdPopups;
import model.suppliers.SuppliersModel;
import model.suppliers.SuppliersService;
import model.year.YearModal;
import model.year.YearService;
import static gui.Home.logger;

/**
 * @author ECOTEC
 */
public class LeafBill extends javax.swing.JPanel {

    private HashMap<String, SuppliersModel> suppliersMap = new HashMap<>(); //to keep suppliers with IDss

    private HashMap<String, String> suppliersNameMap = new HashMap<>(); //to keep suppliers names with IDss
    

    LeafBillTableModel leafBillTableModel;

    Integer page = 1;
    Integer rowCountPerPage = 5;
    Integer totalPage = 1;
    Integer totalData = 0;

    private static LeafBillService leafBillService;

    private static SuppliersService suppliersService;


    /**
     * Creates new form Suppliers
     */
    public LeafBill() {

        leafBillService = new LeafBillService();
        suppliersService = new SuppliersService();

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

        jComboBoxPage.addItem("6");
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        cardPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel43 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
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
        jPanel20.setMinimumSize(new java.awt.Dimension(991, 300));
        jPanel20.setPreferredSize(new java.awt.Dimension(991, 300));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setMaximumSize(new java.awt.Dimension(2147483647, 92));
        jPanel29.setMinimumSize(new java.awt.Dimension(900, 92));
        jPanel29.setPreferredSize(new java.awt.Dimension(900, 92));
        jPanel29.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel23.setMinimumSize(new java.awt.Dimension(270, 78));
        jPanel23.setPreferredSize(new java.awt.Dimension(270, 62));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jComboBox2.setBackground(new java.awt.Color(245, 245, 245));
        jComboBox2.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(15, 15, 18));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setMaximumSize(new java.awt.Dimension(32767, 52));
        jComboBox2.setMinimumSize(new java.awt.Dimension(296, 52));
        jComboBox2.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel23.add(jComboBox2, java.awt.BorderLayout.PAGE_END);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(15, 15, 18));
        jLabel19.setText("wjqreoao");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel19.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel19.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel19.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel19.setVerifyInputWhenFocusTarget(false);
        jPanel23.add(jLabel19, java.awt.BorderLayout.PAGE_START);

        jPanel29.add(jPanel23, java.awt.BorderLayout.LINE_END);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel26.setLayout(new java.awt.BorderLayout());

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(15, 15, 18));
        jLabel18.setText("wxlh");
        jLabel18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel18.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel18.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel18.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel18.setVerifyInputWhenFocusTarget(false);
        jPanel26.add(jLabel18, java.awt.BorderLayout.PAGE_START);

        jTextField4.setBackground(new java.awt.Color(245, 245, 245));
        jTextField4.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(15, 15, 18));
        jTextField4.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField4.setMinimumSize(new java.awt.Dimension(296, 52));
        jTextField4.setPreferredSize(new java.awt.Dimension(296, 52));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        jPanel26.add(jTextField4, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel44.setLayout(new java.awt.BorderLayout());

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(15, 15, 18));
        jLabel24.setText("ku");
        jLabel24.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel24.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel24.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel24.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel24.setVerifyInputWhenFocusTarget(false);
        jPanel44.add(jLabel24, java.awt.BorderLayout.PAGE_START);

        jTextField5.setBackground(new java.awt.Color(245, 245, 245));
        jTextField5.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(15, 15, 18));
        jTextField5.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField5.setMinimumSize(new java.awt.Dimension(296, 52));
        jTextField5.setPreferredSize(new java.awt.Dimension(296, 52));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        jPanel44.add(jTextField5, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel44, java.awt.BorderLayout.LINE_START);

        jPanel20.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel39.setMaximumSize(new java.awt.Dimension(2147483647, 110));
        jPanel39.setMinimumSize(new java.awt.Dimension(900, 110));
        jPanel39.setPreferredSize(new java.awt.Dimension(900, 110));
        jPanel39.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel45.setMinimumSize(new java.awt.Dimension(296, 92));
        jPanel45.setPreferredSize(new java.awt.Dimension(296, 92));
        jPanel45.setLayout(new java.awt.BorderLayout());

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(15, 15, 18));
        jLabel25.setText("udih");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel25.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel25.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel25.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel25.setVerifyInputWhenFocusTarget(false);
        jPanel45.add(jLabel25, java.awt.BorderLayout.PAGE_START);

        jComboBox1.setBackground(new java.awt.Color(245, 245, 245));
        jComboBox1.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(15, 15, 18));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(32767, 52));
        jComboBox1.setMinimumSize(new java.awt.Dimension(296, 52));
        jComboBox1.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel45.add(jComboBox1, java.awt.BorderLayout.PAGE_END);

        jPanel39.add(jPanel45, java.awt.BorderLayout.LINE_START);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel43.setMaximumSize(new java.awt.Dimension(2147483647, 90));
        jPanel43.setMinimumSize(new java.awt.Dimension(900, 90));
        jPanel43.setPreferredSize(new java.awt.Dimension(900, 90));
        jPanel43.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setMaximumSize(new java.awt.Dimension(300, 80));
        jPanel46.setMinimumSize(new java.awt.Dimension(300, 92));
        jPanel46.setPreferredSize(new java.awt.Dimension(551, 80));
        jPanel46.setLayout(new java.awt.BorderLayout());

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setMinimumSize(new java.awt.Dimension(50, 52));
        jPanel48.setPreferredSize(new java.awt.Dimension(50, 52));
        jPanel48.setLayout(new java.awt.BorderLayout());

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setMinimumSize(new java.awt.Dimension(50, 52));

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel48.add(jPanel49, java.awt.BorderLayout.CENTER);

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setMaximumSize(new java.awt.Dimension(460, 52));
        jPanel50.setMinimumSize(new java.awt.Dimension(460, 52));
        jPanel50.setPreferredSize(new java.awt.Dimension(460, 52));
        jPanel50.setLayout(new java.awt.BorderLayout(15, 0));

        jButton17.setBackground(new java.awt.Color(57, 117, 104));
        jButton17.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export.png"))); // NOI18N
        jButton17.setText(",nd .kak");
        jButton17.setIconTextGap(8);
        jButton17.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton17.setMaximumSize(new java.awt.Dimension(135, 52));
        jButton17.setMinimumSize(new java.awt.Dimension(135, 52));
        jButton17.setOpaque(true);
        jButton17.setPreferredSize(new java.awt.Dimension(135, 52));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel50.add(jButton17, java.awt.BorderLayout.CENTER);

        jButton19.setBackground(new java.awt.Color(30, 39, 46));
        jButton19.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/all_loading.png"))); // NOI18N
        jButton19.setText("ish,a, ,nd .kak");
        jButton19.setIconTextGap(8);
        jButton19.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton19.setMaximumSize(new java.awt.Dimension(220, 52));
        jButton19.setMinimumSize(new java.awt.Dimension(220, 52));
        jButton19.setOpaque(true);
        jButton19.setPreferredSize(new java.awt.Dimension(220, 52));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel50.add(jButton19, java.awt.BorderLayout.LINE_END);

        jButton20.setBackground(new java.awt.Color(68, 150, 207));
        jButton20.setFont(new java.awt.Font("FMMalithi", 0, 22)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loading.png"))); // NOI18N
        jButton20.setIconTextGap(8);
        jButton20.setMargin(new java.awt.Insets(2, 9, 2, 9));
        jButton20.setMaximumSize(new java.awt.Dimension(55, 52));
        jButton20.setMinimumSize(new java.awt.Dimension(55, 52));
        jButton20.setOpaque(true);
        jButton20.setPreferredSize(new java.awt.Dimension(55, 52));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel50.add(jButton20, java.awt.BorderLayout.LINE_START);

        jPanel48.add(jPanel50, java.awt.BorderLayout.LINE_END);

        jPanel46.add(jPanel48, java.awt.BorderLayout.PAGE_END);

        jPanel43.add(jPanel46, java.awt.BorderLayout.CENTER);

        jPanel39.add(jPanel43, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel39, java.awt.BorderLayout.CENTER);

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
        jLabel15.setText("w;a;sldrï");
        jPanel21.add(jLabel15);

        jPanel17.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(23, 23, 23, 23, new java.awt.Color(245, 245, 245)));
        jPanel22.setMinimumSize(new java.awt.Dimension(1011, 530));
        jPanel22.setPreferredSize(new java.awt.Dimension(1011, 530));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.LINE_AXIS));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setMinimumSize(new java.awt.Dimension(950, 400));
        jPanel37.setPreferredSize(new java.awt.Dimension(950, 400));
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

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(950, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(950, 200));

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
        jLabel10.setText("w;a;sldrï");
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
        jTextField5.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
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

    private void setupTableWithHorizontalScroll(JTable table, JScrollPane scrollPane) {
        // Disable auto-resizing to allow horizontal scrolling
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Set the preferred widths for each column manually if needed
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(150); // Set width for each column as required
        }

        // Add the table to a JScrollPane if not done already
        scrollPane.setViewportView(table);

        // Optional: Set preferred size for the table to trigger scrolling
        table.setPreferredScrollableViewportSize(new Dimension(800, 400)); // Customize width and height

        // Ensure horizontal scrollbar policy
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void loadTable() {

//        totalData = leafBillService.count();
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
            jButtonLast.setBackground(new Color(249, 249, 249));
            jButtonLast.setFocusPainted(false);

        } else {
            jButtonNext.setEnabled(true);
            jButtonLast.setBackground(new Color(255, 255, 255));
            jButtonLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last_arrow.png")));
        }

        if (page > totalPage) {
            page = 1;
        }

        leafBillTableModel = new LeafBillTableModel();
//        leafBillTableModel.setList(leafBillService.findAll(page, rowCountPerPage));
        jTable.setModel(leafBillTableModel);

        // Set the same width for all columns
//        setSameColumnWidth(jTable, 500);  // Set all columns to a width of 100 pixels

        // Set up custom column widths
        setupCustomColumnWidths(jTable);

        // Setup table scroll (pass jTable and JScrollPane)
        setupTableWithHorizontalScroll(jTable, jScrollPane1); // Ensure jScrollPaneTable is your JScrollPane

        jLabelStatusHalaman.setText("msgq " + page + " isg " + totalPage + " olajd");
        jLabelTotalData.setText(("uq¿ jd¾;d .Kk " + totalData));
        autoResizeColumn(jTable);

        jButtonNum.setText(page.toString());

    }

    // Method to set custom column widths
    private void setSameColumnWidth(JTable table, int width) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(width);
        }
    }

    // Method to set custom column widths
    private void setupCustomColumnWidths(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        // Set preferred widths for specific columns
        columnModel.getColumn(0).setPreferredWidth(50);  // Set column 0 width (e.g., ID)
        columnModel.getColumn(1).setPreferredWidth(150); // Set column 1 width (e.g., Name)
        columnModel.getColumn(2).setPreferredWidth(100); // Set column 2 width (e.g., Date)
        columnModel.getColumn(3).setPreferredWidth(120); // Set column 3 width (e.g., Amount)
        columnModel.getColumn(4).setPreferredWidth(120); // Set column 3 width (e.g., Amount)
        columnModel.getColumn(5).setPreferredWidth(120); // Set column 3 width (e.g., Amount)
        columnModel.getColumn(6).setPreferredWidth(120); // Set column 3 width (e.g., Amount)
        columnModel.getColumn(7).setPreferredWidth(120); // Set column 3 width (e.g., Amount)
        // Add more columns as needed and set their widths

        // Optional: Allow columns to adjust but keep a minimum and maximum width
        columnModel.getColumn(0).setMinWidth(30);
        columnModel.getColumn(0).setMaxWidth(70);
    }

    private HashMap<String, String> loadSuppliers() {
        suppliersMap.clear();
        suppliersNameMap.clear();

        String id = jTextField5.getText().trim();
        int limit = id.isEmpty() ? 0 : 5;  // Define the limit if there's input text

        // Set jTextField4 to empty if limit is 0
        if (limit == 0) {
            jTextField4.setText("");  // Clear the text field
        }

        // Initialize the service class
        SuppliersService suppliersService = new SuppliersService();

        // Fetch transport data using SupplierService
        HashMap<String, SuppliersModel> tMap = suppliersService.getSuppliersName(id, limit);

        // Populate tMap and suppliersNameMap with the fetched data
        for (Map.Entry<String, SuppliersModel> entry : tMap.entrySet()) {
            SuppliersModel suppliersModel = entry.getValue();
            suppliersMap.put(suppliersModel.getId(), suppliersModel); // Adding the transport objects to the suppliersMap
            suppliersNameMap.put(suppliersModel.getName(), suppliersModel.getId()); // Adding the Product names and IDs to the suppliersNameMap
        }

        return suppliersNameMap;
    }

    private HashMap<String, String> loadSuppliersId() {
        suppliersMap.clear();
        suppliersNameMap.clear();

        String id = jTextField4.getText().trim();  // Getting ID from jTextField4
        int limit = id.isEmpty() ? 0 : 5;  // Limit the results to 5 if an ID is provided

        // Set jTextField5 to empty if limit is 0
        if (limit == 0) {
            jTextField5.setText("");  // Clear the text field if no ID is provided
        }

        // Initialize the service class
        SuppliersService suppliersService = new SuppliersService();

        // Fetch supplier data by ID using SupplierService
        HashMap<String, SuppliersModel> tMap = suppliersService.getSuppliersId(id, limit);

        // Populate tMap and suppliersNameMap with the fetched data
        for (Map.Entry<String, SuppliersModel> entry : tMap.entrySet()) {
            SuppliersModel suppliersModel = entry.getValue();
            suppliersMap.put(suppliersModel.getId(), suppliersModel);  // Map supplier ID to the Supplier object
            suppliersNameMap.put(suppliersModel.getId(), suppliersModel.getName());  // Map supplier ID to supplier name
        }

        return suppliersNameMap;  // Return the map with supplier ID and name
    }


    private void searchTable(String searchText) {

//        totalData = leafBillService.findCount(searchText);
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

        leafBillTableModel = new LeafBillTableModel();
//        leafBillTableModel.setList(leafBillService.find(searchText, page, rowCountPerPage));
        jTable.setModel(leafBillTableModel);

        // Setup table scroll (pass jTable and JScrollPane)
        setupTableWithHorizontalScroll(jTable, jScrollPane1); // Ensure jScrollPaneTable is your JScrollPane

        // Set the same width for all columns
//        setSameColumnWidth(jTable, 500);  // Set all columns to a width of 100 pixels

        // Set up custom column widths
        setupCustomColumnWidths(jTable);

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

    }//GEN-LAST:event_jTableMouseClicked

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableKeyReleased

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

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:

        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() != KeyEvent.VK_ESCAPE) {
                SupNameIdPopups.loadPopupTextField5(jPopupMenu1, jTextField5, jTextField4, loadSuppliers(), suppliersMap);
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

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:

        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() != KeyEvent.VK_ESCAPE) {
                SupNameIdPopups.loadPopupTextField4(jPopupMenu2, jTextField5, jTextField4, loadSuppliersId(), suppliersMap);
                if (jTextField4.getText().equals("")) {
                    jPopupMenu2.setVisible(false);
                }

            } else {
                jPopupMenu2.setVisible(false);
            }

            if (evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_UP) {
                jTextField4.grabFocus();
            }
        }

    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // Create an instance of the service class if necessary
        LeafBillService leafBillService = new LeafBillService(); // Ensure this is your actual service class name

        // Call the findAll method with desired page and pageSize
        int page = 1; // Example page number
        int pageSize = 10; // Example page size
        List<LeafBillModel> leafBillList = leafBillService.findAll(page, pageSize);

        // Print the details of each LeafBillModel in the console
        for (LeafBillModel leafBill : leafBillList) {
            System.out.println("Supplier ID: " + leafBill.getSupplier_id());
            System.out.println("Advance Price: " + leafBill.getAdvance_price());
            System.out.println("Debit Price: " + leafBill.getDebit_price());
            System.out.println("Total Gross Qty: " + leafBill.getGross_tqty());
            System.out.println("Total Net Qty: " + leafBill.getNet_tqty());
            System.out.println("Total Transport Rate: " + leafBill.getTransport_rate());
            System.out.println("----------------------------");

            // Optional: Display it in a GUI component, e.g., JTextArea or JTable
        }
    }//GEN-LAST:event_jButton17ActionPerformed


    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
          
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        int row = jTable.getSelectedRow();
        clear();
        if (row != -1) {
            // Unselect the row
            jTable.clearSelection();
        }
        loadTable();
        jTextField2.setText("ටයිප් කරන්න...");

        jTextField4.setEditable(true);
    }//GEN-LAST:event_jButton20ActionPerformed


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
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jShowLabel;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
