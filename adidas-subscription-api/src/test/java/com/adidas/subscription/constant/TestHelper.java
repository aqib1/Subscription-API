package com.adidas.subscription.constant;

import java.util.Objects;

import com.example.model.SubscriptionDto;
import com.example.model.SubscriptionRequest;

public class TestHelper {
	private static TestHelper testHelper = null;

	public SubscriptionRequest getNullSubscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(null);
	}

	public SubscriptionRequest getNullEmailSubscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(new SubscriptionDto().firstName("T").dateOfBirth("1/1/1992")
				.consent(true).email(null).newsletterId(1).gender("M"));
	}
	
	public SubscriptionRequest getNullConsentSubscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(new SubscriptionDto().firstName("T").dateOfBirth("1/1/1992")
				.consent(null).email("test@gmail.com").newsletterId(1).gender("M"));
	}
	
	public SubscriptionRequest getNullDateOfBirthSubscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(new SubscriptionDto().firstName("T").dateOfBirth(null)
				.consent(true).email("test@gmail.com").newsletterId(1).gender("M"));
	}
	
	public SubscriptionRequest getNullNewsLetterIdSubscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(new SubscriptionDto().firstName("T").dateOfBirth("1/1/1992")
				.consent(true).email("test@gmail.com").newsletterId(null).gender("M"));
	}

	private TestHelper() {

	}

	public static TestHelper getInstance() {
		if (Objects.isNull(testHelper)) {
			synchronized (TestHelper.class) {
				if (Objects.isNull(testHelper)) {
					testHelper = new TestHelper();
				}
			}
		}

		return testHelper;
	}

}
