package com.adidas.subscription.business;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.adidas.subscription.constant.TestHelper;
import com.adidas.subscription.entities.SubscriptionEntity;
import com.adidas.subscription.mapper.SubscriptionMapper;
import com.adidas.subscription.service.Impl.SubscriptionServiceImpl;
import com.example.model.SubscriptionDetailedResponse;
import com.example.model.SubscriptionDto;
import com.example.model.SubscriptionResponse;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionBusinessTest_Mock {

	@Mock
	private SubscriptionServiceImpl subscriptionServieImpl;

	@Mock
	private SubscriptionMapper mapper;

	@Spy
	@InjectMocks
	private SubscriptionBusiness subscriptionBusiness;

	@Before
	public void init() {
		mockSubscriptionImpl();
		mockSucbscriptionMapper();
	}

	private void mockSucbscriptionMapper() {
		Mockito.when(subscriptionServieImpl.newSubscription(Mockito.any(SubscriptionEntity.class)))
				.thenReturn(TestHelper.getInstance().subscriptionEntity());
		Mockito.when(subscriptionServieImpl.getSubscriptionByEmail(Mockito.anyString()))
				.thenReturn(TestHelper.getInstance().subscriptionEntity());
		Mockito.when(subscriptionServieImpl.getAllSubscription())
				.thenReturn(Collections.singletonList(TestHelper.getInstance().subscriptionEntity()));

		Mockito.doNothing().when(subscriptionServieImpl).delete(Mockito.anyString());

		Mockito.doNothing().when(subscriptionServieImpl).deleteAll();
	}

	private void mockSubscriptionImpl() {
		Mockito.when(mapper.subscriptionEntityFromSubscriptionDto(Mockito.any(SubscriptionDto.class)))
				.thenReturn(TestHelper.getInstance().subscriptionEntity());

		Mockito.when(mapper.subscriptionDtoFromSubscriptionEntity(Mockito.any(SubscriptionEntity.class)))
				.thenReturn(TestHelper.getInstance().subscriptionDto());

		Mockito.when(mapper.subscriptionDtoListFromSubscriptionEntityList(Mockito.anyList()))
				.thenReturn(Collections.singletonList(TestHelper.getInstance().subscriptionDto()));
	}

	@Test
	public void testNewSubscription() {
		SubscriptionResponse response = subscriptionBusiness
				.newSubscription(TestHelper.getInstance().subscriptionRequest());
		Assert.assertEquals("Fname", response.getSubscriptionDto().getFirstName());
		Assert.assertEquals(true, response.getSubscriptionDto().getConsent());
		Assert.assertTrue(1 == response.getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("test@gmail.com", response.getSubscriptionDto().getEmail());
		Assert.assertEquals("M", response.getSubscriptionDto().getGender());
		Assert.assertTrue(1 == response.getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("1/1/2019", response.getSubscriptionDto().getDateOfBirth());
	}

	@Test
	public void testGetSubscriptionByEmail() {
		SubscriptionResponse response = subscriptionBusiness.getSubscriptionByEmail("test@gmail.com");
		Assert.assertEquals("Fname", response.getSubscriptionDto().getFirstName());
		Assert.assertEquals(true, response.getSubscriptionDto().getConsent());
		Assert.assertTrue(1 == response.getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("test@gmail.com", response.getSubscriptionDto().getEmail());
		Assert.assertEquals("M", response.getSubscriptionDto().getGender());
		Assert.assertTrue(1 == response.getSubscriptionDto().getNewsletterId());
		Assert.assertEquals("1/1/2019", response.getSubscriptionDto().getDateOfBirth());
	}

	@Test
	public void testGetAllSubscriptions() {
		SubscriptionDetailedResponse data = subscriptionBusiness.getAllSubscriptions();
		Assert.assertEquals("Fname", data.getSubscriptionsList().get(0).getFirstName());
		Assert.assertEquals(true, data.getSubscriptionsList().get(0).getConsent());
		Assert.assertTrue(1 == data.getSubscriptionsList().get(0).getNewsletterId());
		Assert.assertEquals("test@gmail.com", data.getSubscriptionsList().get(0).getEmail());
		Assert.assertEquals("M", data.getSubscriptionsList().get(0).getGender());
		Assert.assertTrue(1 == data.getSubscriptionsList().get(0).getNewsletterId());
		Assert.assertEquals("1/1/2019", data.getSubscriptionsList().get(0).getDateOfBirth());
	}

	@Test
	public void testDeleteByEmail() {
		subscriptionBusiness.deleteByEmail("test@gmail.com");
		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteAll() {
		subscriptionBusiness.deleteAll();
		Assert.assertTrue(true);
	}
}
