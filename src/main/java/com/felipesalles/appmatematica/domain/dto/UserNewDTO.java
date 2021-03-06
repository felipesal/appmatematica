package com.felipesalles.appmatematica.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String username;
	@NotNull
	private String senha;

	public UserNewDTO() {
		
	}

	public UserNewDTO(String nome, String email, String username, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.username = username;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
