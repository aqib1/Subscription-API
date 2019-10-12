package com.adidas.subscription.exceptions;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since  10/12/2019
 */
public class InvalidResponseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4738423698647895073L;
	
	/**
	 * @param message
	 */
	public InvalidResponseException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public InvalidResponseException(String message, Throwable e) {
		super(message, e);
	}

}
