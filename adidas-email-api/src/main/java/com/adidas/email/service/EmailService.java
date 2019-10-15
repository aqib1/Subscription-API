package com.adidas.email.service;

import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

public interface EmailService {
	EmailResponse sendEmail(EmailRequest emailRequest);
}
