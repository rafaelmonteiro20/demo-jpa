package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.model.SmsNotification;
import com.demo.service.NotificationSender;

@Service
public class SmsNotificationSender implements NotificationSender<SmsNotification> {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public Class<SmsNotification> appliesTo() {
		return SmsNotification.class;
	}

	@Override
	public void send(SmsNotification notification) {
		LOGGER.info("Send SMS to {} {} via phone number: {}",
				notification.getFirstName(),
				notification.getLastName(),
				notification.getPhoneNumber());
	}

}
