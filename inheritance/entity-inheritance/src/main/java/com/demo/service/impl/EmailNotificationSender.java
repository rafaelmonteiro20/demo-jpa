package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.model.EmailNotification;
import com.demo.service.NotificationSender;

@Service
public class EmailNotificationSender implements NotificationSender<EmailNotification> {

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	public Class<EmailNotification> appliesTo() {
		return EmailNotification.class;
	}

	@Override
	public void send(EmailNotification notification) {
		LOGGER.info("Send Email to {} {} via address: {}",
				notification.getFirstName(),
				notification.getLastName(),
				notification.getEmailAddress());
	}

}
