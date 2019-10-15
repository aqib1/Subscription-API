package com.adidas.email.factory;

import javax.mail.internet.MimeMessage;

import com.adidas.email.constant.Helper;

public class MimeMessageFactory {

	public static MimeMessageFactory mimeMessageFactory = null;
	
	private MimeMessageFactory() {
		
	}
	
	
	
	public static MimeMessageFactory getInstance() {
		if(Helper.isNull(mimeMessageFactory)) {
			synchronized (MimeMessageFactory.class) {
				if(Helper.isNull(mimeMessageFactory)) {
					mimeMessageFactory = new MimeMessageFactory();
				}
			}
		}
		return mimeMessageFactory;
	}
}
