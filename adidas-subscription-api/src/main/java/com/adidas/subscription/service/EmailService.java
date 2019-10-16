package com.adidas.subscription.service;

import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

public interface EmailService {
	EmailResponse sendEmail(EmailRequest request);
}
