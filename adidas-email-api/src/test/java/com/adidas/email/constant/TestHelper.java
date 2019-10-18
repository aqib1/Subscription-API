package com.adidas.email.constant;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.exceptions.InvalidRequestException;
import com.adidas.email.exceptions.InvalidResponseException;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;
import com.example.model.ResponseError;

public class TestHelper {
	private static TestHelper testHelper = null;
	public static final WebRequest TEST_WEB_REQUEST = null;
	public static final RuntimeException TEST_RUNTIME_EXC = new RuntimeException();

	public EmailResponse getEmailResponse() {
		return new EmailResponse().message("Email sent Successfully").success(true).timestamp("1/2/1992");
	}

	public EmailResponse emailResponseFail() {
		return new EmailResponse().message("Email sent not Successfully").success(false).timestamp("1/2/1992");
	}

	public EmailRequest emailRequest() {
		return new EmailRequest().recieverEmail("test@email.com").senderEmail("test@email.com")
				.message("this is test email").subject("Test - Email");
	}
	
	public ResponseEntity<ResponseError> getInvalidRequestException() {
		return new ResponseEntity<>(
				new ResponseError().createdAt("12/11/2019").detailedMessage(InvalidRequestException.class.getName())
						.errorCode(HttpStatus.BAD_REQUEST.value())
						.exceptionName(InvalidRequestException.class.getName()).errorMessage("Error - Message"),
				HttpStatus.BAD_REQUEST);
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
