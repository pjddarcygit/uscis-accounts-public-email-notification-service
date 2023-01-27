package com.myacuity.uscisaccountspublic.sendemail.service;

import com.myacuity.uscisaccountspublic.sendemail.entity.Notification;
import com.myacuity.uscisaccountspublic.sendemail.repository.NotificationRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class NewNotificationService {

	@Autowired
	NotificationRepository notificationRepository;   
	
	@Autowired
	AwsSesService awsSesService;   
    
    public void processNewNotifications() throws Exception {
		
		Boolean emailSent;
		Date date = new Date();
			
		List<Notification> newNotifications = notificationRepository.getNotificationByNotificationProcessStatus("New");
		
		for (Notification notification : newNotifications) {
			
			emailSent = awsSesService.sendEmail(notification.getSubscriberEmail(),
					"The Status has been updated to " + notification.getNotificationMessage());
				
			if(emailSent) {
				notification.setNotificationProcessStatus("Completed");
				notification.setUpdatedAt(date);			
				notificationRepository.save(notification);			
			}	
			else
				System.out.println("boolean-emailSentFAILED need to add a throws exception " + emailSent);
			// TODO add throws exception
			 
		}
		
//     TODO  the below can be deleted I added them for testing  		
//		List<Notification> not = notificationRepository.getNotificationBySubscriberEmail("patrick_darcy@att.net");
//		
//		System.out.println("Notification Update List email : " + not.get(0).getSubscriberEmail());	
//		System.out.println("Notification Update List email : " + not.get(0).getNotificationMessage());		
//		System.out.println("Notification Update List Process Status : " + not.get(0).getNotificationProcessStatus());		
	}	
}

