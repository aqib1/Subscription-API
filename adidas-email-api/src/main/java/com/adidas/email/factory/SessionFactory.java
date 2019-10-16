package com.adidas.email.factory;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.adidas.email.constant.Consts;
import com.adidas.email.constant.Helper;
import com.example.model.EmailRequest;

public class SessionFactory {

	private Properties properties = null;
	private Session session = null;
	private static SessionFactory sessionFactory = null;

	private SessionFactory() {

	}

	public SessionFactory initSession(EmailRequest emailRequest) {
		synchronized (SessionFactory.class) {
			if (Helper.isNull(session)) {
				properties = System.getProperties();
				properties.setProperty(Consts.KEY_SMTP_HOST, Consts.VALUE_SMTP_HOST);
				properties.setProperty(Consts.KEY_SMTP_PORT, Consts.VALUE_SMTP_PORT);
				properties.put(Consts.KEY_SMTP_STARTTLS, Consts.VALUE_SMTP_STARTTLS);
				properties.setProperty(Consts.KEY_MAIL_DEBUG, Consts.VALUE_MAIL_DEBUG);
				properties.setProperty("mail.smtps.ssl.enable", "true");
				properties.setProperty("mail.smtps.auth", "true"); 
				properties.setProperty("mail.smtp.user", emailRequest.getSenderEmail());
				properties.setProperty("mail.smtp.password", emailRequest.getSenderEmailPassword());
				Authenticator authenticator = new Authenticator() {
					
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailRequest.getSenderEmail(), emailRequest.getSenderEmailPassword());
					}
				};
				
				session = Session.getInstance(properties, authenticator);
			}
		}
		return this;
	}

	public Session getSession() {
		return session;
	}

	public static SessionFactory getInstance() {
		if (Helper.isNull(sessionFactory)) {
			synchronized (SessionFactory.class) {
				if (Helper.isNull(sessionFactory)) {
					sessionFactory = new SessionFactory();
				}
			}
		}
		return sessionFactory;
	}
}
