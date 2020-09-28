package com.felipesalles.appmatematica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.felipesalles.appmatematica.services.EmailService;
import com.felipesalles.appmatematica.services.SmtpEmailService;

@Configuration
public class EmailConfig {

	
	@Bean
	public EmailService emailService () {
		return new SmtpEmailService();
	}
}
