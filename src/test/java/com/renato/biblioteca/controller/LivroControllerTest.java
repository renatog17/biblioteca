package com.renato.biblioteca.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	@Test
    @DisplayName("Deve lançar DataIntegrityViolationException quando livroRepository.save() falhar")
    public void postLivro_deveLancarDataIntegrityViolationException() {
        //arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO("123456789", "Título", "Autor", 1); // Dados válidos
		Livro livro = new Livro(postLivroDTO);
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        //mock
        doThrow(DataIntegrityViolationException.class)
                .when(livroRepository).save(livro);        
        // act & assert
        assertThrows(
                DataIntegrityViolationException.class,
                () -> livroController.postLivro(postLivroDTO, uriComponentsBuilder)
        );
        // verify
        verify(livroRepository, times(1)).save(livro);
    }
	
	@Test
	@DisplayName("Deve obter um livro com sucesso")
	public void getLivro_deveObterUmLivroComSucesso(){
		//arrange
		Livro livro = new Livro("123", "123", "123", 123);
		livro.setId(1L);
		Optional<Livro> optionalLivro = Optional.of(livro);
		//mock
		when(livroRepository.findById(anyLong())).thenReturn(optionalLivro);
		//act & assert
		
		ResponseEntity<?> response = livroController.getLivro(1L);
		
		ReadLivroDTO readLivroDTO = (ReadLivroDTO) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("123", readLivroDTO.autor());
		assertEquals("123", readLivroDTO.titulo());
		assertEquals("123", readLivroDTO.isbn());
		assertEquals(123, readLivroDTO.quantidadeTotal());
		verify(livroRepository, times(1)).findById(anyLong());
	}
	
	@Test
	@DisplayName("Deve obter o status code 404")
	public void getLivro_deveObterStatusCode404() {
		//arrange
		Optional<Livro> optinalLivro = Optional.empty();
		//mock
		when(livroRepository.findById(anyLong())).thenReturn(optinalLivro);
		//act
		ResponseEntity<?> response = livroController.getLivro(4545L); //id ramdomico
		//assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(livroRepository, times(1)).findById(anyLong());
	}
	
	public void deleteLivro_deveDeletarLivro() {
		
	}
	public void deleteLivro_deveRetornarErro404() {
		
	}
}
