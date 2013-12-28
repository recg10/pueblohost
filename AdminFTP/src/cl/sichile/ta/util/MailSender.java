package cl.sichile.ta.util;

import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class MailSender {

	private static String hostSmtp  = Property.getInstance().getSmtpHost();
	private static Logger log = Logger.getLogger(MailSender.class);
	private static String senderAddress = Property.getInstance().getAddressSender();
	
	public static boolean send(String toAddress, 
	       String ccAddress, String bccAddress, String subject, 
	       boolean isHTMLFormat, String body, boolean debug){ 
	   log.debug("send mail");
	   MimeMultipart multipart = new MimeMultipart(); 

	   Properties properties = new Properties(); 

	    properties.put("mail.smtp.host", MailSender.getHostSmtp() ); 
	    properties.put("mail.smtp.user", MailSender.getSenderAddress() );
	    properties.put("mail.smtp.pass", Property.getInstance().getPasswordSender() );
	    properties.put("mail.smtp.auth", Property.getInstance().getMailAutentication());
	    
	    //properties.setProperty("mail.smtp.starttls.enable", "true"); //Solo cuanta Gmail
	    
	    log.debug("Propiedades para el envio:" +
	    		"\n mail.smtp.host:"+MailSender.getHostSmtp()+
	    		"\n mail.smtp.user:"+MailSender.getSenderAddress()+
	    		"\n mail.smtp.pass:"+Property.getInstance().getPasswordSender()+
	    		"\n mail.smtp.auth:"+Property.getInstance().getMailAutentication());
	   MailAuthentication autSistemas=new MailAuthentication();
	   Session session = Session.getDefaultInstance(properties, autSistemas); 
	   session.setDebug(debug); 
	   try { 
	      MimeMessage msg = new MimeMessage(session); 
	      msg.setFrom(new InternetAddress(MailSender.getSenderAddress()));
	      msg.setRecipients(Message.RecipientType.TO, MailSender.getCorreosSinPuntoComa(toAddress)); 
	      msg.setRecipients(Message.RecipientType.CC, MailSender.getCorreosSinPuntoComa(ccAddress)); 
	      msg.setRecipients(Message.RecipientType.BCC, MailSender.getCorreosSinPuntoComa(bccAddress)); 
	      msg.setSubject(subject); 
	      msg.setSentDate(new Date()); 

	      // BODY 
	      MimeBodyPart mbp = new MimeBodyPart(); 
	      if(isHTMLFormat){ 
	    	  log.debug("es formato HTML");
	    	  mbp.setContent(body, "text/html"); 
//	    	  BodyPart adjunto = new MimeBodyPart();
		      multipart.addBodyPart(mbp); 
//		      multipart.addBodyPart(adjunto);	      
		  } 
	      else{ 
	    	  log.debug("es formato TEXTO PLANO");
	    	  mbp.setText(body); 
		      multipart.addBodyPart(mbp); 
	      } 


	      msg.setContent(multipart); 
	      Transport.send(msg); 
	      log.debug("Fin envio mail");
	   } 
	   catch (Exception mex){ 
		   log.error(">> MailSender.send() error = ",mex ); 
	      return false; 
	   } 
	   return true; 
	}

	public static String getHostSmtp() {
		return hostSmtp;
	}

	public static void setHostSmtp(String hostSmtp) {
		MailSender.hostSmtp = hostSmtp;
	}

	public static String getSenderAddress() {
		return senderAddress;
	}

	public static void setSenderAddress(String senderAddress) {
		MailSender.senderAddress = senderAddress;
	} 

	private static InternetAddress[] getCorreosSinPuntoComa (String correos) throws AddressException {
		StringTokenizer correo = new StringTokenizer(correos,";");
		InternetAddress[] listaCorreos = new InternetAddress[correo.countTokens()];
	    int i = 0;
		while (correo.hasMoreElements())
	    {
	        listaCorreos[i]= new InternetAddress(correo.nextElement().toString());
	        i++;
	    }
		return listaCorreos;
	}
}
	

