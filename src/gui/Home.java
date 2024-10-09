/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import model.Mysql;
import model.leaf.LeafService;
import model.transport.Transport;
import model.transport.TransportService;
import model.transport.TransportTableModel;

/**
 * @author ECOTEC
 */
public class Home extends javax.swing.JFrame {

    private java.util.HashMap<String, JButton> buttonMap = new HashMap<>();

    TransportTableModel transportTableModel;

    Integer page = 1;
    Integer rowCountPerPage = 5;
    Integer totalPage = 1;
    Integer totalData = 0;

    private static TransportService transportService;

    public static Logger logger = Logger.getLogger("tea_sys");

    /**
     * Creates new form Home
     */
    public Home() {

        transportService = new TransportService();

        initComponents();
        // Inside initComponents() method, find and replace the jPanel6 initialization
        // Replace jPanel6 with your custom panel
        loadSidebarButtonMap();

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

        jComboBoxPage.addItem("5");
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

        //logger
        try {
            FileHandler handler = new FileHandler("teaSystem.log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (Exception e) {
            logger.log(Level.WARNING, "logger", e);
        }

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

        totalData = transportService.count();
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

        transportTableModel = new TransportTableModel();
        transportTableModel.setList(transportService.findAll(page, rowCountPerPage));
        jTable.setModel(transportTableModel);

        jLabelStatusHalaman.setText("msgq " + page + " isg " + totalPage + " olajd");
        jLabelTotalData.setText(("uq¿ jd¾;d .Kk " + totalData));
        autoResizeColumn(jTable);

        jTable.getColumnModel().getColumn(0).setPreferredWidth(20);

        jButtonNum.setText(page.toString());

    }

    private void searchTable(String searchText) {

        totalData = transportService.findCount(searchText);
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

        transportTableModel = new TransportTableModel();
        transportTableModel.setList(transportService.find(searchText, page, rowCountPerPage));
        jTable.setModel(transportTableModel);

        jLabelStatusHalaman.setText("msgq " + page + " isg " + totalPage + " olajd");
        jLabelTotalData.setText(("uq¿ jd¾;d .Kk " + totalData));
        autoResizeColumn(jTable);
        jButtonNum.setText(page.toString());

    }

    private void loadSidebarButtonMap() {
        buttonMap.put("btn1", jButton1);
        buttonMap.put("btn2", jButton2);
        buttonMap.put("btn3", jButton3);
        buttonMap.put("btn4", jButton4);
        buttonMap.put("btn5", jButton5);
        buttonMap.put("btn6", jButton6);
        buttonMap.put("btn7", jButton7);
        buttonMap.put("btn8", jButton8);
        buttonMap.put("btn9", jButton9);
        buttonMap.put("btn10", jButton10);
    }

    public void focusSideBarButton(int button) {
        for (int i = 1; i < 11; i++) {
            String key = "btn" + i;

            boolean isFocused = (i == button);

            updateButtonIcon(key, isFocused);

            if (isFocused) {
                updatePanelsForButton(i);
            }
        }
    }

    private void updateButtonIcon(String key, boolean isFocused) {
        String iconPath = isFocused ? "/img/fill_circle.png" : "/img/circle.png";
        buttonMap.get(key).setIcon(new ImageIcon(getClass().getResource(iconPath)));
    }

    private void updatePanelsForButton(int button) {
        if (button <= 3) {
            setPanelColors(jPanel6, jPanel7, new Color(236, 243, 242), new Color(57, 117, 104));
            setPanelColors(jPanel11, jPanel12, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel13, jPanel14, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel15, jPanel16, Color.WHITE, Color.WHITE);
        } else if (button >= 4 && button <= 7) {
            setPanelColors(jPanel11, jPanel12, new Color(236, 243, 242), new Color(57, 117, 104));
            setPanelColors(jPanel6, jPanel7, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel13, jPanel14, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel15, jPanel16, Color.WHITE, Color.WHITE);
        } else if (button >= 8 && button <= 9) {
            setPanelColors(jPanel13, jPanel14, new Color(236, 243, 242), new Color(57, 117, 104));
            setPanelColors(jPanel6, jPanel7, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel11, jPanel12, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel15, jPanel16, Color.WHITE, Color.WHITE);
        } else if (button == 10) {
            setPanelColors(jPanel15, jPanel16, new Color(236, 243, 242), new Color(57, 117, 104));
            setPanelColors(jPanel6, jPanel7, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel11, jPanel12, Color.WHITE, Color.WHITE);
            setPanelColors(jPanel13, jPanel14, Color.WHITE, Color.WHITE);
        }

    }

    private void setPanelColors(JPanel panel1, JPanel panel2, Color color1, Color color2) {
        panel1.setBackground(color1);
        panel2.setBackground(color2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel()
        ;
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel()
        ;
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel()
        ;
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel()
        ;
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 985));
        setPreferredSize(new java.awt.Dimension(1280, 985));
        setSize(new java.awt.Dimension(1280, 985));

        jPanel1.setMinimumSize(new java.awt.Dimension(269, 986));
        jPanel1.setPreferredSize(new java.awt.Dimension(269, 986));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(269, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setFont(new java.awt.Font("FMEmanee", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(73, 80, 87));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon.png"))); // NOI18N
        jLabel1.setText("ì,am;a moaO;sh");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        jLabel1.setIconTextGap(5);
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 887));
        jPanel4.setPreferredSize(new java.awt.Dimension(269, 887));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setMinimumSize(new java.awt.Dimension(100, 252));
        jPanel5.setPreferredSize(new java.awt.Dimension(269, 252));
        jPanel5.setLayout(new java.awt.GridLayout(4, 1));

        jPanel6.setBackground(new java.awt.Color(236, 243, 242));
        jPanel6.setForeground(new java.awt.Color(236, 243, 242));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 64));
        jPanel6.setPreferredSize(new java.awt.Dimension(269, 64));
        jPanel6.setVerifyInputWhenFocusTarget(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(255, 102, 204));
        jLabel3.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(57, 117, 104));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/master_i.png"))); // NOI18N
        jLabel3.setText("ieliqï");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel3.setDoubleBuffered(true);
        jLabel3.setIconTextGap(8);
        jLabel3.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel6.add(jLabel3, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(57, 117, 104));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/down_ar.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel6.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jPanel7.setBackground(new java.awt.Color(57, 117, 104));
        jPanel7.setMinimumSize(new java.awt.Dimension(4, 26));
        jPanel7.setPreferredSize(new java.awt.Dimension(4, 64));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel5.add(jPanel6);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton1.setForeground(new java.awt.Color(147, 159, 171));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton1.setText("m%jdyk wkqmd;h ");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setIconTextGap(10);
        jButton1.setInheritsPopupMenu(true);
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton2.setForeground(new java.awt.Color(147, 159, 171));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton2.setText("iemhqïlrejka");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setIconTextGap(10);
        jButton2.setInheritsPopupMenu(true);
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton3.setForeground(new java.awt.Color(147, 159, 171));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton3.setText("o¿ ñ<");
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setIconTextGap(10);
        jButton3.setInheritsPopupMenu(true);
        jButton3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton3FocusGained(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, -1, -1));

        jPanel8.setMinimumSize(new java.awt.Dimension(100, 317));
        jPanel8.setPreferredSize(new java.awt.Dimension(269, 317));
        jPanel8.setLayout(new java.awt.GridLayout(5, 1));

        jPanel11.setBackground(new java.awt.Color(236, 243, 242));
        jPanel11.setForeground(new java.awt.Color(236, 243, 242));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 64));
        jPanel11.setPreferredSize(new java.awt.Dimension(269, 64));
        jPanel11.setVerifyInputWhenFocusTarget(false);
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel4.setBackground(new java.awt.Color(255, 102, 204));
        jLabel4.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(57, 117, 104));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/invoice.png"))); // NOI18N
        jLabel4.setText("ñ,§ .ekSï");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel4.setDoubleBuffered(true);
        jLabel4.setIconTextGap(8);
        jLabel4.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel11.add(jLabel4, java.awt.BorderLayout.CENTER);

        jLabel5.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(57, 117, 104));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/down_ar.png"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel11.add(jLabel5, java.awt.BorderLayout.LINE_END);

        jPanel12.setBackground(new java.awt.Color(57, 117, 104));
        jPanel12.setMinimumSize(new java.awt.Dimension(4, 26));
        jPanel12.setPreferredSize(new java.awt.Dimension(4, 64));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel12, java.awt.BorderLayout.LINE_START);

        jPanel8.add(jPanel11);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton4.setForeground(new java.awt.Color(147, 159, 171));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton4.setText("ffoksl o¿");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setIconTextGap(10);
        jButton4.setInheritsPopupMenu(true);
        jButton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton4FocusGained(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton4);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton5.setForeground(new java.awt.Color(147, 159, 171));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton5.setText("f;a");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setIconTextGap(10);
        jButton5.setInheritsPopupMenu(true);
        jButton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton5FocusGained(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton6.setForeground(new java.awt.Color(147, 159, 171));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton6.setText("fmdfydr");
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setIconTextGap(10);
        jButton6.setInheritsPopupMenu(true);
        jButton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton6FocusGained(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton7.setForeground(new java.awt.Color(147, 159, 171));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton7.setText("fvd,uhsÜ");
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setIconTextGap(10);
        jButton7.setInheritsPopupMenu(true);
        jButton7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton7FocusGained(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton7);

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 254, -1, -1));

        jPanel9.setMinimumSize(new java.awt.Dimension(100, 189));
        jPanel9.setPreferredSize(new java.awt.Dimension(269, 189));
        jPanel9.setLayout(new java.awt.GridLayout(3, 1));

        jPanel13.setBackground(new java.awt.Color(236, 243, 242));
        jPanel13.setForeground(new java.awt.Color(236, 243, 242));
        jPanel13.setMinimumSize(new java.awt.Dimension(100, 64));
        jPanel13.setPreferredSize(new java.awt.Dimension(269, 64));
        jPanel13.setVerifyInputWhenFocusTarget(false);
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel6.setBackground(new java.awt.Color(255, 102, 204));
        jLabel6.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(57, 117, 104));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/transaction.png"))); // NOI18N
        jLabel6.setText(".kqfokq");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel6.setDoubleBuffered(true);
        jLabel6.setIconTextGap(8);
        jLabel6.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel6.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel6.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel13.add(jLabel6, java.awt.BorderLayout.CENTER);

        jLabel7.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(57, 117, 104));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/down_ar.png"))); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel13.add(jLabel7, java.awt.BorderLayout.LINE_END);

        jPanel14.setBackground(new java.awt.Color(57, 117, 104));
        jPanel14.setMinimumSize(new java.awt.Dimension(4, 26));
        jPanel14.setPreferredSize(new java.awt.Dimension(4, 64));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel14, java.awt.BorderLayout.LINE_START);

        jPanel9.add(jPanel13);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton8.setForeground(new java.awt.Color(147, 159, 171));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton8.setText("yr uqo,a");
        jButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setIconTextGap(10);
        jButton8.setInheritsPopupMenu(true);
        jButton8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton8FocusGained(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton8);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton9.setForeground(new java.awt.Color(147, 159, 171));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton9.setText("w;a;sldrï");
        jButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setIconTextGap(10);
        jButton9.setInheritsPopupMenu(true);
        jButton9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton9FocusGained(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9);

        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 572, -1, -1));

        jPanel10.setMinimumSize(new java.awt.Dimension(100, 126));
        jPanel10.setPreferredSize(new java.awt.Dimension(269, 126));
        jPanel10.setLayout(new java.awt.GridLayout(2, 1));

        jPanel15.setBackground(new java.awt.Color(236, 243, 242));
        jPanel15.setForeground(new java.awt.Color(236, 243, 242));
        jPanel15.setMinimumSize(new java.awt.Dimension(100, 64));
        jPanel15.setPreferredSize(new java.awt.Dimension(269, 64));
        jPanel15.setVerifyInputWhenFocusTarget(false);
        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel8.setBackground(new java.awt.Color(255, 102, 204));
        jLabel8.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(57, 117, 104));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/report.png"))); // NOI18N
        jLabel8.setText("jd¾;d");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel8.setDoubleBuffered(true);
        jLabel8.setIconTextGap(8);
        jLabel8.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel8.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel15.add(jLabel8, java.awt.BorderLayout.CENTER);

        jLabel9.setFont(new java.awt.Font("FMMalithi", 1, 27)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(57, 117, 104));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/down_ar.png"))); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel15.add(jLabel9, java.awt.BorderLayout.LINE_END);

        jPanel16.setBackground(new java.awt.Color(57, 117, 104));
        jPanel16.setMinimumSize(new java.awt.Dimension(4, 26));
        jPanel16.setPreferredSize(new java.awt.Dimension(4, 64));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel16, java.awt.BorderLayout.LINE_START);

        jPanel10.add(jPanel15);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("FMMalithi", 1, 25)); // NOI18N
        jButton10.setForeground(new java.awt.Color(147, 159, 171));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circle.png"))); // NOI18N
        jButton10.setText("o¿ ì,am;a");
        jButton10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 35, 1, 1));
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setIconTextGap(10);
        jButton10.setInheritsPopupMenu(true);
        jButton10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton10FocusGained(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton10);

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 762, -1, -1));

        jScrollPane2.setViewportView(jPanel4);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setMinimumSize(new java.awt.Dimension(991, 986));
        jPanel3.setPreferredSize(new java.awt.Dimension(991, 986));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        cardPanel.setMinimumSize(new java.awt.Dimension(991, 986));
        cardPanel.setPreferredSize(new java.awt.Dimension(991, 986));
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
        jPanel20.setMinimumSize(new java.awt.Dimension(991, 240));
        jPanel20.setPreferredSize(new java.awt.Dimension(991, 240));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setMaximumSize(new java.awt.Dimension(2147483647, 92));
        jPanel24.setMinimumSize(new java.awt.Dimension(900, 92));
        jPanel24.setPreferredSize(new java.awt.Dimension(900, 92));
        jPanel24.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel23.setMinimumSize(new java.awt.Dimension(296, 92));
        jPanel23.setPreferredSize(new java.awt.Dimension(296, 92));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 15, 18));
        jLabel16.setText("m%jdyk wkqmd;h");
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

        jPanel24.add(jPanel23, java.awt.BorderLayout.LINE_START);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setMaximumSize(new java.awt.Dimension(32767, 92));
        jPanel25.setMinimumSize(new java.awt.Dimension(551, 92));
        jPanel25.setPreferredSize(new java.awt.Dimension(551, 92));
        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setMaximumSize(new java.awt.Dimension(32767, 20));
        jPanel26.setMinimumSize(new java.awt.Dimension(50, 20));
        jPanel26.setName(""); // NOI18N
        jPanel26.setPreferredSize(new java.awt.Dimension(50, 20));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel25.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setMinimumSize(new java.awt.Dimension(50, 52));
        jPanel28.setPreferredSize(new java.awt.Dimension(50, 52));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setMinimumSize(new java.awt.Dimension(50, 52));
        jPanel33.setPreferredSize(new java.awt.Dimension(50, 52));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
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

        jPanel25.add(jPanel28, java.awt.BorderLayout.PAGE_END);

        jPanel24.add(jPanel25, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel24, java.awt.BorderLayout.PAGE_END);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setMaximumSize(new java.awt.Dimension(2147483647, 92));
        jPanel29.setMinimumSize(new java.awt.Dimension(900, 92));
        jPanel29.setPreferredSize(new java.awt.Dimension(900, 92));
        jPanel29.setLayout(new java.awt.BorderLayout(58, 0));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setMaximumSize(new java.awt.Dimension(400, 92));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(15, 15, 18));
        jLabel19.setText("ud¾. wxlh");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel19.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel19.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel19.setPreferredSize(new java.awt.Dimension(296, 40));
        jLabel19.setVerifyInputWhenFocusTarget(false);
        jPanel30.add(jLabel19, java.awt.BorderLayout.PAGE_START);

        jTextField4.setBackground(new java.awt.Color(245, 245, 245));
        jTextField4.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(15, 15, 18));
        jTextField4.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField4.setMinimumSize(new java.awt.Dimension(296, 52));
        jTextField4.setPreferredSize(new java.awt.Dimension(296, 52));
        jPanel30.add(jTextField4, java.awt.BorderLayout.PAGE_END);

        jPanel29.add(jPanel30, java.awt.BorderLayout.LINE_START);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setMaximumSize(new java.awt.Dimension(32767, 92));
        jPanel31.setMinimumSize(new java.awt.Dimension(551, 92));
        jPanel31.setPreferredSize(new java.awt.Dimension(551, 92));
        jPanel31.setLayout(new java.awt.BorderLayout());

        jTextField5.setBackground(new java.awt.Color(245, 245, 245));
        jTextField5.setFont(new java.awt.Font("Iskoola Pota", 0, 24)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(15, 15, 18));
        jTextField5.setMaximumSize(new java.awt.Dimension(2147483647, 52));
        jTextField5.setMinimumSize(new java.awt.Dimension(530, 52));
        jTextField5.setPreferredSize(new java.awt.Dimension(530, 52));
        jPanel31.add(jTextField5, java.awt.BorderLayout.PAGE_END);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("FMMalithi", 0, 26)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 15, 18));
        jLabel20.setText("ud¾.fha ku");
        jLabel20.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 10, 1, new java.awt.Color(255, 255, 255)));
        jLabel20.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jLabel20.setMinimumSize(new java.awt.Dimension(296, 40));
        jLabel20.setPreferredSize(new java.awt.Dimension(296, 40));
        jPanel31.add(jLabel20, java.awt.BorderLayout.CENTER);

        jPanel29.add(jPanel31, java.awt.BorderLayout.CENTER);

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
        jLabel15.setText("m%jdyk .dia;=");
        jPanel21.add(jLabel15);

        jPanel17.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(23, 23, 23, 23, new java.awt.Color(245, 245, 245)));
        jPanel22.setMinimumSize(new java.awt.Dimension(1011, 547));
        jPanel22.setPreferredSize(new java.awt.Dimension(1011, 547));
        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.LINE_AXIS));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setMinimumSize(new java.awt.Dimension(950, 526));
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
        jPanel38.getAccessibleContext().setAccessibleName("");

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
        jPanel18.setPreferredSize(new java.awt.Dimension(991, 50));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("FMMalithi", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(73, 80, 87));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("m%jdyk wkqmd;h");
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

        jPanel3.add(cardPanel);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Change the text and icon of jButton3 when jButton1 is clicked
        new Home();
        cardPanel.removeAll();
        cardPanel.add(card1);
        cardPanel.revalidate();
        cardPanel.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Suppliers suppliers = new Suppliers();
        cardPanel.removeAll();
        cardPanel.add(suppliers);
        cardPanel.revalidate();
        cardPanel.repaint();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        LeafRate leafRate = new LeafRate();
        cardPanel.removeAll();
        cardPanel.add(leafRate);
        cardPanel.revalidate();
        cardPanel.repaint();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DailyLeaf leafRate = new DailyLeaf();
        cardPanel.removeAll();
        cardPanel.add(leafRate);
        cardPanel.revalidate();
        cardPanel.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        // TODO add your handling code here:
        focusSideBarButton(1);
    }//GEN-LAST:event_jButton1FocusGained

    private void jButton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusGained
        // TODO add your handling code here:
        focusSideBarButton(2);
    }//GEN-LAST:event_jButton2FocusGained

    private void jButton3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton3FocusGained
        // TODO add your handling code here:
        focusSideBarButton(3);
    }//GEN-LAST:event_jButton3FocusGained

    private void jButton4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton4FocusGained
        // TODO add your handling code here:
        focusSideBarButton(4);
    }//GEN-LAST:event_jButton4FocusGained

    private void jButton5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusGained
        // TODO add your handling code here:
        focusSideBarButton(5);
    }//GEN-LAST:event_jButton5FocusGained

    private void jButton6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusGained
        // TODO add your handling code here:
        focusSideBarButton(6);
    }//GEN-LAST:event_jButton6FocusGained

    private void jButton8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusGained
        // TODO add your handling code here:
        focusSideBarButton(8);
    }//GEN-LAST:event_jButton8FocusGained

    private void jButton9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton9FocusGained
        // TODO add your handling code here:
        focusSideBarButton(9);
    }//GEN-LAST:event_jButton9FocusGained

    private void jButton10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton10FocusGained
        // TODO add your handling code here:
        focusSideBarButton(10);
    }//GEN-LAST:event_jButton10FocusGained

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusGained
        // TODO add your handling code here:
        focusSideBarButton(7);
    }//GEN-LAST:event_jButton7FocusGained

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

    private void jButtonNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNumActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        int row = jTable.getSelectedRow();

        // Check if the row index is valid
        if (row >= 0 && row < jTable.getRowCount()) {
            // Safely retrieve the values from the selected row
            jTextField4.setText(String.valueOf(jTable.getValueAt(row, 0)));
            jTextField5.setText(String.valueOf(jTable.getValueAt(row, 1)));
            jTextField1.setText(String.valueOf(jTable.getValueAt(row, 2)));

            jTextField4.setEditable(false);
            setUpdateButton();
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void setUpdateButton(){
        // Update button properties
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png")));
        jButton15.setText("fjkia'"); // Assuming you meant "Update" instead of "fjkia"
        jButton15.setBackground(new Color(30, 30, 30));
    }

    private void clear() {

        jTextField4.setText("");
        jTextField5.setText("");
        jTextField1.setText("");

    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        String id = jTextField4.getText().trim();
        String name = jTextField5.getText().trim();
        String rate = jTextField1.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ud¾. wxlh we;=,;a lrkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ud¾.fha ku we;=,;a lrkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (rate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "m%jdyk wkqmd;h we;=,;a lrkak", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Validate if rate is a valid number (either integer or float)
                Float.parseFloat(rate); // Attempt to parse rate as a float

                // First check if the ID already exists

                if ("iqrlskak".equals(jButton15.getText())) {
                    try {

                        TransportService transportService = new TransportService();
                        int ftransport = transportService.findById(id);

                        if (ftransport > 0) {
                            // ID exists, show error message
                            JOptionPane.showMessageDialog(this, "fuu wxlh oekgu;a mj;S' lreKdlr fjk;a wxlhla we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            // Create a Transport object and set the values
                            Transport transport = new Transport();
                            transport.setId(id);
                            transport.setRoad_name(name);
                            transport.setTransport_rate(rate);

                            // Call the save method in transportService
                            transportService.save(transport);
                            String searchText = jTextField2.getText();
                            if(searchText.equals("ටයිප් කරන්න...")){
                                loadTable();
                            } else{
                                searchTable(searchText);
                            }
                            clear();

                        }

                    } catch (Exception e) {
                        logger.log(Level.WARNING, "Home", e);
                        e.printStackTrace();

                    }
                } else {

                    int row = jTable.getSelectedRow();

                    if (row != -1) {  // Ensure a row is selected

                        try {

                            Transport transport = new Transport();
                            transport.setId(id);
                            transport.setRoad_name(name);
                            transport.setTransport_rate(rate);

                            // Call the update method in transportService
                            TransportService transportService = new TransportService();
                            transportService.update(transport);

                        } catch (Exception e) {
                            logger.log(Level.WARNING, "Home", e);
                            e.printStackTrace();

                        }

                        clear();
                        jTextField4.setEditable(true);

                        // Unselect the row if any was selected
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
                JOptionPane.showMessageDialog(this, "lreKdlr m%jdyk wkqmd;h wxlhla f,i we;=,;a lrkak'", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton15ActionPerformed

    private void setSaveButton(){
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png")));
        jButton15.setText("iqrlskak");
        jButton15.setBackground(new Color(57, 117, 104));
    }

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jTableKeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        searchTable(jTextField2.getText().trim());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        String id = jTextField4.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "lreKdlr ud¾.hla f;darkak'", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int row = jTable.getSelectedRow();

            setUpdateButton();

            try {

                // Call the delete method in transportService
                TransportService transportService = new TransportService();
                transportService.delete(id);

            } catch (Exception e) {
                logger.log(Level.WARNING, "Home", e);
                e.printStackTrace();

            }

            clear();
            if (row != -1) {
                // Unselect the row
                jTable.clearSelection();
            }
            setSaveButton();
            String searchText = jTextField2.getText();
            if(searchText.equals("ටයිප් කරන්න...")){
                loadTable();
            } else{
                searchTable(searchText);
            }
        }

    }//GEN-LAST:event_jButton16ActionPerformed

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
        jTextField4.setEditable(true);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        int radius = 15;

        UIManager.put("Button.arc", radius);
        UIManager.put("Component.arc", radius);
        UIManager.put("TextComponent.arc", radius);
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(57, 117, 104)));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "FMMalithi", Font.PLAIN, 18)));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    new Home().setVisible(true);
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card1;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonFirst;
    private javax.swing.JButton jButtonLast;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonNum;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JComboBox jComboBoxPage;
    private javax.swing.JLabel jEntriesLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelStatusHalaman;
    private javax.swing.JLabel jLabelTotalData;
    private javax.swing.JLabel jLabelTotalData2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jShowLabel;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
