package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.EmailNotification;
import com.demo.model.SmsNotification;
import com.demo.service.NotificationService;

@Component
public class CommandLine implements CommandLineRunner {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		SmsNotification sms = new SmsNotification();
		sms.setPhoneNumber("(85) 9 9999-9999");
		sms.setFirstName("Rafael");
		sms.setLastName("Monteiro");
		 
		manager.persist(sms);
		 
		EmailNotification email = new EmailNotification();
		email.setEmailAddress("vlad@acme.com");
		email.setFirstName("Vlad");
		email.setLastName("Mihalcea");
		 
		manager.persist( email );
		
		notificationService.sendCampaign("Best practices", "Java Persistence API");
	}

}
