package org.example.completeapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

// Anotaciones Lombok
@Getter
@Setter
@ToString
@NoArgsConstructor

// Anotaciones Hibernate
@Entity
@Table(name = "authors")
public class Author {

    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicamos que el valor de la clave primaria se generará automáticamente
    private Long id;

    // Indicamos la columna first_name de la table, es una cadena, no puede ser nulo
    @Column(name = "name", nullable = false)
    private String name;

    // Indicamos que existe una relación uno a uno con la tabla Biography definida en la clase Biography
    @OneToOne(mappedBy = "author") // Indicamos el atributo de la clase Biography que se usará para la relación
    private Biography biography; // Tendremos un objeto de tipo Biography

    @OneToMany(mappedBy = "author") // Indicamos el atributo de la clase Book que se usará para la relación
    private Set<Book> books = new HashSet<>(); // Atibuto de tipo Set para almacenar los libros que están en la otra tabla

    // Relación muchos a muchos con Publisher
    // La definción de la relación (tabla intermedia) sólo se hace en una de las clases
    @ManyToMany
    @JoinTable(
            name = "author_publisher",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "author_id"),  // Columna que hace referencia al autor
            inverseJoinColumns = @JoinColumn(name = "publisher_id")  // Columna que hace referencia a la editorial
    )
    private Set<Publisher> publishers = new HashSet<>();  // Relación con editoriales

}


