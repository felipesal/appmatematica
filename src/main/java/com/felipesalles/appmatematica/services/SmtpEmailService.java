package com.felipesalles.appmatematica.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender jms;
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void enviaEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
	}

	@Override
	public void enviaEmailHtml(MimeMessage msg) {
		LOG.info("Enviando email");
		jms.send(msg);
		LOG.info("Email enviado");
		
	}

}
