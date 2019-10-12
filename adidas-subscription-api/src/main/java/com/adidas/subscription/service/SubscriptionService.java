package com.adidas.subscription.service;

import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

public interface SubscriptionService {

	SubscriptionResponse newSubscription(SubscriptionRequest request);
}
