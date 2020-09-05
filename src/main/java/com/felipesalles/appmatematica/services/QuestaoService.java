package com.felipesalles.appmatematica.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.Questao;
import com.felipesalles.appmatematica.domain.dto.QuestaoDTO;
import com.felipesalles.appmatematica.domain.dto.QuestaoNewDTO;
import com.felipesalles.appmatematica.repositories.QuestaoRepository;

@Service
public class QuestaoService {

	@Autowired
	private QuestaoRepository repo;
	
	public List<QuestaoDTO> findAll(){
		
		List<Questao> questoes = repo.findAll();
		
		return questoes.stream().map(x -> new QuestaoDTO(x)).collect(Collectors.toList());
	}

	public Questao findOne(Integer id) {
		
		Optional<Questao> op = repo.findById(id);
		
		return op.get();
	}

	public Questao insert(QuestaoNewDTO novaQuestao) {
		
		Questao questao = fromDto(novaQuestao);
		
		return repo.save(questao);
	}
	
	public Questao fromDto(QuestaoNewDTO qDto) {
		
		Questao q = new Questao(null, qDto.getTitulo(), qDto.getArea(), qDto.getEnunciado(), qDto.getNivel());
		q.setMapAlternativas(qDto.getMapAlternativas());
		
		
		
		return q;
	}

	
}