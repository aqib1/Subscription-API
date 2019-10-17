package com.adidas.subscription.aop;

import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_COMPONENT_SCAN_PATH;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("within(" + SUBSCRIPTION_COMPONENT_SCAN_PATH + ".*.*)")
	protected void allMethod() {
	}

	/**
	 * @param joinPoint
	 */
	@Before("allMethod()")
	public void logStartOfMethod(JoinPoint joinPoint) {
		logger.info("Inside method [" + joinPoint.getSignature().getName() + "/" + joinPoint.getTarget().getClass()
				+ "] @ " + LocalDateTime.now().toString());
	}

	/**
	 * @param joinPoint
	 */
	@After("allMethod()")
	public void logEndOfMethod(JoinPoint joinPoint) {
		logger.info("Outside method [" + joinPoint.getSignature().getName() + "/" + joinPoint.getTarget().getClass()
				+ "]  @ " + LocalDateTime.now().toString());
	}
}
