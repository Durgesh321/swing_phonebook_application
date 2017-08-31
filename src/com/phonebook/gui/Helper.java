package com.phonebook.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

public class Helper {

    public static void FillComboBox(String sql, JComboBox comb) {
        try {

            Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rset = pstmt.executeQuery();
            comb.removeAllItems();
            while (rset.next()) {
                comb.addItem(rset.getInt(1) + "-" + rset.getString(2));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void FillTable(String sql, DefaultTableModel d) {
        try {
            Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();
            Vector<String> heading = new Vector<String>();
            Vector<Vector> data = new Vector<Vector>();
            for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                System.out.print(rset.getMetaData().getColumnLabel(i) + "\t");
                heading.add(rset.getMetaData().getColumnLabel(i));
            }
            System.out.println();
            while (rset.next()) {
                Vector<String> temp = new Vector<String>();
                for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                    System.out.print(rset.getString(i) + "\t");
                    temp.add(rset.getString(i));
                }
                data.add(temp);
            }
            d.setDataVector(data, heading);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void generateHTML(JTable t) {
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.showSaveDialog(null);
            File f = chooser.getSelectedFile();

            FileOutputStream fos = new FileOutputStream(f);
            PrintStream ps = new PrintStream(fos);

            String str = "<html>";
            str += "<head>";
            str += "<title>";
            str += "MyContactDetails";
            str += "</title>";
            str += "</head>";
            str += "<body>";
            str += "<table align='center' border='2' cellpadding='10' cellspacing='10'>";
            str += "<tr>";

            for (int i = 0; i < t.getColumnCount(); i++) {
                str += "<th>";
                str += t.getColumnName(i);
                str += "</th>";
            }

            str += "</tr>";
            for (int j = 0; j < t.getRowCount(); j++) {
                str += "<tr>";

                for (int i = 0; i < t.getColumnCount(); i++) {

                    str += "<td>";
                    str += t.getValueAt(j, i);
                    str += "</td>";
                }
                str += "</tr>";
            }

            str += "</table>";

            str += "</body>";

            str += "</html>";

            ps.println(str);
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(new String[]{"firefox", f.getAbsolutePath()});

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void generatePDF(JTable t) {
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(null);
            File f = jfc.getSelectedFile();
            Document d = new Document();
            FileOutputStream fos = new FileOutputStream(f);
            PdfWriter.getInstance(d, fos);
            d.open();
            d.addTitle("My MyPhoneBook Detail \t"+ new java.util.Date().toString());
            Paragraph p = new Paragraph("My MyPhoneBook Detail \t"+ new java.util.Date().toString());
            Table pt = new Table(t.getColumnCount(), t.getRowCount());
            for (int i = 0; i < t.getColumnCount(); i++) {
                pt.addCell(t.getColumnName(i));

            }
            for (int i =0; i <t.getRowCount(); i++) {
                for (int j = 0; j< t.getColumnCount(); j++) {
                    pt.addCell(t.getValueAt(i, j).toString());
                }

            }
            d.add(p);
            d.add(pt);
           

            d.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        public static String getEmail(int personCode)throws Exception
        {
            String email="";
            
             Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select email from person where personcode=?");
            pstmt.setInt(1, personCode);
            ResultSet set=pstmt.executeQuery();
            if(set.next())
                email=set.getString(1);

            
            
            return email;
            
        }
    }


