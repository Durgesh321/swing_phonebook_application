/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phonebook.gui;

import java.text.SimpleDateFormat;
import javax.swing.JLabel;

/**
 *
 * @author durgesh
 */
public class DkClock extends Thread
{
    private JLabel j;
    public DkClock(JLabel j)
    {
     this.j=j;
    }
    public void run()
    {
        try
        {
            while(true)
            {               
        java.util.Date d = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("d, MMM Y   hh:mm:ss");
        String date = sdf.format(d);
        j.setText(date);
        Thread.currentThread().sleep(1000);
            }
        }catch(Exception e)
        {
           System.out.println(e);
        }
    }
    
}
