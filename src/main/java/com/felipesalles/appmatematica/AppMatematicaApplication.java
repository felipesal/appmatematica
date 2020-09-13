package com.felipesalles.appmatematica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.felipesalles.appmatematica.domain.Questao;
import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.enums.Perfil;
import com.felipesalles.appmatematica.repositories.QuestaoRepository;
import com.felipesalles.appmatematica.repositories.UserRepository;

@SpringBootApplication
public class AppMatematicaApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private QuestaoRepository questoes;
	
	public static void main(String[] args) {
		SpringApplication.run(AppMatematicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(1, "Ana", "ana@gmail.com", "aninha", pe.encode("123abc"), 0);
		User u2 = new User(2, "Bruno", "bruno@gmail.com", "brunin", pe.encode("123abc"), 0);
		u2.addPerfil(Perfil.ADMIN);
		
		users.saveAll(Arrays.asList(u1, u2));
		
		String enunciado1= "Se em uma careca tem 20 fios de cabelo, quantos fios tem meia careca?";
		String enunciado2 = "Qual o perímetro de um quadrado de lado 2?";
		
		String alternativa1Q1 = "Nenhum, careca num tem cabelo";
		String alternativa2Q1 = "10";
		
		Questao q1 = new Questao(null, "Careca", "Lógica", enunciado1, "Iniciante");
		Questao q2 = new Questao(null, "Quadrado", "Geometria", enunciado2, "Iniciante");
		
		Map<String, Boolean> mapAlternativasQ1 = new HashMap<>();
		mapAlternativasQ1.put(alternativa1Q1, true);
		mapAlternativasQ1.put(alternativa2Q1, false);
		
		
		Map<String, Boolean> mapAlternativasQ2 = new HashMap<>();
		String alternativa1Q2 = "8";
		String alternativa2Q2 = "6";
		
		mapAlternativasQ2.put(alternativa1Q2, true);
		mapAlternativasQ2.put(alternativa2Q2, false);
		
		q1.setMapAlternativas(mapAlternativasQ1);
		q2.setMapAlternativas(mapAlternativasQ2);
		
		questoes.saveAll(Arrays.asList(q1, q2));
		
	}

}
