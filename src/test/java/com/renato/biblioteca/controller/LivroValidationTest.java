package com.renato.biblioteca.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.renato.biblioteca.controller.dto.PostLivroDTO;
import com.renato.biblioteca.controller.dto.PutLivroDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@SpringBootTest
public class LivroValidationTest {

	@Test
	@DisplayName("Ao cadastrar um livro deve retornar erros de validações quando todos os campos forem null")
	public void postLivro_deveRetornarErrosDevalidacaoQuandoTodosOsCamposForemNull() {
		//arrange
		PostLivroDTOBuilder postLivroDTOBuilder = new PostLivroDTOBuilder();
		PostLivroDTO postLivroDTO = postLivroDTOBuilder
				.withAutorNull().withIsbnNull().withQuantidadeNull().withTituloNull().build();
		//test validation
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PostLivroDTO>> violations = validator.validate(postLivroDTO);
		//assert
		assertEquals(4, violations.size());
		for (ConstraintViolation<PostLivroDTO> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isbn")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("titulo")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("autor")) {
                assertEquals("O autor não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("quantidade")) {
                assertEquals("A quantidade não pode ser nula.", violation.getMessage());
            }
        }
	}
	
	@Test
	@DisplayName("Ao cadastrar um livro deve retornar erros de validações quando todos os campos do tipo String forem vazios e demais campos forem nulo")
	public void postLivro_deveRetornarErrosDevalidacaoQuandoTodosOsCamposStringForemVaziosEDemaisCamposForemNull() {
		//arrange
		PostLivroDTOBuilder postLivroDTOBuilder = new PostLivroDTOBuilder();
		PostLivroDTO postLivroDTO = postLivroDTOBuilder
				.withAutorBlank().withIsbnBlank().withTituloBlank().withQuantidadeNull().build();
		//test validation
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PostLivroDTO>> violations = validator.validate(postLivroDTO);
		//assert
		assertEquals(4, violations.size());
		for (ConstraintViolation<PostLivroDTO> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isbn")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("titulo")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("autor")) {
                assertEquals("O autor não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("quantidade")) {
                assertEquals("A quantidade não pode ser nula.", violation.getMessage());
            }
        }
	}
	
	@Test
	@DisplayName("Ao atualizar um livro deve retornar erros de validações quando todos os campos forem null")
	public void putLivro_deveRetornarErrosDevalidacaoQuandoTodosOsCamposForemNull() {
		//arrange
		PutLivroDTO putLivroDTO = new PutLivroDTO(null, null, null);
		//test validation
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PutLivroDTO>> violations = validator.validate(putLivroDTO);
		//assert
		assertEquals(3, violations.size());
		for (ConstraintViolation<PutLivroDTO> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isbn")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("titulo")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("autor")) {
                assertEquals("O autor não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("quantidade")) {
                assertEquals("A quantidade não pode ser nula.", violation.getMessage());
            }
        }
	}
	
	@Test
	@DisplayName("Ao atualizar um livro deve retornar erros de validações quando todos os campos do tipo String forem vazios e demais campos forem nulo")
	public void putLivro_deveRetornarErrosDevalidacaoQuandoTodosOsCamposStringForemVaziosEDemaisCamposForemNull() {
		//arrange
		PutLivroDTO postLivroDTO = new PutLivroDTO("", "", null);
		//test validation
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PutLivroDTO>> violations = validator.validate(postLivroDTO);
		//assert
		assertEquals(3, violations.size());
		for (ConstraintViolation<PutLivroDTO> violation : violations) {
            if (violation.getPropertyPath().toString().equals("isbn")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("titulo")) {
                assertEquals("O título não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("autor")) {
                assertEquals("O autor não pode ser nulo ou vazio.", violation.getMessage());
            } else if (violation.getPropertyPath().toString().equals("quantidade")) {
                assertEquals("A quantidade não pode ser nula.", violation.getMessage());
            }
        }
	}
}
