package com.renato.biblioteca.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.renato.biblioteca.domain.EstudanteLivro;

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
		
	}
}
