package com.myacuity.uscisaccountspublic.sendemail.service;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.myacuity.uscisaccountspublic.sendemail.entity.Notification;
import com.myacuity.uscisaccountspublic.sendemail.repository.NotificationRepository;

//import com.amazonaws.services.simpleemail.model.*;
//import com.rkdevblog.ses.exception.AwsSesClientException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AwsSesService {

    private static final String CHAR_SET = "UTF-8";
    private static final String SUBJECT = "Test from our application";
    private final AmazonSimpleEmailService emailService;
    private final String sender;

	@Autowired
	NotificationRepository notificationRepository;
    
    @Autowired
    public AwsSesService(AmazonSimpleEmailService emailService, @Value("${email.sender}") String sender) {
        this.emailService = emailService;
        this.sender = sender;
    }

    /**
     * This method send email using the aws ses sdk
     *
     * @param email email
     * @param body  body
     * @return 
     */
    public Boolean sendEmail(String email, String body) {
        try {

            // The time for request/response round trip to aws in milliseconds
            int requestTimeout = 3000;
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET).withData(body)))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET).withData(SUBJECT)))
                    .withSource(sender).withSdkRequestTimeout(requestTimeout);
            emailService.sendEmail(request);
            return true;
        } catch (RuntimeException e) {
            log.error("Error occurred sending email to {} ", email, e);
  //TODO           throw new AwsSesClientException("Failed to send email ", e);
        }
		return false;
    }   
}

