package com.adidas.email.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.email.constant.TestHelper;
import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.service.Impl.EmailServiceImpl;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

	@Mock
	private EmailServiceImpl emailServiceImpl;

	@Before
	public void init() {
		Mockito.when(emailServiceImpl.sendEmail(Mockito.any(EmailRequest.class)))
				.thenReturn(TestHelper.getInstance().getEmailResponse());
	}

	@Test
	public void testSendEmail() {
		EmailResponse response = emailServiceImpl.sendEmail(TestHelper.getInstance().emailRequest());
		Assert.assertEquals("Email sent Successfully", response.getMessage());
		Assert.assertEquals(true, response.getSuccess());
		Assert.assertEquals("1/2/1992", response.getTimestamp());
	}

	private void throwBadInternalServerException() {
		Mockito.when(emailServiceImpl.sendEmail(Mockito.any(EmailRequest.class)))
				.thenThrow(BadInternalServerException.class);
	}

	@Test(expected = BadInternalServerException.class)
	public void exceptionBadInternalServerException() {
		throwBadInternalServerException();
		emailServiceImpl.sendEmail(TestHelper.getInstance().emailRequest());
	}
}
