/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.other;

import project1.core.product.Movie;
import project1.core.product.Music;
import project1.core.product.Book;
import project1.core.employee.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import project1.core.cost.Cost;

/**
 *
 * @author lequangbkhn
 */
public class SetDataToObject {

    public Employee setEmployee(ResultSet rs, Employee epl) {
        try {
            while (rs.next()) {
                epl.setHeSoLuong(rs.getDouble("HSL"));
                epl.setName(rs.getString("name"));
                epl.setNgaySinh(rs.getString("birthday"));
                epl.setPassWord(rs.getString("password"));
                epl.setTypeCV(rs.getInt("typeuser"));
                epl.setUseName(rs.getString("username"));
                epl.setChucVu(rs.getString("chucvu"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return epl;
    }

    public Book setBook(ResultSet rs, Book book) {
        try {
            while (rs.next()) {
                book.setGiaBan(rs.getInt("GiaBan"));
                book.setGiaNhap(rs.getInt("GiaNhap"));
                book.setMaSanPham(rs.getString("MaSanPham"));
                book.setNXB(rs.getString("NXB"));
                book.setSoLuong(rs.getInt("SoLuong"));
                book.setTacGia(rs.getString("TacGia"));
                book.setTenSanPham(rs.getString("TenSanPham"));
                book.setTheLoai(rs.getString("TheLoai"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return book;
    }

    public Music setMusic(ResultSet rs, Music music) {
        try {
            while (rs.next()) {
                music.setGiaBan(rs.getInt("GiaBan"));
                music.setGiaNhap(rs.getInt("GiaNhap"));
                music.setMaSanPham(rs.getString("MaSanPham"));
                music.setNSX(rs.getString("NSX"));
                music.setSoLuong(rs.getInt("SoLuong"));
                music.setCaSi(rs.getString("CaSi"));
                music.setTenSanPham(rs.getString("TenSanPham"));
                music.setTheLoai(rs.getString("TheLoai"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return music;
    }

    public Movie setMovie(ResultSet rs, Movie movie) {
        try {
            while (rs.next()) {
                movie.setGiaBan(rs.getInt("GiaBan"));
                movie.setGiaNhap(rs.getInt("GiaNhap"));
                movie.setMaSanPham(rs.getString("MaSanPham"));
                movie.setNSX(rs.getString("NSX"));
                movie.setSoLuong(rs.getInt("SoLuong"));
                movie.setDaoDien(rs.getString("DaoDien"));
                movie.setTenSanPham(rs.getString("TenSanPham"));
                movie.setTheLoai(rs.getString("TheLoai"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return movie;
    }

    public Cost setChiPhi(ResultSet rs, Cost chiPhi) {
        try {
            while (rs.next()) {
                chiPhi.setGhiChu(rs.getString("GhiChu"));
                chiPhi.setLoaiChiPhi(rs.getString("LoaiCP"));
                chiPhi.setMaChiPhi(rs.getString("MaCP"));
                chiPhi.setSoTien(rs.getInt("SoTien"));
                chiPhi.setTen(rs.getString("tenCP"));
                chiPhi.setType(rs.getInt("type"));
                chiPhi.setNgayPhatSinh(rs.getString("NgayPhatSinh"));
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return chiPhi;
    }
}
