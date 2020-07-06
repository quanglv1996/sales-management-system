/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.view.manager;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import project1.core.employee.Manager;
import project1.core.employee.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import project1.core.connection.CreateConnectionDB;

/**
 *
 * @author lequangbkhn
 */
public class EmployeeView extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienView
     */
    public EmployeeView() {

    }

    public void init() {
        initComponents();
        getDataToTable();
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
    public String getHoTenTF() {
        return hoTenTF.getText();
    }

    public String getNgaySinhTF() {
        return ngaySinhTF.getText();
    }

    public double getHSLTF() {
        return Double.valueOf(HSLTF.getText());
    }

    public String getUserNameTF() {
        return userNameTF.getText();
    }

    public String getPasswordTF() {
        return passwordTF.getText();
    }

    public String getTimKiemTF() {
        return timKiemTF.getText();
    }

    public int getTypeCV() {
        if (chucVuCB.getSelectedIndex() == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public String getChucVu() {
        return String.valueOf(chucVuCB.getSelectedItem());
    }

    public Employee getInfomationFromTF() {
        Employee epl = new Employee();
        epl.setHeSoLuong(getHSLTF());
        epl.setName(getHoTenTF());
        epl.setNgaySinh(getNgaySinhTF());
        epl.setPassWord(getPasswordTF());
        epl.setChucVu(getChucVu());
        epl.setUseName(getUserNameTF());
        epl.setTypeCV(getTypeCV());
        return epl;
    }

    public int getIndexUser(List<Employee> list, String username) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getUseName().equals(username)) {
                break;
            }
        }
        return i;
    }

    public String[] dataRow(Employee epl) {
        String[] rows = {
            epl.getName(),
            epl.getNgaySinh(),
            String.valueOf(epl.getHeSoLuong()),
            epl.getUseName(),
            epl.getPassWord(),
            epl.getChucVu(),
            String.valueOf(manager.tinhLuong(epl))};
        return rows;
    }

    public void getDataToTable() {
        try {
            String query = "select * from users";
            ResultSet rs = con.select(query);
            while (rs.next()) {
                Employee epl = new Employee();
                epl.setChucVu(rs.getString("chuc_vu"));
                epl.setHeSoLuong(rs.getDouble("HSL"));
                epl.setName(rs.getString("name"));
                epl.setNgaySinh(rs.getString("birth_day"));
                epl.setPassWord(rs.getString("password"));
                epl.setTypeCV(rs.getInt("typeuser"));
                epl.setUseName(rs.getString("username"));
                listEpl.add(epl);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        tableModel.setColumnIdentifiers(colsName);
        jTable1.setModel(tableModel);
        for (int i = 0; i < listEpl.size(); i++) {
            String[] rows = dataRow(listEpl.get(i));
            tableModel.addRow(rows);
        }
        tongLuongLb.setText(String.valueOf(manager.tinhTongLuong(listEpl)));
    }

    public void UpdateDB(List<Employee> epl) {
        //Cai dat
    }

    public void refresh(List<Employee> list, DefaultTableModel table) {
        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(i);
            String[] rows = dataRow(employee);
            table.addRow(rows);
        }
    }

    public void reset(DefaultTableModel table) {
        int rowSize = table.getRowCount();
        for (int i = 0; i < rowSize; i++) {
            table.removeRow(0);
        }
    }

// Check data ------------------------------------------------------------------
    public boolean checkNumberDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean checkInput() {
        boolean check;
        if ((hoTenTF.getText().equals(""))
                || (ngaySinhTF.getText().equals(""))
                || (HSLTF.getText().equals(""))
                || (passwordTF.getText().equals(""))
                || (userNameTF.getText().equals(""))) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public boolean checkUsername(String username) {
        for (int i = 0; i < listEpl.size(); i++) {
            if (username.equals(listEpl.get(i).getUseName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkUsername(String username, int indexList) {
        int i;
        for (i = 0; i < listEpl.size(); i++) {
            if (i == indexList) {
                continue;
            } else if (username.equals(listEpl.get(i).getUseName())) {
                return false;
            }
        }
        return true;
    }

    public int getIndexList(List<Employee> list, String username) {
        int a = 0;
        for (int i = 0; i < list.size(); i++) {
            if (username.equals(list.get(i).getUseName())) {
                a = i;
                break;
            }
        }
        return a;
    }

//Notify -----------------------------------------------------------------------
    public void notifyChoseRow() {
        JOptionPane.showMessageDialog(this, " Hãy chọn 1 hàng trong bảng !");
    }

    public void notifyTimKiem() {
        JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại !");
    }

    public void notifyUsername() {
        JOptionPane.showMessageDialog(this, "Trùng tên TK. Chọn tên TK khác !");
    }

    public void notifyHeSoLuong() {
        JOptionPane.showMessageDialog(this, "Sai hệ số lương !");
    }

    public void notifyThieuThongTin() {
        JOptionPane.showMessageDialog(this, "Nhập thiếu thông tin !");
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
        jLabel2 = new javax.swing.JLabel();
        hoTenTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ngaySinhTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        chucVuCB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        HSLTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        userNameTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        passwordTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        themBT = new javax.swing.JButton();
        suaBt = new javax.swing.JButton();
        xoaBt = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tongLuongLb = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        timKiemBt = new javax.swing.JButton();
        timKiemTF = new javax.swing.JTextField();
        taiLaiBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/users.png"))); // NOI18N
        jLabel1.setText("Nhân viên");

        backBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/back_1.png"))); // NOI18N
        backBt.setText("Quay lại");
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        jLabel2.setText("Họ tên");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Chức vụ");

        chucVuCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bán hàng", "Nhập kho" }));

        jLabel5.setText("HSL");

        jLabel6.setText("Tài  khoản");

        jLabel7.setText("Mật khẩu");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ tên", "Ngày sinh", "HSL", "Tài khoản", "Mật khẩu", "Chức vụ", "Lương(Triệu)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        themBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/add16.png"))); // NOI18N
        themBT.setText("Thêm");
        themBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBTActionPerformed(evt);
            }
        });

        suaBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/process_1.png"))); // NOI18N
        suaBt.setText("Sửa");
        suaBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaBtActionPerformed(evt);
            }
        });

        xoaBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/remove16.png"))); // NOI18N
        xoaBt.setText("Xóa");
        xoaBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBtActionPerformed(evt);
            }
        });

        jLabel8.setText("Tổng lương");

        tongLuongLb.setText("0.0");

        jLabel9.setText("Tr.VNĐ");

        timKiemBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/search16x16 .png"))); // NOI18N
        timKiemBt.setText("Tìm kiếm");
        timKiemBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemBtActionPerformed(evt);
            }
        });

        timKiemTF.setText("Nhập tên tài khoản ...");
        timKiemTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timKiemTFMouseClicked(evt);
            }
        });

        taiLaiBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1/image/repeat.png"))); // NOI18N
        taiLaiBt.setText("Tải lại");
        taiLaiBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taiLaiBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hoTenTF)
                            .addComponent(chucVuCB, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ngaySinhTF)
                                    .addComponent(userNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HSLTF)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(themBT, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(suaBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xoaBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(taiLaiBt)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(timKiemBt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(timKiemTF))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tongLuongLb, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(backBt))
                .addGap(22, 22, 22)
                .addComponent(taiLaiBt)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hoTenTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ngaySinhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(HSLTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(chucVuCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(userNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(themBT)
                        .addGap(42, 42, 42)
                        .addComponent(suaBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(xoaBt))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timKiemBt)
                    .addComponent(timKiemTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tongLuongLb)
                    .addComponent(jLabel9))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:
        String queryDeleteallData = "truncate table users";
        con.insert(queryDeleteallData);
        for (Employee temp : listEpl ) {
            String queryInsertData = "insert into users (username,password,typeuser,name,HSL,chuc_vu,birth_day) value ('"
                    + temp.getUseName() + "','"
                    + temp.getPassWord() + "',"
                    + temp.getTypeCV() + ",'"
                    + temp.getName() + "',"
                    + temp.getHeSoLuong() + ",'"
                    + temp.getChucVu() + "','"
                    + temp.getNgaySinh() + "')";
            con.insert(queryInsertData);
        }
        ManagerView manager =new ManagerView();
        manager.setName(getName());
        manager.setUsername(getUsername());
        manager.setTypeUser(getTypeUser());
        manager.init();
        con.closeConnectDB();
        this.dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void themBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBTActionPerformed
        // TODO add your handling code here:
        if (checkInput()) {
            if (checkUsername(getUserNameTF())) {
                if (checkNumberDouble(HSLTF.getText())) {
                    Employee epl = getInfomationFromTF();
                    //
                    String[] rows = dataRow(epl);
                    tableModel.addRow(rows);
                    manager.themNhanVien(listEpl, epl);
                    //
                    tongLuongLb.setText(String.valueOf(manager.tinhTongLuong(listEpl)));
                    hoTenTF.setText("");
                    ngaySinhTF.setText("");
                    HSLTF.setText("");
                    passwordTF.setText("");
                    userNameTF.setText("");
                    //
                    notifyThemThanhCong();
                } else {
                    notifyHeSoLuong();
                }
            } else {
                notifyUsername();
            }
        } else {
            notifyThieuThongTin();
        }
    }//GEN-LAST:event_themBTActionPerformed

    private void suaBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBtActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == (-1))) {
            if (checkInput()) {
                if (checkNumberDouble(HSLTF.getText())) {
                    String userName = String.valueOf(jTable1.getValueAt(indexTable, 3));
                    int indexList = getIndexList(listEpl, userName);
                    Employee epl = new Employee();
                    if (checkUsername(userNameTF.getText(), indexList)) {
                        Employee epl1 = getInfomationFromTF();
                        //
                        listEpl.remove(indexList);
                        epl = manager.suaNhanVien(listEpl, epl1, indexList);
                        //
                        String[] rowData = dataRow(epl);
                        tableModel.removeRow(indexTable);
                        tableModel.insertRow(indexTable, rowData);
                        //
                        tongLuongLb.setText(String.valueOf(manager.tinhTongLuong(listEpl)));
                        hoTenTF.setText("");
                        ngaySinhTF.setText("");
                        HSLTF.setText("");
                        passwordTF.setText("");
                        userNameTF.setText("");
                        themBT.setEnabled(true);
                        taiLaiBt.setEnabled(true);
                        //
                        notifySuaThanhCong();
                    } else {
                        notifyUsername();
                    }
                } else {
                    notifyHeSoLuong();
                }
            } else {
                notifyThieuThongTin();
            }
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_suaBtActionPerformed

    private void xoaBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBtActionPerformed
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        if (!(indexTable == (-1))) {
            //
            String username = String.valueOf(jTable1.getValueAt(indexTable, 3));
            int indexList = getIndexList(listEpl, username);
            manager.xoaNhanVien(listEpl, indexList);
            tableModel.removeRow(indexTable);
            tongLuongLb.setText(String.valueOf(manager.tinhTongLuong(listEpl)));
            //
            hoTenTF.setText("");
            ngaySinhTF.setText("");
            HSLTF.setText("");
            passwordTF.setText("");
            userNameTF.setText("");
            themBT.setEnabled(true);
            taiLaiBt.setEnabled(true);
            //
            notifyXoaThanhCong();
        } else {
            notifyChoseRow();
        }
    }//GEN-LAST:event_xoaBtActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int indexTable = jTable1.getSelectedRow();
        String username = String.valueOf(jTable1.getValueAt(indexTable, 3));
        int indexList = getIndexList(listEpl, username);
        Employee epl = manager.getUser(listEpl, indexList);
        //
        hoTenTF.setText(epl.getName());
        ngaySinhTF.setText(epl.getNgaySinh());
        HSLTF.setText(String.valueOf(epl.getHeSoLuong()));
        passwordTF.setText(epl.getPassWord());
        userNameTF.setText(epl.getUseName());
        chucVuCB.setSelectedItem(epl.getChucVu());
        themBT.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void timKiemBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemBtActionPerformed
        // TODO add your handling code here:
        String key = timKiemTF.getText();
        List<Employee> tempList = manager.timKiemNV(listEpl, key);
        reset(tableModel);
        refresh(tempList, tableModel);
    }//GEN-LAST:event_timKiemBtActionPerformed

    private void timKiemTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timKiemTFMouseClicked
        // TODO add your handling code here:'
        timKiemTF.setText("");
    }//GEN-LAST:event_timKiemTFMouseClicked

    private void taiLaiBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taiLaiBtActionPerformed
        // TODO add your handling code here:
        reset(tableModel);
        refresh(listEpl, tableModel);
        //
        hoTenTF.setText("");
        ngaySinhTF.setText("");
        HSLTF.setText("");
        passwordTF.setText("");
        userNameTF.setText("");
        chucVuCB.setSelectedIndex(0);
        timKiemTF.setText("Nhập tên tài khoản...");
        tongLuongLb.setText(String.valueOf(manager.tinhTongLuong(listEpl)));
        themBT.setEnabled(true);
    }//GEN-LAST:event_taiLaiBtActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeView().setVisible(true);
            }
        });
    }

    CreateConnectionDB con = new CreateConnectionDB();

    DefaultTableModel tableModel = new DefaultTableModel();

    String[] colsName = {"Họ tên", "Ngày sinh", "HSL", "Tài khoản", "Mật khẩu", "Chức vụ", "Lương(Triệu)"};

    Manager manager = new Manager();

    List<Employee> listEpl = new ArrayList<Employee>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HSLTF;
    private javax.swing.JButton backBt;
    private javax.swing.JComboBox<String> chucVuCB;
    private javax.swing.JTextField hoTenTF;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField ngaySinhTF;
    private javax.swing.JTextField passwordTF;
    private javax.swing.JButton suaBt;
    private javax.swing.JButton taiLaiBt;
    private javax.swing.JButton themBT;
    private javax.swing.JButton timKiemBt;
    private javax.swing.JTextField timKiemTF;
    private javax.swing.JLabel tongLuongLb;
    private javax.swing.JTextField userNameTF;
    private javax.swing.JButton xoaBt;
    // End of variables declaration//GEN-END:variables
}
