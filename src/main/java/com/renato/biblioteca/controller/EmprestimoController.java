package com.renato.biblioteca.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.biblioteca.controller.dto.DadosEmprestimoDTO;
import com.renato.biblioteca.controller.dto.ReadEmprestimoDTO;
import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.domain.EstudanteLivro;
import com.renato.biblioteca.domain.Livro;
import com.renato.biblioteca.repositories.EstudanteLivroRepository;
import com.renato.biblioteca.repositories.EstudanteRepository;
import com.renato.biblioteca.repositories.LivroRepository;


@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	private EstudanteRepository estudanteRepository;
	private LivroRepository livroRepository;
	private EstudanteLivroRepository estudanteLivroRepository;

	public EmprestimoController(EstudanteRepository estudanteRepository, LivroRepository livroRepository,
			EstudanteLivroRepository estudanteLivroRepository) {
		super();
		this.estudanteRepository = estudanteRepository;
		this.livroRepository = livroRepository;
		this.estudanteLivroRepository = estudanteLivroRepository;
	}

	// antes de tudo, tem que ser verificado se há um emprestimo ativo

	@PostMapping("/emprestimo")
	public ResponseEntity<?> realizarEmprestimo(@RequestBody DadosEmprestimoDTO emprestimoDTO) {

		Optional<Estudante> optionalEstudante = estudanteRepository.findById(emprestimoDTO.idEstudante());
		Optional<Livro> optionalLivro = livroRepository.findById(emprestimoDTO.idLivro());

		if (optionalEstudante.isEmpty())
			return ResponseEntity.notFound().build();
		if (optionalLivro.isEmpty())
			return ResponseEntity.notFound().build();

		// por padrão, o emprestimo pode durar no máximo 15 dias, após isso, serão
		// gerados dias de multa

		EstudanteLivro estudanteLivro = new EstudanteLivro(optionalEstudante.get(), optionalLivro.get());
		estudanteLivroRepository.save(estudanteLivro);
		return ResponseEntity.ok(new ReadEmprestimoDTO(estudanteLivro));
	}

	@PostMapping("/devolucao")
	public ResponseEntity<?> realizarDevolucao(@RequestBody DadosEmprestimoDTO emprestimoDTO) {
		Optional<EstudanteLivro> optionalEstudanteLivro = estudanteLivroRepository.findById(emprestimoDTO.idEstudante());
		if (optionalEstudanteLivro.isEmpty())
			return ResponseEntity.notFound().build();
		EstudanteLivro estudanteLivro = optionalEstudanteLivro.get();
		if (estudanteLivro.getFoiDevolvido())
			return ResponseEntity.noContent().build();
		
		estudanteLivro.setFoiDevolvido(true);
		return ResponseEntity.ok(new ReadEmprestimoDTO(estudanteLivro));
	}
}
