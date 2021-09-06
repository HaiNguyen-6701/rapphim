/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.*;
import BUS.khachhangBUS;
import BUS.phimBUS;
import BUS.nvBUS;
import BUS.taiKhoanBUS;
import BUS.hoadonBUS;
import BUS.chiTietHoaDonBUS;

import DAO.phimDAO;
import DAO.hoadonDAO;
import DAO.khachhangDAO;
import DTO.Phim;
import DTO.khach_hang;
import DTO.taiKhoan;
import DTO.Hoadon;
import DTO.chi_tiet_hoa_don;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import GUI.Nhanvien.JPanelGradiant1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
/**
 *
 * @author USer
 */
public class Nhanvien extends javax.swing.JFrame {

    DefaultTableModel modelqlKHNV = new DefaultTableModel();
    ArrayList<khach_hang> listKH = new khachhangBUS().getList();
    DefaultTableModel modelqlPhimNV = new DefaultTableModel();
    ArrayList<Phim> listPhim = new phimBUS().getList();
    
    ArrayList<chi_tiet_hoa_don> listcthd = new chiTietHoaDonBUS().getList();
    DefaultTableModel modelcthd = new DefaultTableModel();
    int[] count = new int[71];
    int select=0;
    public Nhanvien() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initComponents();
        pnHienThiNV.removeAll();
        pnHienThiNV.add(panelDatve);
        showKH();
        showPhim();
        //selected rshowPhimDatveow
        ListSelectionModel modelqlPhimDatve = tbPhimDatve.getSelectionModel();
        modelqlPhimDatve.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!modelqlPhimDatve.isSelectionEmpty()) {
                    int selectedRow = modelqlPhimDatve.getMinSelectionIndex();
                    //JOptionPane.showMessageDialog(null, "Selected Row"+selectedRow);
                    int t = tbPhimDatve.getSelectedRow();
                    String id = (String) tbPhimDatve.getValueAt(t, 0);
                    jTextField3.setText(id);
                    JdialogPhim.setVisible(false);
                }
            }
        });

        for (int i = 1; i < 71; i++) {
            count[i] = 1;
        }

        setLocationRelativeTo(null);
        
        
    }

    class JPanelGradiant1 extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(0, 0, 0);
            Color color2 = new Color(255, 102, 102);

            GradientPaint gp = new GradientPaint(0, 0, color1, 170, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    class JPanelGradiant2 extends JTextField{

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(0, 0, 0);
            Color color2 = new Color(255, 102, 102);

            GradientPaint gp = new GradientPaint(0, 0, color1, 170, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
    public void showPhim() {
        Vector header = new Vector();
        header.add("Id Phim");
        header.add("Tên Phim");
        header.add("Thể loại");
        header.add("Thời lượng");
        header.add("Ngôn ngữ");
        header.add("Diễn viên");
        header.add("Quốc gia");
        header.add("Nhà sản xuất");
        header.add("Ngày chiếu");
        header.add("Giá vé");

        if (modelqlPhimNV.getRowCount() == 0) {

            modelqlPhimNV = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listPhim.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listPhim.get(i).getIdPhim());
            row[1] = (listPhim.get(i).getTenPhim());
            row[2] = (listPhim.get(i).getTheloai());
            row[3] = (listPhim.get(i).getThoiLuong());
            row[4] = (listPhim.get(i).getNgonNgu());
            row[5] = (listPhim.get(i).getDienVien());
            row[6] = (listPhim.get(i).getQuocgia());
            row[7] = (listPhim.get(i).getNhaSanXuat());
            row[8] = (listPhim.get(i).getNgayCongChieu());
            row[9] = (listPhim.get(i).getGiave());

            modelqlPhimNV.addRow(row);

        }
        tblPhimNV.setModel(modelqlPhimNV);
    }

    public void showKH() {
        Vector header = new Vector();
        header.add("ID Khách Hàng");
        header.add("Họ Tên");
        header.add("SĐT");
        header.add("Email");
        header.add("Giới tính");
        header.add("Mật khẩu");

        if (modelqlKHNV.getRowCount() == 0) {

            modelqlKHNV = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listKH.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listKH.get(i).getIdKhachHang());
            row[1] = (listKH.get(i).getHoTen());
            row[2] = (listKH.get(i).getSoDienThoai());
            row[3] = (listKH.get(i).getEmail());
            row[4] = (listKH.get(i).getGioiTinh());
            row[5] = (listKH.get(i).getMatKhau());

            modelqlKHNV.addRow(row);

        }
        tblKHNV.setModel(modelqlKHNV);
    }
public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // ten Cot
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < table.getRowCount(); j++) {
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JdialogPhim = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPhimDatve = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        Jdialogghe = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jPanel74 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jPanel75 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jPanel76 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        EditKH = new javax.swing.JDialog();
        jPanel79 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        AddPhim = new javax.swing.JDialog();
        jPanel81 = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        AddKH = new javax.swing.JDialog();
        jPanel83 = new javax.swing.JPanel();
        jPanel84 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        EditPhim = new javax.swing.JDialog();
        jPanel85 = new javax.swing.JPanel();
        jPanel86 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        RootNV = new javax.swing.JPanel();
        MenuNV = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pnDatVe = new javax.swing.JPanel();
        lbDatVe = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pnPhimNV = new javax.swing.JPanel();
        lbPhim = new javax.swing.JLabel();
        pnKHNV = new javax.swing.JPanel();
        lbKH = new javax.swing.JLabel();
        pnHienThiNV = new javax.swing.JPanel();
        panelDatve = new JPanelGradiant1();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel77 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        PanelPhimNV = new JPanelGradiant1();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhimNV = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        PanelKHNV = new JPanelGradiant1();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKHNV = new javax.swing.JTable();
        jPanel78 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();

        tbPhimDatve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Phim", "Tên phim", "Thể loại", "Diễn viên", "Thời lượng", "Giá vé", "Ngôn ngữ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPhimDatve);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JdialogPhimLayout = new javax.swing.GroupLayout(JdialogPhim.getContentPane());
        JdialogPhim.getContentPane().setLayout(JdialogPhimLayout);
        JdialogPhimLayout.setHorizontalGroup(
            JdialogPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JdialogPhimLayout.createSequentialGroup()
                .addGroup(JdialogPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JdialogPhimLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JdialogPhimLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JdialogPhimLayout.setVerticalGroup(
            JdialogPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JdialogPhimLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(JdialogPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Jdialogghe.setBackground(new java.awt.Color(237, 237, 237));

        jPanel2.setBackground(new java.awt.Color(237, 237, 237));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("G");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("F");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("D");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("E");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("B");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("C");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("A");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("10");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("9");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("7");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("8");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("5");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("6");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("3");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("4");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("1");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("2");

        jPanel3.setBackground(new java.awt.Color(237, 237, 237));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("G10");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel94)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel94)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setText("G9");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel95)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel95)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("G7");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("G8");
        jLabel26.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel26)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("G5");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("G6");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel11.setBackground(new java.awt.Color(255, 204, 204));
        jPanel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("G3");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel13.setBackground(new java.awt.Color(255, 204, 204));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("G4");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel14.setBackground(new java.awt.Color(255, 204, 204));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("G2");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel15.setBackground(new java.awt.Color(255, 204, 204));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("G1");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel16.setBackground(new java.awt.Color(255, 204, 204));
        jPanel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("F1");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel34)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel34)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel17.setBackground(new java.awt.Color(255, 204, 204));
        jPanel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("F2");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel35)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel35)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel18.setBackground(new java.awt.Color(255, 204, 204));
        jPanel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("F3");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel19.setBackground(new java.awt.Color(255, 204, 204));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("F4");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel37)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel37)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel20.setBackground(new java.awt.Color(255, 204, 204));
        jPanel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("F5");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel38)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel21.setBackground(new java.awt.Color(255, 204, 204));
        jPanel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("F6");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel22.setBackground(new java.awt.Color(255, 204, 204));
        jPanel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("F7");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        jPanel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("F8");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel24.setBackground(new java.awt.Color(255, 204, 204));
        jPanel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel24MouseMoved(evt);
            }
        });
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel24MouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("F9");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel25.setBackground(new java.awt.Color(255, 204, 204));
        jPanel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel25MouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("F10");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel43)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel26.setBackground(new java.awt.Color(255, 204, 204));
        jPanel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel26MouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("E10");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel44)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel44)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel27.setBackground(new java.awt.Color(255, 204, 204));
        jPanel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel27MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("E9");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel45)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel45)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel28.setBackground(new java.awt.Color(255, 204, 204));
        jPanel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel28MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("E8");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel46)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel46)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel29.setBackground(new java.awt.Color(255, 204, 204));
        jPanel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel29MouseClicked(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("E7");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel30.setBackground(new java.awt.Color(255, 204, 204));
        jPanel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel30MouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("E6");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel31.setBackground(new java.awt.Color(255, 204, 204));
        jPanel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel31MouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("E5");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel32.setBackground(new java.awt.Color(255, 204, 204));
        jPanel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel32MouseClicked(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("E4");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel33.setBackground(new java.awt.Color(255, 204, 204));
        jPanel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel33MouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("E3");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel34.setBackground(new java.awt.Color(255, 204, 204));
        jPanel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel34MouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("E2");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel52)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel52)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel35.setBackground(new java.awt.Color(255, 204, 204));
        jPanel35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel35MouseClicked(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("E1");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel53)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel53)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel36.setBackground(new java.awt.Color(255, 204, 204));
        jPanel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel36MouseClicked(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("D1");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel54)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel54)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel37.setBackground(new java.awt.Color(255, 204, 204));
        jPanel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel37MouseClicked(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("D2");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel55)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel55)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel38.setBackground(new java.awt.Color(255, 204, 204));
        jPanel38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel38MouseClicked(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("D3");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel38Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel38Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel56)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel39.setBackground(new java.awt.Color(255, 204, 204));
        jPanel39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel39MouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("D4");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel40.setBackground(new java.awt.Color(255, 204, 204));
        jPanel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel40MouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setText("D5");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel58)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel58)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel41.setBackground(new java.awt.Color(255, 204, 204));
        jPanel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel41MouseClicked(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("D6");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel59)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel59)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel42.setBackground(new java.awt.Color(255, 204, 204));
        jPanel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel42MouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText("D7");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel60)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel60)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel43.setBackground(new java.awt.Color(255, 204, 204));
        jPanel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel43MouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("D8");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel61)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel61)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel44.setBackground(new java.awt.Color(255, 204, 204));
        jPanel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel44MouseClicked(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("D9");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel62)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel45.setBackground(new java.awt.Color(255, 204, 204));
        jPanel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel45MouseClicked(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("D10");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel46.setBackground(new java.awt.Color(255, 204, 204));
        jPanel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel46MouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("C10");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel64)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel47.setBackground(new java.awt.Color(255, 204, 204));
        jPanel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel47MouseClicked(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setText("C9");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel65)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel65)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel48.setBackground(new java.awt.Color(255, 204, 204));
        jPanel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel48MouseClicked(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setText("C8");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel48Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel66)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel48Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel66)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel49.setBackground(new java.awt.Color(255, 204, 204));
        jPanel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel49MouseClicked(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setText("C7");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel49Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel67)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel49Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel67)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel50.setBackground(new java.awt.Color(255, 204, 204));
        jPanel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel50MouseClicked(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setText("C6");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel68)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel68)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel51.setBackground(new java.awt.Color(255, 204, 204));
        jPanel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel51MouseClicked(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel69.setText("C5");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel51Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel69)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel51Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel69)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel52.setBackground(new java.awt.Color(255, 204, 204));
        jPanel52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel52MouseClicked(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel70.setText("C4");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel70)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel70)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel53.setBackground(new java.awt.Color(255, 204, 204));
        jPanel53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel53MouseClicked(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel71.setText("C3");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel53Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel71)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel53Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel71)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel54.setBackground(new java.awt.Color(255, 204, 204));
        jPanel54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel54MouseClicked(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setText("C2");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel54Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel72)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel54Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel72)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel55.setBackground(new java.awt.Color(255, 204, 204));
        jPanel55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel55MouseClicked(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setText("C1");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel73)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel73)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel56.setBackground(new java.awt.Color(255, 204, 204));
        jPanel56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel56MouseClicked(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setText("B1");

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel56Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel74)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel56Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel74)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel57.setBackground(new java.awt.Color(255, 204, 204));
        jPanel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel57MouseClicked(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setText("B2");

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel75)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel75)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel58.setBackground(new java.awt.Color(255, 204, 204));
        jPanel58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel58MouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel76.setText("B3");

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel58Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel58Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel76)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel59.setBackground(new java.awt.Color(255, 204, 204));
        jPanel59.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel59MouseClicked(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setText("B4");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel59Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel59Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel77)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel60.setBackground(new java.awt.Color(255, 204, 204));
        jPanel60.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel60MouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setText("B5");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel60Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel78)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel60Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel78)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel61.setBackground(new java.awt.Color(255, 204, 204));
        jPanel61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel61MouseClicked(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel79.setText("B6");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel61Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel79)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel61Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel79)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel62.setBackground(new java.awt.Color(255, 204, 204));
        jPanel62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel62MouseClicked(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel80.setText("B7");

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel62Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel80)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel62Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel80)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel63.setBackground(new java.awt.Color(255, 204, 204));
        jPanel63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel63MouseClicked(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel81.setText("B8");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel63Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel81)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel63Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel81)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel64.setBackground(new java.awt.Color(255, 204, 204));
        jPanel64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel64MouseClicked(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setText("B9");

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel64Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel82)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel64Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel82)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel65.setBackground(new java.awt.Color(255, 204, 204));
        jPanel65.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel65MouseClicked(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel83.setText("B10");

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel65Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel83)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel65Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel83)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel66.setBackground(new java.awt.Color(255, 204, 204));
        jPanel66.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel66MouseClicked(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setText("A10");

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel66Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel84)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel66Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel84)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel67.setBackground(new java.awt.Color(255, 204, 204));
        jPanel67.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel67MouseClicked(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel85.setText("A9");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel67Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel85)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel67Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel85)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel68.setBackground(new java.awt.Color(255, 204, 204));
        jPanel68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel68MouseClicked(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setText("A8");

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel68Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel86)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel68Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel86)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel69.setBackground(new java.awt.Color(255, 204, 204));
        jPanel69.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel69MouseClicked(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel87.setText("A7");

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel69Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel87)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel69Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel87)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel70.setBackground(new java.awt.Color(255, 204, 204));
        jPanel70.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel70MouseClicked(evt);
            }
        });

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel88.setText("A6");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel88)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel88)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel71.setBackground(new java.awt.Color(255, 204, 204));
        jPanel71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel71MouseClicked(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setText("A5");

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel89)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel89)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel72.setBackground(new java.awt.Color(255, 204, 204));
        jPanel72.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel72MouseClicked(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setText("A4");

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel90)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel90)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel73.setBackground(new java.awt.Color(255, 204, 204));
        jPanel73.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel73MouseClicked(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel91.setText("A3");

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel91)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel91)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel74.setBackground(new java.awt.Color(255, 204, 204));
        jPanel74.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel74MouseClicked(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel92.setText("A2");

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel74Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel92)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel74Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel92)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel75.setBackground(new java.awt.Color(255, 204, 204));
        jPanel75.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel75MouseClicked(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setText("A1");

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel75Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel93)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel75Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel93)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)))
        );

        jPanel76.setBackground(new java.awt.Color(51, 0, 51));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Màn hình");

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel76Layout.createSequentialGroup()
                .addContainerGap(307, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JdialoggheLayout = new javax.swing.GroupLayout(Jdialogghe.getContentPane());
        Jdialogghe.getContentPane().setLayout(JdialoggheLayout);
        JdialoggheLayout.setHorizontalGroup(
            JdialoggheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JdialoggheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JdialoggheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JdialoggheLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54))
                    .addGroup(JdialoggheLayout.createSequentialGroup()
                        .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        JdialoggheLayout.setVerticalGroup(
            JdialoggheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JdialoggheLayout.createSequentialGroup()
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel79.setBackground(new java.awt.Color(253, 248, 248));

        jPanel80.setBackground(new java.awt.Color(248, 227, 227));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("Sửa thông tin Khách Hàng");

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField25.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khách Hàng"));

        jTextField26.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Tên"));

        jTextField27.setBorder(javax.swing.BorderFactory.createTitledBorder("SĐT"));

        jButton12.setBackground(new java.awt.Color(255, 204, 51));
        jButton12.setText("Sửa");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField28.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField29.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        jTextField30.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField28, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(38, 38, 38))
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EditKHLayout = new javax.swing.GroupLayout(EditKH.getContentPane());
        EditKH.getContentPane().setLayout(EditKHLayout);
        EditKHLayout.setHorizontalGroup(
            EditKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditKHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditKHLayout.setVerticalGroup(
            EditKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel81.setBackground(new java.awt.Color(253, 248, 248));

        jPanel82.setBackground(new java.awt.Color(248, 227, 227));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("Thêm Phim");

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel100, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField12.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Phim"));

        jTextField13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Phim"));

        jTextField14.setBorder(javax.swing.BorderFactory.createTitledBorder("Thời lượng"));

        jTextField15.setBorder(javax.swing.BorderFactory.createTitledBorder("Thể loại"));

        jButton8.setBackground(new java.awt.Color(255, 204, 51));
        jButton8.setText("Thêm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField16.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngôn ngữ"));

        jTextField17.setBorder(javax.swing.BorderFactory.createTitledBorder("Diễn viên"));

        jTextField18.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà sản xuất"));

        jTextField19.setBorder(javax.swing.BorderFactory.createTitledBorder("Quốc gia"));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jTextField20.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày chiếu"));

        jTextField21.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá vé"));

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField14)
                    .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField21))
                .addGap(38, 38, 38))
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout AddPhimLayout = new javax.swing.GroupLayout(AddPhim.getContentPane());
        AddPhim.getContentPane().setLayout(AddPhimLayout);
        AddPhimLayout.setHorizontalGroup(
            AddPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddPhimLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddPhimLayout.setVerticalGroup(
            AddPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel83.setBackground(new java.awt.Color(253, 248, 248));

        jPanel84.setBackground(new java.awt.Color(248, 227, 227));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("Thêm Khách Hàng");

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField22.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khách Hàng"));

        jTextField23.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Tên"));

        jTextField24.setBorder(javax.swing.BorderFactory.createTitledBorder("SĐT"));

        jButton11.setBackground(new java.awt.Color(255, 204, 51));
        jButton11.setText("Thêm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTextField31.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField34.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        jTextField35.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));
        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(38, 38, 38))
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddKHLayout = new javax.swing.GroupLayout(AddKH.getContentPane());
        AddKH.getContentPane().setLayout(AddKHLayout);
        AddKHLayout.setHorizontalGroup(
            AddKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddKHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddKHLayout.setVerticalGroup(
            AddKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel85.setBackground(new java.awt.Color(253, 248, 248));

        jPanel86.setBackground(new java.awt.Color(248, 227, 227));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("Sửa thông tin phim");

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField40.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Phim"));

        jTextField41.setBorder(javax.swing.BorderFactory.createTitledBorder("Thể loại"));

        jTextField42.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngôn ngữ"));

        jButton17.setBackground(new java.awt.Color(255, 204, 51));
        jButton17.setText("Sửa");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTextField43.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên phim"));

        jTextField44.setBorder(javax.swing.BorderFactory.createTitledBorder("Thời lượng"));

        jTextField45.setBorder(javax.swing.BorderFactory.createTitledBorder("Diễn viên"));
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });

        jTextField46.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà sản xuất"));

        jTextField47.setBorder(javax.swing.BorderFactory.createTitledBorder("Quốc gia"));

        jTextField48.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày công chiếu"));

        jTextField49.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá vé"));
        jTextField49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField48, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField40, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField43, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(38, 38, 38))))
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addComponent(jPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout EditPhimLayout = new javax.swing.GroupLayout(EditPhim.getContentPane());
        EditPhim.getContentPane().setLayout(EditPhimLayout);
        EditPhimLayout.setHorizontalGroup(
            EditPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditPhimLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditPhimLayout.setVerticalGroup(
            EditPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        MenuNV.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nhân viên");

        pnDatVe.setBackground(new java.awt.Color(255, 102, 102));

        lbDatVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbDatVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDatVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-movie-ticket-100.png"))); // NOI18N
        lbDatVe.setText("Đặt vé        ");
        lbDatVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDatVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDatVeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbDatVeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbDatVeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnDatVeLayout = new javax.swing.GroupLayout(pnDatVe);
        pnDatVe.setLayout(pnDatVeLayout);
        pnDatVeLayout.setHorizontalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDatVe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnDatVeLayout.setVerticalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDatVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-thick-long-right-arrow-256.png"))); // NOI18N
        jButton5.setText("Thoát");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        pnPhimNV.setBackground(new java.awt.Color(255, 102, 102));

        lbPhim.setBackground(new java.awt.Color(255, 51, 51));
        lbPhim.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-film-reel-64.png"))); // NOI18N
        lbPhim.setText("Phim           ");
        lbPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPhimMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbPhimMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbPhimMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnPhimNVLayout = new javax.swing.GroupLayout(pnPhimNV);
        pnPhimNV.setLayout(pnPhimNVLayout);
        pnPhimNVLayout.setHorizontalGroup(
            pnPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhimNVLayout.setVerticalGroup(
            pnPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnKHNV.setBackground(new java.awt.Color(255, 102, 102));

        lbKH.setBackground(new java.awt.Color(255, 51, 51));
        lbKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-customer-insight-64.png"))); // NOI18N
        lbKH.setText("Khách hàng");
        lbKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbKHMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbKHMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbKHMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnKHNVLayout = new javax.swing.GroupLayout(pnKHNV);
        pnKHNV.setLayout(pnKHNVLayout);
        pnKHNVLayout.setHorizontalGroup(
            pnKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnKHNVLayout.setVerticalGroup(
            pnKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MenuNVLayout = new javax.swing.GroupLayout(MenuNV);
        MenuNV.setLayout(MenuNVLayout);
        MenuNVLayout.setHorizontalGroup(
            MenuNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnDatVe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnPhimNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnKHNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuNVLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton5)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        MenuNVLayout.setVerticalGroup(
            MenuNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuNVLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(pnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnPhimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnKHNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnHienThiNV.setLayout(null);

        panelDatve.setBackground(new java.awt.Color(255, 255, 255));
        panelDatve.setMinimumSize(new java.awt.Dimension(990, 670));
        panelDatve.setPreferredSize(new java.awt.Dimension(990, 670));

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đặt vé");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        tblhoadon.setBackground(new java.awt.Color(255, 102, 102));
        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Hóa đơn", "ID Phim", "ID Khách hàng", "ID Ghế", "ID Phòng", "ID Nhân viên", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhoadon.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblhoadon);

        jButton3.setText("Thanh toán");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel77.setBackground(new java.awt.Color(255, 102, 102));

        jTextField1.setBackground(new java.awt.Color(255, 102, 102));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Id Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(255, 102, 102));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jTextField3.setBackground(new java.awt.Color(255, 102, 102));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Phim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setBackground(new java.awt.Color(255, 102, 102));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jTextField5.setBackground(new java.awt.Color(255, 102, 102));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Ghế", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("...");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField6.setBackground(new java.awt.Color(255, 102, 102));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 192, 192));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField8.setBackground(new java.awt.Color(255, 102, 102));
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Id Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField10.setBackground(new java.awt.Color(255, 102, 102));
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addComponent(jTextField4)
                        .addComponent(jTextField3)
                        .addComponent(jTextField2)
                        .addComponent(jTextField1))
                    .addComponent(jTextField8)
                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Tổng giá :");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Ngày lập :");

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatveLayout = new javax.swing.GroupLayout(panelDatve);
        panelDatve.setLayout(panelDatveLayout);
        panelDatveLayout.setHorizontalGroup(
            panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelDatveLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatveLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatveLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
            .addGroup(panelDatveLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDatveLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatveLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDatveLayout.setVerticalGroup(
            panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatveLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatveLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        pnHienThiNV.add(panelDatve);
        panelDatve.setBounds(0, 0, 990, 710);

        PanelPhimNV.setBackground(new java.awt.Color(204, 204, 204));

        tblPhimNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Phim", "Tên Phim", "Thể loại", "Thời lượng", "Ngôn ngữ", "Diễn viên", "Quốc gia", "Nhà sản xuất", "Ngày chiếu", "Giá vé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblPhimNV);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("QUẢN LÝ PHIM");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton38.setText("Thêm");
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton39.setText("Sửa");
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton40.setText("Xóa");
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton41.setText("Làm mới");
        jButton41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Tìm kiếm nhân viên:");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Tìm kiếm phim:");

        jTextField32.setToolTipText("");
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton46.setText("Excel");
        jButton46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPhimNVLayout = new javax.swing.GroupLayout(PanelPhimNV);
        PanelPhimNV.setLayout(PanelPhimNVLayout);
        PanelPhimNVLayout.setHorizontalGroup(
            PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPhimNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelPhimNVLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPhimNVLayout.createSequentialGroup()
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelPhimNVLayout.createSequentialGroup()
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPhimNVLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel96)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        PanelPhimNVLayout.setVerticalGroup(
            PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhimNVLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel97)))
                .addGap(18, 18, 18)
                .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
            .addGroup(PanelPhimNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPhimNVLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel96)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnHienThiNV.add(PanelPhimNV);
        PanelPhimNV.setBounds(0, 0, 950, 710);

        PanelKHNV.setBackground(new java.awt.Color(204, 204, 204));

        tblKHNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Khách hàng", "Họ Tên", "SĐT", "Email", "Giới tính", "Mật khẩu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblKHNV);

        jPanel78.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton42.setText("Thêm");
        jButton42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton43.setText("Sửa");
        jButton43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton44.setText("Xóa");
        jButton44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton45.setText("Làm mới");
        jButton45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Tìm kiếm khách hàng:");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton47.setText("Excel");
        jButton47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKHNVLayout = new javax.swing.GroupLayout(PanelKHNV);
        PanelKHNV.setLayout(PanelKHNVLayout);
        PanelKHNVLayout.setHorizontalGroup(
            PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelKHNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelKHNVLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelKHNVLayout.createSequentialGroup()
                                .addComponent(jLabel98)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelKHNVLayout.createSequentialGroup()
                                .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jButton45)))
                        .addGap(62, 96, Short.MAX_VALUE)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        PanelKHNVLayout.setVerticalGroup(
            PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKHNVLayout.createSequentialGroup()
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addGroup(PanelKHNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
        );

        pnHienThiNV.add(PanelKHNV);
        PanelKHNV.setBounds(0, 0, 950, 710);

        javax.swing.GroupLayout RootNVLayout = new javax.swing.GroupLayout(RootNV);
        RootNV.setLayout(RootNVLayout);
        RootNVLayout.setHorizontalGroup(
            RootNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RootNVLayout.createSequentialGroup()
                .addComponent(MenuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnHienThiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
        );
        RootNVLayout.setVerticalGroup(
            RootNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHienThiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
            .addComponent(MenuNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RootNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RootNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDatVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDatVeMouseClicked
        // TODO add your handling code here:
        pnHienThiNV.removeAll();
        pnHienThiNV.add(panelDatve);
        pnHienThiNV.repaint();
        pnDatVe.setBackground(Color.white);
        pnKHNV.setBackground(new Color(255, 102, 102));
        pnPhimNV.setBackground(new Color(255, 102, 102));

    }//GEN-LAST:event_lbDatVeMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel4.setBackground(Color.red);
        } else {
            jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        }

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel7.setBackground(Color.red);
        } else {
            jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel6.setBackground(Color.red);
        } else {
            jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel9.setBackground(Color.red);
        } else {
            jPanel9.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel8.setBackground(Color.red);
        } else {
            jPanel8.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel13.setBackground(Color.red);
        } else {
            jPanel13.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel11.setBackground(Color.red);
        } else {
            jPanel11.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel14.setBackground(Color.red);
        } else {
            jPanel14.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel15.setBackground(Color.red);
        } else {
            jPanel15.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel25.setBackground(Color.red);
        } else {
            jPanel25.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel25MouseClicked

    private void jPanel24MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel24MouseMoved

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel24.setBackground(Color.red);
        } else {
            jPanel24.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel23.setBackground(Color.red);
        } else {
            jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel22.setBackground(Color.red);
        } else {
            jPanel22.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel22MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel21.setBackground(Color.red);
        } else {
            jPanel21.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel20.setBackground(Color.red);
        } else {
            jPanel20.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel19.setBackground(Color.red);
        } else {
            jPanel19.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel18.setBackground(Color.red);
        } else {
            jPanel18.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel17.setBackground(Color.red);
        } else {
            jPanel17.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel16.setBackground(Color.red);
        } else {
            jPanel16.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel26MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel26.setBackground(Color.red);
        } else {
            jPanel26.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel26MouseClicked

    private void jPanel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel27.setBackground(Color.red);
        } else {
            jPanel27.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel27MouseClicked

    private void jPanel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel28.setBackground(Color.red);
        } else {
            jPanel28.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel28MouseClicked

    private void jPanel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel29MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel29.setBackground(Color.red);
        } else {
            jPanel29.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel29MouseClicked

    private void jPanel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel30MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel30.setBackground(Color.red);
        } else {
            jPanel30.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel30MouseClicked

    private void jPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel31.setBackground(Color.red);
        } else {
            jPanel31.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel31MouseClicked

    private void jPanel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel32MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel32.setBackground(Color.red);
        } else {
            jPanel32.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel32MouseClicked

    private void jPanel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel33MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel33.setBackground(Color.red);
        } else {
            jPanel33.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel33MouseClicked

    private void jPanel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel34MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel34.setBackground(Color.red);
        } else {
            jPanel34.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel34MouseClicked

    private void jPanel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel35MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel35.setBackground(Color.red);
        } else {
            jPanel35.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel35MouseClicked

    private void jPanel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel45MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel45.setBackground(Color.red);
        } else {
            jPanel45.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel45MouseClicked

    private void jPanel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel44MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel44.setBackground(Color.red);
        } else {
            jPanel44.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel44MouseClicked

    private void jPanel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel43MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel43.setBackground(Color.red);
        } else {
            jPanel43.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel43MouseClicked

    private void jPanel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel42MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel42.setBackground(Color.red);
        } else {
            jPanel42.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel42MouseClicked

    private void jPanel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel41MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel41.setBackground(Color.red);
        } else {
            jPanel41.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel41MouseClicked

    private void jPanel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel40MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel40.setBackground(Color.red);
        } else {
            jPanel40.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel40MouseClicked

    private void jPanel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel39MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel39.setBackground(Color.red);
        } else {
            jPanel39.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel39MouseClicked

    private void jPanel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel38MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel38.setBackground(Color.red);
        } else {
            jPanel38.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel38MouseClicked

    private void jPanel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel37MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel37.setBackground(Color.red);
        } else {
            jPanel37.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel37MouseClicked

    private void jPanel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel36MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel36.setBackground(Color.red);
        } else {
            jPanel36.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel36MouseClicked

    private void jPanel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel46MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel46.setBackground(Color.red);
        } else {
            jPanel46.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel46MouseClicked

    private void jPanel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel47MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel47.setBackground(Color.red);
        } else {
            jPanel47.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel47MouseClicked

    private void jPanel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel48MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel48.setBackground(Color.red);
        } else {
            jPanel48.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel48MouseClicked

    private void jPanel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel49MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel49.setBackground(Color.red);
        } else {
            jPanel49.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel49MouseClicked

    private void jPanel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel50MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel50.setBackground(Color.red);
        } else {
            jPanel50.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel50MouseClicked

    private void jPanel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel51MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel51.setBackground(Color.red);
        } else {
            jPanel51.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel51MouseClicked

    private void jPanel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel52MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel52.setBackground(Color.red);
        } else {
            jPanel52.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel52MouseClicked

    private void jPanel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel53MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel53.setBackground(Color.red);
        } else {
            jPanel53.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel53MouseClicked

    private void jPanel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel54MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel54.setBackground(Color.red);
        } else {
            jPanel54.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel54MouseClicked

    private void jPanel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel55MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel55.setBackground(Color.red);
        } else {
            jPanel55.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel55MouseClicked

    private void jPanel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel65MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel65.setBackground(Color.red);
        } else {
            jPanel65.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel65MouseClicked

    private void jPanel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel64MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel64.setBackground(Color.red);
        } else {
            jPanel64.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel64MouseClicked

    private void jPanel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel63MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel63.setBackground(Color.red);
        } else {
            jPanel63.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel63MouseClicked

    private void jPanel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel62MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel62.setBackground(Color.red);
        } else {
            jPanel62.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel62MouseClicked

    private void jPanel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel61MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel61.setBackground(Color.red);
        } else {
            jPanel61.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel61MouseClicked

    private void jPanel60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel60MouseClicked
        // TODO add your handling code here:count[1]++;
        if (count[1] % 2 == 0) {
            jPanel60.setBackground(Color.red);
        } else {
            jPanel60.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel60MouseClicked

    private void jPanel59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel59MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel59.setBackground(Color.red);
        } else {
            jPanel59.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel59MouseClicked

    private void jPanel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel58MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel58.setBackground(Color.red);
        } else {
            jPanel58.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel58MouseClicked

    private void jPanel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel57MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel57.setBackground(Color.red);
        } else {
            jPanel57.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel57MouseClicked

    private void jPanel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel56MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel56.setBackground(Color.red);
        } else {
            jPanel56.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel56MouseClicked

    private void jPanel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel66MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel66.setBackground(Color.red);
        } else {
            jPanel66.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel66MouseClicked

    private void jPanel67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel67MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel67.setBackground(Color.red);
        } else {
            jPanel67.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel67MouseClicked

    private void jPanel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel68MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel68.setBackground(Color.red);
        } else {
            jPanel68.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel68MouseClicked

    private void jPanel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel69MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel69.setBackground(Color.red);
        } else {
            jPanel69.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel69MouseClicked

    private void jPanel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel70MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel70.setBackground(Color.red);
        } else {
            jPanel70.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel70MouseClicked

    private void jPanel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel71MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel71.setBackground(Color.red);
        } else {
            jPanel71.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel71MouseClicked

    private void jPanel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel72MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel72.setBackground(Color.red);
        } else {
            jPanel72.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel72MouseClicked

    private void jPanel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel73MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel73.setBackground(Color.red);
        } else {
            jPanel73.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel73MouseClicked

    private void jPanel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel74MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel74.setBackground(Color.red);
        } else {
            jPanel74.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel74MouseClicked

    private void jPanel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel75MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel75.setBackground(Color.red);
        } else {
            jPanel75.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel75MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Vector header = new Vector();
        header.add("ID Hóa đơn");
        header.add("ID Phim ");
        header.add("ID Khách hàng");
        header.add("ID Ghế");
        header.add("ID Phòng");
        header.add("ID Nhân viên");
        header.add("Số lượng");
        header.add("Giá");
        if (modelcthd.getRowCount() == 0) {

            modelcthd = new DefaultTableModel(header, 0);
        }

       
            Object[] row = new Object[10];
            row[0] = (jTextField1.getText());
            row[1] = (jTextField3.getText());
            row[2] = (jTextField2.getText());
            row[3] = (jTextField5.getText());
            row[4] = (jTextField4.getText());
            row[5] = (jTextField8.getText());
            row[6] = (jTextField6.getText());
            row[7] = (jTextField10.getText());
            modelcthd.addRow(row);

        
        tblhoadon.setModel(modelcthd );
        int sum=0;
        for(int i=0;i<modelcthd.getRowCount();i++)
        {
            sum+=Integer.valueOf((String) tblhoadon.getValueAt(i, 7));
        }
        jTextField9.setText((String.valueOf(sum)));
        
//        cthd.setIdHoaDon(Integer.parseInt(jTextField1.getText()));
//        cthd.setIdKhachhang(Integer.parseInt(jTextField2.getText()));
//        cthd.setIdPhim(Integer.parseInt(jTextField3.getText()));
//        cthd.setIdPhong(Integer.parseInt(jTextField4.getText()));
//        cthd.setIdGhe(jTextField5.getText());
//        cthd.setIdNhanvien(jTextField8.getText());
//        cthd.setSoLuong(jTextField6.getText());
        
//        khach_hang kh = new khach_hang();
//        kh.setIdKhachHang(jTextField25.getText());
//        kh.setHoTen(jTextField26.getText());
//        kh.setSoDienThoai(jTextField27.getText());
//        kh.setEmail(jTextField28.getText());
//        kh.setGioiTinh(jTextField29.getText());
//        kh.setMatKhau(jTextField30.getText());
//        khachhangBUS kh_BUS = new khachhangBUS();
//        if (kh_BUS.EditKH(kh)) {
//            modelqlKHNV.setValueAt(jTextField25.getText(),tblKHNV.getSelectedRow(),0);
//            modelqlKHNV.setValueAt(jTextField26.getText(),tblKHNV.getSelectedRow(),1);
//            modelqlKHNV.setValueAt(jTextField27.getText(),tblKHNV.getSelectedRow(),2);
//            modelqlKHNV.setValueAt(jTextField28.getText(),tblKHNV.getSelectedRow(),3);
//            modelqlKHNV.setValueAt(jTextField29.getText(),tblKHNV.getSelectedRow(),4);
//            modelqlKHNV.setValueAt(jTextField30.getText(),tblKHNV.getSelectedRow(),5);
//            tblKHNV.setModel(modelqlKHNV);
//            showMessageDialog(rootPane, "Sửa thành công");
//            EditKH.dispose();
//        }else{
//            showMessageDialog(rootPane, "Sửa thất bại");
//
//        }
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if ("".equals(jTextField4.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phòng");
        } else {
            Jdialogghe.setVisible(true);
            Jdialogghe.pack();
            Jdialogghe.setLocationRelativeTo(null);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        jTextField4.setText((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        count[1]++;
        if (count[1] % 2 == 0) {
            jPanel5.setBackground(Color.red);
        } else {
            jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
        Hoadon hd = new Hoadon();
        hd.setIdHoaDon(Integer.parseInt(jTextField1.getText()));
        hd.setIdKhachhang(Integer.parseInt(jTextField2.getText()));
        hd.setIdNhanvien(Integer.parseInt(jTextField8.getText()));
        hd.setNgayLap(jTextField11.getText());
        hd.setTongGia(Integer.parseInt(jTextField9.getText()));        
        hoadonBUS hd_BUS = new hoadonBUS();
        if (hd_BUS.AddHD(hd)) {
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
        } else
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại");
        for(int i=0;i<tblhoadon.getRowCount();i++)
        {
            chi_tiet_hoa_don cthd = new chi_tiet_hoa_don();
            cthd.setIdHoaDon(Integer.parseInt((String) tblhoadon.getValueAt(i,0)));
            cthd.setIdPhim(Integer.parseInt((String) tblhoadon.getValueAt(i,1)));
            cthd.setIdKhachhang(Integer.parseInt((String) tblhoadon.getValueAt(i,2)));
            cthd.setIdGhe((String) tblhoadon.getValueAt(i,3));
            cthd.setIdPhong(Integer.parseInt((String) tblhoadon.getValueAt(i,4)));
            cthd.setIdNhanvien((String) tblhoadon.getValueAt(i,5));
            cthd.setSoLuong((String) tblhoadon.getValueAt(i,6));
            cthd.setGia(Integer.parseInt((String) tblhoadon.getValueAt(i,7)));
            chiTietHoaDonBUS cthd_BUS = new chiTietHoaDonBUS();
            if (cthd_BUS.AddCTHD(cthd)) {

        } else
            JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thất bại");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void lbPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseClicked
        // TODO add your handling code here:
        pnHienThiNV.removeAll();
        pnHienThiNV.add(PanelPhimNV);
        pnHienThiNV.repaint();
        pnPhimNV.setBackground(Color.white);
        pnKHNV.setBackground(new Color(255, 102, 102));   
        pnDatVe.setBackground(new Color(255, 102, 102)); 
    }//GEN-LAST:event_lbPhimMouseClicked

    private void lbKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseClicked
        // TODO add your handling code here:
        pnHienThiNV.removeAll();
        pnHienThiNV.add(PanelKHNV);
        pnHienThiNV.repaint();
        pnKHNV.setBackground(Color.white);       
        pnPhimNV.setBackground(new Color(255, 102, 102));
        pnDatVe.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbKHMouseClicked

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        
        AddPhim.pack();
        AddPhim.setLocationRelativeTo(null);
        AddPhim.setVisible(true);
        
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
       int i = tblPhimNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần sửa");

        } else {           
            select = (int) modelqlPhimNV.getValueAt(i, 0) ;
            EditPhim.setVisible(true);  
            jTextField40.setText(String.valueOf(tblPhimNV.getValueAt(i, 0)));
            jTextField43.setText((String) tblPhimNV.getValueAt(i, 1));
            jTextField41.setText((String) tblPhimNV.getValueAt(i, 2));
            jTextField44.setText(String.valueOf(tblPhimNV.getValueAt(i, 3)));            
            jTextField42.setText((String) tblPhimNV.getValueAt(i, 4));
            jTextField45.setText((String) tblPhimNV.getValueAt(i, 5)); 
            jTextField47.setText((String) tblPhimNV.getValueAt(i, 6));
            jTextField46.setText((String) tblPhimNV.getValueAt(i, 7));            
            jTextField48.setText((String) tblPhimNV.getValueAt(i, 8));
            jTextField49.setText((String) tblPhimNV.getValueAt(i, 9));
        }
        EditPhim.pack();
        EditPhim.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        int select = 0;
        int i = tblPhimNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần xóa");

        } else if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (int) tblPhimNV.getModel().getValueAt(i, 0);
            if (new phimBUS().DeletePhim(select)) {

                modelqlPhimNV.removeRow(i);
                tblPhimNV.setModel(modelqlPhimNV);
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        modelqlPhimNV.setRowCount(0);
        listPhim = new phimBUS().getList();
        showPhim();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel tbModel = new DefaultTableModel();
        String tenPhim = jTextField32.getText();
        ArrayList<Phim> list = new phimDAO().FindFilm(tenPhim);
        Vector header = new Vector();
        header.add("Id Phim");
        header.add("Tên Phim");
        header.add("Thể loại");
        header.add("Thời lượng");
        header.add("Ngôn ngữ");
        header.add("Diễn viên");
        header.add("Quốc gia");
        header.add("Nhà sản xuất");
        header.add("Ngày chiếu");
        header.add("Giá vé");

        if (tbModel.getRowCount() == 0) {

            tbModel = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (list.get(i).getIdPhim());
            row[1] = (list.get(i).getTenPhim());
            row[2] = (list.get(i).getTheloai());
            row[3] = (list.get(i).getThoiLuong());
            row[4] = (list.get(i).getNgonNgu());
            row[5] = (list.get(i).getDienVien());
            row[6] = (list.get(i).getQuocgia());
            row[7] = (list.get(i).getNhaSanXuat());
            row[8] = (list.get(i).getNgayCongChieu());
            row[9] = (list.get(i).getGiave());

            tbModel.addRow(row);

        }
        tblPhimNV.setModel(tbModel);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        AddKH.pack();
        AddKH.setLocationRelativeTo(null);
        AddKH.setVisible(true);
        
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
       int i = tblKHNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần sửa");

        } else {           
            select = Integer.parseInt((String) modelqlKHNV.getValueAt(i, 0)) ;
            EditKH.setVisible(true);  
            jTextField25.setText((String) tblKHNV.getValueAt(i, 0));
            jTextField26.setText((String) tblKHNV.getValueAt(i, 1));
            jTextField27.setText((String) tblKHNV.getValueAt(i, 2));
            jTextField28.setText((String) tblKHNV.getValueAt(i, 3));            
            jTextField29.setText((String) tblKHNV.getValueAt(i, 4));
            jTextField30.setText((String) tblKHNV.getValueAt(i, 5)); 
            
        }
        EditKH.pack();
        EditKH.setLocationRelativeTo(null);

    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
        String select = "";
        int i = tblKHNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần xóa");

        } else if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa khách hàng này? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (String) tblKHNV.getModel().getValueAt(i, 0);
            if (new khachhangBUS().DeleteKH(select)) {

                modelqlKHNV.removeRow(i);
                tblKHNV.setModel(modelqlKHNV);
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        modelqlKHNV.setRowCount(0);
        listKH = new khachhangBUS().getList();
        showKH();
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DefaultTableModel tbModel = new DefaultTableModel();
        String hoTen = jTextField33.getText();
        ArrayList<khach_hang> list = new khachhangDAO().FindKH(hoTen);
        Vector header = new Vector();
        header.add("ID Khách Hàng");
        header.add("Họ Tên");
        header.add("SĐT");
        header.add("Email");
        header.add("Giới tính");
        header.add("Mật khẩu");

        if (tbModel.getRowCount() == 0) {

            tbModel = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (list.get(i).getIdKhachHang());
            row[1] = (list.get(i).getHoTen());
            row[2] = (list.get(i).getSoDienThoai());
            row[3] = (list.get(i).getEmail());
            row[4] = (list.get(i).getGioiTinh());
            row[5] = (list.get(i).getMatKhau());

            tbModel.addRow(row);

        }
        tblKHNV.setModel(tbModel);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        khach_hang kh = new khach_hang();
        kh.setIdKhachHang(jTextField25.getText());
        kh.setHoTen(jTextField26.getText());
        kh.setSoDienThoai(jTextField27.getText());
        kh.setEmail(jTextField28.getText());
        kh.setGioiTinh(jTextField29.getText());
        kh.setMatKhau(jTextField30.getText());
        khachhangBUS kh_BUS = new khachhangBUS();
        if (kh_BUS.EditKH(kh)) {
            modelqlKHNV.setValueAt(jTextField25.getText(),tblKHNV.getSelectedRow(),0);
            modelqlKHNV.setValueAt(jTextField26.getText(),tblKHNV.getSelectedRow(),1);
            modelqlKHNV.setValueAt(jTextField27.getText(),tblKHNV.getSelectedRow(),2);
            modelqlKHNV.setValueAt(jTextField28.getText(),tblKHNV.getSelectedRow(),3);
            modelqlKHNV.setValueAt(jTextField29.getText(),tblKHNV.getSelectedRow(),4);
            modelqlKHNV.setValueAt(jTextField30.getText(),tblKHNV.getSelectedRow(),5);
            tblKHNV.setModel(modelqlKHNV);
            showMessageDialog(rootPane, "Sửa thành công");
            EditKH.dispose();
        }else{
            showMessageDialog(rootPane, "Sửa thất bại");

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Phim phim = new Phim();
        phim.setIdPhim(Integer.parseInt(jTextField12.getText()));
        phim.setTenPhim(jTextField13.getText());
        phim.setTheloai(jTextField15.getText());
        phim.setThoiLuong(Integer.parseInt(jTextField14.getText()));
        phim.setNgayCongChieu(jTextField20.getText());
        phim.setNgonNgu(jTextField16.getText());
        phim.setDienVien(jTextField17.getText());
        phim.setQuocgia(jTextField19.getText());
        phim.setNhaSanXuat(jTextField18.getText());
        phim.setGiave(jTextField21.getText());
        phimBUS phim_BUS = new phimBUS();
        if (phim_BUS.AddPhim(phim)) {
            JOptionPane.showMessageDialog(null, "themthanhcong");
        } else
        JOptionPane.showMessageDialog(null, "themkhongthanhcong");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        khach_hang kh = new khach_hang();
        kh.setIdKhachHang(jTextField22.getText());
        kh.setHoTen(jTextField23.getText());
        kh.setSoDienThoai(jTextField24.getText());
        kh.setEmail(jTextField31.getText());
        kh.setGioiTinh(jTextField34.getText());
        kh.setMatKhau(jTextField35.getText());
        khachhangBUS kh_BUS = new khachhangBUS();
        if (kh_BUS.AddKH(kh)) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else
        JOptionPane.showMessageDialog(null, "Thêm thất bại");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        Phim phim = new Phim();
        phim.setIdPhim(Integer.parseInt(jTextField40.getText()));
        phim.setTenPhim(jTextField43.getText());
        phim.setTheloai(jTextField41.getText());
        phim.setThoiLuong(Integer.parseInt(jTextField44.getText()));
        phim.setNgonNgu(jTextField42.getText());
        phim.setDienVien(jTextField45.getText());
        phim.setQuocgia(jTextField47.getText());
        phim.setNhaSanXuat(jTextField46.getText());
        phim.setNgayCongChieu(jTextField48.getText());
        phim.setGiave(jTextField49.getText());
        phimBUS phim_BUS = new phimBUS();
        if (phim_BUS.EditPhim(phim)) {
            modelqlPhimNV.setValueAt(jTextField40.getText(),tblPhimNV.getSelectedRow(),0);
            modelqlPhimNV.setValueAt(jTextField43.getText(),tblPhimNV.getSelectedRow(),1);
            modelqlPhimNV.setValueAt(jTextField41.getText(),tblPhimNV.getSelectedRow(),2);
            modelqlPhimNV.setValueAt(jTextField44.getText(),tblPhimNV.getSelectedRow(),3);
            modelqlPhimNV.setValueAt(jTextField42.getText(),tblPhimNV.getSelectedRow(),4);
            modelqlPhimNV.setValueAt(jTextField45.getText(),tblPhimNV.getSelectedRow(),5);
            modelqlPhimNV.setValueAt(jTextField47.getText(),tblPhimNV.getSelectedRow(),2);
            modelqlPhimNV.setValueAt(jTextField46.getText(),tblPhimNV.getSelectedRow(),3);
            modelqlPhimNV.setValueAt(jTextField48.getText(),tblPhimNV.getSelectedRow(),4);
            modelqlPhimNV.setValueAt(jTextField49.getText(),tblPhimNV.getSelectedRow(),5);
            tblPhimNV.setModel(modelqlPhimNV);
            showMessageDialog(rootPane, "Sửa thành công");
            EditPhim.dispose();
        }else{
            showMessageDialog(rootPane, "Sửa thất bại");
    }//GEN-LAST:event_jButton17ActionPerformed
    }
    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField45ActionPerformed

    private void jTextField49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField49ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblPhimNV);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblKHNV);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void lbDatVeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDatVeMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnDatVe.getBackground().toString().equals(s.toString())) {
            pnDatVe.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbDatVeMouseEntered

    private void lbDatVeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDatVeMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnDatVe.getBackground().toString().equals(s.toString())) {
            pnDatVe.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbDatVeMouseExited

    private void lbPhimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnPhimNV.getBackground().toString().equals(s.toString())) {
            pnPhimNV.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbPhimMouseEntered

    private void lbPhimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnPhimNV.getBackground().toString().equals(s.toString())) {
            pnPhimNV.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbPhimMouseExited

    private void lbKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnKHNV.getBackground().toString().equals(s.toString())) {
            pnKHNV.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbKHMouseEntered

    private void lbKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnKHNV.getBackground().toString().equals(s.toString())) {
            pnKHNV.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbKHMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Nhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nhanvien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nhanvien().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddKH;
    private javax.swing.JDialog AddPhim;
    private javax.swing.JDialog EditKH;
    private javax.swing.JDialog EditPhim;
    private javax.swing.JDialog JdialogPhim;
    private javax.swing.JDialog Jdialogghe;
    private javax.swing.JPanel MenuNV;
    private javax.swing.JPanel PanelKHNV;
    private javax.swing.JPanel PanelPhimNV;
    private javax.swing.JPanel RootNV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbDatVe;
    private javax.swing.JLabel lbKH;
    private javax.swing.JLabel lbPhim;
    private javax.swing.JPanel panelDatve;
    private javax.swing.JPanel pnDatVe;
    private javax.swing.JPanel pnHienThiNV;
    private javax.swing.JPanel pnKHNV;
    private javax.swing.JPanel pnPhimNV;
    private javax.swing.JTable tbPhimDatve;
    private javax.swing.JTable tblKHNV;
    private javax.swing.JTable tblPhimNV;
    private javax.swing.JTable tblhoadon;
    // End of variables declaration//GEN-END:variables
}
