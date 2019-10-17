package com.adidas.subscription.business;

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
import com.adidas.subscription.service.EmailService;
import com.example.model.EmailRequest;

@RunWith(MockitoJUnitRunner.class)
public class EmailBusinessTest_Mock {

	@Mock
	private EmailService emailService;

	@Spy
	@InjectMocks
	private EmailBusiness emailBusiness;

	@Before
	public void init() {
		mockEmailService();
	}

	private void mockEmailService() {
		Mockito.when(emailService.sendEmail(Mockito.any(EmailRequest.class)))
				.thenReturn(TestHelper.getInstance().emailResponse());
	}

	@Test
	public void testSendEmail() {
		boolean done = emailBusiness.sendEmail(TestHelper.getInstance().emailRequest());
		Assert.assertTrue(done);
	}

	private void mockEmailServiceForNullResponse() {
		Mockito.when(emailService.sendEmail(Mockito.any(EmailRequest.class))).thenReturn(null);
	}

	@Test(expected = EmailFailoverException.class)
	public void testSendEmailNullResponse() {
		mockEmailServiceForNullResponse();
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequest());
	}

	private void mockEmailServiceForFalseResponse() {
		Mockito.when(emailService.sendEmail(Mockito.any(EmailRequest.class)))
				.thenReturn(TestHelper.getInstance().emailResponseFail());
	}

	@Test(expected = EmailFailoverException.class)
	public void testSendEmailFlaseResponse() {
		mockEmailServiceForFalseResponse();
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequest());
	}
}
