package com.felipesalles.appmatematica.domain.dto;

import java.io.Serializable;

import com.felipesalles.appmatematica.domain.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nome;
	
	private Integer pontuacao;
	

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.nome = user.getNome();
		this.pontuacao = user.getPontuacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
}
