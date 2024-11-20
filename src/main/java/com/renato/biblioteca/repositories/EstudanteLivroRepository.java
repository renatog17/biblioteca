package com.renato.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.renato.biblioteca.domain.EstudanteLivro;

public interface EstudanteLivroRepository extends JpaRepository<EstudanteLivro, Long>{

	
}
