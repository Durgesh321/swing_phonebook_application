package com.phonebook.gui;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email 
{
    private String sender;
    private String reciever;
    private String subject;
    private String text;

    public Email() {
    }

    public Email(String sender, String reciever, String subject, String text) {
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
   public boolean send(String userName,String password)
   {
       boolean flag=false;
       try
       {
           String host = "smtp.gmail.com";
    String from = userName;
    String pass = password;
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true"); 
    props.put("mail.smtp.host", this.sender);
    props.put("mail.smtp.user", userName);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
 
    String[] to = {this.reciever}; // added this line
 
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
 
    InternetAddress[] toAddress = new InternetAddress[to.length]; 
    
    for( int i=0; i < to.length; i++ ) 
    {        
        toAddress[i] = new InternetAddress(to[i]);
    }    
 
    for( int i=0; i < toAddress.length; i++) 
    { 
        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
    }
    
    message.setSubject(this.subject);
    message.setText(this.text);
    Transport transport = session.getTransport("smtp");
    transport.connect(host, from, pass);
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
    flag=true;
       }
       catch(Exception e)
       {
           System.out.println(e.toString());
       }
       return flag;
   }
   public boolean send(String userName,String password,File file)
   {
       boolean flag=false;
       try
       {
           String host = "smtp.gmail.com";
    String from = userName;
    String pass = password;
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true"); 
    props.put("mail.smtp.host", this.sender);
    props.put("mail.smtp.user", userName);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
 
    String[] to = {this.reciever}; // added this line
 
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
 
    InternetAddress[] toAddress = new InternetAddress[to.length]; 
    
    for( int i=0; i < to.length; i++ ) 
    {        
        toAddress[i] = new InternetAddress(to[i]);
    }    
 
    for( int i=0; i < toAddress.length; i++) 
    { 
        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
    }
    message.setSubject(this.subject); 
    
    
    //-------------------------------------------------
    
    BodyPart body1=new MimeBodyPart();
    body1.setText(this.text);
    
    BodyPart body2=new MimeBodyPart();
    DataHandler handler=new DataHandler(new FileDataSource(file));
    body2.setDataHandler(handler);
    body2.setFileName(file.getName());
    
    Multipart multipart=new MimeMultipart();
    multipart.addBodyPart(body1);
    multipart.addBodyPart(body2);
    
    message.setContent(multipart);
    //-------------------------------------------------
    
    Transport transport = session.getTransport("smtp");
    transport.connect(host, from, pass);
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
    flag=true;
       }
       catch(Exception e)
       {
           System.out.println(e.toString());
       }
       return flag;
   } 
}
