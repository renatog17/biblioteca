package com.renato.biblioteca.service.strategy;

import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.Livro;

public class VerificarDisponibilidadeDoLivro implements ValidacaoEmprestimo{

	@Override
	public boolean validar(Estudante estudante, Livro livro) {
		// TODO Auto-generated method stub
		return false;
	}

}
