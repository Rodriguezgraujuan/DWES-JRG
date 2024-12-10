package org.example.completeapp.services;

import org.example.completeapp.entities.Author;
import org.example.completeapp.entities.Biography;
import org.example.completeapp.repositories.AuthorRepository;
import org.example.completeapp.repositories.BiographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Se encarga de la lógica de negocio
// Aquí pondremos todas las operaciones que se pueden hacer con los autores
@Service
public class AuthorService {

    // Inyección de dependencias: Spring "inyecta" "inicializa" una instancia de AuthorRepository en esta propiedad
    // Mejor hacerlo con constructor, se recomienda no usar @Autowired por legibilidad del código
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BiographyRepository biographyRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public int sumNumAuthors() {
        return authorRepository.sumNumAuthors();
    }
}
