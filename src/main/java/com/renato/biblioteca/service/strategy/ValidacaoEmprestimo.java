package com.renato.biblioteca.service.strategy;

import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.Livro;

public interface ValidacaoEmprestimo {

	boolean validar(Estudante estudante, Livro livro);
}
