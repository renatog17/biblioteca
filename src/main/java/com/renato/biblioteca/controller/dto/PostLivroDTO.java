package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostLivroDTO(
		@NotEmpty(message = "O título não pode ser nulo ou vazio.")
		String isbn,
		@NotEmpty(message = "O título não pode ser nulo ou vazio.")
		String titulo,
		@NotEmpty(message = "O autor não pode ser nulo ou vazio.")
		String autor,
		@NotNull(message = "A quantidade não pode ser nula.")
		@Min(value = 1, message = "A quantidade deve ser maior ou igual a 1.")
		Integer quantidade) {
}
