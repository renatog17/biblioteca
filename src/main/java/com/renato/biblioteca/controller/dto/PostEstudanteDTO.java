package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PostEstudanteDTO(
		@NotNull
		String matricula,
		@NotNull
		String nome) {

}
