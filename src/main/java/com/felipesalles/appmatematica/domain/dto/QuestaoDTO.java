package com.felipesalles.appmatematica.domain.dto;

import java.io.Serializable;

import com.felipesalles.appmatematica.domain.Questao;

public class QuestaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String titulo;
	
	private String area;
	
	private String nivel;

	public QuestaoDTO(Questao questao) {
		super();
		this.id = questao.getId();
		this.titulo = questao.getTitulo();
		this.area = questao.getArea();
		this.nivel = questao.getNivel();
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
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
		QuestaoDTO other = (QuestaoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
