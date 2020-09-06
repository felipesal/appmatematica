package com.felipesalles.appmatematica.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipesalles.appmatematica.domain.Questao;
import com.felipesalles.appmatematica.domain.dto.QuestaoDTO;
import com.felipesalles.appmatematica.domain.dto.QuestaoNewDTO;
import com.felipesalles.appmatematica.services.QuestaoService;

@RestController
@RequestMapping("/questoes")
public class QuestaoResources {
	
	@Autowired
	private QuestaoService qservice;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<QuestaoDTO> findAll() {
		
		return qservice.findAll(); 
	}
	
	@GetMapping("/{id}")
	public Questao findOne(@PathVariable Integer id) {
		
		return qservice.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Questao insert(@RequestBody QuestaoNewDTO novaQuestao) {
		
		return qservice.insert(novaQuestao);
	}
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String responder(@PathVariable Integer id, @RequestBody String resposta) {
		
		return qservice.responder(id, resposta);
	}
	
}
