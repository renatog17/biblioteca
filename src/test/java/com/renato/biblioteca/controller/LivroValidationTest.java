package com.renato.biblioteca.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.renato.biblioteca.controller.dto.PostLivroDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@SpringBootTest
public class LivroValidationTest {

	@Test
	@DisplayName("Deve retornar erros de validações quando todos os campos forem null")
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
	@DisplayName("Deve retornar erros de validações quando todos os campos do tipo String forem vazios e demais campos forem nulo")
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
}
