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

import com.renato.biblioteca.controller.dto.PostEstudanteDTO;
import com.renato.biblioteca.controller.dto.PutEstudanteDTO;
import com.renato.biblioteca.controller.dto.ReadEstudanteDTO;
import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.repositories.EstudanteRepository;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

	private EstudanteRepository estudanteRepository;
	
	public EstudanteController(EstudanteRepository estudanteRepository) {
		super();
		this.estudanteRepository = estudanteRepository;
	}

	//ao cadastrar um estudante, cadastrar tbm seu user.
	//distribuir essas funcionalidades em services.
	//se user vai ser cadastrado aqui e em user service, então colocar isso em um service
	@PostMapping
	@Transactional
	public ResponseEntity<?> postEstudante(@RequestBody PostEstudanteDTO postEstudanteDTO, UriComponentsBuilder uriComponentsBuilder){
		Estudante estudante = new Estudante(postEstudanteDTO);
		estudanteRepository.save(estudante);
		URI uri = uriComponentsBuilder.path("/estudantes/{id}").buildAndExpand(estudante.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReadEstudanteDTO(estudante));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEstudante(@PathVariable Long id){
		Optional<Estudante> optionalEstudante = estudanteRepository.findById(id);
		if(optionalEstudante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new ReadEstudanteDTO(optionalEstudante.get()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putLivro(@PathVariable Long id, @RequestBody PutEstudanteDTO putEstudanteDTO){
		Optional<Estudante> optionalEstudante = estudanteRepository.findById(id);
		if(optionalEstudante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Estudante estudante = optionalEstudante.get();
		estudante.atualizar(putEstudanteDTO);
		return ResponseEntity.ok(new ReadEstudanteDTO(estudante));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		Optional<Estudante> optionalEstudante = estudanteRepository.findById(id);
		if(optionalEstudante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		estudanteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}