package cl.sichile.ta.util;



import javax.mail.PasswordAuthentication;

import org.apache.log4j.Logger;

public class MailAuthentication extends javax.mail.Authenticator{
	
	private static Logger log = Logger.getLogger(MailAuthentication.class);
	
	public PasswordAuthentication getPasswordAuthentication()
		{	
			log.debug("datos sesion mail:Responsable()"+Property.getInstance().getAddressSender()+" PasswordSender()"+Property.getInstance().getPasswordSender());		
			return new PasswordAuthentication(Property.getInstance().getAddressSender(),Property.getInstance().getPasswordSender());
		} 

	}
	

