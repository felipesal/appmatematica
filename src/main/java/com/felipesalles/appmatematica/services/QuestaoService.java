package com.felipesalles.appmatematica.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.Questao;
import com.felipesalles.appmatematica.domain.RespostaQuestao;
import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.QuestaoDTO;
import com.felipesalles.appmatematica.domain.dto.QuestaoNewDTO;
import com.felipesalles.appmatematica.repositories.QuestaoRepository;
import com.felipesalles.appmatematica.repositories.UserRepository;

@Service
public class QuestaoService {

	@Autowired
	private QuestaoRepository repo;

	@Autowired
	private UserRepository users;
	
	public List<QuestaoDTO> findAll() {

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

	public String responder(Integer id, RespostaQuestao resposta) {

		Questao q = findOne(id);
		
		User user = users.findById(resposta.getUser().getId()).get();
		String msg = null;
		if(userNaoRespondeu(user, q)) {

		Map<String, Boolean> mapAlternativas = q.getMapAlternativas();

		

		if (verificaResposta(mapAlternativas, resposta.getResposta())) {
			msg = "Parabéns! Resposta correta";
			user.setPontuacao(user.getPontuacao() +10 );
			user.addQuestaoCorreta(q);
			users.save(user);
		}

		else {
			msg = "Resposta incorreta, mas não desista. Você é capaz.";
		}
		}
		
		else {
			msg = "Você já respondeu essa pergunta";
		}

		return msg;

	}

	private boolean userNaoRespondeu(User user, Questao questao) {
		if(user.getQuestoesCorretas().contains(questao)) {
		return false;
		}
		
		else {
			return true;
		}
	}

	private Boolean verificaResposta(Map<String, Boolean> mapAlternativas, String resposta) {

		return mapAlternativas.get(resposta);

	}

}
