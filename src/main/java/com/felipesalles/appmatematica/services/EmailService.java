package com.felipesalles.appmatematica.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.felipesalles.appmatematica.domain.User;

public interface EmailService {
	
	void enviaEmailDeConfirmacao(User user);

	void enviaEmail(SimpleMailMessage msg);
	
	void enviaEmailDeConfirmacaoComHtml(User user);
	
	void enviaEmailHtml(MimeMessage msg);

	void sendNewPassword(User user, String newPassword);
	
}
