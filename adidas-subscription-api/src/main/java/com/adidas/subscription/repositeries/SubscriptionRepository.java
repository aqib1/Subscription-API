package com.adidas.subscription.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adidas.subscription.entities.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

	public SubscriptionEntity findOneByEmail(String email);
}
