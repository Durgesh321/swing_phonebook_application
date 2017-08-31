/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phonebook.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

public class Newuser extends JFrame {

    private JPanel p1;
    private JLabel l1, l2, l3;
    private JTextField t1, t2;JPasswordField t3;
    private JButton b1, b2;

    public Newuser() {
        this.setTitle("New User");
        initComponet();
        initEvent();

    }

    private void initComponet() {
        p1 = new JPanel(new GridLayout(4, 2, 5, 5));

        l1 = new JLabel("Name ");
        l2 = new JLabel("Email");
        l3 = new JLabel("New Password ");

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JPasswordField(15);

        b1 = new JButton("Reset");
        b2 = new JButton("create");
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(b1);
        p1.add(b2);        
        p1.setBorder(BorderFactory.createTitledBorder("Enter User Detail -----"));
       
        this.add(p1);
        this.setVisible(true);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initEvent() {
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
               t2.setText("");
               t3.setText("");
               t1.requestFocus();
                
                
             }
        });
         b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try
                {
               
                    String name=t1.getText();
                    String email=t2.getText();
                    String password=t3.getText();
                    if(Validator.isName(name))
                    {
                        if(Validator.isEmail(email))
                        {
                                 Connection con=ConnectionProvider.getConnection();
                                 int usercode=new Random().nextInt(1000);
                                 PreparedStatement pstmt=con.prepareStatement("insert into user values(?,?,?,?)");
                                 pstmt.setInt(1,usercode);
                                 pstmt.setString(2, name);
                                 pstmt.setString(3,password);
                                 pstmt.setString(4,email);
                                 int i=pstmt.executeUpdate();
                                 if(i>0)
                                 {
                                     JOptionPane.showMessageDialog(null,"User created usercode= "+usercode);
                                     t1.setText("");
                                     t2.setText("");
                                     t3.setText("");
                                     setVisible(false);
                                 }
                                 else
                                 {
                                     throw new Exception("User not create try again");
                                 }
                            
                        }else
                        {
                            throw new Exception("Invalid User Email id");
                        }
                        
                        
                        
                        
                    }else
                    {
                        throw new Exception ("Invalid User Name");
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(null, ee.getMessage());
                }
                
                
                
                
                
                
                
                
                
                
             }
        });

    }

}
