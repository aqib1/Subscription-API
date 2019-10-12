package com.adidas.subscription.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@RestController
public class SubscriptionController {

	private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	public ResponseEntity<SubscriptionResponse> newSubscription(SubscriptionRequest request) {
		ResponseEntity<SubscriptionResponse> response = null;
		
		return response;
	}
}
