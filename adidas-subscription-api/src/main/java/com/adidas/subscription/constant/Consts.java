package com.adidas.subscription.constant;

public class Consts {

	/******************** General Values ********************/
	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;

	/********************** Scan Packages **********************/
	public static final String SUBSCRIPTION_COMPONENT_SCAN_PATH = "com.adidas.subscription";
	public static final String SUBSCRIPTION_REPOSITERIES_PATH = "com.adidas.subscription.repository";
	public static final String SUBSCRIPTION_ENTITIES_PATH = "com.adidas.subscription.entities";

	/***************** URLS ************/
	public static final String URL_SUBSCRIPTION_MAIN = "/subscription";
	public static final String URL_SUBSCRIPTION_GET_BY_EMAIL = "/{email}";
	public static final String URL_SUBSCRIPTION_DELETE_BY_EMAIL = "/{email}";
	public static final String URL_SUBSCRIPTION_GET_ALL = "/all";
	public static final String URL_SUBSCRIPTION_DELETE_ALL = "/all";
	public static final String URL_EMAIL_SENDER = "http://adidas-email-api/email";

	/***************** SUBSCRIPTION_TABLE DETAILS *********************/
	public static final String SUBSCRIPTION_ENTITY_TABLE_NAME = "SUBSCRIPTION_TABLE";
	public static final String SUBSCRIPTION_ENTITY_COL_FIRST_NAME = "FIRST_NAME";
	public static final String SUBSCRIPTION_ENTITY_COL_GENDER = "GENDER";
	public static final String SUBSCRIPTION_ENTITY_COL_DATE_OF_BIRTH = "DATE_OF_BIRTH";
	public static final String SUBSCRIPTION_ENTITY_COL_CONSENT = "CONSENT";
	public static final String SUBSCRIPTION_ENTITY_COL_NEWS_LETTER_ID = "NEWSLETTER_ID";
	public static final String SUBSCRIPTION_ENTITY_COL_EMAIL = "Email";

	/******************* Email Account Details **************************/
	public static final String SENDER_EMAIL_ACCOUNT = "aqibbutt3078@gmail.com";
	public static final String SENDER_EMAIL_PASSWORD = "aqibbutt23136$$$";
	public static final String SENDER_EMAIL_MESSAGE_TEMPLATE = "Email sending against newsletter => ";
	public static final String SENDER_EMAIL_SUBJECT = "EMAIL - Newsletter";
	public static final String SENDER_EMAIL_NAME = "AQIB - JAVED";

	/*********************** REGEX *******************/
	public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	private Consts() {

	}
}
