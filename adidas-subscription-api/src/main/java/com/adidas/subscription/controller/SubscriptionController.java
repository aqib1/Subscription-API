package com.adidas.subscription.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.business.SubscriptionBusiness;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@RestController
public class SubscriptionController {

	private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionBusiness subscriptionBusiness;

	public ResponseEntity<SubscriptionResponse> newSubscription(SubscriptionRequest request) {
		logger.info("Request recieved as [" + request + "]");
		SubscriptionResponse response = subscriptionBusiness.newSubscription(request);
		return ResponseEntity.ok().body(response);
	}
}
