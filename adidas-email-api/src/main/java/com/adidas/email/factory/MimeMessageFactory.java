package com.adidas.email.factory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.adidas.email.constant.Consts;
import com.adidas.email.constant.Helper;
import com.example.model.MimeMessagesTypes;

public class MimeMessageFactory {

	public static MimeMessageFactory mimeMessageFactory = null;

	private MimeMessageFactory() {

	}

	public MimeMessage getMimeMessage(MimeMessagesTypes type) throws MessagingException {
		MimeMessage mimeMessage = null;
		switch (type) {
		case HTML_TEXT:
			mimeMessage = getHtmlTextMimeMessage();
			break;
		default:
			break;
		}
		return mimeMessage;
	}

	private MimeMessage getHtmlTextMimeMessage() throws MessagingException {
		MimeMessage message = new MimeMessage(SessionFactory.getInstance().initSession().getSession());
		message.addHeader(Consts.KEY_CONTENT_TYPE, Consts.VALUE_HTML_TEXT_TYPE);
		message.addHeader(Consts.KEY_FORMAT, Consts.VALUE_FORMAT);
		message.addHeader(Consts.KEY_CONTENT_TRANSFER_ENCODING, Consts._8_BIT);
		return message;
	}

	public static MimeMessageFactory getInstance() {
		if (Helper.isNull(mimeMessageFactory)) {
			synchronized (MimeMessageFactory.class) {
				if (Helper.isNull(mimeMessageFactory)) {
					mimeMessageFactory = new MimeMessageFactory();
				}
			}
		}
		return mimeMessageFactory;
	}
}
