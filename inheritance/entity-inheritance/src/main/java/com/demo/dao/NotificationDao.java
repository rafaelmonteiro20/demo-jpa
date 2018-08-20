package com.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.demo.model.Notification;

@Repository
public class NotificationDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Notification> findAll() {
		return manager.createQuery("from Notification", Notification.class)
					.getResultList();
	}
	
}
