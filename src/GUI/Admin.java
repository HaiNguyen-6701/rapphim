/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.chiTietHoaDonBUS;
import BUS.khachhangBUS;
import BUS.nvBUS;
import BUS.phimBUS;
import BUS.taiKhoanBUS;
import BUS.hoadonBUS;
import DAO.NvDAO;
import DAO.hoadonDAO;
import DAO.khachhangDAO;
import DAO.phimDAO;
import DAO.taiKhoanDAO;
import DTO.Hoadon;
import DTO.Phim;
import DTO.chi_tiet_hoa_don;
import DTO.khach_hang;
import DTO.nhan_vien;
import DTO.taiKhoan;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author USer
 */

public class Admin extends javax.swing.JFrame {

    DefaultTableModel modelqlNV = new DefaultTableModel();
    ArrayList<nhan_vien> listNV = new nvBUS().getList();
    DefaultTableModel modelqlPhim = new DefaultTableModel();
    ArrayList<Phim> listPhim = new phimBUS().getList();
    DefaultTableModel modelqlKH = new DefaultTableModel();
    ArrayList<khach_hang> listKH = new khachhangBUS().getList();
    DefaultTableModel modelqlTK = new DefaultTableModel();
    ArrayList<taiKhoan> listTK = new taiKhoanBUS().getList();
    DefaultTableModel modelqlHD = new DefaultTableModel();
    ArrayList<Hoadon> listHD = new hoadonBUS().getList();
    DefaultTableModel modelqlCTHD = new DefaultTableModel();
    ArrayList<chi_tiet_hoa_don> listCTHD = new chiTietHoaDonBUS().getList();
    int select=0;
    public String checkposition() {
        String position = "";
        if (jRadioButton1.isSelected()) {
            position = "Admin";
        } else if (jRadioButton2.isSelected()) {
            position = "Nh??n vi??n";

        } else {
            position = "";
        }
        return position;
    }
     public String checkgender() {
        String gender = "";
        if (jRadioButton3.isSelected()) {
            gender = "Nam";
        } else if (jRadioButton4.isSelected()) {
            gender = "N???";

        } else {
            gender = "";
        }
        return gender;
    }
    public Admin() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        initComponents();
        pnNV.setBackground(Color.white);
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelNV);
        setLocationRelativeTo(null);
        showNV();
        showPhim();
        showKH();
        showTK();
        ShowHD();
        showCTHD();
    }

    class JPanelGradiant extends JPanel {

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
    class JbuttonGradiant extends JButton{
        protected void paintButton(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(255,103,108);
            Color color2 = new Color(255,140,197);

            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
    

    public void showNV() {
        Vector header = new Vector();
        header.add("ID Nh??n vi??n");
        header.add("H??? t??n");
        header.add("S??T");
        header.add("Email");
        header.add("Gi???i t??nh");
        header.add("Ch???c v???");
        if (modelqlNV.getRowCount() == 0) {

            modelqlNV = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listNV.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listNV.get(i).getIdNhanVien());
            row[1] = (listNV.get(i).getHoTen());
            row[2] = (listNV.get(i).getSoDienThoai());
            row[3] = (listNV.get(i).getEmail());
            row[4] = (listNV.get(i).getGioitinh());
            row[5] = (listNV.get(i).getChucVu());
            
            modelqlNV.addRow(row);

        }
        tblNV.setModel(modelqlNV);
    }

    public void showPhim() {
        Vector header = new Vector();
        header.add("Id Phim");
        header.add("T??n Phim");
        header.add("Th??? lo???i");
        header.add("Th???i l?????ng");
        header.add("Ng??n ng???");
        header.add("Di???n vi??n");
        header.add("Qu???c gia");
        header.add("Nh?? s???n xu???t");
        header.add("Ng??y chi???u");
        header.add("Gi?? v??");

        if (modelqlPhim.getRowCount() == 0) {

            modelqlPhim = new DefaultTableModel(header, 0);
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

            modelqlPhim.addRow(row);

        }
        tblPhimNV.setModel(modelqlPhim);
    }

    public void showKH() {
        Vector header = new Vector();
        header.add("ID Kh??ch H??ng");
        header.add("H??? T??n");
        header.add("S??T");
        header.add("Email");
        header.add("Gi???i t??nh");
        header.add("M???t kh???u");

        if (modelqlKH.getRowCount() == 0) {

            modelqlKH = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listKH.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listKH.get(i).getIdKhachHang());
            row[1] = (listKH.get(i).getHoTen());
            row[2] = (listKH.get(i).getSoDienThoai());
            row[3] = (listKH.get(i).getEmail());
            row[4] = (listKH.get(i).getGioiTinh());
            row[5] = (listKH.get(i).getMatKhau());

            modelqlKH.addRow(row);

        }
        tblKH.setModel(modelqlKH);
    }

    public void showTK() {
        Vector header = new Vector();
        header.add("ID Nh??n vi??n");
        header.add("T??n ????ng nh???p");
        header.add("M???t kh???u");
        header.add("T??n nh??n vi??n");
        header.add("Ch???c v???");

        if (modelqlTK.getRowCount() == 0) {

            modelqlTK = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listTK.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listTK.get(i).getIdNV());
            row[1] = (listTK.get(i).getTenDangNhap());
            row[2] = (listTK.get(i).getmKhau());
            row[3] = (listTK.get(i).getTenNV());
            row[4] = (listTK.get(i).getChucVu());

            modelqlTK.addRow(row);

        }
        tblTK.setModel(modelqlTK);
        
    }
    
    public void ShowHD(){
        Vector header = new Vector();
        header.add("ID H??a ????n");
        header.add("ID Nh??n vi??n");
        header.add("ID Kh??ch h??ng");
        header.add("Ng??y l???p");
        header.add("T???ng gi??");

        if (modelqlHD.getRowCount() == 0) {

            modelqlHD = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listHD.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listHD.get(i).getIdHoaDon());
            row[1] = (listHD.get(i).getIdNhanvien());
            row[2] = (listHD.get(i).getIdKhachhang());
            row[3] = (listHD.get(i).getNgayLap());
            row[4] = (listHD.get(i).getTongGia());

            modelqlHD.addRow(row);

        }
        tblHD.setModel(modelqlHD);
    }
    
    public void showCTHD(){
        Vector header = new Vector();
        header.add("ID H??a ????n");
        header.add("ID Phim");
        header.add("ID Kh??ch h??ng");
        header.add("ID Gh???");
        header.add("ID Ph??ng");
        header.add("ID Nh??n vi??n");
        header.add("S??? l?????ng");
        header.add("Gi??");

        if (modelqlCTHD.getRowCount() == 0) {

            modelqlCTHD = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < listCTHD.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (listCTHD.get(i).getIdHoaDon());
            row[1] = (listCTHD.get(i).getIdPhim());
            row[2] = (listCTHD.get(i).getIdKhachhang());
            row[3] = (listCTHD.get(i).getIdGhe());
            row[4] = (listCTHD.get(i).getIdPhong());
            row[5] = (listCTHD.get(i).getIdNhanvien());
            row[6] = (listCTHD.get(i).getSoLuong());
            row[7] = (listCTHD.get(i).getGia());

            modelqlCTHD.addRow(row);

        }
        tblCTHD.setModel(modelqlCTHD);
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
                JOptionPane.showMessageDialog(null, "L??u file th??nh c??ng!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "L???i khi l??u file!");
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

        AddNV = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        AddPhim = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        AddKH = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        EditNV = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        EditKH = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        AddTK = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jTextField39 = new javax.swing.JTextField();
        EditPhim = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
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
        EditTK = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        RootAdmin = new javax.swing.JPanel();
        MenuAdmin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        pnNV = new javax.swing.JPanel();
        lbNV = new javax.swing.JLabel();
        pnPhim = new javax.swing.JPanel();
        lbPhim = new javax.swing.JLabel();
        pnKH = new javax.swing.JPanel();
        lbKH = new javax.swing.JLabel();
        pnHoadon = new javax.swing.JPanel();
        lbHoaDon = new javax.swing.JLabel();
        pnTaikhoan = new javax.swing.JPanel();
        lbTaikhoan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnHienthiAdmin = new javax.swing.JPanel();
        PanelNV = new JPanelGradiant();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton34 = new JbuttonGradiant();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        PanelPhim = new JPanelGradiant();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhimNV = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        PanelKH = new JPanelGradiant();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        PanelHoaDon = new JPanelGradiant();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jButton48 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField50 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        PanelTaikhoan = new JPanelGradiant();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTK = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        AddNV.setBackground(new java.awt.Color(255, 204, 102));

        jPanel4.setBackground(new java.awt.Color(253, 248, 248));

        jPanel6.setBackground(new java.awt.Color(248, 227, 227));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TH??M NH??N VI??N");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nh??n vi??n"));

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("H??? t??n"));

        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder("S??T"));

        jComboBox1.setBackground(new java.awt.Color(221, 147, 147));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???c v???", "Admin", "Nh??n vi??n" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(221, 147, 147));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gi???i t??nh", "Nam", "N???" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 204, 51));
        jButton5.setText("Th??m");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout AddNVLayout = new javax.swing.GroupLayout(AddNV.getContentPane());
        AddNV.getContentPane().setLayout(AddNVLayout);
        AddNVLayout.setHorizontalGroup(
            AddNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddNVLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddNVLayout.setVerticalGroup(
            AddNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddNVLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        AddPhim.setBackground(new java.awt.Color(248, 227, 227));

        jPanel8.setBackground(new java.awt.Color(253, 248, 248));

        jPanel9.setBackground(new java.awt.Color(248, 227, 227));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Th??m Phim");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Phim"));

        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n Phim"));

        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder("Th???i l?????ng"));

        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder("Th??? lo???i"));

        jButton8.setBackground(new java.awt.Color(255, 204, 51));
        jButton8.setText("Th??m");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField9.setBorder(javax.swing.BorderFactory.createTitledBorder("Ng??n ng???"));

        jTextField10.setBorder(javax.swing.BorderFactory.createTitledBorder("Di???n vi??n"));

        jTextField11.setBorder(javax.swing.BorderFactory.createTitledBorder("Nh?? s???n xu???t"));

        jTextField12.setBorder(javax.swing.BorderFactory.createTitledBorder("Qu???c gia"));
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.setBorder(javax.swing.BorderFactory.createTitledBorder("Ng??y chi???u"));

        jTextField14.setBorder(javax.swing.BorderFactory.createTitledBorder("Gi?? v??"));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6)
                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField14))
                .addGap(38, 38, 38))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddPhimLayout.setVerticalGroup(
            AddPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(253, 248, 248));

        jPanel11.setBackground(new java.awt.Color(248, 227, 227));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Th??m Kh??ch H??ng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField15.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Kh??ch H??ng"));

        jTextField16.setBorder(javax.swing.BorderFactory.createTitledBorder("H??? T??n"));

        jTextField18.setBorder(javax.swing.BorderFactory.createTitledBorder("S??T"));

        jButton11.setBackground(new java.awt.Color(255, 204, 51));
        jButton11.setText("Th??m");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTextField19.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField20.setBorder(javax.swing.BorderFactory.createTitledBorder("Gi???i t??nh"));

        jTextField22.setBorder(javax.swing.BorderFactory.createTitledBorder("M???t kh???u"));
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(38, 38, 38))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddKHLayout.setVerticalGroup(
            AddKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel12.setBackground(new java.awt.Color(253, 248, 248));

        jPanel13.setBackground(new java.awt.Color(248, 227, 227));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("S???a th??ng tin nh??n vi??n");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField17.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nh??n vi??n"));
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField21.setBorder(javax.swing.BorderFactory.createTitledBorder("H??? t??n"));

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField23.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField24.setBorder(javax.swing.BorderFactory.createTitledBorder("S??T"));
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 204, 51));
        jButton16.setText("S???a");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ch???c v???:");

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setText("Admin");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("Nh??n vi??n");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Gi???i t??nh:");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton3.setText("Nam");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton4.setText("N???");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EditNVLayout = new javax.swing.GroupLayout(EditNV.getContentPane());
        EditNV.getContentPane().setLayout(EditNVLayout);
        EditNVLayout.setHorizontalGroup(
            EditNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditNVLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditNVLayout.setVerticalGroup(
            EditNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel14.setBackground(new java.awt.Color(253, 248, 248));

        jPanel15.setBackground(new java.awt.Color(248, 227, 227));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("S???a th??ng tin Kh??ch H??ng");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField25.setEditable(false);
        jTextField25.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Kh??ch H??ng"));

        jTextField26.setBorder(javax.swing.BorderFactory.createTitledBorder("H??? T??n"));

        jTextField27.setBorder(javax.swing.BorderFactory.createTitledBorder("S??T"));

        jButton12.setBackground(new java.awt.Color(255, 204, 51));
        jButton12.setText("S???a");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField28.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jTextField29.setBorder(javax.swing.BorderFactory.createTitledBorder("Gi???i t??nh"));

        jTextField30.setBorder(javax.swing.BorderFactory.createTitledBorder("M???t kh???u"));
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField28, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(38, 38, 38))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
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
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditKHLayout.setVerticalGroup(
            EditKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(253, 248, 248));

        jPanel17.setBackground(new java.awt.Color(248, 227, 227));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Th??m t??i kho???n");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField35.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nh??n vi??n"));

        jTextField36.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n ????ng nh???p"));

        jTextField37.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n nh??n vi??n"));

        jTextField38.setBorder(javax.swing.BorderFactory.createTitledBorder("M???t kh???u"));

        jButton15.setBackground(new java.awt.Color(255, 204, 51));
        jButton15.setText("Th??m");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jTextField39.setBorder(javax.swing.BorderFactory.createTitledBorder("Ch???c v???"));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout AddTKLayout = new javax.swing.GroupLayout(AddTK.getContentPane());
        AddTK.getContentPane().setLayout(AddTKLayout);
        AddTKLayout.setHorizontalGroup(
            AddTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddTKLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AddTKLayout.setVerticalGroup(
            AddTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddTKLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel18.setBackground(new java.awt.Color(253, 248, 248));

        jPanel19.setBackground(new java.awt.Color(248, 227, 227));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("S???a th??ng tin phim");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField40.setEditable(false);
        jTextField40.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Phim"));

        jTextField41.setBorder(javax.swing.BorderFactory.createTitledBorder("Th??? lo???i"));

        jTextField42.setBorder(javax.swing.BorderFactory.createTitledBorder("Ng??n ng???"));

        jButton17.setBackground(new java.awt.Color(255, 204, 51));
        jButton17.setText("S???a");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTextField43.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n phim"));

        jTextField44.setBorder(javax.swing.BorderFactory.createTitledBorder("Th???i l?????ng"));

        jTextField45.setBorder(javax.swing.BorderFactory.createTitledBorder("Di???n vi??n"));
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });

        jTextField46.setBorder(javax.swing.BorderFactory.createTitledBorder("Nh?? s???n xu???t"));

        jTextField47.setBorder(javax.swing.BorderFactory.createTitledBorder("Qu???c gia"));

        jTextField48.setBorder(javax.swing.BorderFactory.createTitledBorder("Ng??y c??ng chi???u"));

        jTextField49.setBorder(javax.swing.BorderFactory.createTitledBorder("Gi?? v??"));
        jTextField49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField48, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField40, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField43, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(38, 38, 38))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
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
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditPhimLayout.setVerticalGroup(
            EditPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(253, 248, 248));

        jPanel21.setBackground(new java.awt.Color(248, 227, 227));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("S???a th??ng tin t??i kho???n");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField51.setEditable(false);
        jTextField51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField51.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nh??n vi??n"));
        jTextField51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField51ActionPerformed(evt);
            }
        });

        jTextField52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField52.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n ????ng nh???p"));

        jTextField53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField53.setBorder(javax.swing.BorderFactory.createTitledBorder("T??n nh??n vi??n"));

        jTextField54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField54.setBorder(javax.swing.BorderFactory.createTitledBorder("M???t kh???u"));
        jTextField54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField54ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(255, 204, 51));
        jButton21.setText("S???a");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Ch???c v???:");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton5.setText("Admin");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton6.setText("Nh??n vi??n");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton6)))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addGap(61, 61, 61)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EditTKLayout = new javax.swing.GroupLayout(EditTK.getContentPane());
        EditTK.getContentPane().setLayout(EditTKLayout);
        EditTKLayout.setHorizontalGroup(
            EditTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditTKLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EditTKLayout.setVerticalGroup(
            EditTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        MenuAdmin.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/administrator.png"))); // NOI18N
        jLabel1.setText("Admin");

        jButton4.setBackground(new java.awt.Color(237, 237, 237));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-thick-long-right-arrow-256.png"))); // NOI18N
        jButton4.setText("Tho??t");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        pnNV.setBackground(new java.awt.Color(255, 102, 102));

        lbNV.setBackground(new java.awt.Color(0, 0, 0));
        lbNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-staff-64.png"))); // NOI18N
        lbNV.setText("Nh??n vi??n");
        lbNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNVMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnNVLayout = new javax.swing.GroupLayout(pnNV);
        pnNV.setLayout(pnNVLayout);
        pnNVLayout.setHorizontalGroup(
            pnNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnNVLayout.setVerticalGroup(
            pnNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnPhim.setBackground(new java.awt.Color(255, 102, 102));
        pnPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbPhim.setBackground(new java.awt.Color(255, 51, 51));
        lbPhim.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-film-reel-64.png"))); // NOI18N
        lbPhim.setText("  Phim       ");
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

        javax.swing.GroupLayout pnPhimLayout = new javax.swing.GroupLayout(pnPhim);
        pnPhim.setLayout(pnPhimLayout);
        pnPhimLayout.setHorizontalGroup(
            pnPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnPhimLayout.setVerticalGroup(
            pnPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnKH.setBackground(new java.awt.Color(255, 102, 102));

        lbKH.setBackground(new java.awt.Color(255, 51, 51));
        lbKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-customer-insight-64.png"))); // NOI18N
        lbKH.setText("Kh??ch h??ng");
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

        javax.swing.GroupLayout pnKHLayout = new javax.swing.GroupLayout(pnKH);
        pnKH.setLayout(pnKHLayout);
        pnKHLayout.setHorizontalGroup(
            pnKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnKHLayout.setVerticalGroup(
            pnKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnHoadon.setBackground(new java.awt.Color(255, 102, 102));

        lbHoaDon.setBackground(new java.awt.Color(255, 51, 51));
        lbHoaDon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-paid-bill-48.png"))); // NOI18N
        lbHoaDon.setText("H??a ????n    ");
        lbHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHoaDonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnHoadonLayout = new javax.swing.GroupLayout(pnHoadon);
        pnHoadon.setLayout(pnHoadonLayout);
        pnHoadonLayout.setHorizontalGroup(
            pnHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnHoadonLayout.setVerticalGroup(
            pnHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnTaikhoan.setBackground(new java.awt.Color(255, 102, 102));

        lbTaikhoan.setBackground(new java.awt.Color(255, 51, 51));
        lbTaikhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTaikhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-test-account-40.png"))); // NOI18N
        lbTaikhoan.setText("T??i kho???n");
        lbTaikhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbTaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTaikhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbTaikhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbTaikhoanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnTaikhoanLayout = new javax.swing.GroupLayout(pnTaikhoan);
        pnTaikhoan.setLayout(pnTaikhoanLayout);
        pnTaikhoanLayout.setHorizontalGroup(
            pnTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTaikhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnTaikhoanLayout.setVerticalGroup(
            pnTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/skycinema.png"))); // NOI18N
        jLabel11.setText(" ");

        javax.swing.GroupLayout MenuAdminLayout = new javax.swing.GroupLayout(MenuAdmin);
        MenuAdmin.setLayout(MenuAdminLayout);
        MenuAdminLayout.setHorizontalGroup(
            MenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnHoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addGroup(MenuAdminLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuAdminLayout.setVerticalGroup(
            MenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuAdminLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnHienthiAdmin.setBackground(new java.awt.Color(255, 255, 255));
        pnHienthiAdmin.setLayout(null);

        PanelNV.setBackground(new java.awt.Color(255, 255, 204));

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Nh??n vi??n", "H??? T??n", "S??T", "Email", "Ch???c v???", "Gi???i t??nh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblNV);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QU???N L?? NH??N VI??N");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton34.setBackground(new java.awt.Color(237, 237, 237));
        jButton34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton34.setText("Th??m");
        jButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(237, 237, 237));
        jButton35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton35.setText("S???a");
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(237, 237, 237));
        jButton36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton36.setText("X??a");
        jButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(237, 237, 237));
        jButton37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton37.setText("L??m m???i");
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jTextField31.setToolTipText("");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });
        jTextField31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField31KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("T??m ki???m nh??n vi??n:");

        jButton2.setBackground(new java.awt.Color(237, 237, 237));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(237, 237, 237));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton6.setText("Excel");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelNVLayout = new javax.swing.GroupLayout(PanelNV);
        PanelNV.setLayout(PanelNVLayout);
        PanelNVLayout.setHorizontalGroup(
            PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelNVLayout.createSequentialGroup()
                .addGroup(PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelNVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(PanelNVLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelNVLayout.createSequentialGroup()
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jButton37))
                            .addGroup(PanelNVLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 67, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        PanelNVLayout.setVerticalGroup(
            PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNVLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField31)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );

        pnHienthiAdmin.add(PanelNV);
        PanelNV.setBounds(0, 0, 920, 700);

        PanelPhim.setBackground(new java.awt.Color(204, 204, 204));

        tblPhimNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Phim", "T??n Phim", "Th??? lo???i", "Th???i l?????ng", "Ng??n ng???", "Di???n vi??n", "Qu???c gia", "Nh?? s???n xu???t", "Ng??y chi???u", "Gi?? v??"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPhimNV);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("QU???N L?? PHIM");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton38.setBackground(new java.awt.Color(237, 237, 237));
        jButton38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton38.setText("Th??m");
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(237, 237, 237));
        jButton39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton39.setText("S???a");
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(237, 237, 237));
        jButton40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton40.setText("X??a");
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(237, 237, 237));
        jButton41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton41.setText("L??m m???i");
        jButton41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("T??m ki???m nh??n vi??n:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("T??m ki???m phim:");

        jTextField32.setToolTipText("");
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(237, 237, 237));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(237, 237, 237));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton1.setText("Excel");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPhimLayout = new javax.swing.GroupLayout(PanelPhim);
        PanelPhim.setLayout(PanelPhimLayout);
        PanelPhimLayout.setHorizontalGroup(
            PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelPhimLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPhimLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelPhimLayout.createSequentialGroup()
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPhimLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        PanelPhimLayout.setVerticalGroup(
            PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhimLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(PanelPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPhimLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnHienthiAdmin.add(PanelPhim);
        PanelPhim.setBounds(0, 0, 920, 700);

        PanelKH.setBackground(new java.awt.Color(204, 204, 204));

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Kh??ch h??ng", "H??? T??n", "S??T", "Email", "Gi???i t??nh", "M???t kh???u"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblKH);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QU???N L?? KH??CH H??NG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton42.setBackground(new java.awt.Color(237, 237, 237));
        jButton42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton42.setText("Th??m");
        jButton42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setBackground(new java.awt.Color(237, 237, 237));
        jButton43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton43.setText("S???a");
        jButton43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setBackground(new java.awt.Color(237, 237, 237));
        jButton44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton44.setText("X??a");
        jButton44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setBackground(new java.awt.Color(237, 237, 237));
        jButton45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton45.setText("L??m m???i");
        jButton45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("T??m ki???m kh??ch h??ng:");

        jButton7.setBackground(new java.awt.Color(237, 237, 237));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton7.setText("Excel");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(237, 237, 237));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKHLayout = new javax.swing.GroupLayout(PanelKH);
        PanelKH.setLayout(PanelKHLayout);
        PanelKHLayout.setHorizontalGroup(
            PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelKHLayout.createSequentialGroup()
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelKHLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(PanelKHLayout.createSequentialGroup()
                                .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66)
                        .addComponent(jButton45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        PanelKHLayout.setVerticalGroup(
            PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKHLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(PanelKHLayout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );

        pnHienthiAdmin.add(PanelKH);
        PanelKH.setBounds(0, 0, 920, 700);

        PanelHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        PanelHoaDon.setForeground(new java.awt.Color(255, 0, 0));
        PanelHoaDon.setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(255, 0, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QU???N L?? H??A ????N");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID H??a ????n", "ID Nh??n vi??n", "ID Kh??ch h??ng", "Ng??y l???p", "T???ng gi??"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblHD);

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID H??a ????n", "ID Phim", "ID Kh??ch h??ng", "ID Gh???", "ID Ph??ng", "Id Nh??n vi??n", "S??? l?????ng", "Gi??"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblCTHD);

        jButton48.setBackground(new java.awt.Color(237, 237, 237));
        jButton48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton48.setText("X??a");
        jButton48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 153, 102));
        jLabel21.setText("H??a ????n");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 153, 102));
        jLabel22.setText("Chi ti???t h??a ????n");

        jButton9.setBackground(new java.awt.Color(237, 237, 237));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton9.setText("Excel");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("T??m ki???m h??a ????n:");

        jTextField50.setToolTipText("");
        jTextField50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField50ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(237, 237, 237));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHoaDonLayout = new javax.swing.GroupLayout(PanelHoaDon);
        PanelHoaDon.setLayout(PanelHoaDonLayout);
        PanelHoaDonLayout.setHorizontalGroup(
            PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
            .addGroup(PanelHoaDonLayout.createSequentialGroup()
                .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelHoaDonLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelHoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelHoaDonLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(255, 255, 255)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelHoaDonLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(PanelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        PanelHoaDonLayout.setVerticalGroup(
            PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHoaDonLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24))
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnHienthiAdmin.add(PanelHoaDon);
        PanelHoaDon.setBounds(0, 0, 920, 700);

        PanelTaikhoan.setBackground(new java.awt.Color(204, 204, 204));
        PanelTaikhoan.setForeground(new java.awt.Color(255, 0, 0));

        tblTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Nh??n vi??n", "T??n ????ng nh???p", "M???t kh???u", "T??n nh??n vi??n", "Ch???c v???"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblTK);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("QU???N L?? T??I KHO???N");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jButton30.setBackground(new java.awt.Color(237, 237, 237));
        jButton30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-plus-64.png"))); // NOI18N
        jButton30.setText("Th??m");
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setBackground(new java.awt.Color(237, 237, 237));
        jButton31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-delete-64.png"))); // NOI18N
        jButton31.setText("X??a");
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(237, 237, 237));
        jButton32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-minus-64.png"))); // NOI18N
        jButton32.setText("S???a");
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(237, 237, 237));
        jButton33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-refresh-64.png"))); // NOI18N
        jButton33.setText("L??m m???i");
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("T??m ID nh??n vi??n:");

        jButton14.setBackground(new java.awt.Color(237, 237, 237));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/search.png"))); // NOI18N
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(237, 237, 237));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons8-microsoft-excel-2019-48 .png"))); // NOI18N
        jButton10.setText("Excel");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTaikhoanLayout = new javax.swing.GroupLayout(PanelTaikhoan);
        PanelTaikhoan.setLayout(PanelTaikhoanLayout);
        PanelTaikhoanLayout.setHorizontalGroup(
            PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                        .addComponent(jScrollPane6)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelTaikhoanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))))
        );
        PanelTaikhoanLayout.setVerticalGroup(
            PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelTaikhoanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnHienthiAdmin.add(PanelTaikhoan);
        PanelTaikhoan.setBounds(0, 0, 920, 700);

        javax.swing.GroupLayout RootAdminLayout = new javax.swing.GroupLayout(RootAdmin);
        RootAdmin.setLayout(RootAdminLayout);
        RootAdminLayout.setHorizontalGroup(
            RootAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RootAdminLayout.createSequentialGroup()
                .addComponent(MenuAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnHienthiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE))
        );
        RootAdminLayout.setVerticalGroup(
            RootAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHienthiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(MenuAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RootAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RootAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        nhan_vien nv = new nhan_vien();
        nv.setIdNhanVien(jTextField1.getText());
        nv.setHoTen(jTextField3.getText());
        nv.setSoDienThoai(jTextField7.getText());
        nv.setEmail(jTextField4.getText());
        nv.setChucVu((String) jComboBox1.getSelectedItem());
        nv.setGioitinh((String) jComboBox2.getSelectedItem());
        nvBUS nv_BUS = new nvBUS();
        if (nv_BUS.AddNV(nv)) {
            JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
        } else
            JOptionPane.showMessageDialog(null, "Th??m th???t b???i");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Phim phim = new Phim();
        phim.setIdPhim(Integer.parseInt(jTextField2.getText()));
        phim.setTenPhim(jTextField5.getText());
        phim.setTheloai(jTextField8.getText());
        phim.setThoiLuong(Integer.parseInt(jTextField6.getText()));
        phim.setNgayCongChieu(jTextField13.getText());
        phim.setNgonNgu(jTextField9.getText());
        phim.setDienVien(jTextField10.getText());
        phim.setQuocgia(jTextField12.getText());
        phim.setNhaSanXuat(jTextField11.getText());
        phim.setGiave(jTextField14.getText());
        phimBUS phim_BUS = new phimBUS();
        if (phim_BUS.AddPhim(phim)) {
            JOptionPane.showMessageDialog(null, "themthanhcong");
        } else
            JOptionPane.showMessageDialog(null, "themkhongthanhcong");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        modelqlTK.setRowCount(0);
        listTK = new taiKhoanBUS().getList();
        showTK();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        int i = tblTK.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n s???a");

        } else {           
            select = (int) modelqlTK.getValueAt(i, 0) ;
            EditTK.setVisible(true);  
            jTextField51.setText(String.valueOf(tblTK.getValueAt(i, 0)) );
            jTextField52.setText((String) tblTK.getValueAt(i, 1));
            jTextField54.setText((String) tblTK.getValueAt(i, 2));
            jTextField53.setText( tblTK.getValueAt(i, 3).toString());            
            if ("Admin".equals((String)tblTK.getValueAt(i,4))) {
                jRadioButton5.setSelected(true);
            }else if ("Nh??n vi??n".equals((String)tblTK.getValueAt(i, 4))) {
                jRadioButton6.setSelected(true);
            }        
        }
        EditTK.pack();
        EditTK.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        modelqlKH.setRowCount(0);
        listKH = new khachhangBUS().getList();
        showKH();
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        int i = tblKH.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n s???a");

        } else {           
            select = Integer.parseInt((String) modelqlKH.getValueAt(i, 0)) ;
            EditKH.setVisible(true);  
            jTextField25.setText((String) tblKH.getValueAt(i, 0));
            jTextField26.setText((String) tblKH.getValueAt(i, 1));
            jTextField27.setText((String) tblKH.getValueAt(i, 2));
            jTextField28.setText((String) tblKH.getValueAt(i, 3));            
            jTextField29.setText((String) tblKH.getValueAt(i, 4));
            jTextField30.setText((String) tblKH.getValueAt(i, 5)); 
            
        }
        EditKH.pack();
        EditKH.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        modelqlPhim.setRowCount(0);
        listPhim = new phimBUS().getList();
        showPhim();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        int i = tblPhimNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n s???a");

        } else {           
            select = (int) modelqlPhim.getValueAt(i, 0) ;
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

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        AddPhim.pack();
        AddPhim.setLocationRelativeTo(null);
        AddPhim.setVisible(true);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        modelqlNV.setRowCount(0);
        listNV = new nvBUS().getList();
        showNV();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        
        
        int i = tblNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n s???a");

        } else {           
            select = Integer.parseInt((String) modelqlNV.getValueAt(i, 0)) ;
            EditNV.setVisible(true);  
            jTextField17.setText((String) tblNV.getValueAt(i, 0));
            jTextField21.setText((String) tblNV.getValueAt(i, 1));
            jTextField24.setText((String) tblNV.getValueAt(i, 2));
            jTextField23.setText( tblNV.getValueAt(i, 3).toString());            
            if ("Admin".equals((String)tblNV.getValueAt(i,5))) {
                jRadioButton1.setSelected(true);
            }else if ("Nh??n vi??n".equals((String)tblNV.getValueAt(i, 5))) {
                jRadioButton2.setSelected(true);
            } 
            if (String.valueOf(tblNV.getValueAt(i, 4)).equals("Nam")) {
                jRadioButton3.setSelected(true);
            }else if (String.valueOf(tblNV.getValueAt(i, 4)).equals("N???")) {
                jRadioButton4.setSelected(true);
            }
            
        }
        EditNV.pack();
        EditNV.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:

        AddNV.pack();
        AddNV.setLocationRelativeTo(null);
        AddNV.setVisible(true);
    
    }//GEN-LAST:event_jButton34ActionPerformed

    private void lbTaikhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaikhoanMouseClicked
        // TODO add your handling code here:
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelTaikhoan);
        pnHienthiAdmin.repaint();
        pnTaikhoan.setBackground(Color.white);
        pnHoadon.setBackground(new Color(255, 102, 102));
        pnKH.setBackground(new Color(255, 102, 102));
        pnPhim.setBackground(new Color(255, 102, 102));
        pnNV.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbTaikhoanMouseClicked

    private void lbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonMouseClicked
        // TODO add your handling code here:
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelHoaDon);
        pnHienthiAdmin.repaint();
        pnHoadon.setBackground(Color.white);
        pnNV.setBackground(new Color(255, 102, 102));
        pnKH.setBackground(new Color(255, 102, 102));
        pnPhim.setBackground(new Color(255, 102, 102));
        pnTaikhoan.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbHoaDonMouseClicked

    private void lbKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseClicked
        // TODO add your handling code here:
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelKH);
        pnHienthiAdmin.repaint();
        pnKH.setBackground(Color.white);
        pnHoadon.setBackground(new Color(255, 102, 102));
        pnNV.setBackground(new Color(255, 102, 102));
        pnPhim.setBackground(new Color(255, 102, 102));
        pnTaikhoan.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbKHMouseClicked

    private void lbPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseClicked
        // TODO add your handling code here:
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelPhim);
        pnHienthiAdmin.repaint();
        pnPhim.setBackground(Color.white);
        pnHoadon.setBackground(new Color(255, 102, 102));
        pnKH.setBackground(new Color(255, 102, 102));
        pnNV.setBackground(new Color(255, 102, 102));
        pnTaikhoan.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbPhimMouseClicked

    private void lbNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNVMouseClicked
        // TODO add your handling code here:
        pnHienthiAdmin.removeAll();
        pnHienthiAdmin.add(PanelNV);
        pnHienthiAdmin.repaint();
        pnNV.setBackground(Color.white);
        pnHoadon.setBackground(new Color(255, 102, 102));
        pnKH.setBackground(new Color(255, 102, 102));
        pnPhim.setBackground(new Color(255, 102, 102));
        pnTaikhoan.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_lbNVMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        String select = "";
        int i = tblNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n x??a");

        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n x??a nh??n vi??n n??y? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (String) tblNV.getModel().getValueAt(i, 0);
            if (new nvBUS().DeleteNV(select)) {

                modelqlNV.removeRow(i);
                tblNV.setModel(modelqlNV);
                JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(rootPane, "X??a th???t b???i");
            }
        }

    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        int select = 0;
        int i = tblPhimNV.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n x??a");

        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n phim vi??n n??y? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (int) tblPhimNV.getModel().getValueAt(i, 0);
            if (new phimBUS().DeletePhim(select)) {

                modelqlPhim.removeRow(i);
                tblPhimNV.setModel(modelqlPhim);
                JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(rootPane, "X??a th???t b???i");
            }
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        nhan_vien nv = new nhan_vien();
        nv.setIdNhanVien(jTextField17.getText());
        nv.setHoTen(jTextField21.getText());
        nv.setSoDienThoai(jTextField24.getText());
        nv.setEmail(jTextField23.getText());
        nv.setChucVu(checkposition());
        nv.setGioitinh(checkgender());
        nvBUS nv_BUS = new nvBUS();
        if (nv_BUS.EditNV(nv)) {
                modelqlNV.setValueAt(jTextField17.getText(),tblNV.getSelectedRow(),0);
                modelqlNV.setValueAt(jTextField21.getText(),tblNV.getSelectedRow(),1);
                modelqlNV.setValueAt(jTextField24.getText(),tblNV.getSelectedRow(),2);
                modelqlNV.setValueAt(jTextField23.getText(),tblNV.getSelectedRow(),3);
                modelqlNV.setValueAt(checkposition(),tblNV.getSelectedRow(),4); 
                modelqlNV.setValueAt(checkgender(),tblNV.getSelectedRow(),5);
                tblNV.setModel(modelqlNV);
                showMessageDialog(rootPane, "S???a th??nh c??ng");
                EditNV.dispose();
        }else{
                showMessageDialog(rootPane, "S???a th???t b???i");
        }
   
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        AddKH.pack();
        AddKH.setLocationRelativeTo(null);
        AddKH.setVisible(true);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        khach_hang kh = new khach_hang();
        kh.setIdKhachHang(jTextField15.getText());
        kh.setHoTen(jTextField16.getText());
        kh.setSoDienThoai(jTextField18.getText());
        kh.setEmail(jTextField19.getText());
        kh.setGioiTinh(jTextField20.getText());
        kh.setMatKhau(jTextField22.getText());
        khachhangBUS kh_BUS = new khachhangBUS();
        if (kh_BUS.AddKH(kh)) {
            JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
        } else
            JOptionPane.showMessageDialog(null, "Th??m th???t b???i");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
         String select = "";
        int i = tblKH.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n x??a");

        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n x??a kh??ch h??ng n??y? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (String) tblKH.getModel().getValueAt(i, 0);
            if (new khachhangBUS().DeleteKH(select)) {

                modelqlKH.removeRow(i);
                tblKH.setModel(modelqlKH);
                JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(rootPane, "X??a th???t b???i");
            }
        }
    }//GEN-LAST:event_jButton44ActionPerformed

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
                modelqlKH.setValueAt(jTextField25.getText(),tblKH.getSelectedRow(),0);
                modelqlKH.setValueAt(jTextField26.getText(),tblKH.getSelectedRow(),1);
                modelqlKH.setValueAt(jTextField27.getText(),tblKH.getSelectedRow(),2);
                modelqlKH.setValueAt(jTextField28.getText(),tblKH.getSelectedRow(),3);
                modelqlKH.setValueAt(jTextField29.getText(),tblKH.getSelectedRow(),4);
                modelqlKH.setValueAt(jTextField30.getText(),tblKH.getSelectedRow(),5);
                tblKH.setModel(modelqlKH);
                showMessageDialog(rootPane, "S???a th??nh c??ng");
                EditKH.dispose();
        }else{
                showMessageDialog(rootPane, "S???a th???t b???i");
                
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       DefaultTableModel tbModel = new DefaultTableModel();
       String TenNV = jTextField34.getText();
       ArrayList<taiKhoan> list = new taiKhoanDAO().FindTK(TenNV);
       Vector header = new Vector();
        header.add("ID Nh??n vi??n");
        header.add("T??n ????ng nh???p");
        header.add("M???t kh???u");
        header.add("T??n nh??n vi??n");
        header.add("Ch???c v???");

        if (tbModel.getRowCount() == 0) {

            tbModel = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (list.get(i).getIdNV());
            row[1] = (list.get(i).getTenDangNhap());
            row[2] = (list.get(i).getmKhau());
            row[3] = (list.get(i).getTenNV());
            row[4] = (list.get(i).getChucVu());

            tbModel.addRow(row);

        }
        tblTK.setModel(tbModel);
       
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel tbModel = new DefaultTableModel();
        String hoTen = jTextField31.getText();
        ArrayList<nhan_vien> list = new NvDAO().FindNV(hoTen);
        Vector header = new Vector();
        header.add("ID Nh??n vi??n");
        header.add("H??? t??n");
        header.add("S??T");
        header.add("Email");
        header.add("Gi???i t??nh");
        header.add("Ch???c v???");
        if (tbModel.getRowCount() == 0) {

            tbModel = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (list.get(i).getIdNhanVien());
            row[1] = (list.get(i).getHoTen());
            row[2] = (list.get(i).getSoDienThoai());
            row[3] = (list.get(i).getEmail());
            row[4] = (list.get(i).getGioitinh());
            row[5] = (list.get(i).getChucVu());
            
            tbModel.addRow(row);

        }
        tblNV.setModel(tbModel);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel tbModel = new DefaultTableModel();
        String tenPhim = jTextField32.getText();
        ArrayList<Phim> list = new phimDAO().FindFilm(tenPhim);
        Vector header = new Vector();
        header.add("Id Phim");
        header.add("T??n Phim");
        header.add("Th??? lo???i");
        header.add("Th???i l?????ng");
        header.add("Ng??n ng???");
        header.add("Di???n vi??n");
        header.add("Qu???c gia");
        header.add("Nh?? s???n xu???t");
        header.add("Ng??y chi???u");
        header.add("Gi?? v??");

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
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        AddTK.pack();
        AddTK.setLocationRelativeTo(null);
        AddTK.setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        taiKhoan tk = new taiKhoan();
        tk.setIdNV(Integer.parseInt(jTextField35.getText()));
        tk.setTenDangNhap(jTextField36.getText());
        tk.setmKhau(jTextField38.getText());
        tk.setTenNV(jTextField37.getText());
        tk.setChucVu(jTextField39.getText());
        taiKhoanBUS tk_BUS = new taiKhoanBUS();
        if (tk_BUS.AddTK(tk)) {
            JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
        } else
            JOptionPane.showMessageDialog(null, "Th??m th???t b???i");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        int select = 0;
        int i = tblTK.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n x??a");

        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n x??a t??i kho???n n??y? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (int) tblTK.getModel().getValueAt(i, 0);
            if (new taiKhoanBUS().DeleteTK(select)) {

                modelqlTK.removeRow(i);
                tblTK.setModel(modelqlTK);
                JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(rootPane, "X??a th???t b???i");
            }
        }
    }//GEN-LAST:event_jButton31ActionPerformed

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
                modelqlPhim.setValueAt(jTextField40.getText(),tblPhimNV.getSelectedRow(),0);
                modelqlPhim.setValueAt(jTextField43.getText(),tblPhimNV.getSelectedRow(),1);
                modelqlPhim.setValueAt(jTextField41.getText(),tblPhimNV.getSelectedRow(),2);
                modelqlPhim.setValueAt(jTextField44.getText(),tblPhimNV.getSelectedRow(),3);
                modelqlPhim.setValueAt(jTextField42.getText(),tblPhimNV.getSelectedRow(),4);
                modelqlPhim.setValueAt(jTextField45.getText(),tblPhimNV.getSelectedRow(),5);
                modelqlPhim.setValueAt(jTextField47.getText(),tblPhimNV.getSelectedRow(),2);
                modelqlPhim.setValueAt(jTextField46.getText(),tblPhimNV.getSelectedRow(),3);
                modelqlPhim.setValueAt(jTextField48.getText(),tblPhimNV.getSelectedRow(),4);
                modelqlPhim.setValueAt(jTextField49.getText(),tblPhimNV.getSelectedRow(),5);
                tblPhimNV.setModel(modelqlPhim);
                showMessageDialog(rootPane, "S???a th??nh c??ng");
                EditPhim.dispose();
        }else{
                showMessageDialog(rootPane, "S???a th???t b???i");
    }//GEN-LAST:event_jButton17ActionPerformed
    }
    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField45ActionPerformed

    private void jTextField49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField49ActionPerformed

    private void jTextField31KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField31KeyPressed
        // TODO add your handling code here:        
        if (evt.getKeyCode()==evt.VK_ENTER){
        jButton2.doClick();
    }
    }//GEN-LAST:event_jTextField31KeyPressed

    private void lbNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNVMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnNV.getBackground().toString().equals(s.toString())) {
            pnNV.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbNVMouseEntered

    private void lbNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNVMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnNV.getBackground().toString().equals(s.toString())) {
            pnNV.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbNVMouseExited

    private void lbPhimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnPhim.getBackground().toString().equals(s.toString())) {
            pnPhim.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbPhimMouseEntered

    private void lbPhimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPhimMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnPhim.getBackground().toString().equals(s.toString())) {
            pnPhim.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbPhimMouseExited

    private void lbKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnKH.getBackground().toString().equals(s.toString())) {
            pnKH.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbKHMouseEntered

    private void lbKHMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKHMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnKH.getBackground().toString().equals(s.toString())) {
            pnKH.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbKHMouseExited

    private void lbHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnHoadon.getBackground().toString().equals(s.toString())) {
            pnHoadon.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbHoaDonMouseEntered

    private void lbTaikhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaikhoanMouseEntered
        // TODO add your handling code here:
        Color s = new Color(255,102,102);
        if (pnTaikhoan.getBackground().toString().equals(s.toString())) {
            pnTaikhoan.setBackground(new Color(204,204,255));
        }
    }//GEN-LAST:event_lbTaikhoanMouseEntered

    private void lbTaikhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTaikhoanMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnTaikhoan.getBackground().toString().equals(s.toString())) {
            pnTaikhoan.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbTaikhoanMouseExited

    private void lbHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonMouseExited
        // TODO add your handling code here:
        Color s = new Color(204,204,255);
        if (pnHoadon.getBackground().toString().equals(s.toString())) {
            pnHoadon.setBackground(new Color(255,102,102));           
        }
    }//GEN-LAST:event_lbHoaDonMouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblNV);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblPhimNV);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblKH);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblHD);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblTK);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField50ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbModel = new DefaultTableModel();
        String attributes = jTextField50.getText();
        ArrayList<Hoadon> list = new hoadonDAO().FindHD(attributes);
        Vector header = new Vector();
        header.add("ID H??a ????n");
        header.add("ID Nh??n vi??n");
        header.add("ID Kh??ch h??ng");
        header.add("Ng??y l???p");
        header.add("T???ng gi??");

        if (tbModel.getRowCount() == 0) {

            tbModel = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[10];
            row[0] = (list.get(i).getIdHoaDon());
            row[1] = (list.get(i).getIdNhanvien());
            row[2] = (list.get(i).getIdKhachhang());
            row[3] = (list.get(i).getNgayLap());
            row[4] = (list.get(i).getTongGia());

            tbModel.addRow(row);

        }
        tblHD.setModel(tbModel);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbModel = new DefaultTableModel();
        String hoTen = jTextField33.getText();
        ArrayList<khach_hang> list = new khachhangDAO().FindKH(hoTen);
        Vector header = new Vector();
        header.add("ID Kh??ch H??ng");
        header.add("H??? T??n");
        header.add("S??T");
        header.add("Email");
        header.add("Gi???i t??nh");
        header.add("M???t kh???u");

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
        tblKH.setModel(tbModel);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
        String select = "";
        int i = tblHD.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui l??ng ch???n d??ng c???n x??a");

        } else if (JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n x??a h??a ????n n??y? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (String) tblHD.getModel().getValueAt(i, 0);
            if (new hoadonBUS().DeleteHD(select)) {

                modelqlHD.removeRow(i);
                tblHD.setModel(modelqlHD);
                JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng");
            } else {
                JOptionPane.showMessageDialog(rootPane, "X??a th???t b???i");
            }
        }

    }//GEN-LAST:event_jButton48ActionPerformed

    private void jTextField51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField51ActionPerformed

    private void jTextField54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField54ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        taiKhoan tk = new taiKhoan();
        tk.setIdNV(Integer.parseInt(jTextField51.getText()));
        tk.setTenDangNhap(jTextField52.getText());
        tk.setmKhau(jTextField54.getText());
        tk.setTenNV(jTextField53.getText());
        tk.setChucVu(checkposition());
        taiKhoanBUS tk_BUS = new taiKhoanBUS();
        if (tk_BUS.EditTK(tk)) {
                modelqlTK.setValueAt(jTextField51.getText(),tblTK.getSelectedRow(),0);
                modelqlTK.setValueAt(jTextField52.getText(),tblTK.getSelectedRow(),1);
                modelqlTK.setValueAt(jTextField54.getText(),tblTK.getSelectedRow(),2);
                modelqlTK.setValueAt(jTextField53.getText(),tblTK.getSelectedRow(),3);
                modelqlTK.setValueAt(checkposition(),tblTK.getSelectedRow(),4);
                tblTK.setModel(modelqlTK);
                showMessageDialog(rootPane, "S???a th??nh c??ng");
                EditTK.dispose();
        }else{
                showMessageDialog(rootPane, "S???a th???t b???i");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed
     
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddKH;
    private javax.swing.JDialog AddNV;
    private javax.swing.JDialog AddPhim;
    private javax.swing.JDialog AddTK;
    private javax.swing.JDialog EditKH;
    private javax.swing.JDialog EditNV;
    private javax.swing.JDialog EditPhim;
    private javax.swing.JDialog EditTK;
    private javax.swing.JPanel MenuAdmin;
    private javax.swing.JPanel PanelHoaDon;
    private javax.swing.JPanel PanelKH;
    private javax.swing.JPanel PanelNV;
    private javax.swing.JPanel PanelPhim;
    private javax.swing.JPanel PanelTaikhoan;
    private javax.swing.JPanel RootAdmin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
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
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbHoaDon;
    private javax.swing.JLabel lbKH;
    private javax.swing.JLabel lbNV;
    private javax.swing.JLabel lbPhim;
    private javax.swing.JLabel lbTaikhoan;
    private javax.swing.JPanel pnHienthiAdmin;
    private javax.swing.JPanel pnHoadon;
    private javax.swing.JPanel pnKH;
    private javax.swing.JPanel pnNV;
    private javax.swing.JPanel pnPhim;
    private javax.swing.JPanel pnTaikhoan;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblKH;
    private javax.swing.JTable tblNV;
    private javax.swing.JTable tblPhimNV;
    private javax.swing.JTable tblTK;
    // End of variables declaration//GEN-END:variables
}
