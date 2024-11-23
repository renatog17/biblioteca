package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PostLivroDTO(
		@NotNull
		String isbn,
		@NotNull
		String titulo,
		@NotNull
		String autor) {

}
