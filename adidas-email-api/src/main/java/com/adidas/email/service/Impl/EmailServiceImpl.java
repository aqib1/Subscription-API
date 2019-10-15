package com.adidas.email.service.Impl;

import org.springframework.stereotype.Service;

import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.service.EmailService;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Override
	public EmailResponse sendEmail(EmailRequest emailRequest) throws BadInternalServerException {
		
		
		
		return null;
	}

	

}
