package com.renato.biblioteca.service.strategy.emprestimo;

import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.Livro;

public class VerificarDisponibilidadeDoLivro implements ValidacaoEmprestimo{
	
	@Override
	public boolean validar(Estudante estudante, Livro livro) {
		if(livro.getQuantidadeDisponivel()>2) {
			return true;
		}
		return false;
	}

}
