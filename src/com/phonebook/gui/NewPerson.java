package com.phonebook.gui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPerson extends JFrame {

    private JPanel p1;
    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4;
    private JButton b1, b2;

    public NewPerson() {
       // setTitle("New User Form ");
        DKmove dk=new DKmove(this,"New User Form ");
       Thread t=new Thread(dk);
       t.start();
        initComponet();
        initEvent();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(p1);
        this.getRootPane().setDefaultButton(b2);
        this.setVisible(true);
    }

    private void initComponet() {
        p1 = new JPanel(new GridLayout(5, 2, 8, 8));
        p1.setBorder(BorderFactory.createTitledBorder("Enter Detail "));
        l1 = new JLabel("Name ");
        l2 = new JLabel("Mobile Number ");
        l3 = new JLabel("Email Id ");
        l4 = new JLabel("Address ");

        t1 = new JTextField(15);

        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextField(15);

        b1 = new JButton("Reset");
        b2 = new JButton("Create");

        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(b1);
        p1.add(b2);
        this.getContentPane().add(p1);

    }

    private void initEvent() {
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t1.requestFocus();
            }

        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                try {
                    String name = t1.getText();
                    String phone = t2.getText();
                    String email = t3.getText();
                    String address = t4.getText();
                    int personcode = new Random().nextInt(1000);
                    int usercode = LoginForm.USERCODE;
                    if (!(name.equals("") || phone.equals("") || email.equals("") || address.equals(""))) {

                        if (Validator.isName(name)) {
                            if (Validator.isMobile(phone)) {
                                if (Validator.isEmail(email)) {

                                    Connection con = ConnectionProvider.getConnection();
                                    PreparedStatement pstmt = con.prepareStatement("insert into person values(?,?,?,?,?,?)");
                                    pstmt.setInt(1, personcode);
                                    pstmt.setString(2, name);
                                    pstmt.setString(3, phone);
                                    pstmt.setString(4, email);
                                    pstmt.setString(5, address);
                                    pstmt.setInt(6, usercode);
                                    int i = pstmt.executeUpdate();
                                    if (i > 0) {
                                        JOptionPane.showMessageDialog(null, "successfully created person code =\t" + personcode);
                                        t1.setText("");
                                        t2.setText("");
                                        t3.setText("");
                                        t4.setText("");
                                        t1.requestFocus();
                                        setTitle("Create another person ");
                                       
                                    } else {
                                        JOptionPane.showMessageDialog(null, "User not created try again!!");
                                    }

                                } else {
                                    throw new Exception("Invalid Email Address !");
                                }
                            } else {
                                throw new Exception("Invalid mobile number");
                            }
                        } else {
                            throw new Exception("Invalid Person Name!!");
                        }
                    } else {
                        throw new Exception("Blank entries not allowed");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

        });
    }

}
