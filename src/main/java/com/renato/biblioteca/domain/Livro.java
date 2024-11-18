package com.renato.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.controller.dto.PutLivroDTO;

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
	private String titulo;
	private String autor;
	@OneToMany(mappedBy = "livro")
	private List<EstudanteLivro> emprestimos = new ArrayList<>();
	
	public Livro() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Livro(String isbn, String titulo, String autor) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
	}


	public Livro(PostLivroDTO postLivroDTO) {
		this.isbn = postLivroDTO.isbn();
		this.titulo = postLivroDTO.titulo();
		this.autor = postLivroDTO.autor();
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
		if(putLivroDTO.isbn()!=null)
			this.isbn = putLivroDTO.isbn();
	}
	
	
}
