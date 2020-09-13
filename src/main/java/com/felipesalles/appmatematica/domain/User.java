package com.felipesalles.appmatematica.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipesalles.appmatematica.domain.enums.Perfil;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private String nome;
	
	private String email;
	
	private String username;
	
	@JsonIgnore
	private String senha;
	
	private Integer pontuacao;
	
	 @ElementCollection(fetch=FetchType.EAGER)
	 @CollectionTable(name = "PERFIS")
	 private Set<Integer> perfis = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="QUESTOES_CORRETAS")
	private Set<Questao> questoesCorretas = new HashSet<>();
	

	public User() {
		addPerfil(Perfil.CLIENTE);
	}
	
	

	public User(Integer id, String nome, String email, String username, String senha, Integer pontuacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.username = username;
		this.senha = senha;
		this.pontuacao = pontuacao;
		addPerfil(Perfil.CLIENTE);
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

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(obj -> Perfil.toEnum(obj)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Set<Questao> getQuestoesCorretas() {
		return questoesCorretas;
	}



	public void setQuestoesCorretas(Set<Questao> questoesCorretas) {
		this.questoesCorretas = questoesCorretas;
	}


	public void addQuestaoCorreta(Questao q) {
		questoesCorretas.add(q);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
