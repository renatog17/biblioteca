package com.renato.biblioteca.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.controller.dto.ReadLivroDTO;
import com.renato.biblioteca.domain.Livro;
import com.renato.biblioteca.repositories.LivroRepository;

@SpringBootTest
public class LivroControllerTest {

	@InjectMocks
	private LivroController livroController;
	
	@Mock
	private LivroRepository livroRepository;
	
	@Test
	@DisplayName("Deve criar um livro com sucesso")
	public void postLivro_deveCriarLivroComSucesso() {
		//arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO("123", "Livro Teste", "Autor Teste", 15);
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
		//mock
		doAnswer(invocation -> {
	        Livro livroPersistido = invocation.getArgument(0);
	        livroPersistido.setId(1L); // Simula a atribuição do ID após salvar
	        return null; // Indica que o método `save` não tem retorno
	    }).when(livroRepository).save(Mockito.any(Livro.class));
		
		//act
		ResponseEntity<?> response = livroController.postLivro(postLivroDTO, uriComponentsBuilder);
		ReadLivroDTO readLivroDTO = (ReadLivroDTO) response.getBody();

		//assertions
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("/livros/1", response.getHeaders().getLocation().getPath());
		assertEquals("Livro Teste", readLivroDTO.titulo());
		assertEquals(1, readLivroDTO.id());
		assertEquals("Autor Teste", readLivroDTO.autor());
		
		verify(livroRepository, times(1)).save(any(Livro.class));
	}
}
