package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PostEmprestimoDTO(
		@NotNull
		Long idLivro,
		@NotNull
		Long idEstudante) {

}
