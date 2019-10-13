package com.adidas.subscription.service.Impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.subscription.constant.Helper;
import com.adidas.subscription.entities.SubscriptionEntity;
import com.adidas.subscription.exceptions.BadInternalServerException;
import com.adidas.subscription.exceptions.DublicateDataException;
import com.adidas.subscription.repositeries.SubscriptionRepository;
import com.adidas.subscription.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository repository;
	private static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class.getName());
	private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();
	private Lock readLock = reentrantLock.readLock();
	private Lock writeLock = reentrantLock.writeLock();

	@Override
	public SubscriptionEntity newSubscription(SubscriptionEntity request) {
		writeLock.lock();
		logger.info("Acquire write lock...");
		logger.debug("Check if given email already exists, for subscriptions.");
		if (!Helper.isNull(getSubscriptionByEmail(request.getEmail()))) {
			throw new DublicateDataException("Email, already exists in subscriptions");
		}
		try {
			logger.debug("saving entity [" + request + "]");
			return repository.save(request);
		} catch (Exception e) {
			throw new BadInternalServerException(e.getMessage(), e);
		} finally {
			logger.info("Release write lock...");
			writeLock.unlock();
		}
	}

	@Override
	public SubscriptionEntity getSubscriptionByEmail(String email) {
		readLock.lock();
		logger.info("Acquire read lock...");
		try {
			logger.debug("find subscription against email [" + email + "]");
			return repository.findOneByEmail(email);
		} finally {
			logger.info("Release read lock...");
			readLock.unlock();
		}
	}

	@Override
	public List<SubscriptionEntity> getAllSubscription() {
		readLock.lock();
		logger.info("Acquire read lock...");
		try {
			logger.debug("find all subscriptions");
			return repository.findAll();
		} finally {
			logger.info("Release read lock...");
			readLock.unlock();
		}
	}

	@Override
	public void delete(SubscriptionEntity entity) {
		writeLock.lock();
		logger.info("Acquire write lock...");
		try {
			logger.debug("delete subscription");
			repository.delete(entity);
		} catch (Exception e) {
			throw new BadInternalServerException(e.getMessage(), e);
		} finally {
			logger.info("Release write lock...");
			writeLock.unlock();
		}
	}

	@Override
	public void deleteAll() {
		writeLock.lock();
		logger.info("Acquire write lock...");
		try {
			logger.debug("delete all subscriptions");
			repository.deleteAll();
		} catch (Exception e) {
			throw new BadInternalServerException(e.getMessage(), e);
		} finally {
			logger.info("Release write lock...");
			writeLock.unlock();
		}
	}

}
