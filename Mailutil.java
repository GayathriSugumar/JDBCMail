package com.healthcare.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailutil {
	public static void sendMail(String recepient) throws Exception{
		System.out.println("Invoked sendMail()");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myAccountEmail = "gayathrih312@gmail.com";
		String password = "Gayathri$0312";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message = prepareMessage(session, myAccountEmail, recepient);
		Transport.send(message);
		message.saveChanges();
		System.out.println("Message send successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
		
		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Test mail");
			message.setText("Verifing Mail,\n Confirm your email verification");
			return message;
		} catch (Exception e) {
			Logger.getLogger(Mailutil.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null; 
		
	}

}
