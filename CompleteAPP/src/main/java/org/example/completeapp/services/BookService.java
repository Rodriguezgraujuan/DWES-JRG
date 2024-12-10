package org.example.completeapp.services;

import org.example.completeapp.entities.Book;
import org.example.completeapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Se encarga de la lógica de negocio
// Aquí pondremos todas las operaciones que se pueden hacer con los libros
@Service
public class BookService {
    private final BookRepository bookRepository;

    // Inyección de dependencias: Spring "inyecta" "inicializa" una instancia de AuthorRepository en el constructor
    // Mejor hacerlo de esta forma
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchByKeyword(String keyword) {
        return bookRepository.searchByKeyword(keyword);
    }

    public List<Book> searchByLowerPrice(String keyword, double price) {
        return bookRepository.searchByLowerPrice(keyword, price);
    }

    public Integer sumNumPages() {
        return bookRepository.sumNumPages();
    }

}
