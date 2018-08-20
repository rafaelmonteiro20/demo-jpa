package com.demo.service;

import com.demo.model.Notification;

public interface NotificationSender<N extends Notification> {

	Class<N> appliesTo();
	
	void send(N notification);
	
}
