package com.demo.listener;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.Customer;

public class DebugCustomerListener {

	private static final Logger logger = LoggerFactory.getLogger(DebugCustomerListener.class);
	
	@PrePersist
	public void prePersist(Customer customer) {
		logger.debug("Persist on customer id: {}", customer.getId());
	}

	@PreUpdate
	public void preUpdate(Customer customer) {
		logger.debug("Update on customer id: {}", customer.getId());
	}
	
	@PreRemove
	public void preRemove(Customer customer) {
		logger.debug("Remove customer id: {}", customer.getId());
	}
	
	@PostLoad
	public void postLoad(Customer customer) {
		logger.debug("Load customer id: {}", customer.getId());
	}
	
}
