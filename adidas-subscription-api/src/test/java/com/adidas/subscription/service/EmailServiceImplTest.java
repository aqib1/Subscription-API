package com.adidas.subscription.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.feignclient.EmailAPI;
import com.adidas.subscription.service.Impl.EmailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

	@Mock
	private EmailAPI emailAPI;
	
	@Spy
	@InjectMocks
	private EmailServiceImpl emailServiceImpl;
}
