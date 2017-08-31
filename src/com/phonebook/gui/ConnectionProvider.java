/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phonebook.gui;

/**
 *
 * @author durgesh kumar
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionProvider {

    private static Connection con = null;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "root");
                if (!con.isClosed()) {
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }

    public static  void close() {
        try {
            if (!con.isClosed()) {
                con.close();
                System.out.println("connection from the DB closed");
            }
        } catch (Exception e) {
        }
    }
}
