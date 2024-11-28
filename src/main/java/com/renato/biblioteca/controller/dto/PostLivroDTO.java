package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostLivroDTO(
		@NotEmpty
		String isbn,
		@NotEmpty
		String titulo,
		@NotEmpty
		String autor,
		@NotNull
		@Min(value = 1)
		Integer quantidade) {

}
