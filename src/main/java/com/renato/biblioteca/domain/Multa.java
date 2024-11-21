package com.renato.biblioteca.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * 
 */
@Entity(name = "livros")
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer duracaoEmdias;
	private LocalDateTime inicio;
	@ManyToOne
	@JoinColumn(name = "estudante_id")
	private Estudante estudante;

	public Multa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Multa(Long id, Integer duracaoEmdias, LocalDateTime inicio, Estudante estudante) {
		super();
		this.id = id;
		this.duracaoEmdias = duracaoEmdias;
		this.inicio = inicio;
		this.estudante = estudante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDuracaoEmdias() {
		return duracaoEmdias;
	}

	public void setDuracaoEmdias(Integer duracaoEmdias) {
		this.duracaoEmdias = duracaoEmdias;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Multa other = (Multa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Multa [id=" + id + ", duracaoEmdias=" + duracaoEmdias + ", inicio=" + inicio + ", estudante="
				+ estudante + "]";
	}

}
