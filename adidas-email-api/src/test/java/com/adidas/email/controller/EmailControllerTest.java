package com.adidas.email.controller;

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

import com.adidas.email.business.EmailBusiness;
import com.adidas.email.constant.TestHelper;
import com.adidas.email.exceptions.BadInternalServerException;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.ResponseError;

@RunWith(MockitoJUnitRunner.class)
public class EmailControllerTest {

	@Mock
	private EmailBusiness emailBusiness;
	
	@Spy
	@InjectMocks
	private EmailController emailController;
	
	@Before
	public void init() {
		Mockito.when(emailBusiness.newEmailRequest(Mockito.any(EmailRequest.class)))
		.thenReturn(TestHelper.getInstance().getEmailResponse());
	}
	
	@Test
	public void testNewEmailRequest() {
		EmailResponse response = emailBusiness.newEmailRequest(TestHelper.getInstance().emailRequest());
		Assert.assertEquals("Email sent Successfully", response.getMessage());
		Assert.assertEquals(true, response.getSuccess());
		Assert.assertEquals("1/2/1992", response.getTimestamp());
	}
	
	@Test
	public void testGetFallBackOnCircuitBreaker() {
		ResponseEntity<ResponseError> response = emailController.getFallBackOnCircuitBreaker(TestHelper.getInstance().emailRequest());
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());
		Assert.assertTrue(HttpStatus.GONE.value() == response.getStatusCodeValue());
	}
}
