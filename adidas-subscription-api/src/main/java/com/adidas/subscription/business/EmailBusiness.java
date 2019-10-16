package com.adidas.subscription.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@Component
public class EmailBusiness {
	private Logger logger = LoggerFactory.getLogger(EmailBusiness.class);
	@Autowired
	private EmailService emailService;

	public boolean sendEmail(EmailRequest request) {
		logger.info("Validating email request object");
		validateEmailRequest(request);
		logger.info("Sending request to email service");
		return validateResponse(emailService.sendEmail(request));
	}

	private boolean validateResponse(EmailResponse response) {
		if (Helper.isNull(response)) {
			throw new EmailFailoverException("Resposne recieved as null");
		}
		if (Helper.isNull(response.getSuccess()) || !response.getSuccess()) {
			throw new EmailFailoverException("Resposne success recieved as null or false");
		}
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
