package com.adidas.subscription.controller.advice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.adidas.subscription.constant.TestHelper;
import com.adidas.subscription.exceptions.BadInternalServerException;
import com.adidas.subscription.exceptions.DublicateDataException;
import com.adidas.subscription.exceptions.EmailFailoverException;
import com.adidas.subscription.exceptions.InvalidRequestException;
import com.adidas.subscription.exceptions.InvalidResponseException;
import com.example.model.ResponseError;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionAdviceTest {

	@Mock
	private ExceptionsAdvice exceptionsAdvice;

	@Before
	public void init() {
		mockExceptionAdvice();
	}

	private void mockExceptionAdvice() {
		Mockito.when(exceptionsAdvice.handleInvalidRequestException(Mockito.any(RuntimeException.class), Mockito.any()))
				.thenReturn(TestHelper.getInstance().getInvalidRequestException());
		Mockito.when(exceptionsAdvice.handleDublicateDataException(Mockito.any(RuntimeException.class), Mockito.any()))
				.thenReturn(TestHelper.getInstance().getHandleDublicateDataException());
		Mockito.when(
				exceptionsAdvice.handleInvalidResponseException(Mockito.any(RuntimeException.class), Mockito.any()))
				.thenReturn(TestHelper.getInstance().getHandleInvalidResponseException());
		Mockito.when(
				exceptionsAdvice.handleBadInternalServerException(Mockito.any(RuntimeException.class), Mockito.any()))
				.thenReturn(TestHelper.getInstance().getHandleBadInternalServerException());
		Mockito.when(exceptionsAdvice.handleEmailFailoverException(Mockito.any(RuntimeException.class), Mockito.any()))
				.thenReturn(TestHelper.getInstance().getHandleEmailFailoverException());
	}

	@Test
	public void testHandleEmailFailoverException() {
		ResponseEntity<ResponseError> response = exceptionsAdvice
				.handleEmailFailoverException(TestHelper.TEST_RUNTIME_EXC, TestHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12/11/2019", response.getBody().getCreatedAt());
		Assert.assertEquals(EmailFailoverException.class.getName(), response.getBody().getDetailedMessage());
		Assert.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
		Assert.assertEquals("Error - Message", response.getBody().getErrorMessage());
	}

	@Test
	public void testHandleBadInternalServerException() {
		ResponseEntity<ResponseError> response = exceptionsAdvice
				.handleBadInternalServerException(TestHelper.TEST_RUNTIME_EXC, TestHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12/11/2019", response.getBody().getCreatedAt());
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getDetailedMessage());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Error - Message", response.getBody().getErrorMessage());
	}

	@Test
	public void testHandleInvalidResponseException() {
		ResponseEntity<ResponseError> response = exceptionsAdvice
				.handleInvalidResponseException(TestHelper.TEST_RUNTIME_EXC, TestHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12/11/2019", response.getBody().getCreatedAt());
		Assert.assertEquals(InvalidResponseException.class.getName(), response.getBody().getDetailedMessage());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Error - Message", response.getBody().getErrorMessage());
	}

	@Test
	public void testHandleDublicateDataException() {
		ResponseEntity<ResponseError> response = exceptionsAdvice
				.handleDublicateDataException(TestHelper.TEST_RUNTIME_EXC, TestHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12/11/2019", response.getBody().getCreatedAt());
		Assert.assertEquals(DublicateDataException.class.getName(), response.getBody().getDetailedMessage());
		Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assert.assertEquals("Error - Message", response.getBody().getErrorMessage());
	}

	@Test
	public void testHandleInvalidRequestException() {
		ResponseEntity<ResponseError> response = exceptionsAdvice
				.handleInvalidRequestException(TestHelper.TEST_RUNTIME_EXC, TestHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12/11/2019", response.getBody().getCreatedAt());
		Assert.assertEquals(InvalidRequestException.class.getName(), response.getBody().getDetailedMessage());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Error - Message", response.getBody().getErrorMessage());
	}
}
