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
import project1.core.cost.Cost;
import project1.core.connection.CreateConnectionDB;
import project1.core.employee.EmployeeWarehouse;
import project1.core.other.SetDataToObject;
import project1.core.other.TimeSystem;
import project1.core.product.ActionLog;
import project1.core.product.Book;

/**
 *
 * @author lequangbkhn
 */
public class InputCostView extends javax.swing.JFrame {

    /**
     * Creates new form InputCostView
     */
    public InputCostView() {

    }

    public void init() {
        initComponents();
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
    public String getNgayPhatSinh() {
        return time.getTimeSystem();
    }

    public String getMaCP() {
        return maCPTF.getText();
    }

    public String getTenCP() {
        return tenCPTF.getText();
    }

    public String getLoaiCP() {
        return String.valueOf(choseCB.getSelectedItem());
    }

    public int getSoTien() {
        return Integer.valueOf(soTienTF.getText());
    }

    public String getGhiChu() {
        return ghiChuArea.getText();
    }

    public int getIndexList(List<Cost> list, String maChiPhi) {
        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            if (maChiPhi.equals(list.get(i).getMaChiPhi())) {
                a = i;
                break;
            }
        }
        return a;
    }

    public Cost getInfomationFromTF() {
        Cost chiPhi = new Cost();
        //
        chiPhi.setGhiChu(getGhiChu());
        chiPhi.setLoaiChiPhi(getLoaiCP());
        chiPhi.setMaChiPhi(getMaCP());
        chiPhi.setSoTien(getSoTien());
        chiPhi.setTen(getTenCP());
        chiPhi.setNgayPhatSinh(getNgayPhatSinh());
        chiPhi.setType(Cost.TYPE_CP);
        //
        return chiPhi;
    }

    public Cost getCP(List<Cost> list, int index) {
        return list.get(index);
    }

//Notify -----------------------------------------------------------------------
    public void notifyNumber() {
        JOptionPane.showMessageDialog(this, "Nhập sai số tiền !");
    }

    public void notifyThieuThongTin() {
        JOptionPane.showMessageDialog(this, "Nhập thiếu thông tin !");
    }

    public void notifyChoseRow() {
        JOptionPane.showMessageDialog(this, "Hãy chọn 1 hàng trong bảng !");
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
        JOptionPane.showMessageDialog(this, "Chưa nhập chi phí nào !");
    }

    public void notifyMaCP() {
        JOptionPane.showMessageDialog(this, "Mã chi phí đã tồn tại !");
    }

//Check data -------------------------------------------------------------------
    public boolean checkNumberInt(String s) {
        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean checkInput() {
        boolean check;
        if ((tenCPTF.getText().equals(""))
                || (soTienTF.getText().equals(""))
                || (maCPTF.getText().equals(""))
                || (ghiChuArea.getText().equals(""))) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public boolean checkMaCP(String maCP) {
        for (int i = 0; i < list.size(); i++) {
            if (maCP.equals(list.get(i).getMaChiPhi())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMaCP(String maCP, int indexList) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (i == indexList) {
                continue;
            } else if (maCP.equals(list.get(i).getMaChiPhi())) {
                return false;
            }
        }
        return true;
    }

//Other-------------------------------------------------------------------------
    public String[] dataRow(Cost chiPhi) {
        String[] rows = {
            chiPhi.getMaChiPhi(),
            chiPhi.getTen(),
            chiPhi.getLoaiChiPhi(),
            chiPhi.getNgayPhatSinh(),
            String.valueOf(chiPhi.getSoTien()),
            chiPhi.getGhiChu()
        };
        return rows;
    }

    public void setTextSpace() {
        maCPTF.setText("");
        tenCPTF.setText("");
        ghiChuArea.setText("");
        soTienTF.setText("");
    }

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        themBt = new javax.swing.JButton();
        Sửa = new javax.swing.JButton();
        Xóa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tenCPTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        soTienTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        choseCB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ghiChuArea = new javax.swing.JTextArea();
        inHDBt = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tongTienLb = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        maCPTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/blog_post.png"))); // NOI18N
        jLabel1.setText("Chi phí");

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CP", "Tên CP", "Loại CP", "Ngày PS", "Số tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
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

        themBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/add16.png"))); // NOI18N
        themBt.setText("Thêm");
        themBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtActionPerformed(evt);
            }
        });

        Sửa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/process_1.png"))); // NOI18N
        Sửa.setText("Sửa");
        Sửa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SửaActionPerformed(evt);
            }
        });

        Xóa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/remove16.png"))); // NOI18N
        Xóa.setText("Xóa");
        Xóa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XóaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên CP");

        jLabel3.setText("Số tiền");

        jLabel4.setText("Loại chị phí");

        choseCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cố định", "Phát sinh" }));

        jLabel5.setText("Ghi chú");

        ghiChuArea.setColumns(20);
        ghiChuArea.setRows(5);
        jScrollPane2.setViewportView(ghiChuArea);

        inHDBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/printer16.png"))); // NOI18N
        inHDBt.setText("In HĐ");
        inHDBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inHDBtActionPerformed(evt);
            }
        });

        jLabel6.setText("Tổng tiền");

        tongTienLb.setText("tongTienLb");

        jLabel7.setText("Mã CP");

        jLabel8.setText("Nhân viên nhập kho :");

        jLabel9.setText(getName());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(themBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Xóa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Sửa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inHDBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(276, 276, 276)
                                .addComponent(backBt))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tongTienLb, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maCPTF)
                            .addComponent(tenCPTF)
                            .addComponent(choseCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soTienTF, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                        .addGap(148, 148, 148)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBt))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(maCPTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tenCPTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(soTienTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(choseCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(themBt)
                        .addGap(30, 30, 30)
                        .addComponent(Sửa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(Xóa)
                        .addGap(30, 30, 30)
                        .addComponent(inHDBt))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tongTienLb))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtActionPerformed
        // TODO add your handling code here:
        Cost chiPhi;
        if (checkInput()) {
            if (checkNumberInt(soTienTF.getText())) {
                if (checkMaCP(maCPTF.getText())) {
                    //
                    chiPhi = getInfomationFromTF();
                    //
                    tableModel.setColumnIdentifiers(colsName);
                    jTable1.setModel(tableModel);
                    String[] rows = dataRow(chiPhi);
                    tableModel.addRow(rows);
                    empWH.themCP(list, chiPhi);
                    //
                    setTextSpace();
                    tongTienLb.setText(String.valueOf(empWH.tongTienCP(list)));
                    //
                    notifyThemThanhCong();
                } else {
                    notifyMaCP();
                }
            } else {
                notifyNumber();
            }
        } else {
            notifyThieuThongTin();
        }
    }//GEN-LAST:event_themBtActionPerformed

    private void XóaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XóaActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == -1)) {
            empWH.xoaCP(list, indexTable);
            tableModel.removeRow(indexTable);
            //
            setTextSpace();
            tongTienLb.setText(String.valueOf(empWH.tongTienCP(list)));
            themBt.setEnabled(true);
            //
            notifyXoaThanhCong();
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_XóaActionPerformed

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

    private void SửaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SửaActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == -1)) {
            if (checkInput()) {
                if (checkNumberInt(soTienTF.getText())) {
                    //
                    String maCP = String.valueOf(jTable1.getValueAt(indexTable, 0));
                    int indexList = getIndexList(list, maCP);
                    //
                    Cost chiPhi = new Cost();
                    if (checkMaCP(maCPTF.getText(), indexList)) {
                        Cost chiPhi1 = getInfomationFromTF();
                        //
                        list.remove(indexList);
                        chiPhi = empWH.suaCP(list, chiPhi1, indexList);
                        //
                        tableModel.setColumnIdentifiers(colsName);
                        jTable1.setModel(tableModel);
                        String[] rowData = dataRow(chiPhi);
                        tableModel.removeRow(indexTable);
                        tableModel.insertRow(indexTable, rowData);
                        //
                        setTextSpace();
                        tongTienLb.setText(String.valueOf(empWH.tongTienCP(list)));
                        themBt.setEnabled(true);
                        //
                        notifySuaThanhCong();
                    } else {
                        notifyMaCP();
                    }
                } else {
                    notifyNumber();
                }
            } else {
                notifyThieuThongTin();
            }
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_SửaActionPerformed

    private void inHDBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inHDBtActionPerformed
        // TODO add your handling code here:
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Cost chiPhi = list.get(i);
                String query = "insert into chi_phi (MaChiPhi,TenChiPhi,SoTien,LoaiChiPhi,NgayPhatSinh,GhiChu) "
                        + "value ('" + chiPhi.getMaChiPhi() + "','"
                        + chiPhi.getTen() + "',"
                        + chiPhi.getSoTien() + ",'"
                        + chiPhi.getLoaiChiPhi() + "','"
                        + chiPhi.getNgayPhatSinh() + "','"
                        + chiPhi.getGhiChu() + "')";
                con.insert(query);

                try {
                    //
                    ActionLog actionLog = new ActionLog();
                    actionLog.setCreatedDate(new Date());
                    actionLog.setMsp(chiPhi.getMaChiPhi());
                    actionLog.setUsername(getUsername());
                    actionLog.setActionType(ActionLog.TYPE_ADD);
                    actionLog.setProductType(Cost.TYPE_CP);
                    actionLog.setSoLuong(1);

                    String query1 = "insert into action_log(usename,so_luong,product_type,ma_sp,creat_date,action_type) "
                            + " values('" + actionLog.getUsername()
                            + "'," + actionLog.getSoLuong()
                            + "," + actionLog.getProductType()
                            + ",'" + actionLog.getMsp()
                            + "',now()"
                            + "," + actionLog.getActionType() + ")";
                    con.insert(query1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            JFileChooser chooser = new JFileChooser(); // gọi lên dailog chọn vị trí lưu file
            int i = chooser.showSaveDialog(this); // làm hiện dailog save
            if (i == JFileChooser.APPROVE_OPTION) { // nếu vị trí đã được chọn
                empWH.inHoaDonChiPhi(jTable1, chooser, String.valueOf(empWH.tongTienCP(list)), getName());
            }
        } else {
            notifyPrintBill();
        }

        list.removeAll(list);

        int rowSize = tableModel.getRowCount();
        for (int j = 0; j < rowSize; j++) {
            tableModel.removeRow(0);
        }
        tongTienLb.setText(String.valueOf(empWH.tongTienCP(list)));

    }//GEN-LAST:event_inHDBtActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        String maSanPham = String.valueOf(jTable1.getValueAt(indexTable, 0));
        int indexList = getIndexList(list, maSanPham);
        Cost chiPhi = getCP(list, indexList);
        //
        tenCPTF.setText(chiPhi.getTen());
        maCPTF.setText(chiPhi.getMaChiPhi());
        soTienTF.setText(String.valueOf(chiPhi.getSoTien()));
        ghiChuArea.setText(chiPhi.getGhiChu());
        //
        themBt.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(InputCostView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputCostView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputCostView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputCostView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputCostView().setVisible(true);
            }
        });
    }

    CreateConnectionDB con = new CreateConnectionDB();

    DefaultTableModel tableModel = new DefaultTableModel();

    String[] colsName = {"Mã CP", "Tên CP", "Loại CP", "Ngày PS", "Số tiền", "Ghi chú"};

    List<Cost> list = new ArrayList<Cost>();

    EmployeeWarehouse empWH = new EmployeeWarehouse();

    TimeSystem time = new TimeSystem();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sửa;
    private javax.swing.JButton Xóa;
    private javax.swing.JButton backBt;
    private javax.swing.JComboBox<String> choseCB;
    private javax.swing.JTextArea ghiChuArea;
    private javax.swing.JButton inHDBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField maCPTF;
    private javax.swing.JTextField soTienTF;
    private javax.swing.JTextField tenCPTF;
    private javax.swing.JButton themBt;
    private javax.swing.JLabel tongTienLb;
    // End of variables declaration//GEN-END:variables
}
