package org.example.completeapp.repositories;

import org.example.completeapp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Los repositorios son interfaces que extienden de JpaRepository
// JpaRepository tiene métodos para hacer operaciones CRUD hacia la base de datos (y algunas operaciones más)
// Así que antes de implementar un méthod, busca si ya existe en JpaRepository
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Query methods: Podemos crear métodos que hagan consultas a la base de datos personalizadas
    // Spring Data JPA se encarga de implementar el método
    @Query("SELECT count(a) FROM Author a")
    Integer sumNumAuthors();
}