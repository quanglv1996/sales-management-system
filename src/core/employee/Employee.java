/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.employee;

/**
 *
 * @author lequangbkhn
 */
public class Employee {
    public static final double LUONGCOBAN = 1.1;
    private String name;
    private String ngaySinh;
    private double heSoLuong;
    private String useName;
    private String passWord;
    private int typeCV;
    private String chucVu;

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getTypeCV() {
        return typeCV;
    }

    public void setTypeCV(int typeCV) {
        this.typeCV = typeCV;
    }

   

}
