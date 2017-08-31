/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phonebook.gui;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class DKmove implements Runnable
{
   private JFrame f;
   private String  str;

    public DKmove(JFrame f,String str) {
        this.f=f;
        this.str=str;
    }
   
    public void run()
    {
        try
        {
            while(true)
            {
                char ch=str.charAt(0);
                String temp=str.substring(1);
                 str="";
                 str=temp+ch;
                
                set(str);
                //System.out.println(str);
                Thread.currentThread().sleep(400);
            }
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
    }
   private void set(String s)
   {
       f.setTitle(s);
          }
    
}
