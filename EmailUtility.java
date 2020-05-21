package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtility
{
  public static void sendEmailAttachment(String subject,String msg,String toaddress,List<File> attachedFiles) throws AddressException, MessagingException 
    {
    	
        Properties props = System.getProperties();
       // props.put("mail.smtp.host", "dedrelay-hosting.seureserver.net"); 
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.socketFactory.port", "587");  
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");  
        final String fromUser = "tr@technoreachit.com";
        final String fromUserEmailPassword = "Sairam@123";

        Session mailSession = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {  
        protected PasswordAuthentication getPasswordAuthentication() {  
      return new PasswordAuthentication(fromUser,fromUserEmailPassword);//change accordingly  
        }  
          });  
        //2) compose message     
        try{  
        String toEmail =toaddress;
       // String emailSubject = subject;
        String emailBody = msg;
        MimeMessage emailMessage = new MimeMessage(mailSession);
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));       
        emailMessage.setSubject(subject);
        emailMessage.setFrom(fromUser);
        emailMessage.setSentDate(new Date());
        
      //3) create MimeBodyPart object and set your message text     
        BodyPart messageBodyPart1 = new MimeBodyPart();  
        messageBodyPart1.setContent(emailBody, "text/html");
        
        Multipart multipart = new MimeMultipart();  
        multipart.addBodyPart(messageBodyPart1);  
        
        // adds attachments
        if (attachedFiles != null && attachedFiles.size() > 0) {
            for (File aFile : attachedFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(aFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // set the multiplart object to the message object  
        emailMessage.setContent(multipart);  
         
        //7) send message  
        Transport.send(emailMessage);
        System.out.println("Email sent successfully.");
        }catch (MessagingException ex) {ex.printStackTrace();}  

    }
  
  
  
  public static void conactusEmail(String subject, String msg,String toaddress) throws AddressException, MessagingException 
  {
  	
      Properties props = System.getProperties();
      //props.put("mail.smtp.host", "dedrelay-hosting.seureserver.net"); 
      props.put("mail.smtp.host", "smtp.gmail.com");  
      props.put("mail.smtp.socketFactory.port", "587");  
      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
      props.put("mail.smtp.auth", "true");  
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.port", "587");  
      final String fromUser = "tr@technoreachit.com";
      final String fromUserEmailPassword = "Sairam@123";


      Session mailSession = Session.getDefaultInstance(props,  
      new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(fromUser,fromUserEmailPassword);//change accordingly  
      }  
        });  
      try{
      String toEmail =toaddress;
      String emailSubject = subject;
      String emailBody = msg;
      MimeMessage emailMessage = new MimeMessage(mailSession);
      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));       
      emailMessage.setSubject(emailSubject);
      emailMessage.setFrom(fromUser);
      emailMessage.setSentDate(new Date());
      emailMessage.setContent(emailBody, "text/html");
      //emailMessage.setText(emailBody);// for a text email
      
      /**
       * Send the mail
       * */
      Transport.send(emailMessage);
      System.out.println("Email sent successfully.");
	}catch (MessagingException ex) {ex.printStackTrace();}  	
  }
  
  
  public static void sendEmail(String subject,String msg,String toaddress) throws AddressException, MessagingException 
  {
  	
      Properties props = System.getProperties();
     // props.put("mail.smtp.host", "dedrelay-hosting.seureserver.net"); 
      props.put("mail.smtp.host", "smtp.gmail.com");  
      props.put("mail.smtp.socketFactory.port", "587");  
      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
      props.put("mail.smtp.auth", "true");  
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.port", "587");  
      final String fromUser = "tr@technoreachit.com";
      final String fromUserEmailPassword = "Sairam@123";

      Session mailSession = Session.getDefaultInstance(props,  
      new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(fromUser,fromUserEmailPassword);//change accordingly  
      }  
        });  
      //2) compose message     
      try{  
      String toEmail =toaddress;
      String emailSubject = subject;
      String emailBody = msg;
      MimeMessage emailMessage = new MimeMessage(mailSession);
      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));       
      emailMessage.setSubject(emailSubject);
      emailMessage.setFrom(fromUser);
      emailMessage.setSentDate(new Date());
      emailMessage.setContent(emailBody, "text/html");
      //emailMessage.setText(emailBody);// for a text email
      
      /**
       * Send the mail
       * */
       
      //7) send message  
      Transport.send(emailMessage);
      System.out.println("Email sent successfully.");
      }catch (MessagingException ex) {ex.printStackTrace();}  

  }
}