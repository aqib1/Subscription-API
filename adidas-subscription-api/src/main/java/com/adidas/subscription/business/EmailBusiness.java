package com.adidas.subscription.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.service.EmailService;
import com.example.model.EmailRequest;

@Component
public class EmailBusiness {
	@Autowired
	private EmailService emailService;

	public boolean sendEmail(EmailRequest request) {
		validateEmailRequest(request);
		emailService.sendEmail(request);
		return true;
	}

	private void validateEmailRequest(EmailRequest request) {
		if (Helper.isNull(request)) {
			throw new InvalidRequestException("Request can not be null");
		}
		if (Helper.isNullOrEmptyString(request.getRecieverEmail())) {
			throw new InvalidRequestException("Reciever email can not be null or empty");
		}
		if (Helper.isNullOrEmptyString(request.getSenderEmail())) {
			throw new InvalidRequestException("Sender email can not be null or empty");
		}
		if (Helper.isNullOrEmptyString(request.getSubject())) {
			throw new InvalidRequestException("Subject can not be null or empty");
		}
		if (Helper.isNullOrEmptyString(request.getMessage())) {
			throw new InvalidRequestException("Message can not be null or empty");
		}
	}
}
