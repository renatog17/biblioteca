package com.renato.biblioteca.service.strategy;

import com.renato.biblioteca.domain.EstudanteLivro;

public interface ValidacaoEmprestimo {

	boolean validar(EstudanteLivro estudanteLivro);
}
