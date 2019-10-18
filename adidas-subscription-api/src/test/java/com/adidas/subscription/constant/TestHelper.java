package com.adidas.subscription.constant;

import java.util.Collections;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.adidas.subscription.entities.SubscriptionEntity;
import com.adidas.subscription.exceptions.BadInternalServerException;
import com.adidas.subscription.exceptions.DublicateDataException;
import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.exceptions.InvalidResponseException;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.ResponseError;
import com.example.model.SubscriptionDetailedResponse;
import com.example.model.SubscriptionDto;
import com.example.model.SubscriptionRequest;
import com.example.model.SubscriptionResponse;

public class TestHelper {
	private static TestHelper testHelper = null;
	public static final WebRequest TEST_WEB_REQUEST = null;
	public static final RuntimeException TEST_RUNTIME_EXC = new RuntimeException();

	public ResponseEntity<ResponseError> getInvalidRequestException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(InvalidRequestException.class.getName())
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.exceptionName(InvalidRequestException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ResponseError> getHandleDublicateDataException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(DublicateDataException.class.getName())
						.errorCode(HttpStatus.FORBIDDEN.value())
						.exceptionName(DublicateDataException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.FORBIDDEN);
	}
	
	public ResponseEntity<ResponseError> getHandleInvalidResponseException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(InvalidResponseException.class.getName())
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.exceptionName(InvalidResponseException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ResponseError> getHandleBadInternalServerException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(BadInternalServerException.class.getName())
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.exceptionName(BadInternalServerException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ResponseError> getHandleEmailFailoverException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(EmailFailoverException.class.getName())
						.errorCode(HttpStatus.EXPECTATION_FAILED.value())
						.exceptionName(EmailFailoverException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.EXPECTATION_FAILED);
	}
	
	public SubscriptionRequest subscriptionRequest() {
		return new SubscriptionRequest().subscriptionDto(subscriptionDto());
	}

	public SubscriptionResponse subscriptionResponse() {
		return new SubscriptionResponse().emailSent(true).subscriptionDto(subscriptionDto()).subscriptionId(1L);
	}

	public SubscriptionDetailedResponse subscriptionDetailsResponse() {
		return new SubscriptionDetailedResponse().count(1)
				.subscriptionsList(Collections.singletonList(subscriptionDto()));
	}

	public EmailRequest emailRequestNullRecieverEmail() {
		return new EmailRequest().recieverEmail(null).senderEmail("test@email.com").newsletterId(1L)
				.message("this is test email").subject("Test - Email");
	}

	public EmailRequest emailRequestNullSenderEmail() {
		return new EmailRequest().recieverEmail("test@email.com").senderEmail(null).newsletterId(1L)
				.message("this is test email").subject("Test - Email");
	}

	public EmailRequest emailRequestNullSubjectEmail() {
		return new EmailRequest().recieverEmail("test@email.com").senderEmail(null).newsletterId(1L)
				.message("this is test email").subject(null);
	}

	public EmailRequest emailRequestNullMessageEmail() {
		return new EmailRequest().recieverEmail("test@email.com").senderEmail(null).newsletterId(1L).message(null)
				.subject("Test - Email");
	}

	public EmailRequest emailRequest() {
		return new EmailRequest().recieverEmail("test@email.com").senderEmail("test@email.com").newsletterId(1L)
				.message("this is test email").subject("Test - Email");
	}

	public EmailResponse emailResponse() {
		return new EmailResponse().message("Email sent Successfully").success(true).timestamp("1/2/1992");
	}

	public EmailResponse emailResponseFail() {
		return new EmailResponse().message("Email sent not Successfully").success(false).timestamp("1/2/1992");
	}

	public SubscriptionEntity subscriptionEntity() {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setId(1L);
		subscriptionEntity.setFirstName("Fname");
		subscriptionEntity.setConsent(true);
		subscriptionEntity.setDateOfBirth("1/1/2019");
		subscriptionEntity.setNewsletterId(1);
		subscriptionEntity.setEmail("test@gmail.com");
		subscriptionEntity.setGender("M");
		return subscriptionEntity;
	}

	public SubscriptionEntity subscriptionEntity1() {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setId(1L);
		subscriptionEntity.setFirstName("Fname");
		subscriptionEntity.setConsent(true);
		subscriptionEntity.setDateOfBirth("1/1/2019");
		subscriptionEntity.setNewsletterId(1);
		subscriptionEntity.setEmail("temp@gmail.com");
		subscriptionEntity.setGender("M");
		return subscriptionEntity;
	}

	public SubscriptionDto subscriptionDto() {
		return new SubscriptionDto().firstName("Fname").dateOfBirth("1/1/2019").consent(true).email("test@gmail.com")
				.newsletterId(1).gender("M");
	}

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
