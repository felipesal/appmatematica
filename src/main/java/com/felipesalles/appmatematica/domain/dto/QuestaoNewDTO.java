package com.felipesalles.appmatematica.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QuestaoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	
	private String area;
	
	private String enunciado;
	
	private String nivel;
	
	private Map<String, Boolean> mapAlternativas = new HashMap<>();

	public QuestaoNewDTO() {
		
	}

	public QuestaoNewDTO(String titulo, String area, String enunciado, String nivel) {
		super();
		this.titulo = titulo;
		this.area = area;
		this.enunciado = enunciado;
		this.nivel = nivel;
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
	
	public void addAlternativas(String descricao, Boolean value) {
		mapAlternativas.put(descricao, value);
	}
	
	
}
