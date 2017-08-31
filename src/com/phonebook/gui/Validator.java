package com.phonebook.gui;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validator
{
	public static boolean isName(String name)
    {       
        boolean flag=true;
        if(name.trim().length()>0)
        {
            String temp=name.toUpperCase();
            for(int i=0;i<temp.length();i++)
            {
                char ch=temp.charAt(i);
                if((ch>='A' && ch<='Z') || ch==' ')
                {
                    continue;
                }
                else
                {
                    flag=false;
                    break;
                }
            }
        }
        else
        {
            flag=false;
        }
        
        return flag;
    }

    public static boolean isMobile(String phone)
    {
        boolean flag=true;
        try
        {
            if(phone.length()>0 && phone.length()==10)
            {
                Long.parseLong(phone);
            }
            else
            {
                flag=false;
            }
        }
        catch(Exception e)
        {
            flag=false;
        }
        return flag;
    }

    public static boolean isAge(String age)
    {
        boolean flag=true;
        try
        {
            if(Integer.parseInt(age)>0 && Integer.parseInt(age)<=100)
            {
                
            }
            else
            {
                flag=false;
            }
        }
        catch(Exception e)
        {
            flag=false;
        }
        return flag;
    }

    public static boolean isInteger(String data)
    {
        try
        {
            Integer.parseInt(data);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public static boolean isDouble(String data)
    {
        try
        {
            Double.parseDouble(data);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public static boolean isPercentage(String data)
    {
        try
        {
            double n=Double.parseDouble(data);
            return n>=0.0 && n<=100.0;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static boolean isDate(String data)
    {
        boolean flag=false;
        try
        {
            String a[]=data.split("/");
            if(isInteger(a[0]) && isInteger(a[1]) && isInteger(a[2]))
            {
                int d=Integer.parseInt(a[0]);
                int m=Integer.parseInt(a[1]);
                int y=Integer.parseInt(a[2]);                
                if(d>0 && d<=31)
                {
                    if(m>0 && m<=12)
                    {
                        int currentYear=new GregorianCalendar().get(Calendar.YEAR);
                        if(y>=0 && y<=currentYear)
                        {
                            flag=true;
                        }
                    }
                }                 
            }
        }
        catch(Exception e){}
        return flag;
    }
	public static boolean isDate(String data,String separator)
    {
        boolean flag=false;
        try
        {
            String a[]=data.split(separator);
            if(isInteger(a[0]) && isInteger(a[1]) && isInteger(a[2]))
            {
                int d=Integer.parseInt(a[0]);
                int m=Integer.parseInt(a[1]);
                int y=Integer.parseInt(a[2]);                
                if(d>0 && d<=31)
                {
                    if(m>0 && m<=12)
                    {
                        int currentYear=new GregorianCalendar().get(Calendar.YEAR);
                        if(y>=0 && y<=currentYear)
                        {
                            flag=true;
                        }
                    }
                }                 
            }
        }
        catch(Exception e){}
        return flag;
    }
    public static boolean isEmail(String data)
    {
        boolean flag=false;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern=Pattern.compile(EMAIL_PATTERN);
        Matcher matcher=pattern.matcher(data);
        flag=matcher.matches();
        return flag;
    }
}
