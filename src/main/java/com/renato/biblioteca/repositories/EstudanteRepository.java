package com.renato.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.biblioteca.domain.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{

}
