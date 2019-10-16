package com.adidas.email.factory;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adidas.email.constant.Consts;
import com.adidas.email.constant.Helper;
import com.example.model.EmailRequest;

public class SessionFactory {
	private Logger logger = LoggerFactory.getLogger(SessionFactory.class);

	private Properties properties = null;
	private Session session = null;
	private static SessionFactory sessionFactory = null;

	private SessionFactory() {

	}

	public SessionFactory initSession(EmailRequest emailRequest) {
		synchronized (SessionFactory.class) {
			if (Helper.isNull(session)) {
				logger.info("Initialize properties for session");
				properties = System.getProperties();
				properties.setProperty(Consts.KEY_SMTP_HOST, Consts.VALUE_SMTP_HOST);
				properties.setProperty(Consts.KEY_SMTP_PORT, Consts.VALUE_SMTP_PORT);
				properties.put(Consts.KEY_SMTP_STARTTLS, Consts.VALUE_SMTP_STARTTLS);
				properties.setProperty(Consts.KEY_MAIL_DEBUG, Consts.VALUE_MAIL_DEBUG);
				properties.setProperty(Consts.KEY_MAIL_MAIL_SMTPS_SSL_ENABLE, Consts.VALUE_MAIL_MAIL_SMTPS_SSL_ENABLE);
				properties.setProperty(Consts.KEY_MAIL_STMPS_AUTH, Consts.VALUE_MAIL_STMPS_AUTH);
				properties.setProperty(Consts.KEY_MAIL_SMTP_USER, emailRequest.getSenderEmail());
				properties.setProperty(Consts.KEY_SMTP_PASSORD, emailRequest.getSenderEmailPassword());
				Authenticator authenticator = new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						logger.info("Creating Authenticator for sender and password");
						return new PasswordAuthentication(emailRequest.getSenderEmail(),
								emailRequest.getSenderEmailPassword());
					}
				};
				logger.info("Creating session object");
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
