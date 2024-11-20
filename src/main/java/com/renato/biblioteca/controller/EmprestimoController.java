package com.renato.biblioteca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.biblioteca.controller.dto.EmprestimoDTO;

@RestController
@RequestMapping("/emrpestimos")
public class EmprestimoController {

	public ResponseEntity<?> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){
		
		return null;
	}
}
