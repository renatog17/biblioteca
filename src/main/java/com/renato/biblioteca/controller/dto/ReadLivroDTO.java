package com.renato.biblioteca.controller.dto;

import com.renato.biblioteca.domain.Livro;

public record ReadLivroDTO(
		Long id,
		String isbn,
		String titulo,
		String autor,
		Integer quantidadeTotal) {

	public ReadLivroDTO( Livro livro ) {
		this(livro.getId(), livro.getIsbn(), livro.getTitulo(), livro.getAutor(), livro.getQuantidadeTotal());
	}

}
