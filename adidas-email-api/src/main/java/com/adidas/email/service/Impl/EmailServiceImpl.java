package com.adidas.email.service.Impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adidas.email.business.EmailBusiness;
import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.factory.MimeMessageFactory;
import com.adidas.email.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.MimeMessagesTypes;

@Service
public class EmailServiceImpl implements EmailService {
	private Logger logger = LoggerFactory.getLogger(EmailBusiness.class);

	@Override
	public EmailResponse sendEmail(EmailRequest emailRequest) throws BadInternalServerException {
		try {
			logger.debug("Creating MimeMessage against email request -> " + emailRequest);
			MimeMessage mimeMessage = MimeMessageFactory.getInstance().getMimeMessage(MimeMessagesTypes.HTML_TEXT,
					emailRequest);
			mimeMessage.setFrom(new InternetAddress(emailRequest.getSenderEmail(), emailRequest.getSenderName()));
			mimeMessage.setReplyTo(InternetAddress.parse(emailRequest.getRecieverEmail(), false));
			mimeMessage.setSubject(emailRequest.getSubject());
			mimeMessage.setText(emailRequest.getMessage());
			mimeMessage.setSentDate(new Date());
			mimeMessage.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailRequest.getRecieverEmail(), false));
			Transport.send(mimeMessage, emailRequest.getSenderEmail(), emailRequest.getSenderEmailPassword());

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new BadInternalServerException(e.getMessage(), e);
		}

		return new EmailResponse().message("Email [" + emailRequest.getMessage() + "] Sent successfully.").success(true)
				.timestamp(LocalDateTime.now().toString());
	}

}
