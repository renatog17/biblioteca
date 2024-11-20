package com.renato.biblioteca.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.renato.biblioteca.security.domain.User;
import com.renato.biblioteca.security.repository.UserRepository;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

	private EstudanteRepository estudanteRepository;
	private UserRepository userRepository;
	
	public EstudanteController(EstudanteRepository estudanteRepository, UserRepository userRepository) {
		super();
		this.estudanteRepository = estudanteRepository;
		this.userRepository = userRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> postEstudante(@RequestBody PostEstudanteDTO postEstudanteDTO, UriComponentsBuilder uriComponentsBuilder,
			Authentication authentication){
		
		User user = (User) userRepository.findByLogin(authentication.getName());
		
		Optional<Estudante> findByUserId = estudanteRepository.findByUserId(user.getId());
		if(findByUserId.isPresent()) {
			return ResponseEntity.status(HttpStatusCode.valueOf(409)).build(); 
		}
		
		Estudante estudante = new Estudante(postEstudanteDTO);
		estudante.setUser(user);
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