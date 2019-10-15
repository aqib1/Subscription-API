package com.adidas.email.constant;

public class Consts {
	
	/***********************Properties for JavaMail Service***************/
	public static final String KEY_SMTP_HOST = "mail.smtp.host";
	public static final String VALUE_SMTP_HOST = "localhost";
	public static final String KEY_CONTENT_TYPE = "Content-type";
	public static final String VALUE_HTML_TEXT_TYPE = "text/HTML; charset=UTF-8";
	public static final String KEY_FORMAT = "format";
	public static final String VALUE_FORMAT = "flowed";
	public static final String KEY_CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
	public static final String _8_BIT = "8bit";
	
	/***********************REGEX*******************/
	public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private Consts() {

	}
}
