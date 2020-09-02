package com.felipesalles.appmatematica;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.repositories.UserRepository;

@SpringBootApplication
public class AppMatematicaApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository users;
	
	public static void main(String[] args) {
		SpringApplication.run(AppMatematicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(1, "Ana", "ana@gmail.com", "aninha", "123abc", 0);
		User u2 = new User(2, "Bruno", "bruno@gmail.com", "brunin", "123abc", 0);
		
		users.saveAll(Arrays.asList(u1, u2));
		
	}

}
