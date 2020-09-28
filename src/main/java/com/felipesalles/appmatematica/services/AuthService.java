package com.felipesalles.appmatematica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.repositories.UserRepository;
import com.felipesalles.appmatematica.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	
	
	public void sendNewPassword(String email) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new ObjectNotFoundException("Email " + email + " não encontrado.");
		}
		
		String newPassword = newPassword(email);
		user.setSenha(pe.encode(newPassword));
		
		userRepository.save(user);
		emailService.sendNewPassword(user, newPassword);
		
	}
	
	private String newPassword(String email) {
		
		char[] vet = new char[5];
		
		for(int i = 0; i<5 ; i++) {
			vet[i] = email.charAt(i);
		}
		
		return new String(vet).concat("1a2b3");
		
	}

	
}
