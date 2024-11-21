package com.renato.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.renato.biblioteca.controller.dto.PostEstudanteDTO;
import com.renato.biblioteca.controller.dto.PutEstudanteDTO;
import com.renato.biblioteca.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * 
 */
/**
 * 
 */
@Entity(name = "estudantes")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String matricula;
	private String nome;
	@OneToMany(mappedBy = "estudante")
	private List<EstudanteLivro> emprestimos = new ArrayList<>();
	@OneToMany(mappedBy = "multa")
	private List<Multa> multas = new ArrayList<>();
	@OneToOne
	private User user;
	
	public Estudante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estudante(String matricula, String nome) {
		super();
		this.matricula = matricula;
		this.nome = nome;
	}

	public Estudante(PostEstudanteDTO postEstudanteDTO) {
		this.matricula = postEstudanteDTO.matricula();
		this.nome = postEstudanteDTO.nome();
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Estudante other = (Estudante) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Estudante [id=" + id + ", matricula=" + matricula + ", nome=" + nome + "]";
	}

	public void atualizar(PutEstudanteDTO putEstudanteDTO) {
		if(putEstudanteDTO.nome()!=null) 
			this.nome = putEstudanteDTO.nome();
		if(putEstudanteDTO.matricula()!=null)
			this.matricula = putEstudanteDTO.matricula();
		
	}
	
	
	
}
