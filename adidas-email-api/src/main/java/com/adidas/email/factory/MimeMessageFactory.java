package com.adidas.email.factory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adidas.email.constant.Consts;
import com.adidas.email.constant.Helper;
import com.example.model.EmailRequest;
import com.example.model.MimeMessagesTypes;

public class MimeMessageFactory {
	private Logger logger = LoggerFactory.getLogger(MimeMessageFactory.class);
	public static MimeMessageFactory mimeMessageFactory = null;

	private MimeMessageFactory() {

	}

	public MimeMessage getMimeMessage(MimeMessagesTypes type, EmailRequest request) throws MessagingException {
		logger.debug("MimeMessage type recieved as -> "+type);
		MimeMessage mimeMessage = null;
		switch (type) {
		case HTML_TEXT:
			logger.info("Preparing html_text mimemessage");
			mimeMessage = getHtmlTextMimeMessage(request);
			break;
		default:
			break;
		}
		return mimeMessage;
	}

	private MimeMessage getHtmlTextMimeMessage(EmailRequest request) throws MessagingException {
		MimeMessage message = new MimeMessage(SessionFactory.getInstance().initSession(request).getSession());
		logger.info("Putting headers to mime message");
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
