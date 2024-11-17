package com.renato.biblioteca.controller.dto;

import com.renato.biblioteca.domain.Estudante;

public record ReadEstudanteDTO(String matricula,
		String nome) {

	public ReadEstudanteDTO(Estudante estudante) {
		this(estudante.getMatricula(), estudante.getNome());
	}

}
