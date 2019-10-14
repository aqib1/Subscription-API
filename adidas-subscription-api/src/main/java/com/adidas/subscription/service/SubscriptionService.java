package com.adidas.subscription.service;

import java.util.List;

import com.adidas.subscription.entities.SubscriptionEntity;

public interface SubscriptionService {

	SubscriptionEntity newSubscription(SubscriptionEntity request);

	SubscriptionEntity getSubscriptionByEmail(String email);

	List<SubscriptionEntity> getAllSubscription();

	void delete(String email);
	
	void deleteAll();
}
