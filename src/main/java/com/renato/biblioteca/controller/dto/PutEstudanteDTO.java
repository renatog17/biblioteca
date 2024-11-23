package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PutEstudanteDTO(
		@NotNull
		String matricula,
		@NotNull
		String nome) {

}
