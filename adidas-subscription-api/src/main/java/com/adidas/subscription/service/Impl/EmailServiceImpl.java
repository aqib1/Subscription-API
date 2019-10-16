package com.adidas.subscription.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.feignclient.EmailAPI;
import com.adidas.subscription.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailAPI emailAPI;

	@Override
	public EmailResponse sendEmail(EmailRequest request) {
		try {
			return emailAPI.sendEmail(request);
		} catch (Exception e) {
			throw new EmailFailoverException(e.getMessage(), e);
		}
	}

}
