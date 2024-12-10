package org.example.completeapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// Anotaciones Lombok
@Getter
@Setter
@NoArgsConstructor

// Anotaciones Hibernate
@Entity
public class Publisher {

    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicamos que el valor de la clave primaria se generará automáticamente
    private Long id;

    // Indicamos la columna name de la table, es una cadena, no puede ser nulo
    @Column(name = "name", nullable = false)
    private String name;

    // Relación muchos a muchos con Author (Autor)
    @ManyToMany(mappedBy = "publishers")  // Indicamos el atributo de la clase Author que se usará para la relación
    private Set<Author> authors = new HashSet<>();  // Atibuto de tipo Set para almacenar los autores

    public Publisher(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}