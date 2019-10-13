package com.adidas.subscription.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.entities.SubscriptionEntity;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.mapper.SubscriptionMapper;
import com.adidas.subscription.service.SubscriptionService;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@Component
public class SubscriptionBusiness {

	private Logger logger = LoggerFactory.getLogger(SubscriptionBusiness.class);

	@Autowired
	private SubscriptionService service;

	@Autowired
	private SubscriptionMapper mapper;

	public SubscriptionResponse newSubscription(SubscriptionRequest request) {
		logger.debug("validating request [" + request + "]");
		validateSubscriptionRequest(request);
		logger.info("Saving new subscription in database");
		SubscriptionEntity entity = service
				.newSubscription(mapper.subscriptionEntityFromSubscriptionDto(request.getSubscriptionDto()));
		return new SubscriptionResponse().subscriptionId(entity.getId())
				.subscriptionDto(mapper.subscriptionDtoFromSubscriptionEntity(entity));
	}

	private void validateSubscriptionRequest(SubscriptionRequest request) {
		if (Helper.isNull(request)) {
			throw new InvalidRequestException("Request can't be null");
		}
		if (Helper.isNull(request.getSubscriptionDto())) {
			throw new InvalidRequestException("Request [subscription dto] can't be null");
		}
		if (Helper.isNullOrEmptyString(request.getSubscriptionDto().getEmail())) {
			throw new InvalidRequestException("Email for subscription can not be null or empty");
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
	}
}
