package org.example.completeapp.controllers;
import org.example.completeapp.entities.Author;
import org.example.completeapp.entities.Book;
import org.example.completeapp.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.completeapp.services.BookService;
import java.util.List;


@Controller
// La ruta base para todas las rutas de este controlador
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    // Cuando se cree el controlador, automáticamente se inyectará el servicio
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // La ruta para listar todos los libros /books
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // La ruta para mostrar el formulario de creación de un libro /books/create
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "books/form";
    }

    // La ruta para guardar un libro /books/save
    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        Author author = authorService.findById(book.getAuthor().getId()); // Nos aseguramos de que el objeto es el de la base de datos
        book.setAuthor(author); // Asociamos el autor al libro
        bookService.save(book);
        return "redirect:/books";
    }

    // La ruta para mostrar el formulario de edición de un libro /books/edit/{id}
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "books/form";
    }

    // La ruta para eliminar un libro /books/delete/{id}
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    // La ruta para filtrar los libros /books/filter?keyword=PALABRA CLAVE
    @GetMapping("/filter")
    public String filterBooks(@RequestParam(required = false) String keyword, Model model) { // Recibimos el parámetro keyword de la URL
        List<Book> books;
        if (keyword != null) {
            books = bookService.searchByKeyword(keyword); // Buscamos los libros por la palabra clave
        } else {
            books = bookService.findAll(); // Si no hay palabra clave, mostramos todos los libros
        }
        model.addAttribute("books", books); // Añadimos al modelo los libros para acceder a la variable books desde la plantilla

        return "books/list"; // Podemos reutilzar la plantilla de listado de libros
    }

    @GetMapping("/details/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books/list"; // Redirigir si el libro no existe
        }

        model.addAttribute("book", book);
        return "books/details";
    }

}

