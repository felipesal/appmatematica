package com.felipesalles.appmatematica.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	private String area;
	
	private String enunciado;
	
	private String nivel;
	
	@ElementCollection
	@CollectionTable(name="ALTERNATIVAS")
	private Map<String, Boolean> mapAlternativas = new HashMap<>();

	public Questao() {
		
	}

	public Questao(Integer id, String titulo, String area, String enunciado, String nivel) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.area = area;
		this.enunciado = enunciado;
		this.nivel = nivel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public Map<String, Boolean> getMapAlternativas() {
		return mapAlternativas;
	}

	public void setMapAlternativas(Map<String, Boolean> mapAlternativas) {
		this.mapAlternativas = mapAlternativas;
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
		Questao other = (Questao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
