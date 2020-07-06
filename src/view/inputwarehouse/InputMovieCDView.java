/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.view.inputwarehouse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project1.core.connection.CreateConnectionDB;
import project1.core.product.Movie;
import project1.core.employee.EmployeeWarehouse;
import project1.core.other.SetDataToObject;
import project1.core.product.ActionLog;
import project1.view.sell.SellProductsView;

/**
 *
 * @author lequangbkhn
 */
public class InputMovieCDView extends javax.swing.JFrame {

    /**
     * Creates new form InputMovieCDView
     */
    public InputMovieCDView() {

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

    public String getDaoDienTF() {
        return daoDienTF.getText();
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

    public int getIndexList(List<Movie> list, String maSanPham) {
        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            if (maSanPham.equals(list.get(i).getMaSanPham())) {
                a = i;
                break;
            }
        }
        return a;
    }

    public Movie getMovie(List<Movie> list, int index) {
        return list.get(index);
    }

    public Movie getInfomationFromTF() {
        Movie movie = new Movie();

        movie.setGiaBan(Integer.valueOf(giaBanTF.getText()));
        movie.setGiaNhap(Integer.valueOf(giaNhapTF.getText()));
        movie.setMaSanPham(maSanPhamTF.getText());
        movie.setNSX(NSXTF.getText());
        movie.setSoLuong(Integer.valueOf(soLuongTF.getText()));
        movie.setDaoDien(daoDienTF.getText());
        movie.setTenSanPham(tenSanPhamTF.getText());
        movie.setTheLoai(theLoaiTF.getText());
        movie.setType(Movie.TYPE_MOVIE);

        return movie;
    }

    public String[] dataRow(Movie movie) {
        String[] rows = {
            movie.getMaSanPham(),
            movie.getTenSanPham(),
            movie.getDaoDien(),
            movie.getTheLoai(),
            movie.getNSX(),
            String.valueOf(movie.getGiaNhap()),
            String.valueOf(movie.getGiaBan()),
            String.valueOf(movie.getSoLuong())
        };
        return rows;
    }

    public void setTextSpace() {
        maSanPhamTF.setText("");
        tenSanPhamTF.setText("");
        daoDienTF.setText("");
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
                || (daoDienTF.getText().equals(""))
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

    public boolean checkNumberInteger(String s) {
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

//Notify -----------------------------------------------------------------------
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tenSanPhamTF = new javax.swing.JTextField();
        daoDienTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NSXTF = new javax.swing.JTextField();
        giaBanTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        soLuongTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        giaNhapTF = new javax.swing.JTextField();
        themBt = new javax.swing.JButton();
        xoaBt = new javax.swing.JButton();
        inHDBt = new javax.swing.JButton();
        suaBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tongTienLb = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        theLoaiTF = new javax.swing.JTextField();
        maSanPhamTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        notifyMSPLb = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        backBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/movie_track_add.png"))); // NOI18N
        jLabel1.setText("NHẬP THÔNG TIN SẢN PHẨM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(303, 303, 303))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setText("Thể loại");

        jLabel4.setText("Đạo diễn");

        jLabel5.setText("Mã SP");

        jLabel6.setText("NSX");

        jLabel7.setText("Số lượng");

        jLabel8.setText("Giá bán");

        jLabel9.setText("Giá nhập");

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

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đạo diễn", "Thể loại", "NSX", "Giá nhập", "Giá bán", "SL"
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

        jLabel11.setText("Thời gian");

        jLabel12.setText(String.valueOf(new Date()));

        tongTienLb.setText("0.0");

        jLabel10.setText("Tổng tiền");

        maSanPhamTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                maSanPhamTFKeyReleased(evt);
            }
        });

        jLabel2.setText("Tên SP");

        notifyMSPLb.setForeground(new java.awt.Color(255, 0, 0));

        jLabel13.setText("Nhân viên nhập kho :");

        jLabel14.setText(getName());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(xoaBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(themBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(inHDBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suaBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tongTienLb, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(daoDienTF, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(theLoaiTF)
                                    .addComponent(maSanPhamTF))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(giaBanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(giaNhapTF, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(tenSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NSXTF, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(soLuongTF))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(NSXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(theLoaiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(giaNhapTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(daoDienTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(giaBanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(tongTienLb)
                    .addComponent(jLabel10))
                .addGap(33, 33, 33))
        );

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBt))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtActionPerformed
        // TODO add your handling code here:
        Movie movie;
        if (checkInput()) {
            if (checkNumberInt(soLuongTF.getText())
                    && checkNumberInteger(giaBanTF.getText())
                    && checkNumberInteger(giaNhapTF.getText())) {
                if (checkMaSanPham(maSanPhamTF.getText())) {
                    //
                    movie = getInfomationFromTF();
                    //
                    tableModel.setColumnIdentifiers(colsName);
                    jTable1.setModel(tableModel);
                    String[] rows = dataRow(movie);
                    //
                    tableModel.addRow(rows);
                    empWH.themMovie(bill, movie);
                    //
                    setTextSpace();
                    tongTienLb.setText(String.valueOf(empWH.tongTienMovie(bill)));
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
            empWH.xoaMovie(bill, indexTable);
            tableModel.removeRow(indexTable);
            //
            setTextSpace();
            tongTienLb.setText(String.valueOf(empWH.tongTienMovie(bill)));
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
                Movie movie = bill.get(i);
                String query1 = "select * from movie where MaSanPham ='"
                        + movie.getMaSanPham() + "' limit 1";
                ResultSet rs = con.select(query1);
                Movie temp = new Movie();
                new SetDataToObject().setMovie(rs, temp);
                if (temp.getMaSanPham() != null) {
                    String query2 = "update movie set SoLuong="
                            + ((temp.getSoLuong() + movie.getSoLuong()))
                            + " where MaSanPham='" + movie.getMaSanPham() + "'";
                    con.update(query2);
                } else {
                    String query3 = "insert into movie (MaSanPham,TenSanPham,TheLoai,DaoDien,NSX,SoLuong,GiaNhap,GiaBan) "
                            + "value ('" + movie.getMaSanPham() + "','"
                            + movie.getTenSanPham() + "','"
                            + movie.getTheLoai() + "','"
                            + movie.getDaoDien() + "','"
                            + movie.getNSX() + "',"
                            + movie.getSoLuong() + ","
                            + movie.getGiaNhap() + ","
                            + movie.getGiaBan() + ")";
                    con.insert(query3);
                }
                try {
                    //
                    ActionLog actionLog = new ActionLog();
                    actionLog.setCreatedDate(new Date());
                    actionLog.setMsp(movie.getMaSanPham());
                    actionLog.setUsername(getUsername());
                    actionLog.setActionType(ActionLog.TYPE_ADD);
                    actionLog.setProductType(Movie.TYPE_MOVIE);
                    actionLog.setSoLuong(movie.getSoLuong());

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
                empWH.inHoaDon(jTable1, chooser, String.valueOf(empWH.tongTienMovie(bill)), getName());
            }
        } else {
            notifyPrintBill();
        }

        bill.removeAll(bill);

        int rowSize = tableModel.getRowCount();
        for (int j = 0; j < rowSize; j++) {
            tableModel.removeRow(0);
        }
        tongTienLb.setText(String.valueOf(empWH.tongTienMovie(bill)));
    }//GEN-LAST:event_inHDBtActionPerformed

    private void suaBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBtActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == -1)) {
            if (checkInput()) {
                if (checkNumberInt(soLuongTF.getText())
                        && checkNumberInteger(giaBanTF.getText())
                        && checkNumberInteger(giaNhapTF.getText())) {
                    //
                    String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
                    int indexList = getIndexList(bill, maSanPham);
                    //
                    Movie movie = new Movie();
                    if (checkMaSanPham(maSanPhamTF.getText(), indexList)) {
                        Movie movie1 = getInfomationFromTF();

                        bill.remove(indexList);
                        movie = empWH.suaMovie(bill, movie1, indexList);

                        tableModel.setColumnIdentifiers(colsName);
                        jTable1.setModel(tableModel);
                        String[] rowData = dataRow(movie);
                        tableModel.removeRow(indexTable);
                        tableModel.insertRow(indexTable, rowData);
                        //
                        setTextSpace();
                        tongTienLb.setText(String.valueOf(empWH.tongTienMovie(bill)));
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
        //
        String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
        int indexList = getIndexList(bill, maSanPham);
        Movie movie = getMovie(bill, indexList);
        //
        tenSanPhamTF.setText(movie.getTenSanPham());
        maSanPhamTF.setText(movie.getMaSanPham());
        theLoaiTF.setText(movie.getTheLoai());
        giaBanTF.setText(String.valueOf(movie.getGiaBan()));
        giaNhapTF.setText(String.valueOf(movie.getGiaNhap()));
        soLuongTF.setText(String.valueOf(movie.getSoLuong()));
        daoDienTF.setText(movie.getDaoDien());
        NSXTF.setText(movie.getNSX());
        themBt.setEnabled(false);
        notifyMSPLb.setText("");

    }//GEN-LAST:event_jTable1MouseClicked

    private void maSanPhamTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maSanPhamTFKeyReleased
        // TODO add your handling code here:
        String getText = maSanPhamTF.getText();
        String query5 = "select * from movie where MaSanPham='" + getText + "' limit 1";
        ResultSet rs = con.select(query5);
        Movie temp = new Movie();
        new SetDataToObject().setMovie(rs, temp);
        if (temp.getMaSanPham() != null) {
            tenSanPhamTF.setText(temp.getTenSanPham());
            theLoaiTF.setText(temp.getTheLoai());
            giaBanTF.setText(String.valueOf(temp.getGiaBan()));
            giaNhapTF.setText(String.valueOf(temp.getGiaNhap()));
            NSXTF.setText(temp.getNSX());
            daoDienTF.setText(temp.getDaoDien());
            notifyMSPLb.setText("");
        } else {
            notifyMSPLb.setText("Mã sản phẩm chưa tồn tại vui lòng nhập đủ thông tin !");
            tenSanPhamTF.setText("");
            daoDienTF.setText("");
            theLoaiTF.setText("");
            NSXTF.setText("");
            giaBanTF.setText("");
            giaNhapTF.setText("");
            soLuongTF.setText("");
        }
    }//GEN-LAST:event_maSanPhamTFKeyReleased

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
                new InputMovieCDView().setVisible(true);
            }
        });
    }

    CreateConnectionDB con = new CreateConnectionDB();

    DefaultTableModel tableModel = new DefaultTableModel();

    String[] colsName = {"Mã", "Tên SP", "Đạo diễn", "Thể loại", "NSX", "Giá nhập", "Giá bán", "SL"};

    List<Movie> bill = new ArrayList<Movie>();

    EmployeeWarehouse empWH = new EmployeeWarehouse();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NSXTF;
    private javax.swing.JButton backBt;
    private javax.swing.JTextField daoDienTF;
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
