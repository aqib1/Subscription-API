package com.adidas.subscription.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.entities.SubscriptionEntity;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.mapper.SubscriptionMapper;
import com.adidas.subscription.service.Impl.SubscriptionServiceImpl;
import com.example.model.SubscriptionDetailedResponse;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@Component
public class SubscriptionBusiness {

	private Logger logger = LoggerFactory.getLogger(SubscriptionBusiness.class);

	@Autowired
	private SubscriptionServiceImpl service;

	@Autowired
	private SubscriptionMapper mapper;

	public SubscriptionResponse newSubscription(SubscriptionRequest request) {
		logger.debug("validating request [" + request + "]");
		validateSubscriptionRequest(request);
		logger.info("Saving new subscription in database");
		SubscriptionEntity entity = service
				.newSubscription(mapper.subscriptionEntityFromSubscriptionDto(request.getSubscriptionDto()));
		logger.debug("New entity [" + entity + "] Recieved");
		return new SubscriptionResponse().subscriptionId(entity.getId())
				.subscriptionDto(mapper.subscriptionDtoFromSubscriptionEntity(entity));
	}

	public SubscriptionResponse getSubscriptionByEmail(String email) {
		validateEmail(email);
		logger.debug("Get subscription against email [" + email + "]");
		SubscriptionEntity entity = service.getSubscriptionByEmail(email);
		logger.debug("New entity [" + entity + "] Recieved");
		return new SubscriptionResponse().subscriptionId(entity.getId())
				.subscriptionDto(mapper.subscriptionDtoFromSubscriptionEntity(entity));
	}

	public SubscriptionDetailedResponse getAllSubscriptions() {
		logger.info("Get all subscriptions");
		List<SubscriptionEntity> entities = service.getAllSubscription();
		logger.debug("Subscriptions recieved [" + entities + "]");
		return new SubscriptionDetailedResponse().count(entities.size())
				.subscriptionsList(mapper.subscriptionDtoListFromSubscriptionEntityList(entities));
	}

	public void deleteByEmail(String email) {
		validateEmail(email);
		logger.debug("Delete by email [" + email + "]");
		service.delete(email);
	}

	public void deleteAll() {
		logger.info("Deleting all subscriptions");
		service.deleteAll();
	}

	private void validateSubscriptionRequest(SubscriptionRequest request) {
		if (Helper.isNull(request)) {
			throw new InvalidRequestException("Request can't be null");
		}
		if (Helper.isNull(request.getSubscriptionDto())) {
			throw new InvalidRequestException("Request [subscription dto] can't be null");
		}
		if (Helper.isNullOrEmptyString(request.getSubscriptionDto().getDateOfBirth())) {
			throw new InvalidRequestException("Date of birth can not be null or empty");
		}
		if (Helper.isNull(request.getSubscriptionDto().getConsent())) {
			throw new InvalidRequestException("Consent can not be null or empty");
		}
		if (Helper.isNull(request.getSubscriptionDto().getNewsletterId())) {
			throw new InvalidRequestException("Newsletter Id can not be null");
		}
		validateEmail(request.getSubscriptionDto().getEmail());
	}

	private void validateEmail(String email) {
		if (Helper.isNullOrEmptyString(email)) {
			throw new InvalidRequestException("Email for subscription can not be null or empty");
		}
		if (!Helper.isEmailValid(email)) {
			throw new InvalidRequestException("Email is not present in valid format");
		}
	}
}
