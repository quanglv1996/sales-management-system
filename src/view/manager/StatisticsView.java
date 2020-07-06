/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.view.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project1.core.cost.CostStatistics;
import project1.core.connection.CreateConnectionDB;
import project1.core.employee.Manager;
import project1.core.product.ProductStatistics;

/**
 *
 * @author lequangbkhn
 */
//Class chinh ------------------------------------------------------------------
public class StatisticsView extends javax.swing.JFrame {

    /**
     * Creates new form ThongKeView
     */
    public StatisticsView() {

    }

    public void init() {
        initComponents();
        createDateOnComboBox(tuNgayCB, tuThangCB, tuNamCB);
        createDateOnComboBox(denNgayCB, denThangCB, denNamCB);
        setFalseItem();
        setResizable(false);
        setVisible(true);
    }

//Field-------------------------------------------------------------------------
    private String username;
    private String Name;
    private int typeUser;

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String Name) {
        this.Name = Name;
    }
//Getter------------------------------------------------------------------------

    public void getDataProductToTableInput(String query1) {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(colsName);
        jTable1.setModel(tableModel);
        List<ProductStatistics> list = new ArrayList<ProductStatistics>();
        String query = query1;
        ResultSet rs = con.select(query);
        try {
            while (rs.next()) {
                ProductStatistics pro = new ProductStatistics();

                pro.setMaSanPham(rs.getString("MaSanPham"));
                pro.setNgayThang(rs.getString("creat_date"));
                pro.setSoLuong(rs.getInt("so_luong"));
                pro.setTenSanPham(rs.getString("TenSanPham"));
                pro.setTheLoai(rs.getString("TheLoai"));
                pro.setUser(rs.getString("usename"));
                pro.setGiaBan(rs.getInt("giaBan"));
                pro.setGiaNhap(rs.getInt("giaNhap"));

                list.add(pro);
            }

            changeLb1.setText("Tổng chi");
            changeLb2.setText("");
            loiNhuanLb.setText("");
            doanhThuLb.setText(String.valueOf(manager.tinhTongNhap(list)));

            for (int i = 0; i < list.size(); i++) {
                ProductStatistics pro = new ProductStatistics();
                pro = list.get(i);
                String[] rowData = dataRow(pro);
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public void getDataProductToTableSell(String query1) {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(colsName);
        jTable1.setModel(tableModel);
        List<ProductStatistics> list = new ArrayList<ProductStatistics>();
        String query = query1;
        ResultSet rs = con.select(query);
        try {
            while (rs.next()) {
                ProductStatistics pro = new ProductStatistics();

                pro.setMaSanPham(rs.getString("MaSanPham"));
                pro.setNgayThang(rs.getString("creat_date"));
                pro.setSoLuong(rs.getInt("so_luong"));
                pro.setTenSanPham(rs.getString("TenSanPham"));
                pro.setTheLoai(rs.getString("TheLoai"));
                pro.setUser(rs.getString("usename"));
                pro.setGiaBan(rs.getInt("giaBan"));
                pro.setGiaNhap(rs.getInt("giaNhap"));

                list.add(pro);
            }
            changeLb1.setText("Doanh thu");
            changeLb2.setText("Lợi nhuận");
            loiNhuanLb.setText("0.0");

            doanhThuLb.setText(String.valueOf(manager.tinhDoanhThuTK(list)));
            loiNhuanLb.setText(String.valueOf(manager.tinhLoiNhuanTK(list)));

            for (int i = 0; i < list.size(); i++) {
                ProductStatistics pro = new ProductStatistics();
                pro = list.get(i);
                String[] rowData = dataRow(pro);
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public void getDataCPToTable(String query1) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(colsNameCP);
        jTable1.setModel(tableModel);
        List<CostStatistics> list = new ArrayList<CostStatistics>();
        String query = query1;
        ResultSet rs = con.select(query);
        try {
            while (rs.next()) {
                CostStatistics cp = new CostStatistics();
                cp.setMaChiPhi(rs.getString("MaChiPhi"));
                cp.setTen(rs.getString("TenChiPhi"));
                cp.setSoTien(rs.getInt("SoTien"));
                cp.setLoaiChiPhi(rs.getString("LoaiChiPhi"));
                cp.setUser(rs.getString("usename"));
                cp.setNgayPhatSinh(rs.getString("NgayPhatSinh"));
                cp.setGhiChu(rs.getString("GhiChu"));
                list.add(cp);
            }

            changeLb1.setText("Tổng chi phí");
            changeLb2.setText("");
            loiNhuanLb.setText("");
            doanhThuLb.setText(String.valueOf(manager.tinhTongCPPS(list)));

            for (int i = 0; i < list.size(); i++) {
                CostStatistics cp = new CostStatistics();
                cp = list.get(i);
                String[] rowData = dataRow(cp);
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public String getStringDate() {
        String str = "and date_format(a.creat_date, '%y/%m/%dd') >= "
                + "DATE('" + String.valueOf(tuNamCB.getSelectedItem())
                + "/" + String.valueOf(tuThangCB.getSelectedItem())
                + "/" + String.valueOf(tuNgayCB.getSelectedItem())
                + "') "
                + "and date_format(a.creat_date, '%y/%m/%dd') <= "
                + "DATE('" + String.valueOf(denNamCB.getSelectedItem())
                + "/" + String.valueOf(denThangCB.getSelectedItem())
                + "/" + String.valueOf(denNgayCB.getSelectedItem())
                + "') ";
        return str;
    }

    public String getStringTK(String pro) {
        String getText = timKiemTF.getText();
        String str = " and (" + pro + ".MaSanPham like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%' or " + pro + ".TenSanPham like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%' or " + pro + ".TheLoai like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%' or a.so_luong like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%' or a.usename like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%' or a.creat_date like '"
                + getText + "%%%%%%%%%%%%%%%%%%%%%%%%%%%')";
        return str;
    }

//Other-------------------------------------------------------------------------
    public String[] dataRow(ProductStatistics pro) {
        String[] rows = {
            pro.getMaSanPham(),
            pro.getTenSanPham(),
            pro.getTheLoai(),
            String.valueOf(pro.getSoLuong()),
            pro.getUser(),
            pro.getNgayThang()
        };
        return rows;
    }

    public String[] dataRow(CostStatistics cp) {
        String[] rows = {
            cp.getMaChiPhi(),
            cp.getTen(),
            String.valueOf(cp.getSoTien()),
            cp.getLoaiChiPhi(),
            cp.getUser(),
            cp.getNgayPhatSinh(),
            cp.getGhiChu()
        };
        return rows;
    }

    public String[] ngay() {
        String[] data = new String[31];
        for (int i = 0; i < data.length; i++) {
            data[i] = String.valueOf(i + 1);
        }
        return data;
    }

    public String[] creatNumber(int n, int a) {
        String[] data = new String[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = String.valueOf(i + a);
        }
        return data;
    }

    public void createDateOnComboBox(JComboBox ngayCB, JComboBox thangCB, JComboBox namCB) {
        ngayCB.setModel(new DefaultComboBoxModel<>(creatNumber(31, 1)));
        thangCB.setModel(new DefaultComboBoxModel<>(creatNumber(12, 1)));
        namCB.setModel(new DefaultComboBoxModel<>(creatNumber(30, 2000)));
        namCB.setSelectedIndex(16);
    }

    public void setDateOnComboBoxWithCondition(JComboBox ngayCB, JComboBox thangCB, JComboBox namCB) {
        int ngay = Integer.valueOf(String.valueOf(ngayCB.getSelectedItem()));
        int thang = Integer.valueOf(String.valueOf(thangCB.getSelectedItem()));
        int nam = Integer.valueOf((String) namCB.getSelectedItem());
        if (thang == 2) {
            if ((nam % 4) == 0) {
                ngayCB.setModel(new DefaultComboBoxModel<>(creatNumber(29, 1)));
            } else {
                ngayCB.setModel(new DefaultComboBoxModel<>(creatNumber(28, 1)));
            }
        } else if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
            ngayCB.setModel(new DefaultComboBoxModel<>(creatNumber(31, 1)));
        } else {
            ngayCB.setModel(new DefaultComboBoxModel<>(creatNumber(30, 1)));
        }
    }

    public void setTrueItem() {
        thongKeLb.setEnabled(true);
        choseCB.setEnabled(true);
        thongKeTheoCB.setEnabled(true);
        timKiemTF.setEnabled(true);
        jTable1.setEnabled(true);
    }

    public void setFalseItem() {
        thongKeLb.setEnabled(false);
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
        jTable1.setEnabled(false);
    }

    public void checkTime() {

        int d1 = Integer.valueOf((String) tuNgayCB.getSelectedItem());
        int m1 = Integer.valueOf((String) tuThangCB.getSelectedItem());
        int y1 = Integer.valueOf((String) tuNamCB.getSelectedItem());
        int d2 = Integer.valueOf((String) denNgayCB.getSelectedItem());
        int m2 = Integer.valueOf((String) denThangCB.getSelectedItem());
        int y2 = Integer.valueOf((String) denNamCB.getSelectedItem());

        if (y1 < y2) {
            notifyOK();
            setTrueItem();
        } else if (y1 == y2) {
            if (m1 < m2) {
                notifyOK();
                setTrueItem();
            } else if (m1 == m2) {
                if (d1 <= d2) {
                    notifyOK();
                    setTrueItem();
                } else {
                    notifyNgay();
                    setFalseItem();
                }
            } else {
                notifyThang();
                setFalseItem();
            }
        } else {
            notifyNam();
            setFalseItem();
        }
    }

    public void runChooseCB() {
        if (choseCB.getSelectedIndex() == 0) {
            thongKeTheoCB.setEnabled(true);
            if (thongKeTheoCB.getSelectedIndex() == 0) {

                //Thong ke tat ca cac san pham ban
                String query = "select bk.MaSanPham,"
                        + "bk.TenSanPham,"
                        + "bk.TheLoai,"
                        + "bk.GiaNhap,"
                        + "bk.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date "
                        + "from book as bk"
                        + ",action_log as a"
                        + " where bk.MaSanPham = a.ma_sp"
                        + " and a.action_type='1' "
                        + getStringDate()
                        + getStringTK("bk");

                getDataProductToTableSell(query);
                System.out.println(query);

            } else if (thongKeTheoCB.getSelectedIndex() == 1) {

                //
                String query = "select mv.MaSanPham,"
                        + "mv.TenSanPham,"
                        + "mv.TheLoai,"
                        + "mv.GiaNhap,"
                        + "mv.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from movie as mv"
                        + ",action_log as a"
                        + " where mv.MaSanPham = a.ma_sp"
                        + " and a.action_type='1'"
                        + getStringDate()
                        + getStringTK("mv");

                getDataProductToTableSell(query);
                System.out.println(query);
            } else if (thongKeTheoCB.getSelectedIndex() == 2) {
                String query = "select ms.MaSanPham,"
                        + "ms.TenSanPham,"
                        + "ms.TheLoai,"
                        + "ms.GiaNhap,"
                        + "ms.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from music as ms"
                        + ",action_log as a"
                        + " where ms.MaSanPham = a.ma_sp"
                        + " and a.action_type='1'"
                        + getStringDate()
                        + getStringTK("ms");

                getDataProductToTableSell(query);
                System.out.println(query);
            } else {
                String query = "select bk.MaSanPham,"
                        + "bk.TenSanPham,"
                        + "bk.TheLoai,"
                        + "bk.GiaNhap,"
                        + "bk.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from book as bk,"
                        + "action_log as a "
                        + "where bk.MaSanPham = a.ma_sp and a.action_type='1'"
                        + getStringDate()
                        + getStringTK("bk")
                        + "union\n"
                        + "select mv.MaSanPham,"
                        + "mv.TenSanPham,"
                        + "mv.TheLoai,"
                        + "mv.GiaNhap,"
                        + "mv.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from movie as mv,"
                        + "action_log as a"
                        + " where mv.MaSanPham = a.ma_sp and a.action_type='1'"
                        + getStringDate()
                        + getStringTK("mv")
                        + "union\n"
                        + "select ms.MaSanPham,"
                        + "ms.TenSanPham,"
                        + "ms.TheLoai,"
                        + "ms.GiaNhap,"
                        + "ms.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from music as ms,"
                        + "action_log as a "
                        + "where ms.MaSanPham = a.ma_sp and a.action_type='1'"
                        + getStringDate()
                        + getStringTK("ms");

                getDataProductToTableSell(query);
                System.out.println(query);

            }
        } else if (choseCB.getSelectedIndex() == 1) {
            thongKeTheoCB.setEnabled(true);
            if (thongKeTheoCB.getSelectedIndex() == 0) {

                //Thong ke sach nhap kho
                String query = "select bk.MaSanPham,"
                        + "bk.TenSanPham,"
                        + "bk.TheLoai,"
                        + "bk.GiaNhap,"
                        + "bk.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from book as bk"
                        + ",action_log as a"
                        + " where bk.MaSanPham = a.ma_sp"
                        + " and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("bk");
                getDataProductToTableInput(query);

            } else if (thongKeTheoCB.getSelectedIndex() == 1) {

                //Thong ke phim nhap kho
                String query = "select mv.MaSanPham,"
                        + "mv.TenSanPham,"
                        + "mv.TheLoai,"
                        + "mv.GiaNhap,"
                        + "mv.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from movie as mv"
                        + ",action_log as a"
                        + " where mv.MaSanPham = a.ma_sp"
                        + " and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("mv");
                getDataProductToTableInput(query);

            } else if (thongKeTheoCB.getSelectedIndex() == 2) {

                //Thong ke nhac nhap kho
                String query = "select ms.MaSanPham,"
                        + "ms.TenSanPham,"
                        + "ms.TheLoai,"
                        + "ms.GiaNhap,"
                        + "ms.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from music as ms"
                        + ",action_log as a"
                        + " where ms.MaSanPham = a.ma_sp"
                        + " and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("ms");
                getDataProductToTableInput(query);

            } else {

                //Thong ke tat ca san pham nhap kho
                String query = "select bk.MaSanPham,"
                        + "bk.TenSanPham,"
                        + "bk.TheLoai,"
                        + "bk.GiaNhap,"
                        + "bk.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from book as bk,"
                        + "action_log as a "
                        + "where bk.MaSanPham = a.ma_sp and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("bk")
                        + " union\n"
                        + "select mv.MaSanPham,"
                        + "mv.TenSanPham,"
                        + "mv.TheLoai,"
                        + "mv.GiaNhap,"
                        + "mv.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from movie as mv,"
                        + "action_log as a"
                        + " where mv.MaSanPham = a.ma_sp and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("mv")
                        + " union\n"
                        + "select ms.MaSanPham,"
                        + "ms.TenSanPham,"
                        + "ms.TheLoai,"
                        + "ms.GiaNhap,"
                        + "ms.GiaBan,"
                        + "a.so_luong,"
                        + "a.usename,"
                        + "a.creat_date  "
                        + "from music as ms,"
                        + "action_log as a "
                        + "where ms.MaSanPham = a.ma_sp and a.action_type='2'"
                        + getStringDate()
                        + getStringTK("ms");
                getDataProductToTableInput(query);
                System.out.println(query);

            }
        } else {

            //Thong ke chi phi phat sinh
            thongKeTheoCB.setEnabled(false);
            String query = "select cp.MaChiPhi,"
                    + "cp.TenChiPhi,"
                    + "cp.SoTien,"
                    + "cp.LoaiChiPhi,"
                    + "a.usename,"
                    + "cp.NgayPhatSinh,"
                    + "cp.GhiChu "
                    + "from action_log as a,"
                    + " chi_phi as cp "
                    + "where a.ma_sp = cp.MaChiPhi "
                    + getStringDate();

            getDataCPToTable(query);

        }
    }

//Notify------------------------------------------------------------------------
    public void notifyNgay() {
        JOptionPane.showMessageDialog(this, "Khoảng thời gian đã chọn không tồn tại. Sửa lại ngày !");
    }

    public void notifyThang() {
        JOptionPane.showMessageDialog(this, "Khoảng thời gian đã chọn không tồn tại. Sửa lại tháng !");
    }

    public void notifyNam() {
        JOptionPane.showMessageDialog(this, "Khoảng thời gian đã chọn không tồn tại. Sửa lại năm !");
    }

    public void notifyOK() {
        JOptionPane.showMessageDialog(this, "OK. Bạn có thể bắt đầu xem thống kê !");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backBt = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tuNgayCB = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tuThangCB = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tuNamCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        denNamCB = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        denNgayCB = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        denThangCB = new javax.swing.JComboBox<>();
        choseCB = new javax.swing.JComboBox<>();
        thongKeLb = new javax.swing.JLabel();
        timKiemTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        thongKeTheoCB = new javax.swing.JComboBox<>();
        changeLb1 = new javax.swing.JLabel();
        changeLb2 = new javax.swing.JLabel();
        doanhThuLb = new javax.swing.JLabel();
        loiNhuanLb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/chart.png"))); // NOI18N
        jLabel1.setText("Thống kê");

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/clock.png"))); // NOI18N
        jLabel2.setText("Thời gian");

        jLabel3.setText("Từ :");

        jLabel4.setText("Đến :");

        jLabel5.setText("Ngày");

        tuNgayCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuNgayCBActionPerformed(evt);
            }
        });

        jLabel6.setText("Tháng");

        tuThangCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuThangCBActionPerformed(evt);
            }
        });

        jLabel7.setText("Năm");

        tuNamCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuNamCBActionPerformed(evt);
            }
        });

        jLabel8.setText("Năm");

        denNamCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denNamCBActionPerformed(evt);
            }
        });

        jLabel9.setText("Ngày");

        denNgayCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denNgayCBActionPerformed(evt);
            }
        });

        jLabel10.setText("Tháng");

        denThangCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denThangCBActionPerformed(evt);
            }
        });

        choseCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bán hàng", "Nhập hàng", "Chi phí" }));
        choseCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choseCBActionPerformed(evt);
            }
        });

        thongKeLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/search16x16 .png"))); // NOI18N
        thongKeLb.setText("Thống kê theo");

        timKiemTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timKiemTFKeyReleased(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/calculator_accept.png"))); // NOI18N
        jLabel12.setText("Tính tổng");

        thongKeTheoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sách", "Phim", "Nhạc", "All" }));
        thongKeTheoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongKeTheoCBActionPerformed(evt);
            }
        });

        changeLb1.setText("Doanh thu");

        changeLb2.setText("Lợi nhuận");

        doanhThuLb.setText("0.0");

        loiNhuanLb.setText("0.0");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Check time");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(choseCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tuNgayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(denNgayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(denThangCB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(tuThangCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(denNamCB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tuNamCB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(thongKeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(thongKeTheoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(74, 74, 74)
                                .addComponent(timKiemTF, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(doanhThuLb, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loiNhuanLb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(tuNgayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tuThangCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tuNamCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(denNgayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(denThangCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(denNamCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choseCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thongKeLb)
                    .addComponent(timKiemTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thongKeTheoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeLb1)
                    .addComponent(changeLb2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(doanhThuLb)
                    .addComponent(loiNhuanLb))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:
        ManagerView manager = new ManagerView();
        manager.setName(getName());
        manager.setUsername(getUsername());
        manager.setTypeUser(getTypeUser());
        manager.init();
        con.closeConnectDB();
        this.dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void tuThangCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuThangCBActionPerformed
        // TODO add your handling code here:   
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
        setDateOnComboBoxWithCondition(tuNgayCB, tuThangCB, tuNamCB);

    }//GEN-LAST:event_tuThangCBActionPerformed

    private void denThangCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denThangCBActionPerformed
        // TODO add your handling code here:
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
        setDateOnComboBoxWithCondition(denNgayCB, denThangCB, denNamCB);

    }//GEN-LAST:event_denThangCBActionPerformed

    private void tuNamCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuNamCBActionPerformed
        // TODO add your handling code here:   
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
        setDateOnComboBoxWithCondition(tuNgayCB, tuThangCB, tuNamCB);

    }//GEN-LAST:event_tuNamCBActionPerformed

    private void denNamCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denNamCBActionPerformed
        // TODO add your handling code here:   
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
        setDateOnComboBoxWithCondition(denNgayCB, denThangCB, denNamCB);

    }//GEN-LAST:event_denNamCBActionPerformed

    private void choseCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choseCBActionPerformed
        // TODO add your handling code here:   
        runChooseCB();
    }//GEN-LAST:event_choseCBActionPerformed

    private void thongKeTheoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thongKeTheoCBActionPerformed
        // TODO add your handling code here:
        runChooseCB();
    }//GEN-LAST:event_thongKeTheoCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        checkTime();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tuNgayCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuNgayCBActionPerformed
        // TODO add your handling code here:
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
    }//GEN-LAST:event_tuNgayCBActionPerformed

    private void denNgayCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denNgayCBActionPerformed
        // TODO add your handling code here:
        choseCB.setEnabled(false);
        thongKeTheoCB.setEnabled(false);
        timKiemTF.setEnabled(false);
    }//GEN-LAST:event_denNgayCBActionPerformed

    private void timKiemTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timKiemTFKeyReleased
        // TODO add your handling code here:
        runChooseCB();
    }//GEN-LAST:event_timKiemTFKeyReleased

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
            java.util.logging.Logger.getLogger(StatisticsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatisticsView().setVisible(true);
            }
        });
    }

    CreateConnectionDB con = new CreateConnectionDB();

    Manager manager = new Manager();

    String[] colsName = {"Mã SP", "Tên SP", "Thể Loại", "SL", "Nhân viên", "Ngày tháng"};

    String[] colsNameCP = {"Mã CP", "Tên CP", "Số tiền", "Loại CP", "Nhân viên", "Ngày tháng", "Ghi chú"};

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton backBt;
    private javax.swing.JLabel changeLb1;
    private javax.swing.JLabel changeLb2;
    private javax.swing.JComboBox<String> choseCB;
    private javax.swing.JComboBox<String> denNamCB;
    private javax.swing.JComboBox<String> denNgayCB;
    private javax.swing.JComboBox<String> denThangCB;
    private javax.swing.JLabel doanhThuLb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel loiNhuanLb;
    private javax.swing.JLabel thongKeLb;
    private javax.swing.JComboBox<String> thongKeTheoCB;
    private javax.swing.JTextField timKiemTF;
    private javax.swing.JComboBox<String> tuNamCB;
    private javax.swing.JComboBox<String> tuNgayCB;
    private javax.swing.JComboBox<String> tuThangCB;
    // End of variables declaration//GEN-END:variables
}
