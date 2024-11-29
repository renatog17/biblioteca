package com.renato.biblioteca.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.controller.dto.PutLivroDTO;
import com.renato.biblioteca.controller.dto.ReadLivroDTO;
import com.renato.biblioteca.domain.Livro;
import com.renato.biblioteca.repositories.LivroRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private LivroRepository livroRepository;
	
	public LivroController(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> postLivro(@RequestBody @Valid PostLivroDTO postLivroDTO, UriComponentsBuilder uriComponentsBuilder){
		Livro livro = new Livro(postLivroDTO);
		livroRepository.save(livro);
		URI uri = uriComponentsBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReadLivroDTO(livro));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLivro(@PathVariable Long id){
		Optional<Livro> optionalLivro = livroRepository.findById(id);
		if(optionalLivro.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new ReadLivroDTO(optionalLivro.get()));
	}
	
	@PutMapping("/{id}")
	@Transactional
	//to-do: alterar a quantidade disponível tbm
	public ResponseEntity<?> putLivro(@PathVariable Long id, @RequestBody PutLivroDTO putLivroDTO){
		Optional<Livro> optionalLivro = livroRepository.findById(id);
		if(optionalLivro.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Livro livro = optionalLivro.get();
		livro.atualizar(putLivroDTO);
		return ResponseEntity.ok(new ReadLivroDTO(livro));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		Optional<Livro> optionalLivro = livroRepository.findById(id);
		if(optionalLivro.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
