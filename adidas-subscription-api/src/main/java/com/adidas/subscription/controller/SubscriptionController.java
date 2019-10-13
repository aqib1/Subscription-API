package com.adidas.subscription.controller;

import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_MAIN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.business.SubscriptionBusiness;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@RestController
@RequestMapping(URL_SUBSCRIPTION_MAIN)
public class SubscriptionController {

	private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionBusiness subscriptionBusiness;

	@PostMapping
	public ResponseEntity<SubscriptionResponse> newSubscription(@RequestBody SubscriptionRequest request) {
		logger.info("Request recieved as [" + request + "]");
		SubscriptionResponse response = subscriptionBusiness.newSubscription(request);
		return ResponseEntity.ok().body(response);
	}
	
}
