package com.adidas.subscription.exceptions;

public class DublicateDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8211053009037445120L;
	
	/**
	 * @param message
	 */
	public DublicateDataException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public DublicateDataException(String message, Throwable e) {
		super(message, e);
	}

}
