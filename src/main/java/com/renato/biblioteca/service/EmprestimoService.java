package com.renato.biblioteca.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renato.biblioteca.domain.EstudanteLivro;
import com.renato.biblioteca.service.strategy.emprestimo.ValidacaoEmprestimo;
import com.renato.biblioteca.service.strategy.emprestimo.ValidarSeHaEmprestimoAtivo;
import com.renato.biblioteca.service.strategy.emprestimo.ValidarSeHaMulta;
import com.renato.biblioteca.service.strategy.emprestimo.VerificarDisponibilidadeDoLivro;

@Service
public class EmprestimoService {

	public void verificarECalcularMulta(EstudanteLivro estudanteLivro) {
		LocalDateTime dataHoraInicioEmprestimo = estudanteLivro.getDataHoraInicioEmprestimo();
		LocalDateTime agora = LocalDateTime.now();
	    long diasDiferenca = Duration.between(dataHoraInicioEmprestimo, agora).toDays();
	    System.out.println(diasDiferenca);
	    if(diasDiferenca>15) {
	    	System.out.println();
	    }
	}
	
	public void validarEmprestimo() {
		ValidacaoEmprestimo validarSeHaEmprestimoAtivo = new ValidarSeHaEmprestimoAtivo();
		ValidacaoEmprestimo validarSeHaMulta = new ValidarSeHaMulta();
		ValidacaoEmprestimo verificarDisponibilidadeDoLivro = new VerificarDisponibilidadeDoLivro();
		
		List<ValidacaoEmprestimo> validacoes = new ArrayList<ValidacaoEmprestimo>();
		validacoes.add(verificarDisponibilidadeDoLivro);
		validacoes.add(validarSeHaMulta);
		validacoes.add(validarSeHaEmprestimoAtivo);
	}
}
