package com.phonebook.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.event.AncestorListener;


public class LoginForm extends JFrame {

    private JTextField t1;
    private JPasswordField pass1;
    private JLabel l1, l2;
    private JPanel p1, p2;
    private JButton b1, b2;
    public static int USERCODE;

    public LoginForm() {
       // this.setTitle("Login");
        DKmove dk=new DKmove(this," Login ");
        Thread t=new Thread(dk);
        t.start();
        initComponent();
        initKeyEvent();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponent() {
        p1 = new JPanel(new GridLayout(2, 2, 5, 5));
        p1.setBorder(BorderFactory.createTitledBorder("Enter user detail"));
        p2 = new JPanel(new GridLayout(1, 2, 5, 5));
        p2.setBorder(BorderFactory.createTitledBorder("Action"));
        b1 = new JButton("New User",new ImageIcon("src/images/insert.png"));
        
        b1.setFont(new Font("",Font.PLAIN,15));
        b2 = new JButton("Submit",new ImageIcon("src/images/lock.png"));
       // b2.setEnabled(false);
        b2.setFont(new Font("",Font.PLAIN,15)); 
        l1 = new JLabel("User Code");
         l1.setFont(new Font("",Font.PLAIN,15));
        l2 = new JLabel("Password");
         l2.setFont(new Font("",Font.PLAIN,15));
        t1 = new JTextField(15);
        pass1 = new JPasswordField(15);

        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(pass1);
        p2.add(b1);
        p2.add(b2);
        this.getRootPane().setDefaultButton(b2);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.SOUTH);
     



    }
    private void initKeyEvent()
    {
       
       b1.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
                //setVisible(false);
               //JOptionPane.showMessageDialog(null, "working);
               new Newuser();
               
           }
       });
       b2.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) 
           {
           
            try
            {
                 int usercode=Integer.valueOf(t1.getText());
            String pass=pass1.getText();
                if(!pass.equals("")){
            Connection con= ConnectionProvider.getConnection();
            PreparedStatement pstmt=con.prepareStatement("select * from user where usercode=? and password=?");
            pstmt.setInt(1,usercode);
            pstmt.setString(2, pass);
               ResultSet rset=pstmt.executeQuery();
               if(rset.next())
               {
                   setVisible(false);
                 String name=rset.getString(2);
                
                 new LoginForm().USERCODE=usercode;
                
           
                 new MainForm(con, name); 
                  
               }
               else
               {
                   JOptionPane.showMessageDialog(null,"You have entered wrong usercode or password!!");
               }
                }
                else
                {
                    throw new Exception("Blank entries not allowed");
                }
            }catch(Exception ee)
            {
                JOptionPane.showMessageDialog(null,ee.getMessage());
                
            }
           
          
           }
       });
       this.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent event)
           {
             ConnectionProvider.close();
           }
});
    }
}
