package com.adidas.subscription.service;

import java.util.Collections;
import java.util.List;

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
import com.adidas.subscription.exceptions.DublicateDataException;
import com.adidas.subscription.repository.SubscriptionRepository;
import com.adidas.subscription.service.Impl.SubscriptionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SubscrptionServiceImplTest {

	@Mock
	private SubscriptionRepository repository;

	@Spy
	@InjectMocks
	private SubscriptionServiceImpl subscriptionServiceImpl;

	@Before
	public void init() {
		mockSubscriptionRepository();
	}

	private void mockSubscriptionRepository() {
		Mockito.when(repository.findOneByEmail(Mockito.anyString()))
				.thenReturn(TestHelper.getInstance().subscriptionEntity());
		Mockito.when(repository.findAll())
				.thenReturn(Collections.singletonList(TestHelper.getInstance().subscriptionEntity()));
		Mockito.doNothing().when(repository).deleteOneByEmail(Mockito.anyString());
		Mockito.doNothing().when(repository).deleteAll();
	}

	@Test(expected = DublicateDataException.class)
	public void testNewSubscriptionDuplicate() {
		SubscriptionEntity subscriptionEntity = subscriptionServiceImpl
				.newSubscription(TestHelper.getInstance().subscriptionEntity());
		Assert.assertTrue(1L == subscriptionEntity.getId());
		Assert.assertEquals("Fname", subscriptionEntity.getFirstName());
		Assert.assertEquals(true, subscriptionEntity.getConsent());
		Assert.assertEquals("1/1/2019", subscriptionEntity.getDateOfBirth());
		Assert.assertTrue(1 == subscriptionEntity.getNewsletterId());
		Assert.assertEquals("test@gmail.com", subscriptionEntity.getEmail());
		Assert.assertEquals("M", subscriptionEntity.getGender());
	}

	private void mockNewSubscriptionNonRepeated() {
		Mockito.when(repository.save(Mockito.any(SubscriptionEntity.class)))
				.thenReturn(TestHelper.getInstance().subscriptionEntity1());

		Mockito.when(repository.findOneByEmail(Mockito.anyString())).thenReturn(null);
	}

	@Test
	public void testNewSubscription() {
		mockNewSubscriptionNonRepeated();
		SubscriptionEntity subscriptionEntity = subscriptionServiceImpl
				.newSubscription(TestHelper.getInstance().subscriptionEntity1());
		Assert.assertTrue(1L == subscriptionEntity.getId());
		Assert.assertEquals("Fname", subscriptionEntity.getFirstName());
		Assert.assertEquals(true, subscriptionEntity.getConsent());
		Assert.assertEquals("1/1/2019", subscriptionEntity.getDateOfBirth());
		Assert.assertTrue(1 == subscriptionEntity.getNewsletterId());
		Assert.assertEquals("temp@gmail.com", subscriptionEntity.getEmail());
		Assert.assertEquals("M", subscriptionEntity.getGender());
	}

	@Test
	public void testGetSubscriptionByEmail() {
		SubscriptionEntity subscriptionEntity = subscriptionServiceImpl.getSubscriptionByEmail("test@gmail.com");
		Assert.assertTrue(1L == subscriptionEntity.getId());
		Assert.assertEquals("Fname", subscriptionEntity.getFirstName());
		Assert.assertEquals(true, subscriptionEntity.getConsent());
		Assert.assertEquals("1/1/2019", subscriptionEntity.getDateOfBirth());
		Assert.assertTrue(1 == subscriptionEntity.getNewsletterId());
		Assert.assertEquals("test@gmail.com", subscriptionEntity.getEmail());
		Assert.assertEquals("M", subscriptionEntity.getGender());
	}

	@Test
	public void testGetAllSubscriptions() {
		List<SubscriptionEntity> subscriptionEntities = subscriptionServiceImpl.getAllSubscription();
		Assert.assertTrue(1L == subscriptionEntities.get(0).getId());
		Assert.assertEquals("Fname", subscriptionEntities.get(0).getFirstName());
		Assert.assertEquals(true, subscriptionEntities.get(0).getConsent());
		Assert.assertEquals("1/1/2019", subscriptionEntities.get(0).getDateOfBirth());
		Assert.assertTrue(1 == subscriptionEntities.get(0).getNewsletterId());
		Assert.assertEquals("test@gmail.com", subscriptionEntities.get(0).getEmail());
		Assert.assertEquals("M", subscriptionEntities.get(0).getGender());
	}

	@Test
	public void testDeleteAgainstEmail() {
		subscriptionServiceImpl.delete("test@gmail.com");
		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteAll() {
		subscriptionServiceImpl.deleteAll();
		Assert.assertTrue(true);
	}
}
