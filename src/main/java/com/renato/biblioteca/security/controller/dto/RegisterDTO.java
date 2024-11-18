package com.renato.biblioteca.security.controller.dto;

import com.renato.biblioteca.security.domain.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}