/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.employee;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import project1.core.cost.Cost;
import project1.core.product.Book;
import project1.core.product.Movie;
import project1.core.product.Music;

/**
 *
 * @author lequangbkhn
 */
public class EmployeeWarehouse extends Employee {

//Book--------------------------------------------------------------------------
    public void themBook(List<Book> list, Book book) {
        list.add(book);
    }

    public void xoaBook(List<Book> list, int index) {
        list.remove(index);
    }

    public Book suaBook(List<Book> list, Book book, int index) {
        Book book1 = new Book();

        book1.setGiaBan(book.getGiaBan());
        book1.setGiaNhap(book.getGiaNhap());
        book1.setMaSanPham(book.getMaSanPham());
        book1.setNXB(book.getNXB());
        book1.setSoLuong(Integer.valueOf(book.getSoLuong()));
        book1.setTacGia(book.getTacGia());
        book1.setTenSanPham(book.getTenSanPham());
        book1.setTheLoai(book.getTheLoai());
        book1.setType(book.TYPE_BOOK);

        list.add(index, book1);
        return book1;
    }

    public int thanhTien(Book book) {
        return book.getGiaBan() * book.getSoLuong();
    }

    public int tongTienBook(List<Book> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getGiaNhap() * list.get(i).getSoLuong();
        }
        return sum;
    }

//Music-------------------------------------------------------------------------
    public void themMusic(List<Music> list, Music music) {
        list.add(music);
    }

    public void xoaMusic(List<Music> list, int index) {
        list.remove(index);
    }

    public Music suaMusic(List<Music> list, Music music, int index) {
        Music music1 = new Music();

        music1.setGiaBan(music.getGiaBan());
        music1.setGiaNhap(music.getGiaNhap());
        music1.setMaSanPham(music.getMaSanPham());
        music1.setNSX(music.getNSX());
        music1.setSoLuong(Integer.valueOf(music.getSoLuong()));
        music1.setCaSi(music.getCaSi());
        music1.setTenSanPham(music.getTenSanPham());
        music1.setTheLoai(music.getTheLoai());
        music1.setType(music.TYPE_MUSIC);

        list.add(index, music1);
        return music1;
    }

    public int thanhTien(Music music) {
        return music.getGiaBan() * music.getSoLuong();
    }

    public int tongTienMusic(List<Music> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getGiaNhap() * list.get(i).getSoLuong();
        }
        return sum;
    }

//Movie-------------------------------------------------------------------------
    public int thanhTien(Movie movie) {
        return movie.getGiaBan() * movie.getSoLuong();
    }

    public int tongTienMovie(List<Movie> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getGiaNhap() * list.get(i).getSoLuong();
        }
        return sum;
    }

    public void themMovie(List<Movie> list, Movie movie) {
        list.add(movie);
    }

    public void xoaMovie(List<Movie> list, int index) {
        list.remove(index);
    }

    public Movie suaMovie(List<Movie> list, Movie movie, int index) {
        Movie movie1 = new Movie();

        movie1.setGiaBan(movie.getGiaBan());
        movie1.setGiaNhap(movie.getGiaNhap());
        movie1.setMaSanPham(movie.getMaSanPham());
        movie1.setNSX(movie.getNSX());
        movie1.setSoLuong(Integer.valueOf(movie.getSoLuong()));
        movie1.setDaoDien(movie.getDaoDien());
        movie1.setTenSanPham(movie.getTenSanPham());
        movie1.setTheLoai(movie.getTheLoai());
        movie1.setType(movie.TYPE_MOVIE);

        list.add(index, movie1);
        return movie1;
    }

//Chi phi-----------------------------------------------------------------------
    public void themCP(List<Cost> list, Cost chiPhi) {
        list.add(chiPhi);
    }

    public void xoaCP(List<Cost> list, int index) {
        list.remove(index);
    }

    public Cost suaCP(List<Cost> list, Cost chiPhi, int index) {
        Cost chiPhi1 = new Cost();

        chiPhi1.setGhiChu(chiPhi.getGhiChu());
        chiPhi1.setLoaiChiPhi(chiPhi.getLoaiChiPhi());
        chiPhi1.setSoTien(chiPhi.getSoTien());
        chiPhi1.setTen(chiPhi.getTen());
        chiPhi1.setNgayPhatSinh(chiPhi.getNgayPhatSinh());
        chiPhi1.setMaChiPhi(chiPhi.getMaChiPhi());
        chiPhi1.setType(Cost.TYPE_CP);

        list.add(index, chiPhi1);
        return chiPhi1;
    }

    public int tongTienCP(List<Cost> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getSoTien();
        }
        return sum;
    }

//In hoa don nhap hang--------------------------------------------------------------------
    public static void inHoaDon(JTable myTable, JFileChooser chooser, String tongtien, String name) {
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            File file = chooser.getSelectedFile();
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + ".pdf"));
                document.open();

                try {

                    com.itextpdf.text.Font f = new com.itextpdf.text.Font(BaseFont.createFont("C:/Windows/Fonts/Tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
                    f.setStyle(com.itextpdf.text.Font.BOLD);

                    com.itextpdf.text.Font f1 = new com.itextpdf.text.Font(BaseFont.createFont("C:/Windows/Fonts/Tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
                    f1.setStyle(com.itextpdf.text.Font.BOLD);
                    f1.setSize(20);

                    Paragraph paragraph1 = new Paragraph("MEDIA ONE", f1);
                    paragraph1.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph1);

                    Paragraph paragraph2 = new Paragraph("Hóa đơn nhập hàng", f1);
                    paragraph2.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph2);

                    Paragraph paragraph3 = new Paragraph(" ", f1);
                    paragraph3.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph3);

                    PdfPTable t = new PdfPTable(5);
                    t.setSpacingBefore(50);
                    t.setSpacingAfter(50);
                    t.setWidthPercentage(100);
                    t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPCell j1 = new PdfPCell(new Phrase("ID ", f));
                    j1.setBackgroundColor(BaseColor.GREEN);
                    j1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j1);

                    PdfPCell j2 = new PdfPCell(new Phrase("Tên sản phẩm", f));
                    j2.setBackgroundColor(BaseColor.GREEN);
                    j2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j2);

                    PdfPCell j3 = new PdfPCell(new Phrase("Giá nhập", f));
                    j3.setBackgroundColor(BaseColor.GREEN);
                    j3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j3);

                    PdfPCell j4 = new PdfPCell(new Phrase("Giá bán", f));
                    j4.setBackgroundColor(BaseColor.GREEN);
                    j4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j4);

                    PdfPCell j5 = new PdfPCell(new Phrase("SL", f));
                    j5.setBackgroundColor(BaseColor.GREEN);
                    j5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j5);

                    int n = myTable.getRowCount();
                    int q;
                    for (int j = 0; j < n; j++) {
                        for (q = 0; q < 8; q++) {
                            if (q == 2 || q == 3 || q == 4) {
                                continue;
                            } else {
                                t.addCell(myTable.getValueAt(j, q).toString());
                            }
                        }
                    }

                    document.add(t);
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    Paragraph paragraph10 = new Paragraph("Tổng tiền :  " + tongtien, f);
                    paragraph10.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph10);

                    Paragraph paragraph9 = new Paragraph("Ngày " + day + " tháng " + month + " năm " + year, f);
                    paragraph9.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph9);

                    Paragraph paragraph4 = new Paragraph("Người nhập hàng      ", f);
                    paragraph4.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph4);

                    Paragraph paragraph6 = new Paragraph(" ", f1);
                    paragraph6.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph6);

                    Paragraph paragraph5 = new Paragraph("" + name + "       ", f);
                    paragraph5.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph5);

                } catch (IOException e) {
                    e.getStackTrace();
                }
                document.close();

            } catch (DocumentException e) {
                e.getStackTrace();
            }
            document.open();

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }

    }

    //In hoa don chi phi phat sinh--------------------------------------------------
    public static void inHoaDonChiPhi(JTable myTable, JFileChooser chooser, String tongtien, String name) {
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            File file = chooser.getSelectedFile();
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + ".pdf"));
                document.open();

                try {

                    com.itextpdf.text.Font f = new com.itextpdf.text.Font(BaseFont.createFont("C:/Windows/Fonts/Tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
                    f.setStyle(com.itextpdf.text.Font.BOLD);

                    com.itextpdf.text.Font f1 = new com.itextpdf.text.Font(BaseFont.createFont("C:/Windows/Fonts/Tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
                    f1.setStyle(com.itextpdf.text.Font.BOLD);
                    f1.setSize(20);

                    Paragraph paragraph1 = new Paragraph("MEDIA ONE", f1);
                    paragraph1.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph1);

                    Paragraph paragraph2 = new Paragraph("Hóa đơn chi phí", f1);
                    paragraph2.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph2);

                    Paragraph paragraph3 = new Paragraph(" ", f1);
                    paragraph3.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph3);

                    PdfPTable t = new PdfPTable(5);
                    t.setSpacingBefore(50);
                    t.setSpacingAfter(50);
                    t.setWidthPercentage(100);
                    t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPCell j1 = new PdfPCell(new Phrase("ID ", f));
                    j1.setBackgroundColor(BaseColor.GREEN);
                    j1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j1);

                    PdfPCell j2 = new PdfPCell(new Phrase("Tên CP", f));
                    j2.setBackgroundColor(BaseColor.GREEN);
                    j2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j2);

                    PdfPCell j3 = new PdfPCell(new Phrase("Loại CP", f));
                    j3.setBackgroundColor(BaseColor.GREEN);
                    j3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j3);

                    PdfPCell j4 = new PdfPCell(new Phrase("Số tiền", f));
                    j4.setBackgroundColor(BaseColor.GREEN);
                    j4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j4);

                    PdfPCell j5 = new PdfPCell(new Phrase("Ghi chú", f));
                    j5.setBackgroundColor(BaseColor.GREEN);
                    j5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j5);

                    int n = myTable.getRowCount();
                    System.out.print(n);

                    int q;
                    for (int j = 0; j < n; j++) {
                        for (q = 0; q < 6; q++) {
                            if (q == 3) {
                                continue;
                            } else {
                                t.addCell(myTable.getValueAt(j, q).toString());
                            }
                        }
                    }

                    document.add(t);
                    Calendar c = Calendar.getInstance(); // hàm lấy thời gian hiện tại của hệ thống
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    Paragraph paragraph10 = new Paragraph("Tổng tiền :  " + tongtien, f);
                    paragraph10.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph10);

                    Paragraph paragraph9 = new Paragraph("Ngày " + day + " tháng " + month + " năm " + year, f);
                    paragraph9.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph9);

                    Paragraph paragraph4 = new Paragraph("Người nhập      ", f);
                    paragraph4.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph4);

                    Paragraph paragraph6 = new Paragraph(" ", f1);
                    paragraph6.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph6);

                    Paragraph paragraph5 = new Paragraph("" + name + "       ", f);
                    paragraph5.setAlignment(Element.ALIGN_RIGHT);
                    document.add(paragraph5);

                } catch (IOException e) {
                    e.getStackTrace();
                }
                document.close();
            } catch (DocumentException e) {
                e.getStackTrace();
            }
            document.open();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}
