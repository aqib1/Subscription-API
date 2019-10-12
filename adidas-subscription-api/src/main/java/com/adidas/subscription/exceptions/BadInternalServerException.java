package com.adidas.subscription.exceptions;


/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 	10/12/2019
 */
public class BadInternalServerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -859512830786799974L;
	
	/**
	 * @param message
	 */
	public BadInternalServerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public BadInternalServerException(String message, Throwable e) {
		super(message, e);
	}

}