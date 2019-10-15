package com.adidas.email.constant;

public class Consts {
	
	/***********************Properties for JavaMail Service***************/
	public static final String KEY_SMTP_HOST = "mail.smtp.host";
	public static final String VALUE_SMTP_HOST = "localhost";
	
	
	/***********************REGEX*******************/
	public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private Consts() {

	}
}
