package com.renato.biblioteca.service;

import org.springframework.stereotype.Service;

import com.renato.biblioteca.security.controller.dto.RegisterDTO;
import com.renato.biblioteca.security.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public void cadastrarUsuario(RegisterDTO registerDTO) {
		
	}
}
