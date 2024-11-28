package com.renato.biblioteca.controller.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> tratarErroJdbcSQLIntegrityConstraintViolationException(DataIntegrityViolationException exception){
		 // Mensagem padrão de erro
	    String mensagem = "Erro de integridade no banco de dados. Verifique as restrições de dados.";

	    // Variável para armazenar o nome do campo violado
	    String campo = "campo desconhecido"; // valor padrão

	    // Verifica se a causa da exceção é uma ConstraintViolationException
	    if (exception.getCause() != null && exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
	        org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) exception.getCause();

	        // Obtém o nome da restrição (exemplo: "PUBLIC.UNIQUE_TITULO_INDEX_8")
	        String constraintName = constraintViolationException.getConstraintName();

	        // Tenta extrair o nome do campo a partir do nome da restrição
	        // Considerando que o nome da restrição tenha o padrão 'UNIQUE_<campo>_INDEX'
	        if (constraintName != null) {
	            // Extraímos a parte do campo, assumindo que esteja após "UNIQUE_"
	            String[] partes = constraintName.split("_");

	            if (partes.length > 1) {
	                campo = partes[1]; // Assume que o nome do campo esteja logo após "UNIQUE"
	            } else {
	                // Se não conseguir identificar, usa "campo desconhecido"
	                campo = "campo desconhecido";
	            }
	        }

	        mensagem = "Violação de restrição de integridade no campo: " + campo;
	    }

	    // Cria o objeto de erro utilizando a classe DadosErroValidacao
	    DadosErroValidacao erroValidacao = new DadosErroValidacao(campo.toLowerCase(), mensagem);

	    // Retorna a resposta com status 409 (CONFLICT) e a mensagem de erro
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(erroValidacao);
	}
}
