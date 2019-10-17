package com.adidas.subscription.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.constant.TestHelper;
import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.feignclient.EmailAPI;
import com.adidas.subscription.service.Impl.EmailServiceImpl;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

	@Mock
	private EmailAPI emailAPI;

	@Spy
	@InjectMocks
	private EmailServiceImpl emailServiceImpl;

	@Before
	public void init() {
		mockEmailAPI();
	}

	private void mockEmailAPI() {
		Mockito.when(emailAPI.sendEmail(Mockito.any(EmailRequest.class)))
				.thenReturn(TestHelper.getInstance().emailResponse());
	}

	@Test
	public void testSendEmail() {
		EmailResponse response = emailServiceImpl.sendEmail(TestHelper.getInstance().emailRequest());
		Assert.assertEquals("Email sent Successfully", response.getMessage());
		Assert.assertEquals(true, response.getSuccess());
		Assert.assertEquals("1/2/1992", response.getTimestamp());
	}

	private void mockEmailAPIForException() {
		Mockito.when(emailAPI.sendEmail(Mockito.any(EmailRequest.class))).thenThrow(EmailFailoverException.class);
	}
	
	@Test(expected = EmailFailoverException.class)
	public void testSendEmailException() {
		mockEmailAPIForException();
		emailServiceImpl.sendEmail(TestHelper.getInstance().emailRequest());
	}
}
