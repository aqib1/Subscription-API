package com.adidas.subscription.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.subscription.constant.TestHelper;
import com.adidas.subscription.exceptions.InvalidRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionBusinessTest {

	@Autowired
	private SubscriptionBusiness business;

	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithNullRequest() {
		business.newSubscription(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithNullSubscription() {
		business.newSubscription(TestHelper.getInstance().getNullSubscriptionRequest());
	}

	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithEmptyEmail() {
		business.newSubscription(TestHelper.getInstance().getNullEmailSubscriptionRequest());
	}
	
	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithEmptyConsent() {
		business.newSubscription(TestHelper.getInstance().getNullConsentSubscriptionRequest());
	}
	
	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithEmptyDateOfBirth() {
		business.newSubscription(TestHelper.getInstance().getNullDateOfBirthSubscriptionRequest());
	}
	
	@Test(expected = InvalidRequestException.class)
	public void newSubscriptionWithNullNewsLetterId() {
		business.newSubscription(TestHelper.getInstance().getNullNewsLetterIdSubscriptionRequest());
	}
}
