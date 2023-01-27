package com.myacuity.uscisaccountspublic.sendemail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
//import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;

import com.myacuity.uscisaccountspublic.sendemail.entity.Notification;
import com.myacuity.uscisaccountspublic.sendemail.repository.NotificationRepository;
import com.myacuity.uscisaccountspublic.sendemail.service.AwsSesService;
import com.myacuity.uscisaccountspublic.sendemail.service.NewNotificationService;

@SpringBootApplication
public class SendEmailApplication implements CommandLineRunner {
	 
	
	@Autowired
	NewNotificationService newNotificationService; 

	@Autowired
	NotificationRepository notificationRepository;
	
	public static void main(String[] args) {SpringApplication.run(SendEmailApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
			
		//TODO
		//insert row for testing  -  this will be deleted		
		//insertNoticicationRow();
		
		newNotificationService.processNewNotifications();				
	}

	public void insertNoticicationRow() throws Exception {
			
		Date date = new Date();
		
		Notification notification = new Notification();		
		notification.setActorUsername("patrick");
		notification.setCreatedAt(date);
		notification.setNotificationMessage("Approved");
		notification.setNotificationProcessStatus("New");
		notification.setSubscriberUsername("SubscriberDavid");
		notification.setSubscriberEmail("patrick_darcy@att.net");
		notification.setUpdatedAt(date);
		
		notificationRepository.save(notification);
		
	}	
	
}
