package com.phonebook.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class EditRemovePerson extends JFrame {

    private JPanel p1, p2;
    private JLabel l1, l2, l3, l4, l5;
    private JTextField t1, t2, t3, t4;
    private JComboBox comb;
    private JButton b1, b2, b3;

    public EditRemovePerson() {
        this.setTitle("Edit/Remove Person");
        initComponent();
        initKeyEvent();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initComponent() {
        p1 = new JPanel(new GridLayout(5, 2, 8, 8));
        p1.setBorder(BorderFactory.createTitledBorder("Enter User Details"));
        p2 = new JPanel(new GridLayout(1, 3, 5, 5));

        l1 = new JLabel("Select Person ");
        l2 = new JLabel("Name ");
        l3 = new JLabel("Mobile Number ");
        l4 = new JLabel("Email ");
        l5 = new JLabel("Address ");

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextField(15);

        b1 = new JButton("Reset", new ImageIcon("src/images/clear.png"));
        b2 = new JButton("Remove", new ImageIcon("src/images/delete.png"));
        b3 = new JButton("Edit", new ImageIcon("src/images/insert.png"));

        comb = new JComboBox();
        Helper.FillComboBox("select personcode,name from person where usercode=" + LoginForm.USERCODE, comb);
        p1.add(l1);
        p1.add(comb);
        p1.add(l2);
        p1.add(t1);
        p1.add(l3);
        p1.add(t2);
        p1.add(l4);
        p1.add(t3);
        p1.add(l5);
        p1.add(t4);

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        this.getContentPane().add(p1, BorderLayout.NORTH);
        this.getContentPane().add(p2, BorderLayout.SOUTH);
        this.getRootPane().setDefaultButton(b3);

    }

    private void initKeyEvent() {
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");

            }

        });
        comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String personcode = comb.getSelectedItem().toString().split("-")[0];
                    Connection con = ConnectionProvider.getConnection();
                    PreparedStatement pstmt = con.prepareStatement("select * from person where personcode=" + personcode + " and usercode=" + LoginForm.USERCODE);

                    ResultSet rset = pstmt.executeQuery();
                    if (rset.next()) {
                        t1.setText(rset.getString(2));
                        t2.setText(rset.getString(3));
                        t3.setText(rset.getString(4));
                        t4.setText(rset.getString(5));

                    }
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee.getMessage());
                }
            }

        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                try {

                    String personcode = comb.getSelectedItem().toString().split("-")[0];
                    Connection con = ConnectionProvider.getConnection();
                    PreparedStatement pstmt = con.prepareStatement("delete from person where personcode=" + personcode + " and usercode=" + LoginForm.USERCODE);

                    int i = pstmt.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
                        setVisible(false);
                      //  Helper.FillComboBox("select personcode,name from person where usercode=" + LoginForm.USERCODE,comb);
                        b1.doClick();
                    } else {
                        throw new Exception("Not Deleted");
                    }

                } catch (Exception e) {

                }

            }

        });
    }

}
