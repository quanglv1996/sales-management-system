/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.view.inputwarehouse;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project1.core.connection.CreateConnectionDB;
import project1.core.product.Book;
import project1.core.employee.EmployeeWarehouse;
import project1.core.other.SetDataToObject;
import project1.core.product.ActionLog;
import project1.view.sell.SellProductsView;

/**
 *
 * @author lequangbkhn
 */
public class InputBookView extends javax.swing.JFrame {

    /**
     * Creates new form InputBookView
     */
    public InputBookView() {

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

    public String getTacGiaTF() {
        return tacGiaTF.getText();
    }

    public String getTheLoaiTF() {
        return theLoaiTF.getText();
    }

    public String getNXBTF() {
        return NXBTF.getText();
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

    public int getIndexList(List<Book> list, String maSanPham) {
        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            if (maSanPham.equals(list.get(i).getMaSanPham())) {
                a = i;
                break;
            }
        }
        return a;
    }

    public Book getInfomationFromTF() {
        Book book = new Book();

        book.setGiaBan(getGiaBanTF());
        book.setGiaNhap(getGiaNhapTF());
        book.setMaSanPham(getMaSanPhamTF());
        book.setNXB(getNXBTF());
        book.setSoLuong(getSoLuongTF());
        book.setTacGia(getTacGiaTF());
        book.setTenSanPham(getTenSanPhamTF());
        book.setTheLoai(getTheLoaiTF());
        book.setType(Book.TYPE_BOOK);

        return book;
    }

    public Book getBook(List<Book> list, int index) {
        return list.get(index);
    }

    public String[] dataRow(Book book) {
        String[] rows = {
            book.getMaSanPham(),
            book.getTenSanPham(),
            book.getTacGia(),
            book.getTheLoai(),
            book.getNXB(),
            String.valueOf(book.getGiaNhap()),
            String.valueOf(book.getGiaBan()),
            String.valueOf(book.getSoLuong())
        };
        return rows;
    }

    public void setTextSpace() {
        maSanPhamTF.setText("");
        tenSanPhamTF.setText("");
        tacGiaTF.setText("");
        theLoaiTF.setText("");
        NXBTF.setText("");
        giaNhapTF.setText("");
        giaBanTF.setText("");
        soLuongTF.setText("");
    }

//Check data -------------------------------------------------------------------
    public boolean checkInput() {
        boolean check;
        if ((tenSanPhamTF.getText().equals(""))
                || (maSanPhamTF.getText().equals(""))
                || (tacGiaTF.getText().equals(""))
                || (theLoaiTF.getText().equals(""))
                || (NXBTF.getText().equals(""))
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

//Notify -----------------------------------------------------------------------
    public void notifyNumber() {
        JOptionPane.showMessageDialog(this, "Nhập sai số lượng, giá bán hoặc giá nhập !");
    }

    public int notifyWrongInput() {
        int x = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm ?");
        return x;
    }

    public void notifyThieuThongTinSanPham() {
        JOptionPane.showMessageDialog(this, "Nhập thiếu thông tin sản phẩm !");
    }

    public void notifyChoseRow() {
        JOptionPane.showMessageDialog(this, "Hãy chọn 1 hàng trong bảng !");
    }

    public void notifyMaSanPham() {
        JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại vui lòng sửa trong bảng !");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        maSanPhamTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        giaNhapTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tacGiaTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        NXBTF = new javax.swing.JTextField();
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
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tongTienLb = new javax.swing.JLabel();
        theLoaiTF = new javax.swing.JTextField();
        tenSanPhamTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        notifyMSPLb = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        backBt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Mã SP");

        maSanPhamTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                maSanPhamTFKeyReleased(evt);
            }
        });

        jLabel4.setText("Thể Loại");

        jLabel6.setText("Tác giả");

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

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Tác giả", "Thể loại", "NXB", "Giá nhập", "Giá bán", "SL"
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

        jLabel22.setText("Thời gian");

        jLabel23.setText(String.valueOf(new Date()));

        jLabel11.setText("NXB");

        tongTienLb.setText("0.0");

        jLabel2.setText("Tên SP");

        notifyMSPLb.setForeground(new java.awt.Color(255, 0, 0));

        jLabel12.setText("Nhân viên nhập kho :");

        jLabel13.setText(getName());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(xoaBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(themBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(suaBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inHDBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tongTienLb, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(maSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tacGiaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(giaBanTF)
                            .addComponent(tenSanPhamTF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giaNhapTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NXBTF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(722, 722, 722)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(maSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NXBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(theLoaiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tacGiaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tenSanPhamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giaNhapTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaBanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(notifyMSPLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(themBt)
                        .addGap(18, 18, 18)
                        .addComponent(xoaBt)
                        .addGap(18, 18, 18)
                        .addComponent(suaBt)
                        .addGap(18, 18, 18)
                        .addComponent(inHDBt))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel8)
                    .addComponent(tongTienLb))
                .addGap(32, 32, 32))
        );

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/archive.png"))); // NOI18N
        jLabel1.setText("NHẬP THÔNG TIN SẢN PHẨM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addComponent(backBt))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void themBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtActionPerformed
        // TODO add your handling code here:
        Book book;
        if (checkInput()) {
            if (checkNumberInt(soLuongTF.getText())
                    && checkNumberInt(giaBanTF.getText())
                    && checkNumberInt(giaNhapTF.getText())) {
                if (checkMaSanPham(maSanPhamTF.getText())) {
                    //
                    book = getInfomationFromTF();
                    //
                    tableModel.setColumnIdentifiers(colsName);
                    jTable1.setModel(tableModel);
                    String[] rows = dataRow(book);
                    //
                    tableModel.addRow(rows);
                    empWH.themBook(bill, book);
                    //
                    setTextSpace();
                    tongTienLb.setText(String.valueOf(empWH.tongTienBook(bill)));
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
            empWH.xoaBook(bill, indexTable);
            tableModel.removeRow(indexTable);
            //
            setTextSpace();
            tongTienLb.setText(String.valueOf(empWH.tongTienBook(bill)));
            themBt.setEnabled(true);
            //
            notifyXoaThanhCong();
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_xoaBtActionPerformed

    private void inHDBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inHDBtActionPerformed
        // TODO add your handling code here:
        if (!(bill.isEmpty())) {
            for (int i = 0; i < bill.size(); i++) {
                Book book = bill.get(i);
                String query1 = "select * from Book where MaSanPham ='"
                        + book.getMaSanPham() + "' limit 1";
                ResultSet rs = con.select(query1);
                Book temp = new Book();
                new SetDataToObject().setBook(rs, temp);

                if (temp.getMaSanPham() != null) {
                    String query2 = "update Book set SoLuong="
                            + ((temp.getSoLuong() + book.getSoLuong()))
                            + " where MaSanPham='" + book.getMaSanPham() + "'";
                    con.update(query2);
                } else {
                    String query3 = "insert into book (MaSanPham,TenSanPham,TheLoai,TacGia,NXB,SoLuong,GiaNhap,GiaBan) "
                            + "value ('" + book.getMaSanPham() + "','"
                            + book.getTenSanPham() + "','"
                            + book.getTheLoai() + "','"
                            + book.getTacGia() + "','"
                            + book.getNXB() + "',"
                            + book.getSoLuong() + ","
                            + book.getGiaNhap() + ","
                            + book.getGiaBan() + ")";
                    con.insert(query3);
                }
                try {
                    //
                    ActionLog actionLog = new ActionLog();
                    actionLog.setCreatedDate(new Date());
                    actionLog.setMsp(book.getMaSanPham());
                    actionLog.setUsername(getUsername());
                    actionLog.setActionType(ActionLog.TYPE_ADD);
                    actionLog.setProductType(Book.TYPE_BOOK);
                    actionLog.setSoLuong(book.getSoLuong());

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
            JFileChooser chooser = new JFileChooser();
            int i = chooser.showSaveDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                empWH.inHoaDon(jTable1, chooser, String.valueOf(empWH.tongTienBook(bill)), getName());
            }
        } else {
            notifyPrintBill();
        }

        bill.removeAll(bill);

        int rowSize = tableModel.getRowCount();
        for (int j = 0; j < rowSize; j++) {
            tableModel.removeRow(0);
        }
        tongTienLb.setText(String.valueOf(empWH.tongTienBook(bill)));

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
                    Book book = new Book();
                    if (checkMaSanPham(maSanPhamTF.getText(), indexList)) {
                        Book book1 = getInfomationFromTF();
                        //
                        bill.remove(indexList);
                        book = empWH.suaBook(bill, book1, indexList);
                        //
                        tableModel.setColumnIdentifiers(colsName);
                        jTable1.setModel(tableModel);
                        String[] rowData = dataRow(book);
                        tableModel.removeRow(indexTable);
                        tableModel.insertRow(indexTable, rowData);
                        //
                        setTextSpace();
                        tongTienLb.setText(String.valueOf(empWH.tongTienBook(bill)));
                        themBt.setEnabled(true);
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
        int indexList = getIndexList(bill, maSanPham);
        Book book = getBook(bill, indexList);
        //
        tenSanPhamTF.setText(book.getTenSanPham());
        maSanPhamTF.setText(book.getMaSanPham());
        theLoaiTF.setText(book.getTheLoai());
        giaBanTF.setText(String.valueOf(book.getGiaBan()));
        giaNhapTF.setText(String.valueOf(book.getGiaNhap()));
        soLuongTF.setText(String.valueOf(book.getSoLuong()));
        tacGiaTF.setText(book.getTacGia());
        NXBTF.setText(book.getNXB());
        themBt.setEnabled(false);
        notifyMSPLb.setText("");
    }//GEN-LAST:event_jTable1MouseClicked

    private void maSanPhamTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maSanPhamTFKeyReleased
        // TODO add your handling code here:
        String getText = maSanPhamTF.getText();
        String query5 = "select * from book where MaSanPham='" + getText + "' limit 1";
        ResultSet rs = con.select(query5);
        Book temp = new Book();
        new SetDataToObject().setBook(rs, temp);
        if (temp.getMaSanPham() != null) {
            tenSanPhamTF.setText(temp.getTenSanPham());
            theLoaiTF.setText(temp.getTheLoai());
            giaBanTF.setText(String.valueOf(temp.getGiaBan()));
            giaNhapTF.setText(String.valueOf(temp.getGiaNhap()));
            NXBTF.setText(temp.getNXB());
            tacGiaTF.setText(temp.getTacGia());
            notifyMSPLb.setText("");
        } else {
            notifyMSPLb.setText("Mã sản phẩm chưa tồn tại vui lòng nhập đủ thông tin !");
            tenSanPhamTF.setText("");
            tacGiaTF.setText("");
            theLoaiTF.setText("");
            NXBTF.setText("");
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
                new InputBookView().setVisible(true);
            }
        });
    }
    CreateConnectionDB con = new CreateConnectionDB();

    DefaultTableModel tableModel = new DefaultTableModel();

    String[] colsName = {"Mã", "Tên SP", "Tác giả", "Thể loại", "NXB", "Giá nhập", "Giá bán", "SL"};

    List<Book> bill = new ArrayList<Book>();

    EmployeeWarehouse empWH = new EmployeeWarehouse();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NXBTF;
    private javax.swing.JButton backBt;
    private javax.swing.JTextField giaBanTF;
    private javax.swing.JTextField giaNhapTF;
    private javax.swing.JButton inHDBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JTextField tacGiaTF;
    private javax.swing.JTextField tenSanPhamTF;
    private javax.swing.JTextField theLoaiTF;
    private javax.swing.JButton themBt;
    private javax.swing.JLabel tongTienLb;
    private javax.swing.JButton xoaBt;
    // End of variables declaration//GEN-END:variables

}
