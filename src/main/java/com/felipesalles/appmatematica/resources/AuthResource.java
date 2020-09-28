package com.felipesalles.appmatematica.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipesalles.appmatematica.domain.dto.EmailDTO;
import com.felipesalles.appmatematica.security.JWTUtil;
import com.felipesalles.appmatematica.security.UserSS;
import com.felipesalles.appmatematica.services.AuthService;
import com.felipesalles.appmatematica.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	@PostMapping("/refresh_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void refreshToken(HttpServletResponse response) {
		
		UserSS user = UserService.authenticated();
		
		String token = jwtUtil.generateToken(user.getUsername());
		
		response.addHeader("Authorization", "Bearer " + token);
		
	}
	
	@PostMapping("/forgot")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void forgot(@RequestBody EmailDTO emailDto) {
		
		service.sendNewPassword(emailDto.getEmail());
		
		
		
	}
}
