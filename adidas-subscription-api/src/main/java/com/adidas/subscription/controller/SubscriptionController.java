package com.adidas.subscription.controller;

import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_DELETE_ALL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_DELETE_BY_EMAIL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_GET_ALL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_GET_BY_EMAIL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_MAIN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.business.SubscriptionBusiness;
import com.example.model.SubscriptionDetailedResponse;
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

	@GetMapping(URL_SUBSCRIPTION_GET_BY_EMAIL)
	public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable("email") String email) {
		logger.info("Email recieved as [" + email + "]");
		SubscriptionResponse response = subscriptionBusiness.getSubscriptionByEmail(email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(URL_SUBSCRIPTION_GET_ALL)
	public ResponseEntity<SubscriptionDetailedResponse> getAllSubscription() {
		SubscriptionDetailedResponse response = subscriptionBusiness.getAllSubscriptions();
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(URL_SUBSCRIPTION_DELETE_BY_EMAIL)
	public ResponseEntity<Void> deleteByEmail(@PathVariable("email") String email) {
		subscriptionBusiness.deleteByEmail(email);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(URL_SUBSCRIPTION_DELETE_ALL)
	public ResponseEntity<Void> deleteAll() {
		subscriptionBusiness.deleteAll();
		return ResponseEntity.ok().build();
	}
}
