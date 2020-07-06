/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.cost;

import java.util.Date;

/**
 *
 * @author lequangbkhn
 */
public class Cost {

    public static final int TYPE_CP = 4;
    private String maChiPhi;
    private String ten;
    private int soTien;
    private int type;
    private String loaiChiPhi;
    private String ghiChu;
    private String ngayPhatSinh;

    public String getMaChiPhi() {
        return maChiPhi;
    }

    public void setMaChiPhi(String maChiPhi) {
        this.maChiPhi = maChiPhi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLoaiChiPhi() {
        return loaiChiPhi;
    }

    public void setLoaiChiPhi(String loaiChiPhi) {
        this.loaiChiPhi = loaiChiPhi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayPhatSinh() {
        return ngayPhatSinh;
    }

    public void setNgayPhatSinh(String ngayPhatSinh) {
        this.ngayPhatSinh = ngayPhatSinh;
    }

}
