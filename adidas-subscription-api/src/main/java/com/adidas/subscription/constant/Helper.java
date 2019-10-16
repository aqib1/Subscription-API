package com.adidas.subscription.constant;

import static com.adidas.subscription.constant.Consts.SENDER_EMAIL_ACCOUNT;
import static com.adidas.subscription.constant.Consts.SENDER_EMAIL_MESSAGE_TEMPLATE;
import static com.adidas.subscription.constant.Consts.SENDER_EMAIL_NAME;
import static com.adidas.subscription.constant.Consts.SENDER_EMAIL_PASSWORD;
import static com.adidas.subscription.constant.Consts.SENDER_EMAIL_SUBJECT;

import java.util.Objects;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.EmailRequest;
import com.example.model.SubscriptionResponse;

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

	/**
	 * @param response
	 * @return
	 */
	public static EmailRequest getEmailRequestObject(SubscriptionResponse response) {
		return new EmailRequest().message(SENDER_EMAIL_MESSAGE_TEMPLATE).newsletterId(response.getSubscriptionId())
				.senderEmail(SENDER_EMAIL_ACCOUNT).senderEmailPassword(SENDER_EMAIL_PASSWORD)
				.subject(SENDER_EMAIL_SUBJECT).senderName(SENDER_EMAIL_NAME)
				.recieverEmail(response.getSubscriptionDto().getEmail());
	}

	private Helper() {

	}
}
