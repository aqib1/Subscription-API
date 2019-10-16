package com.adidas.subscription.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.feignclient.EmailAPI;
import com.adidas.subscription.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@Service
public class EmailServiceImpl implements EmailService {

	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private EmailAPI emailAPI;

	@Override
	public EmailResponse sendEmail(EmailRequest request) {
		try {
			logger.info("Sending email using Feign client");
			return emailAPI.sendEmail(request);
		} catch (Exception e) {
			throw new EmailFailoverException(e.getMessage(), e);
		}
	}

}
