package org.example.completeapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Anotaciones Lombok
@Getter
@Setter
@NoArgsConstructor

// Anotaciones Hibernate
@Entity
public class Biography {
    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Indicamos la columna content de la tabla, es una cadena, tamaño máximo 1000
    @Column(name = "content", length = 1000)
    private String content;

    // Relación uno a uno con Author
    @OneToOne(cascade = CascadeType.REMOVE) // Indicamos que se eliminará la biografía si se elimina el autor
    @JoinColumn(name = "author_id", referencedColumnName = "id") // Indicamos la columna de la tabla authors que se usará para la relación
    private Author author;

    public Biography(String content, Author author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString(){
        String txt = "";
        if (content != null) {
            txt = content;
        }
        return content;
    }
}