package com.myacuity.uscisaccountspublic.sendemail.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notification")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notification {
	@Id
	@SequenceGenerator(name = "notification_sequence", allocationSize = 1)
	@GeneratedValue(generator = "notification_sequence")
	Integer id;
	
	@Column(name = "actor_username")
	private String actorUsername;
	
	@Column(name = "subscriber_username")
	private String subscriberUsername;
	
	@Column(name = "subscriber_email")
	private String subscriberEmail;
	
	@Column(name = "notification_process_status")
	private String notificationProcessStatus;
	
	@Column(name = "notification_message")
	private String notificationMessage;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
}
