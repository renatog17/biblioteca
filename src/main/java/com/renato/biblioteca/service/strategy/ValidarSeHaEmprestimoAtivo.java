package com.renato.biblioteca.service.strategy;

import com.renato.biblioteca.domain.EstudanteLivro;

public class ValidarSeHaEmprestimoAtivo implements ValidacaoEmprestimo{

	@Override
	public boolean validar(EstudanteLivro estudanteLivro) {
		
		return false;
	}

}
