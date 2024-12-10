package org.example.completeapp.repositories;

import org.example.completeapp.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Query methods: Podemos crear métodos que hagan consultas a la base de datos personalizadas
    // Aquí estamos buscando libros por palabra clave en el título o en el nombre del autor
    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1% OR b.author.name LIKE %?1%")
    List<Book> searchByKeyword(String keyword);

    // Aquí estamos buscando libros por palabra clave en el título o en el nombre del autor y que el precio sea menor o igual al precio que le pasamos
    @Query("SELECT b FROM Book b WHERE b.title LIKE ?1 OR b.author.name LIKE ?1 AND b.price <= ?2")
    List<Book> searchByLowerPrice(String keyword, double price);

    // Aquí estamos sumando el número de páginas de todos los libros
    @Query("SELECT SUM(b.pages) FROM Book b")
    Integer sumNumPages();

}
