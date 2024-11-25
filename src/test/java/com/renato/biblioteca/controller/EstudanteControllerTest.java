package com.renato.biblioteca.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.biblioteca.controller.dto.PostEstudanteDTO;
import com.renato.biblioteca.controller.dto.ReadEstudanteDTO;
import com.renato.biblioteca.domain.Estudante;
import com.renato.biblioteca.repositories.EstudanteRepository;
import com.renato.biblioteca.security.domain.User;
import com.renato.biblioteca.security.repository.UserRepository;

import jakarta.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EstudanteControllerTest {

	@Mock
    private EstudanteRepository estudanteRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private EstudanteController estudanteController;

    @Test
    void testPostEstudante_Success() {
        // Dados de entrada
        PostEstudanteDTO postEstudanteDTO = new PostEstudanteDTO("123", "Renato");
        User user = new User(); 
        user.setId(1L);

        Estudante estudante = new Estudante(postEstudanteDTO);
        estudante.setId(1L);
        estudante.setUser(user);

        // Mockando comportamentos
        when(authentication.getName()).thenReturn("user");
        when(userRepository.findByLogin("user")).thenReturn(user);
        when(estudanteRepository.findByUserId(1L)).thenReturn(Optional.empty());
        when(estudanteRepository.save(any(Estudante.class))).thenReturn(estudante);

        // Execução
        ResponseEntity<?> response = estudanteController.postEstudante(postEstudanteDTO, UriComponentsBuilder.newInstance(), authentication);

        // Verificações
        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ReadEstudanteDTO);
        verify(estudanteRepository, times(1)).save(any(Estudante.class));
    }
}
