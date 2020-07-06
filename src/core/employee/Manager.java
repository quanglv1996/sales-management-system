/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.employee;

import java.util.ArrayList;
import project1.core.cost.Cost;
import project1.core.product.Product;
import java.util.List;
import project1.core.cost.CostStatistics;
import project1.core.product.ProductStatistics;

/**
 *
 * @author lequangbkhn
 */
public class Manager extends Employee {

    public double tinhLuong(Employee user) {
        double luong = 0;
        luong = LUONGCOBAN * user.getHeSoLuong();
        return luong;
    }

    public double tinhTongLuong(List<Employee> list) {
        double tong = 0;
        for (int i = 0; i < list.size(); i++) {
            tong += LUONGCOBAN * list.get(i).getHeSoLuong();
        }
        return tong;
    }

    public int tinhTongCPPS(List<CostStatistics> list) {
        int tong = 0;
        for (int i = 0; i < list.size(); i++) {
            tong += list.get(i).getSoTien();
        }
        return tong;
    }

    public int tinhTongDT(List<Product> list) {
        int tong = 0;
        for (int i = 0; i < list.size(); i++) {
            tong += list.get(i).getGiaBan() - list.get(i).getGiaNhap();
        }
        return tong;
    }

    public int tinhDoanhThuTK(List<ProductStatistics> list) {
        int doanhThu = 0;
        for (int i = 0; i < list.size(); i++) {
            doanhThu += list.get(i).getGiaBan() * list.get(i).getSoLuong();
        }
        return doanhThu;
    }

    public int tinhTongNhap(List<ProductStatistics> list) {
        int soTien = 0;
        for (int i = 0; i < list.size(); i++) {
            soTien += list.get(i).getGiaNhap() * list.get(i).getSoLuong();
        }
        return soTien;
    }

    public int tinhLoiNhuanTK(List<ProductStatistics> list) {
        int loiNhuan = 0;
        for (int i = 0; i < list.size(); i++) {
            loiNhuan += (list.get(i).getGiaBan() - list.get(i).getGiaNhap()) * list.get(i).getSoLuong();
        }
        return loiNhuan;
    }

    public void themNhanVien(List<Employee> list, Employee epl) {
        list.add(epl);
    }

    public Employee suaNhanVien(List<Employee> list, Employee user, int index) {
        Employee epl1 = new Employee();
        epl1.setHeSoLuong(user.getHeSoLuong());
        epl1.setName(user.getName());
        epl1.setNgaySinh(user.getNgaySinh());
        epl1.setPassWord(user.getPassWord());
        epl1.setChucVu(user.getChucVu());
        epl1.setUseName(user.getUseName());
        epl1.setTypeCV(user.getTypeCV());
        list.add(index, epl1);
        return epl1;
    }

    public boolean xoaNhanVien(List<Employee> list, int index) {
        try {
            list.remove(index);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public List timKiemNV(List<Employee> list, String key) {
        List<Employee> tempList = new ArrayList<Employee>();
        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            if (key.equals(emp.getUseName())
                    || key.equals(emp.getChucVu())
                    || key.equals(emp.getHeSoLuong())
                    || key.equals(emp.getName())
                    || key.equals(emp.getNgaySinh())) {
                tempList.add(emp);
            }
        }
        return tempList;
    }

    public Employee getUser(List<Employee> list, int index) {
        return list.get(index);
    }

}
