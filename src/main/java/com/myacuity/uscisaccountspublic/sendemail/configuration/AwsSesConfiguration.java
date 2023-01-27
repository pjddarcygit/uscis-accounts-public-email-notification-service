package com.myacuity.uscisaccountspublic.sendemail.configuration;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfiguration {

    private final String region;

    public AwsSesConfiguration(@Value("${email.region}") String region) {
    	
        this.region = region;
    }

    /**
     * Build the AWS ses client
     *
     * @return AmazonSimpleEmailServiceClientBuilder
     */
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
 
    	// The below is also using and requires the AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY for the ses-user User ID in AWS
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region).build();
    }
}
