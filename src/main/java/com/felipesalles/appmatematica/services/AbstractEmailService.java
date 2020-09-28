package com.felipesalles.appmatematica.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.felipesalles.appmatematica.domain.User;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender jms;
	
	@Override
	public void enviaEmailDeConfirmacao(User user) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromUser(user);
		enviaEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromUser(User user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject(user.getNome() + " seja bem vindo ao Matem Enem");
		sm.setSentDate(new Date (System.currentTimeMillis()));
		sm.setText(user.toString());
		
		return sm;
	}
	
	protected String htmlFromTemplateUser(User obj) {
		Context context = new Context();
		context.setVariable("user", obj);
		return templateEngine.process("email/confirmacaoCadastro", context);
	}
	
	@Override
	public void enviaEmailDeConfirmacaoComHtml(User user) {
		try {
		MimeMessage mm = prepareMimeMessageFromUser(user);
		enviaEmailHtml(mm);
		}
		catch (Exception e) {
			enviaEmailDeConfirmacao(user);
		}
	}

	protected MimeMessage prepareMimeMessageFromUser(User user) throws Exception {
		MimeMessage mm = jms.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(user.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject(user.getNome() + " seja bem vindo ao Matem Enem.");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateUser(user), true);
		return mm;
	}
	
	@Override
	public void sendNewPassword(User user, String newPassword) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPassword);
		enviaEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date (System.currentTimeMillis()));
		sm.setText("Nova senha " + newPassword);
		
		return sm;
		
	}
	
}
