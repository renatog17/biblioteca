package com.renato.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.UniqueElements;

import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.controller.dto.PutLivroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String isbn;
	@Column(unique = true)
	private String titulo;
	private String autor;
	@OneToMany(mappedBy = "livro")
	private List<EstudanteLivro> emprestimos = new ArrayList<>();
	private Integer quantidadeTotal;
	private Integer quantidadeDisponivel;
	
	public Livro() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Livro(String isbn, String titulo, String autor, Integer quantidade) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.quantidadeTotal = quantidade;
		this.quantidadeDisponivel = quantidade;
	}


	public Livro(PostLivroDTO postLivroDTO) {
		this.isbn = postLivroDTO.isbn();
		this.titulo = postLivroDTO.titulo();
		this.autor = postLivroDTO.autor();
		this.quantidadeTotal = postLivroDTO.quantidade();
		this.quantidadeDisponivel = postLivroDTO.quantidade();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}

	

	public List<EstudanteLivro> getEmprestimos() {
		return emprestimos;
	}


	public void setEmprestimos(List<EstudanteLivro> emprestimos) {
		this.emprestimos = emprestimos;
	}


	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}


	public void setQuantidadeTotal(Integer quantidade) {
		this.quantidadeTotal = quantidade;
	}

	
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}


	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
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
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + "]";
	}


	public void atualizar(PutLivroDTO putLivroDTO) {
		if(putLivroDTO.autor()!=null)
			this.autor = putLivroDTO.autor();
		if(putLivroDTO.titulo()!=null)
			this.titulo = putLivroDTO.titulo();
		if(putLivroDTO.quantidade()!=null) {
			this.quantidadeTotal = putLivroDTO.quantidade();
		}
	}
	
	
}
