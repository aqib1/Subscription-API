package com.adidas.subscription.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.adidas.subscription.business.EmailBusiness;
import com.adidas.subscription.business.SubscriptionBusiness;
import com.adidas.subscription.constant.TestHelper;
import com.adidas.subscription.exceptions.BadInternalServerException;
import com.example.model.EmailRequest;
import com.example.model.ResponseError;
import com.example.model.SubscriptionDetailedResponse;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionControllerTest_Mock {

	@Mock
	private SubscriptionBusiness subscriptionBusiness;
	
	@Mock
	private EmailBusiness emailBusiness;

	@Spy
	@InjectMocks
	private SubscriptionController subscriptionController;

	@Before
	public void init() {
		mockSubscriptionBusiness();
		mockEmailBusiness();
	}

	private void mockEmailBusiness() {
		Mockito.when(emailBusiness.sendEmail(Mockito.any(EmailRequest.class)))
				.thenReturn(true);
	}
	
	private void mockSubscriptionBusiness() {
		Mockito.doNothing().when(subscriptionBusiness).deleteAll();
		Mockito.doNothing().when(subscriptionBusiness).deleteByEmail(Mockito.anyString());
		Mockito.when(subscriptionBusiness.getAllSubscriptions())
				.thenReturn(TestHelper.getInstance().subscriptionDetailsResponse());
		Mockito.when(subscriptionBusiness.getSubscriptionByEmail(Mockito.anyString()))
				.thenReturn(TestHelper.getInstance().subscriptionResponse());
		Mockito.when(subscriptionBusiness.newSubscription(Mockito.any(SubscriptionRequest.class)))
				.thenReturn(TestHelper.getInstance().subscriptionResponse());
	}

	@Test
	public void testNewSubscription() {
		ResponseEntity<SubscriptionResponse> response = subscriptionController
				.newSubscription(TestHelper.getInstance().subscriptionRequest());
		Assert.assertEquals("Fname", response.getBody().getSubscriptionDto().getFirstName());
		Assert.assertEquals(true, response.getBody().getSubscriptionDto().getConsent());
		Assert.assertTrue(1 == response.getBody().getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("test@gmail.com", response.getBody().getSubscriptionDto().getEmail());
		Assert.assertEquals("M", response.getBody().getSubscriptionDto().getGender());
		Assert.assertTrue(1 == response.getBody().getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("1/1/2019", response.getBody().getSubscriptionDto().getDateOfBirth());
	}

	@Test
	public void testSubscriptionByEmail() {
		ResponseEntity<SubscriptionResponse> response = subscriptionController.getSubscription("");
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(true, response.getBody().getSubscriptionDto().getConsent());
		Assert.assertEquals("1/1/2019", response.getBody().getSubscriptionDto().getDateOfBirth());
		Assert.assertEquals("Fname", response.getBody().getSubscriptionDto().getFirstName());
		Assert.assertEquals("M", response.getBody().getSubscriptionDto().getGender());
		Assert.assertTrue(1 == response.getBody().getSubscriptionDto().getNewsletterId());
		Assert.assertEquals(true, response.getBody().getEmailSent());
		Assert.assertTrue(1 == response.getBody().getSubscriptionId());
	}

	@Test
	public void testGetAllSubscriptions() {
		ResponseEntity<SubscriptionDetailedResponse> response = subscriptionController.getAllSubscription();
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertTrue(1 == response.getBody().getCount());
		Assert.assertEquals(true, response.getBody().getSubscriptionsList().get(0).getConsent());
		Assert.assertEquals("1/1/2019", response.getBody().getSubscriptionsList().get(0).getDateOfBirth());
		Assert.assertEquals("Fname", response.getBody().getSubscriptionsList().get(0).getFirstName());
		Assert.assertEquals("M", response.getBody().getSubscriptionsList().get(0).getGender());
		Assert.assertTrue(1 == response.getBody().getSubscriptionsList().get(0).getNewsletterId());
	}

	@Test
	public void testDeleteByEmail() {
		ResponseEntity<Void> response = subscriptionController.deleteByEmail("");
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testDeleteAll() {
		ResponseEntity<Void> response = subscriptionController.deleteAll();
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testNewSubscriptionCircuitBreaker() {
		ResponseEntity<ResponseError> response = subscriptionController
				.newSubscriptionCircuitBreaker(TestHelper.getInstance().subscriptionRequest());
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}

	@Test
	public void testGetSubscriptionCircuitBreaker() {
		ResponseEntity<ResponseError> response = subscriptionController.getSubscriptionCircuitBreaker("");
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}

	@Test
	public void testGetAllSubscriptionCircuitBreaker() {
		ResponseEntity<ResponseError> response = subscriptionController.getAllSubscriptionCircuitBreaker();
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}

	@Test
	public void testDeleteByEmailCircuitBreaker() {
		ResponseEntity<ResponseError> response = subscriptionController.deleteByEmailCircuitBreaker("");
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}

	@Test
	public void testDeleteAllCircuitBreaker() {
		ResponseEntity<ResponseError> response = subscriptionController.deleteAllCircuitBreaker();
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}
}
