package com.renato.biblioteca.controller;

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
	
	@Test
	public void postLivro_sucesso() throws JsonProcessingException, Exception {
		//arrange
		PostLivroDTO postLivroDTO = new PostLivroDTO("123", "123", "123", 123);
		//act
		mockMvc.perform(MockMvcRequestBuilders.post("/livros").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(postLivroDTO)))
				.andExpect(MockMvcResultMatchers.status().isCreated());	
	}
}
