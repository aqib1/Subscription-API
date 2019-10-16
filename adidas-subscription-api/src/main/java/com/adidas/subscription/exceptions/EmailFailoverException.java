package com.adidas.subscription.exceptions;

public class EmailFailoverException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1976013880082998226L;

	/**
	 * @param message
	 */
	public EmailFailoverException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public EmailFailoverException(String message, Throwable e) {
		super(message, e);
	}

}
