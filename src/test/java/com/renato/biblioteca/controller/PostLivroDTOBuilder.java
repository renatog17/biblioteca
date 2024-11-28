package com.renato.biblioteca.controller;

import com.renato.biblioteca.controller.dto.PostLivroDTO;

public class PostLivroDTOBuilder {

	private String isbn;
	private String titulo;
	private String autor;
	private Integer quantidade;

	public PostLivroDTOBuilder withIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public PostLivroDTOBuilder withTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public PostLivroDTOBuilder withAutor(String autor) {
		this.autor = autor;
		return this;
	}
	public PostLivroDTOBuilder withQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	public PostLivroDTOBuilder withIsbnBlank() {
		this.isbn = "";
		return this;
	}

	public PostLivroDTOBuilder withTituloBlank() {
		this.titulo = "";
		return this;
	}

	public PostLivroDTOBuilder withAutorBlank() {
		this.autor = "";
		return this;
	}

	
	public PostLivroDTOBuilder withIsbnNull() {
		this.isbn = null;
		return this;
	}

	public PostLivroDTOBuilder withTituloNull() {
		this.titulo = null;
		return this;
	}

	public PostLivroDTOBuilder withAutorNull() {
		this.autor = null;
		return this;
	}
	public PostLivroDTOBuilder withQuantidadeNull() {
		this.quantidade = null;
		return this;
	}
	

	public PostLivroDTO build() {
		PostLivroDTO postLivroDTO = new PostLivroDTO(this.isbn, this.titulo, this.autor, this.quantidade);
		return postLivroDTO;
	}
}
