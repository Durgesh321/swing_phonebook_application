
package myphonebook;

import com.phonebook.gui.LoginForm;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MyPhonebook {

   
    public static void main(String[] args)
    {
        try
        {
         UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
         LoginForm lf=new LoginForm();
          SwingUtilities.updateComponentTreeUI(lf);
      
        }catch(Exception e)
        {
            
        }
        
    }
}
