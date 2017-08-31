/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phonebook.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author durgesh
 */
public class DKEmail extends JFrame {

    private JComboBox cmb;
    private JPanel p1;
    private JPanel p2;
    private JTextField t1, t2;
    private JLabel l1, l2, l3;
    private JButton b1, b2, b3;

    private void initComponent() {
        l1 = new JLabel("Select Person");
        cmb = new JComboBox();
        Helper.FillComboBox("select personcode,name from person where usercode=" +LoginForm.USERCODE,cmb);
        l2 = new JLabel("Subject Of Mail");
        t1 = new JTextField(20);
        t1.setSelectedTextColor(Color.red);
        l3 = new JLabel("Message");
        t2 = new JTextField(20);
        p1 = new JPanel(new GridLayout(3, 2, 10, 5));
        p1.add(l1);
        p1.add(cmb);
        p1.add(l2);
        p1.add(t1);
        p1.add(l3);
        p1.add(t2);
        p1.setBorder(BorderFactory.createTitledBorder("Send Email"));
        this.add(p1, BorderLayout.NORTH);

        b1 = new JButton("Send");
        b2 = new JButton("Cancel");
        b3 = new JButton("Clear");

        p2 = new JPanel(new GridLayout(1, 3, 3, 3));
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        this.add(p2, BorderLayout.SOUTH);

    }

    public DKEmail() {
        this.setTitle("Send Email");

        this.initComponent();
        this.initEvent();
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    private void  initEvent()
    {
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
             String toemail=Helper.getEmail(Integer.parseInt(cmb.getSelectedItem().toString().split("-")[0]));
             String from="durgeshkumar305@gmail.com";
             String UserName="durgeshkumar305";
             String password="password";
             String subject=t1.getText();
             String messgage=t2.getText();
             Email eee=new Email(from, toemail,subject ,messgage );
            if( eee.send(UserName, password))
            {
                JOptionPane.showMessageDialog(null, "Email is successfully Send");
            }
            
;                }
                catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(null, ee.getMessage());
                }
            }
        });
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           DKEmail.this.setVisible(false);
            
            }
        });
        
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
             cmb.removeAll();
             t1.setText("");
             t2.setText("");
            
            }
        });
        
    }

}
