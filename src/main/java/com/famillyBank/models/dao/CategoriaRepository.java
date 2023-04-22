package com.famillyBank.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famillyBank.models.entity.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);
}
