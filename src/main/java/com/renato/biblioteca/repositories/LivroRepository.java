package com.renato.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.biblioteca.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
