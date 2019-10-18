package com.adidas.email.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.junit.Assert;
import org.junit.Test;

public class LoggingAspectTest {

	@Test
	public void checkMethodsAllMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "allMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName);
		Assert.assertNotNull(method);
	}

	@Test
	public void checkMethodsLogStartOfMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "logStartOfMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName, JoinPoint.class);
		Assert.assertNotNull(method);
	}

	@Test
	public void checkMethodsLogEndOfMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "logEndOfMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName, JoinPoint.class);
		Assert.assertNotNull(method);
	}
}
