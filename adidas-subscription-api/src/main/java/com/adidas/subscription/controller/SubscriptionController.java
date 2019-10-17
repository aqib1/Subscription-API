package com.adidas.subscription.controller;

import static com.adidas.subscription.constant.Consts.TRUE;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_DELETE_ALL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_DELETE_BY_EMAIL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_GET_ALL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_GET_BY_EMAIL;
import static com.adidas.subscription.constant.Consts.URL_SUBSCRIPTION_MAIN;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.subscription.business.EmailBusiness;
import com.adidas.subscription.business.SubscriptionBusiness;
import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.exceptions.BadInternalServerException;
import com.adidas.subscription.exceptions.EmailFailoverException;
import com.example.model.EmailRequest;
import com.example.model.ResponseError;
import com.example.model.SubscriptionDetailedResponse;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(URL_SUBSCRIPTION_MAIN)
public class SubscriptionController {

	private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionBusiness subscriptionBusiness;

	@Autowired
	private EmailBusiness emailBusiness;

	@PostMapping
	@HystrixCommand(fallbackMethod = "newSubscriptionCircuitBreaker")
	public ResponseEntity<SubscriptionResponse> newSubscription(@RequestBody SubscriptionRequest request) {
		logger.info("Request recieved as [" + request + "]");
		SubscriptionResponse response = subscriptionBusiness.newSubscription(request);
		logger.debug("Recieved subscription response -> " + response);
		EmailRequest eRequest = Helper.getEmailRequestObject(response);
		logger.debug("Sender email against request -> " + request);
		try {
			if (emailBusiness.sendEmail(eRequest)) {
				response.emailSent(TRUE);
				logger.info("email sent successfully...");
			}
		} catch (EmailFailoverException e) {
			logger.info("Exception recieved, Rolling back subscriptions");
			deleteByEmail(request.getSubscriptionDto().getEmail());
			throw e;
		}
		logger.debug("Response recieved -> " + response);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(URL_SUBSCRIPTION_GET_BY_EMAIL)
	@HystrixCommand(fallbackMethod = "getSubscriptionCircuitBreaker")
	public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable("email") String email) {
		logger.info("Get subscriptions against email recieved as [" + email + "]");
		SubscriptionResponse response = subscriptionBusiness.getSubscriptionByEmail(email);
		logger.debug("Response recieved -> " + response);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(URL_SUBSCRIPTION_GET_ALL)
	@HystrixCommand(fallbackMethod = "getAllSubscriptionCircuitBreaker")
	public ResponseEntity<SubscriptionDetailedResponse> getAllSubscription() {
		logger.info("Getting all subscriptions");
		SubscriptionDetailedResponse response = subscriptionBusiness.getAllSubscriptions();
		logger.debug("Response recieved -> " + response);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(URL_SUBSCRIPTION_DELETE_BY_EMAIL)
	@HystrixCommand(fallbackMethod = "deleteByEmailCircuitBreaker")
	public ResponseEntity<Void> deleteByEmail(@PathVariable("email") String email) {
		logger.info("Delete subscription against email recieved as [" + email + "]");
		subscriptionBusiness.deleteByEmail(email);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(URL_SUBSCRIPTION_DELETE_ALL)
	@HystrixCommand(fallbackMethod = "deleteAllCircuitBreaker")
	public ResponseEntity<Void> deleteAll() {
		logger.info("Deleting all subscriptions");
		subscriptionBusiness.deleteAll();
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<ResponseError> deleteAllCircuitBreaker() {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked against deleteAll.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}

	public ResponseEntity<ResponseError> deleteByEmailCircuitBreaker(@PathVariable("email") String email) {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked against delete by email.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}

	public ResponseEntity<ResponseError> getAllSubscriptionCircuitBreaker() {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked against get all subscriptions.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}

	public ResponseEntity<ResponseError> getSubscriptionCircuitBreaker(@PathVariable("email") String email) {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked against get subscription by email.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}

	public ResponseEntity<ResponseError> newSubscriptionCircuitBreaker(@RequestBody SubscriptionRequest request) {
		return new ResponseEntity<>(
				new ResponseError().createdAt(LocalDateTime.now().toString())
						.detailedMessage("Circuit breaker invoked against new subscription.")
						.errorMessage("Circuit breaker, unable to connect to server")
						.exceptionName(BadInternalServerException.class.getName()).errorCode(HttpStatus.GONE.value()),
				HttpStatus.GONE);
	}
}
