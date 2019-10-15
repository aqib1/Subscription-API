package com.adidas.email.factory;

import java.util.Properties;

import javax.mail.Session;

import com.adidas.email.constant.Consts;
import com.adidas.email.constant.Helper;

public class SessionFactory {

	private Properties properties = null;
	private Session session = null;
	private static SessionFactory sessionFactory = null;

	private SessionFactory() {

	}

	public SessionFactory initSession() {
		synchronized (SessionFactory.class) {
			if (Helper.isNull(session)) {
				properties = System.getProperties();
				properties.setProperty(Consts.KEY_SMTP_HOST, Consts.VALUE_SMTP_HOST);
				session = Session.getDefaultInstance(properties);
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
