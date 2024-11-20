package com.renato.biblioteca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renato.biblioteca.domain.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{

	@Query("SELECT e FROM estudantes e WHERE e.user.id = :userId")
    Optional<Estudante> findByUserId(@Param("userId") Long userId);
}
