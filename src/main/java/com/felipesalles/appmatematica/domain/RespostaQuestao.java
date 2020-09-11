package com.felipesalles.appmatematica.domain;

public class RespostaQuestao {

	private User user;
	
	private String resposta;

	public RespostaQuestao() {
		
	}

	public RespostaQuestao(User user, String resposta) {
		super();
		this.user = user;
		this.resposta = resposta;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
}
