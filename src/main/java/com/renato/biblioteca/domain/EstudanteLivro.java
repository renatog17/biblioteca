package com.renato.biblioteca.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EstudanteLivro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "estudante_id")
	private Estudante estudante;
	@ManyToOne
	@JoinColumn(name = "livro_id")
	private Livro livro;
	private LocalDateTime dataHoraInicioEmprestimo;
	private Boolean foiDevolvido;
	
	public EstudanteLivro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudanteLivro(Estudante estudante, Livro livro, LocalDateTime dataHoraInicioEmprestimo,
			Boolean foiDevolvido) {
		super();
		this.estudante = estudante;
		this.livro = livro;
		this.dataHoraInicioEmprestimo = dataHoraInicioEmprestimo;
		this.foiDevolvido = foiDevolvido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public LocalDateTime getDataHoraInicioEmprestimo() {
		return dataHoraInicioEmprestimo;
	}

	public void setDataHoraInicioEmprestimo(LocalDateTime dataHoraInicioEmprestimo) {
		this.dataHoraInicioEmprestimo = dataHoraInicioEmprestimo;
	}

	public Boolean getFoiDevolvido() {
		return foiDevolvido;
	}

	public void setFoiDevolvido(Boolean foiDevolvido) {
		this.foiDevolvido = foiDevolvido;
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
		EstudanteLivro other = (EstudanteLivro) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
