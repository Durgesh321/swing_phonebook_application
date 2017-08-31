package com.phonebook.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import sun.awt.ModalExclude;

public class ViewPersonsForm extends JFrame {

    private JPanel p1, p2;
    private JButton b1, b2, b3, b4, b5;
    private JMenuBar jmb;
    private JMenu m;
    private JMenuItem print_item;
    private JTable tbl1;
    private DefaultTableModel dtm;
    private JTextField txt;

    public ViewPersonsForm() {
        setTitle("Persons");
        initComponet();
        initKeyEvent();
        pack();
       // this.setSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    private void initComponet() {
        p1 = new JPanel(new GridLayout(1, 1, 5, 5));
        p2 = new JPanel(new GridLayout(1, 5, 5, 5));
        p1.setBorder(BorderFactory.createTitledBorder("Person List"));
        p2.setBorder(BorderFactory.createTitledBorder("Action"));

        dtm = new DefaultTableModel();
        

        tbl1 = new JTable(dtm);
     Helper.FillTable("select personcode,name,phone,email,address from person where usercode=" + LoginForm.USERCODE, dtm);
       
  
        b1 = new JButton("Refresh");
        b1.setToolTipText("Refresh to get latest added data");
        b2 = new JButton("Print");
        b3 = new JButton("HTML");
        b4 = new JButton("Excel");
        b5 = new JButton("PDF");
        txt=new JTextField(15);

        p1.add(new JScrollPane(tbl1));
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        p2.add(txt);

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.SOUTH);

    }
    private void initKeyEvent()
    {
        b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        Helper.FillTable("select personcode,name,phone,email,address from person where usercode=" + LoginForm.USERCODE, dtm);

            
        }
        
        });
        b2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            try
            {
            tbl1.print();
            }
            catch(Exception ee)
            {
                
            }
        }
        
        });
        b3.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            Helper.generateHTML(tbl1);
        }
        
        });
        
         b5.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            Helper.generatePDF(tbl1);
        }
        
        });
         
        txt.addKeyListener(new KeyAdapter() {
            
            
            public void keyReleased(KeyEvent k)
            {
                try{
                //JOptionPane.showMessageDialog(null,txt.getText());
                String name=txt.getText();
                Helper.FillTable("select personcode,name,phone,email,address from person where name like '"+name+"%' and usercode="+LoginForm.USERCODE, dtm);
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                        }}
});
        
        
        
        
    }

}
