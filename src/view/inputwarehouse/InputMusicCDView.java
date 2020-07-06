/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.view.inputwarehouse;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project1.core.connection.CreateConnectionDB;
import project1.core.other.TimeSystem;
import project1.core.product.Music;
import project1.core.employee.EmployeeWarehouse;
import project1.core.other.SetDataToObject;
import project1.core.product.ActionLog;
import project1.view.sell.SellProductsView;

/**
 *
 * @author lequangbkhn
 */
public class InputMusicCDView extends javax.swing.JFrame {

    /**
     * Creates new form InputMusicCDView
     */
    public InputMusicCDView() {

    }

    public void init() {
        initComponents();
        setResizable(false);
        setVisible(true);
    }

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
//Getter -----------------------------------------------------------------------

    public String getMaSanPhamTF() {
        return maSanPhamTF.getText();
    }

    public String getTenSanPhamTF() {
        return tenSanPhamTF.getText();
    }

    public String getCaSiTF() {
        return caSiTF.getText();
    }

    public String getTheLoaiTF() {
        return theLoaiTF.getText();
    }

    public String getNSXTF() {
        return NSXTF.getText();
    }

    public int getGiaNhapTF() {
        return Integer.valueOf(giaNhapTF.getText());
    }

    public int getGiaBanTF() {
        return Integer.valueOf(giaBanTF.getText());
    }

    public int getSoLuongTF() {
        return Integer.valueOf(soLuongTF.getText());
    }

    public int getIndexList(List<Music> list, String maSanPham) {
        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            if (maSanPham.equals(list.get(i).getMaSanPham())) {
                a = i;
                break;
            }
        }
        return a;
    }

    public Music getMusic(List<Music> list, int index) {
        return list.get(index);
    }

    public Music getInfomationFromTF() {
        Music music = new Music();

        music.setGiaBan(Integer.valueOf(giaBanTF.getText()));
        music.setGiaNhap(Integer.valueOf(giaNhapTF.getText()));
        music.setMaSanPham(maSanPhamTF.getText());
        music.setNSX(NSXTF.getText());
        music.setSoLuong(Integer.valueOf(soLuongTF.getText()));
        music.setCaSi(caSiTF.getText());
        music.setTenSanPham(tenSanPhamTF.getText());
        music.setTheLoai(theLoaiTF.getText());
        music.setType(Music.TYPE_MUSIC);

        return music;
    }

    public String[] dataRow(Music music) {
        String[] rows = {
            music.getMaSanPham(),
            music.getTenSanPham(),
            music.getCaSi(),
            music.getTheLoai(),
            music.getNSX(),
            String.valueOf(music.getGiaNhap()),
            String.valueOf(music.getGiaBan()),
            String.valueOf(music.getSoLuong())
        };
        return rows;
    }

    public void setTextSpace() {
        maSanPhamTF.setText("");
        tenSanPhamTF.setText("");
        caSiTF.setText("");
        theLoaiTF.setText("");
        NSXTF.setText("");
        giaNhapTF.setText("");
        giaBanTF.setText("");
        soLuongTF.setText("");
    }

//Check data -------------------------------------------------------------------
    public boolean checkInput() {
        boolean check;
        if ((tenSanPhamTF.getText().equals(""))
                || (maSanPhamTF.getText().equals(""))
                || (caSiTF.getText().equals(""))
                || (theLoaiTF.getText().equals(""))
                || (NSXTF.getText().equals(""))
                || (giaBanTF.getText().equals(""))
                || (giaNhapTF.getText().equals(""))
                || (soLuongTF.getText().equals(""))) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public boolean checkNumberInt(String s) {
        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean checkMaSanPham(String maSanPham) {
        for (int i = 0; i < bill.size(); i++) {
            if (maSanPham.equals(bill.get(i).getMaSanPham())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMaSanPham(String maSanPham, int indexList) {
        int i;
        for (i = 0; i < bill.size(); i++) {
            if (i == indexList) {
                continue;
            } else if (maSanPham.equals(bill.get(i).getMaSanPham())) {
                return false;
            }
        }
        return true;
    }

//Notify------------------------------------------------------------------------
    public void notifySaiSoLuong() {
        JOptionPane.showMessageDialog(this, "Nhập sai số lượng, giá bán hoặc giá nhập !");
    }

    public int notifyWrongInput() {
        JOptionPane jOP = new JOptionPane();
        int x = jOP.showConfirmDialog(this, "Bạn chắc chắn muốn thêm ?");
        return x;
    }

    public void thieuThongTinSanPham() {
        JOptionPane.showMessageDialog(this, "Nhập thiếu thông tin sản phẩm !");
    }

    public void notifyChoseRow() {
        JOptionPane.showMessageDialog(this, "Hãy chọn 1 hàng trong bảng !");
    }

    public void notifyMaSanPham() {
        JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại vui lòng sửa trong bảng !");
    }

    public void notifyNumber() {
        JOptionPane.showMessageDialog(this, "Nhập sai số lượng, giá bán hoặc giá nhập !");
    }

    public void notifyThieuThongTinSanPham() {
        JOptionPane.showMessageDialog(this, "Nhập thiếu thông tin sản phẩm !");
    }

    public void notifyThemThanhCong() {
        JOptionPane.showMessageDialog(this, "Thêm thành công !");
    }

    public void notifySuaThanhCong() {
        JOptionPane.showMessageDialog(this, "Sửa thành công !");
    }

    public void notifyXoaThanhCong() {
        JOptionPane.showMessageDialog(this, "Xóa thành công !");
    }

    public void notifyPrintBill() {
        JOptionPane.showMessageDialog(this, "Chưa nhập sản phẩm nào !");
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
        jLabel1 = new javax.swing.JLabel();
        backBt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        giaNhapTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        caSiTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        NSXTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        soLuongTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        giaBanTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        themBt = new javax.swing.JButton();
        xoaBt = new javax.swing.JButton();
        inHDBt = new javax.swing.JButton();
        suaBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tongTienLb = new javax.swing.JLabel();
        theLoaiTF = new javax.swing.JTextField();
        maSanPhamTF = new javax.swing.JTextField();
        tenSanPhamTF = new javax.swing.JTextField();
        notifyMSPLb = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/sound.png"))); // NOI18N
        jLabel1.setText("NHẬP THÔNG TIN SẢN PHẨM");

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(backBt)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backBt)))
        );

        jLabel2.setText("Tên SP");

        jLabel3.setText("Mã SP");

        jLabel4.setText("Thể Loại");

        jLabel6.setText("Ca sĩ");

        jLabel7.setText("NSX");

        jLabel9.setText("Giá bán");

        jLabel10.setText("Giá nhập");

        jLabel5.setText("Số lượng");

        themBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/add16.png"))); // NOI18N
        themBt.setText("Thêm");
        themBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtActionPerformed(evt);
            }
        });

        xoaBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/remove16.png"))); // NOI18N
        xoaBt.setText("Xóa");
        xoaBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBtActionPerformed(evt);
            }
        });

        inHDBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/printer16.png"))); // NOI18N
        inHDBt.setText("In HĐ");
        inHDBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inHDBtActionPerformed(evt);
            }
        });

        suaBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/process_1.png"))); // NOI18N
        suaBt.setText("Sửa");
        suaBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaBtActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Ca sĩ", "Thể loại", "NSX", "Giá nhập", "Giá bán", "SL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setText("Tổng tiền");

        jLabel11.setText("Thời gian");

        jLabel12.setText(String.valueOf(new Date()));

        tongTienLb.setText("0.0");

        maSanPhamTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                maSanPhamTFKeyReleased(evt);
            }
        });

        notifyMSPLb.setForeground(new java.awt.Color(204, 0, 0));

        jLabel13.setText("Nhân viên nhập kho :");

        jLabel14.setText(getName());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tongTienLb, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(xoaBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(themBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(suaBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(inHDBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(theLoaiTF)
                                    .addComponent(maSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(caSiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(giaNhapTF, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(giaBanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tenSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NSXTF, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(NSXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(giaNhapTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(theLoaiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(caSiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giaBanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(themBt)
                        .addGap(18, 18, 18)
                        .addComponent(xoaBt)
                        .addGap(18, 18, 18)
                        .addComponent(suaBt)
                        .addGap(18, 18, 18)
                        .addComponent(inHDBt))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tongTienLb)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtActionPerformed
        // TODO add your handling code here:
        Music music;
        if (checkInput()) {
            if (checkNumberInt(soLuongTF.getText())
                    && checkNumberInt(giaBanTF.getText())
                    && checkNumberInt(giaNhapTF.getText())) {
                if (checkMaSanPham(maSanPhamTF.getText())) {
                    //
                    music = getInfomationFromTF();
                    //
                    tableModel.setColumnIdentifiers(colsName);
                    jTable1.setModel(tableModel);
                    //
                    String[] rows = dataRow(music);
                    tableModel.addRow(rows);
                    empWH.themMusic(bill, music);
                    //
                    setTextSpace();
                    tongTienLb.setText(String.valueOf(empWH.tongTienMusic(bill)));
                    notifyMSPLb.setText("");
                    //
                    notifyThemThanhCong();
                } else {
                    notifyMaSanPham();
                }
            } else {
                notifyNumber();
            }
        } else {
            notifyThieuThongTinSanPham();
        }


    }//GEN-LAST:event_themBtActionPerformed

    private void xoaBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBtActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == -1)) {
            empWH.xoaMusic(bill, indexTable);
            tableModel.removeRow(indexTable);
            //
            setTextSpace();
            tongTienLb.setText(String.valueOf(empWH.tongTienMusic(bill)));
            themBt.setEnabled(true);
            notifyMSPLb.setText("");
            //
            notifyXoaThanhCong();
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_xoaBtActionPerformed

    private void inHDBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inHDBtActionPerformed
        // TODO add your handling code here:
        if (!bill.isEmpty()) {
            for (int i = 0; i < bill.size(); i++) {
                Music music = bill.get(i);
                String query1 = "select * from music where MaSanPham ='"
                        + music.getMaSanPham() + "' limit 1";
                ResultSet rs = con.select(query1);
                Music temp = new Music();
                new SetDataToObject().setMusic(rs, temp);
                if (temp.getMaSanPham() != null) {
                    String query2 = "update music set SoLuong="
                            + ((temp.getSoLuong() + music.getSoLuong()))
                            + " where MaSanPham='" + music.getMaSanPham() + "'";
                    con.update(query2);
                } else {
                    String query3 = "insert into music (MaSanPham,TenSanPham,TheLoai,CaSi,NSX,SoLuong,GiaNhap,GiaBan) "
                            + "value ('" + music.getMaSanPham() + "','"
                            + music.getTenSanPham() + "','"
                            + music.getTheLoai() + "','"
                            + music.getCaSi() + "','"
                            + music.getNSX() + "',"
                            + music.getSoLuong() + ","
                            + music.getGiaNhap() + ","
                            + music.getGiaBan() + ")";
                    con.insert(query3);
                }
                try {
                    //
                    ActionLog actionLog = new ActionLog();
                    actionLog.setCreatedDate(new Date());
                    actionLog.setMsp(music.getMaSanPham());
                    actionLog.setUsername(getUsername());
                    actionLog.setActionType(ActionLog.TYPE_ADD);
                    actionLog.setProductType(Music.TYPE_MUSIC);
                    actionLog.setSoLuong(music.getSoLuong());

                    String query4 = "insert into action_log(usename,so_luong,product_type,ma_sp,creat_date,action_type) "
                            + " values('" + actionLog.getUsername()
                            + "'," + actionLog.getSoLuong()
                            + "," + actionLog.getProductType()
                            + ",'" + actionLog.getMsp()
                            + "',now()"
                            + "," + actionLog.getActionType() + ")";
                    con.insert(query4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            JFileChooser chooser = new JFileChooser(); // gọi lên dailog chọn vị trí lưu file
            int i = chooser.showSaveDialog(this); // làm hiện dailog save
            if (i == JFileChooser.APPROVE_OPTION) { // nếu vị trí đã được chọn
                empWH.inHoaDon(jTable1, chooser, String.valueOf(empWH.tongTienMusic(bill)), getName());
            }
        } else {
            notifyPrintBill();
        }

        bill.removeAll(bill);
        int rowSize = tableModel.getRowCount();
        for (int j = 0; j < rowSize; j++) {
            tableModel.removeRow(0);
        }
        tongTienLb.setText(String.valueOf(empWH.tongTienMusic(bill)));

    }//GEN-LAST:event_inHDBtActionPerformed

    private void suaBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBtActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == -1)) {
            if (checkInput()) {
                if (checkNumberInt(soLuongTF.getText())
                        && checkNumberInt(giaBanTF.getText())
                        && checkNumberInt(giaNhapTF.getText())) {
                    //
                    String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
                    int indexList = getIndexList(bill, maSanPham);
                    //
                    Music music = new Music();
                    if (checkMaSanPham(maSanPhamTF.getText(), indexList)) {
                        Music music1 = getInfomationFromTF();
                        //
                        bill.remove(indexList);
                        music = empWH.suaMusic(bill, music1, indexList);
                        //
                        tableModel.setColumnIdentifiers(colsName);
                        jTable1.setModel(tableModel);
                        String[] rowData = dataRow(music);
                        //
                        tableModel.removeRow(indexTable);
                        tableModel.insertRow(indexTable, rowData);
                        //
                        setTextSpace();
                        tongTienLb.setText(String.valueOf(empWH.tongTienMusic(bill)));
                        themBt.setEnabled(true);
                        notifyMSPLb.setText("");
                        //
                        notifySuaThanhCong();
                    } else {
                        notifyMaSanPham();
                    }
                } else {
                    notifyNumber();
                }
            } else {
                notifyThieuThongTinSanPham();
            }
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_suaBtActionPerformed

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:
        WarehouseView warehouse = new WarehouseView();
        warehouse.setUsername(getUsername());
        warehouse.setName(getName());
        warehouse.setTypeUser(getTypeUser());
        warehouse.init();
        con.closeConnectDB();
        this.dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
        int indexList = getIndexList(bill, maSanPham);
        Music music = getMusic(bill, indexList);
        //
        tenSanPhamTF.setText(music.getTenSanPham());
        maSanPhamTF.setText(music.getMaSanPham());
        theLoaiTF.setText(music.getTheLoai());
        giaBanTF.setText(String.valueOf(music.getGiaBan()));
        giaNhapTF.setText(String.valueOf(music.getGiaNhap()));
        soLuongTF.setText(String.valueOf(music.getSoLuong()));
        caSiTF.setText(music.getCaSi());
        NSXTF.setText(music.getNSX());
        themBt.setEnabled(false);
        notifyMSPLb.setText("");
    }//GEN-LAST:event_jTable1MouseClicked

    private void maSanPhamTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maSanPhamTFKeyReleased
        // TODO add your handling code here:
        String getText = maSanPhamTF.getText();
        String query5 = "select * from music where MaSanPham='" + getText + "' limit 1";
        ResultSet rs = con.select(query5);
        Music temp = new Music();
        new SetDataToObject().setMusic(rs, temp);
        if (temp.getMaSanPham() != null) {
            tenSanPhamTF.setText(temp.getTenSanPham());
            theLoaiTF.setText(temp.getTheLoai());
            giaBanTF.setText(String.valueOf(temp.getGiaBan()));
            giaNhapTF.setText(String.valueOf(temp.getGiaNhap()));
            NSXTF.setText(temp.getNSX());
            caSiTF.setText(temp.getCaSi());
            notifyMSPLb.setText("");
        } else {
            notifyMSPLb.setText("Mã sản phẩm chưa tồn tại vui lòng nhập đủ thông tin !");
            tenSanPhamTF.setText("");
            caSiTF.setText("");
            theLoaiTF.setText("");
            NSXTF.setText("");
            giaBanTF.setText("");
            giaNhapTF.setText("");
            soLuongTF.setText("");
        }
    }//GEN-LAST:event_maSanPhamTFKeyReleased

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
            java.util.logging.Logger.getLogger(SellProductsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellProductsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellProductsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellProductsView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputMusicCDView().setVisible(true);
            }
        });
    }

    CreateConnectionDB con = new CreateConnectionDB();

    TimeSystem time = new TimeSystem();

    DefaultTableModel tableModel = new DefaultTableModel();

    String[] colsName = {"Mã", "Tên SP", "Ca sĩ", "Thể loại", "NSX", "Giá nhập", "Giá bán", "SL"};

    List<Music> bill = new LinkedList<Music>();

    EmployeeWarehouse empWH = new EmployeeWarehouse();

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NSXTF;
    private javax.swing.JButton backBt;
    private javax.swing.JTextField caSiTF;
    private javax.swing.JTextField giaBanTF;
    private javax.swing.JTextField giaNhapTF;
    private javax.swing.JButton inHDBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField maSanPhamTF;
    private javax.swing.JLabel notifyMSPLb;
    private javax.swing.JTextField soLuongTF;
    private javax.swing.JButton suaBt;
    private javax.swing.JTextField tenSanPhamTF;
    private javax.swing.JTextField theLoaiTF;
    private javax.swing.JButton themBt;
    private javax.swing.JLabel tongTienLb;
    private javax.swing.JButton xoaBt;
    // End of variables declaration//GEN-END:variables
}
