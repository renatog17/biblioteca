package com.renato.biblioteca.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.biblioteca.controller.dto.EmprestimoDTO;
import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.EstudanteLivro;
import com.renato.biblioteca.domain.Livro;
import com.renato.biblioteca.repositories.EstudanteLivroRepository;
import com.renato.biblioteca.repositories.EstudanteRepository;
import com.renato.biblioteca.repositories.LivroRepository;

@RestController
@RequestMapping("/emrpestimos")
public class EmprestimoController {

	private EstudanteRepository estudanteRepository;
	private LivroRepository livroRepository;
	private EstudanteLivroRepository estudanteLivroRepository;
	
	public ResponseEntity<?> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){
		
		Optional<Estudante> optionalEstudante = estudanteRepository.findById(emprestimoDTO.idEstudante());
		Optional<Livro> optionalLivro = livroRepository.findById(emprestimoDTO.idLivro());
		
		if(optionalEstudante.isEmpty())
			return ResponseEntity.notFound().build();
		if(optionalLivro.isEmpty())
			return ResponseEntity.notFound().build();
		
		//fazer outras verificações
		
		EstudanteLivro estudanteLivro = new EstudanteLivro(optionalEstudante.get(), optionalLivro.get(), LocalDateTime.now(), false);
		estudanteLivroRepository.save(estudanteLivro);
		
		
		return ResponseEntity.ok(estudanteLivro);
	}
}
