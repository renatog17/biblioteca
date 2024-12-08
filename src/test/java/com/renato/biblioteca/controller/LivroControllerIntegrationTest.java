package com.renato.biblioteca.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.domain.Livro;
import com.renato.biblioteca.repositories.LivroRepository;

import jakarta.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LivroControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private LivroRepository livroRepository;
	
	@Test
	public void postLivro_sucesso() throws JsonProcessingException, Exception {
		//arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO("123", "123", "123", 123);
		//act
		mockMvc.perform(MockMvcRequestBuilders.post("/livros").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(postLivroDTO)))
				.andExpect(MockMvcResultMatchers.status().isCreated());	
	}
	@Test
	public void postLivro_DeveRetornarErroDeValidacaoCamposNulo() throws JsonProcessingException, Exception {
		//arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO(null, null, null, null);
		//act
		mockMvc.perform(MockMvcRequestBuilders.post("/livros").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(postLivroDTO)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
	            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4))) // Verifica 4 erros no array
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'autor')].mensagem").value("O autor não pode ser nulo ou vazio."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'quantidade')].mensagem").value("A quantidade não pode ser nula."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'isbn')].mensagem").value("O título não pode ser nulo ou vazio."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'titulo')].mensagem").value("O título não pode ser nulo ou vazio."));
	}
	@Test
	public void postLivro_DeveRetornarErroDeValidacaoCamposEmpty() throws JsonProcessingException, Exception {
		//arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO("", "", "", null);
		//act
		mockMvc.perform(MockMvcRequestBuilders.post("/livros").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(postLivroDTO)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
	            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4))) // Verifica 4 erros no array
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'autor')].mensagem").value("O autor não pode ser nulo ou vazio."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'quantidade')].mensagem").value("A quantidade não pode ser nula."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'isbn')].mensagem").value("O título não pode ser nulo ou vazio."))
	            .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.campo == 'titulo')].mensagem").value("O título não pode ser nulo ou vazio."));
	}
	@Test
	public void getLivro_deveRetornarUmDTOLivroPeloIdDado() throws Exception {
		//arrange
		Livro livro = new Livro("123", "123", "123", 123);
		Livro save = livroRepository.save(livro);
		//act
		mockMvc.perform(MockMvcRequestBuilders.get("/livros/"+save.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(save.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.autor").value("123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantidadeTotal").value(123));
	}
	@Test
	public void getLivro_deveRetornarErro404() throws Exception {
		//act
		mockMvc.perform(MockMvcRequestBuilders.get("/livros/"+1))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	//put id não encontrado
	//put id inválido
	//put erro de validação
	//put happy path
	//delete id não encontrado
	//delete happy path
}
