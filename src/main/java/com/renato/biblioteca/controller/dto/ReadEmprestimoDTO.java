package com.renato.biblioteca.controller.dto;

import java.time.LocalDateTime;

import com.renato.biblioteca.domain.EstudanteLivro;

public record ReadEmprestimoDTO(Long idEstudante,
		String nomeEstudante,
		Long idLivro,
		String tituloLivro,
		LocalDateTime dataRealizacaoEmprestimo,
		Boolean foiDevolvido) {

	public ReadEmprestimoDTO(EstudanteLivro estudanteLivro) {
		this(estudanteLivro.getEstudante().getId(),
				estudanteLivro.getEstudante().getNome(),
				estudanteLivro.getLivro().getId(),
				estudanteLivro.getLivro().getTitulo(),
				estudanteLivro.getDataHoraInicioEmprestimo(),
				estudanteLivro.getFoiDevolvido());
	}

	
	
}
