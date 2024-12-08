package com.renato.biblioteca.controller.dto;

import jakarta.validation.constraints.NotNull;

public record DadosEmprestimoDTO(
		@NotNull
		Long idLivro,
		@NotNull
		Long idEstudante) {

}
