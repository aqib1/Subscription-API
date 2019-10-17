package com.adidas.email.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.business.EmailBusiness;
import com.adidas.email.constant.Consts;
import com.adidas.email.exceptions.BadInternalServerException;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.ResponseError;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(Consts.URL_EMAIL_SENDER)
public class EmailController {

	private Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailBusiness emailBusiness;

	@PostMapping
	@HystrixCommand(fallbackMethod = "getFallBackOnCircuitBreaker")
	public ResponseEntity<EmailResponse> newEmailRequest(@RequestBody EmailRequest request) {
		logger.info("Email request recieved -> " + request);
		return ResponseEntity.ok().body(emailBusiness.newEmailRequest(request));
	}

	public ResponseEntity<ResponseError> getFallBackOnCircuitBreaker(@RequestBody EmailRequest request) {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}
}
