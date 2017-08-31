/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phonebook.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import java.text.*;
import javafx.geometry.Orientation;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainForm extends JFrame {

    private Connection connection;

    private JMenuBar menuBar;
    private JMenu menu1, menu2, sendMail;
    private JMenuItem item1, item2, item3, item4, item5, item11, item6;
    private JToolBar toolBar, toolbar1;
    private JButton cmd1, cmd2, cmd3, cmd4, cmd5;
    private JPanel p;

    private void initComponent() {
        p = new JPanel(new GridLayout(1, 2, 5, 5));
        /*JLabel jl = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("/home/durgesh/Desktop/IMG_20160619_213529_225.jpg")));
         jl.setSize(400, 400);
         p.add(jl);*/

        item1 = new JMenuItem("Change Profile");
        item11 = new JMenuItem("Delete your acount");
        item2 = new JMenuItem("Add Person");
        item3 = new JMenuItem("Edit / Remove Person");
        item4 = new JMenuItem("View Person");
        item5 = new JMenuItem("Send Email");
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        item1.setFont(new Font("", Font.PLAIN, 15));
        item2.setFont(new Font("", Font.PLAIN, 15));
        item3.setFont(new Font("", Font.PLAIN, 15));
        item4.setFont(new Font("", Font.PLAIN, 15));
        item5.setFont(new Font("", Font.PLAIN, 15));
        item11.setFont(new Font("", Font.PLAIN, 15));

        item1.setMnemonic('C');
        item2.setMnemonic('A');
        item3.setMnemonic('E');
        item4.setMnemonic('V');
        item5.setMnemonic('S');

        menu1 = new JMenu("User");
        menu1.setFont(new Font("", Font.PLAIN, 20));
        menu1.add(item1);
        menu1.add(item11);
        menu1.setMnemonic('U');

        menu2 = new JMenu("Person");
        menu2.setFont(new Font("", Font.PLAIN, 20));
        menu2.setMnemonic('P');
        menu2.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        menu2.add(item5);

        sendMail = new JMenu("SendEmail");
        sendMail.setFont(new Font("", Font.PLAIN, 20));
        item6 = new JMenuItem("Gmail");
        item6.setFont(new Font("", Font.PLAIN, 15));
        sendMail.add(item6);

        menuBar = new JMenuBar();
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(sendMail);
        this.setJMenuBar(menuBar);

        cmd1 = new JButton(new ImageIcon("src/images/user.png"));
        cmd2 = new JButton(new ImageIcon("src/images/insert.png"));
        cmd3 = new JButton(new ImageIcon("src/images/update.png"));
        cmd4 = new JButton(new ImageIcon("src/images/viewall.png"));
        cmd5 = new JButton(new ImageIcon("src/images/clear.png"));

        cmd1.setToolTipText("Change Profile CTRL+C");
        cmd2.setToolTipText("Add Person CTRL+A");
        cmd3.setToolTipText("Edit / Remove Person CTRL+E");
        cmd4.setToolTipText("View Person CTRL+V");
        cmd5.setToolTipText("Send Email CTRL+S");

        toolBar = new JToolBar();
        toolbar1 = new JToolBar("Clock");
        toolBar.add(cmd1);
        toolBar.addSeparator();
        toolBar.add(cmd2);
        toolBar.addSeparator();
        toolBar.add(cmd3);
        toolBar.addSeparator();
        toolBar.add(cmd4);
        toolBar.addSeparator();
        toolBar.add(cmd5);
        toolBar.addSeparator();

        JLabel clock = new JLabel();
        DkClock dkc = new DkClock(clock);
        dkc.start();
        clock.setFont(new Font("", Font.BOLD, 18));

        toolbar1.add(clock);

        toolBar.setFloatable(false);
        toolbar1.setFloatable(false);

        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.add(toolbar1, BorderLayout.SOUTH);
        this.add(p, BorderLayout.CENTER);

    }

    public MainForm(Connection connection, String userName) {
        this.connection = connection;
        this.initComponent();
        this.initEvent();

        DKmove dk = new DKmove(this, " Welcome " + userName + " to Phonebook ");
        Thread t = new Thread(dk);
        t.start();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initEvent() {
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                cmd2.doClick();
            }

        });
        cmd2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                new NewPerson();
            }
        });
        item11.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure ??");
                if (i == 0) {

                    int ii = JOptionPane.showConfirmDialog(null, "All your persons will get deleted if you delete your account!!");
                    // JOptionPane.showConfirmDialog(null,i);
                    if (ii == 0) {
                        try {
                            Connection con = ConnectionProvider.getConnection();
                            PreparedStatement pstmt = con.prepareStatement("delete from user where usercode=?");
                            pstmt.setInt(1, LoginForm.USERCODE);
                            int flag = pstmt.executeUpdate();
                            if (flag > 0) {
                                JOptionPane.showMessageDialog(null, "You have deleted your account succeffully..Thankyou for using this software");
                                setVisible(false);
                            } else {
                                throw new Exception("Error!");
                            }
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(null, ee.getMessage());
                        }

                    }

                } else {

                }

            }
        });
        item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmd3.doClick();
            }
        });
        cmd3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditRemovePerson();
            }

        });
        item4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                cmd4.doClick();

            }

        });
        cmd4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ee) {
                new ViewPersonsForm();
            }
        });

        item6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new DKEmail();

            }
        });
        cmd5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                item6.doClick();
            }
        });

    }

}
