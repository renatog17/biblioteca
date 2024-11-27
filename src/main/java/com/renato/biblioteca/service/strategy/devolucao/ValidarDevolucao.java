package com.renato.biblioteca.service.strategy.devolucao;

import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.Livro;

public interface ValidarDevolucao {

	boolean validar(Estudante estudante, Livro livro);
}
