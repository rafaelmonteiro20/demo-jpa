package com.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.NotificationDao;
import com.demo.model.Notification;
import com.demo.service.NotificationSender;
import com.demo.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private List<NotificationSender<?>> notificationsSender;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<? extends Notification>, NotificationSender> notificationsSenderMap;
	
	@PostConstruct
	public void init() {
		notificationsSenderMap = new HashMap<>();
	
		for (NotificationSender<? extends Notification> notificationSender : notificationsSender) {
			notificationsSenderMap.put(notificationSender.appliesTo(), notificationSender);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void sendCampaign(String name, String message) {
	
		List<Notification> notifications = notificationDao.findAll();
		
		for (Notification notification : notifications) {
			notificationsSenderMap.get(notification.getClass()).send(notification);
		}
	}
	
}
