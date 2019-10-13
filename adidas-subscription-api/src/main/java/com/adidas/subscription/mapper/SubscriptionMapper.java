package com.adidas.subscription.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.adidas.subscription.entities.SubscriptionEntity;
import com.example.model.SubscriptionDto;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

	SubscriptionEntity subscriptionEntityFromSubscriptionDto(SubscriptionDto subscriptionDto);

	SubscriptionDto subscriptionDtoFromSubscriptionEntity(SubscriptionEntity subscriptionEntity);

	List<SubscriptionEntity> subscriptionEntityListFromSubscriptionDtoList(List<SubscriptionDto> subscriptionDtoList);

	List<SubscriptionDto> subscriptionDtoListFromSubscriptionEntityList(
			List<SubscriptionEntity> subscriptionEntityList);
}
