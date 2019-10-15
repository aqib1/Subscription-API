package com.adidas.subscription.constant;

import java.util.Objects;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {

	private static final Logger logger = LoggerFactory.getLogger(Helper.class);

	/**
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmptyString(String string) {
		logger.info("isNull & isEmpty check against string[" + string + "]");
		return Objects.isNull(string) || string.isEmpty();
	}

	/**
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		logger.info("isNull check against object [" + object + "]");
		return Objects.isNull(object);
	}

	/**
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(Consts.EMAIL_REGEX);
		return pattern.matcher(email).matches();
	}

	private Helper() {

	}
}
