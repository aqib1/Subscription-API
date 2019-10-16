package com.adidas.email.constant;

public class Consts {
	
	/**********************Scan Packages**********************/
	public static final String SUBSCRIPTION_COMPONENT_SCAN_PATH = "com.adidas.email";
	
	/*************************URLS**************************/
	public static final String URL_EMAIL_SENDER = "/email";
	
	
	/***********************Properties for JavaMail Service***************/
	public static final String KEY_SMTP_HOST = "mail.smtp.host";
	public static final String KEY_SMTP_PORT = "mail.smtp.port";
	public static final String KEY_MAIL_DEBUG = "mail.debug";
	public static final String VALUE_MAIL_DEBUG = "true"; 
	public static final String KEY_SMTP_STARTTLS = "mail.smtp.starttls.enable";
	public static final String VALUE_SMTP_STARTTLS = "true";
	public static final String VALUE_SMTP_PORT = "587";
	public static final String VALUE_SMTP_HOST = "smtp.gmail.com";
	public static final String KEY_CONTENT_TYPE = "Content-type";
	public static final String VALUE_HTML_TEXT_TYPE = "text/HTML; charset=UTF-8";
	public static final String KEY_FORMAT = "format";
	public static final String VALUE_FORMAT = "flowed";
	public static final String KEY_CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
	public static final String _8_BIT = "8bit";
	public static final String UTF_8 = "UTF_8";
	
	/***********************REGEX*******************/
	public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private Consts() {

	}
}
