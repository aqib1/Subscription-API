package com.adidas.email.service.Impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.adidas.email.constant.Consts;
import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.factory.MimeMessageFactory;
import com.adidas.email.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.MimeMessagesTypes;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public EmailResponse sendEmail(EmailRequest emailRequest) throws BadInternalServerException {
		try {
			MimeMessage mimeMessage = MimeMessageFactory.getInstance().getMimeMessage(MimeMessagesTypes.HTML_TEXT,
					emailRequest);
			mimeMessage.setFrom(new InternetAddress(emailRequest.getSenderEmail(), emailRequest.getSenderName()));
			mimeMessage.setReplyTo(InternetAddress.parse(emailRequest.getRecieverEmail(), false));
			mimeMessage.setSubject(emailRequest.getSubject(), Consts.UTF_8);
			mimeMessage.setText(emailRequest.getMessage(), Consts.UTF_8);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailRequest.getRecieverEmail(), false));
			Transport.send(mimeMessage);

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new BadInternalServerException(e.getMessage(), e);
		}

		return new EmailResponse().message("Email [" + emailRequest.getMessage() + "] Sent successfully.").success(true)
				.timestamp(LocalDateTime.now().toString());
	}

}
