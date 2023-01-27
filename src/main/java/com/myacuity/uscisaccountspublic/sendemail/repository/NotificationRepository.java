package com.myacuity.uscisaccountspublic.sendemail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myacuity.uscisaccountspublic.sendemail.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	List<Notification> getNotificationBySubscriberEmail(String subscriberEmail);
	
	List<Notification> getNotificationByNotificationProcessStatus(String notificationProcessStatus);
}
