/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.employee;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import project1.core.product.Product;
import project1.core.product.ProductSell;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Dimension;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.*;
import java.awt.Graphics;
import java.io.*;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lequangbkhn
 */
public class EmployeeSell extends Employee {

    public int thanhTien(ProductSell pro) {
        return pro.getGiaBan() * pro.getSoLuongBan();
    }

    public int tongTien(List<ProductSell> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i).getGiaBan() * list.get(i).getSoLuongBan();
        }
        return sum;
    }

    public void themSP(List<ProductSell> bill, ProductSell pro) {
        bill.add(pro);
    }

    public void xoaSP(List<ProductSell> bill, int index) {
        bill.remove(index);
    }

    // hàm xuất hóa đơn thành pdf
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

                    Paragraph paragraph2 = new Paragraph("Hóa đơn thanh toán", f1);
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

                    PdfPCell j3 = new PdfPCell(new Phrase("Giá bán", f));
                    j3.setBackgroundColor(BaseColor.GREEN);
                    j3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j3);

                    PdfPCell j4 = new PdfPCell(new Phrase("Số lượng", f));
                    j4.setBackgroundColor(BaseColor.GREEN);
                    j4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j4);

                    PdfPCell j5 = new PdfPCell(new Phrase("Thành tiền", f));
                    j5.setBackgroundColor(BaseColor.GREEN);
                    j5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    t.addCell(j5);

                    int n = myTable.getRowCount();
                    System.out.print(n);

                    int q;
                    for (int j = 0; j < n; j++) {
                        for (q = 0; q < 5; q++) {
                            t.addCell(myTable.getValueAt(j, q).toString());
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

                    Paragraph paragraph4 = new Paragraph("Người bán hàng      ", f);
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
