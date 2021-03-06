package com.adidas.email.controller.advice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.adidas.email.exceptions.BadInternalServerException;
import com.adidas.email.exceptions.InvalidRequestException;
import com.adidas.email.exceptions.InvalidResponseException;
import com.example.model.ResponseError;

@RestControllerAdvice
public class ExceptionsAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionsAdvice.class);

	/**
	 * @param e
	 * @param wr
	 * @return
	 */
	@ExceptionHandler(value = { InvalidRequestException.class })
	public ResponseEntity<ResponseError> handleInvalidRequestException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Invalid request exception! => (InvalidRequestException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(InvalidRequestException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @param wr
	 * @return
	 */
	@ExceptionHandler(value = { InvalidResponseException.class })
	public ResponseEntity<ResponseError> handleInvalidResponseException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Invalid response exception! => (InvalidResponseException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(InvalidResponseException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param e
	 * @param wr
	 * @return
	 */
	@ExceptionHandler(value = { BadInternalServerException.class })
	public ResponseEntity<ResponseError> handleBadInternalServerException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Bad internal server exception! => (BadInternalServerException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(BadInternalServerException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
