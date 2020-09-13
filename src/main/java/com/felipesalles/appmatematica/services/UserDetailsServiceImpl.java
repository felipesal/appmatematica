package com.felipesalles.appmatematica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.repositories.UserRepository;
import com.felipesalles.appmatematica.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository users;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSS(user.getId(), user.getUsername(), user.getSenha(), user.getPerfis());
	}

}
