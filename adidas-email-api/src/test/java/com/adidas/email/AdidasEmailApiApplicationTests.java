package com.adidas.email;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdidasEmailApiApplicationTests {

	/**
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void contextLoads() throws NoSuchMethodException, SecurityException {
		String methodName = "main";
		Class<?> c = AdidasEmailApiApplication.class;
		Method method = c.getDeclaredMethod(methodName, String[].class);
		Assert.assertNotNull(method);
	}
}
