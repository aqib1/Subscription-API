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
public class EmailBusinessTest_Exception {

	@Autowired
	private EmailBusiness emailBusiness;

	@Test(expected = InvalidRequestException.class)
	public void testSendEmailNullRecieverEmail() {
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequestNullRecieverEmail());
	}

	@Test(expected = InvalidRequestException.class)
	public void testSendEmailNullRequest() {
		emailBusiness.sendEmail(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void testSendEmailNullSenderEmail() {
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequestNullSenderEmail());
	}

	@Test(expected = InvalidRequestException.class)
	public void testSendEmailNullSubjectEmail() {
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequestNullSubjectEmail());
	}

	@Test(expected = InvalidRequestException.class)
	public void testSendEmailNullMessage() {
		emailBusiness.sendEmail(TestHelper.getInstance().emailRequestNullMessageEmail());
	}

}
