package org.example.completeapp.controllers;

import org.example.completeapp.entities.Author;
import org.example.completeapp.entities.Biography;
import org.example.completeapp.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.completeapp.services.AuthorService;

@Controller
// La ruta base para todas las rutas de este controlador
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final PublisherService publisherService;

    // Cuando se cree el controlador, automáticamente se inyectará los servicios que indiquemos
    // Pondremos en el contructor todos los que nos hagan falta en los métodos del controlador
    // Se inicializarán por "inyección de dependencias"
    // Spring se encargará de crear una instancia de AuthorService y PublisherService y pasársela al controlador
    public AuthorController(AuthorService authorService, PublisherService publisherService) {
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    // La ruta para listar todos los autores /authors
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll()); // Añadimos al modelo todos los autores para acceder a la variable authors desde la plantilla
        return "authors/list"; // Devolvemos el nombre de la plantilla, en este caso authors/list.html
    }

    // La ruta para mostrar el formulario de creación de un autor /authors/create
    @GetMapping("/create")
    public String createForm(Model model) {
        Author author = new Author();
        author.setBiography(new Biography()); // Creamos un objeto Biography vacío para que no de error al acceder a los campos en la plantilla
        model.addAttribute("author", author); // Añadimos al modelo el autor para acceder a la variable author desde la plantilla
        model.addAttribute("publishers", publisherService.findAll()); // Añadimos al modelo todas las editoriales para acceder a la variable publishers desde la plantilla y poder seleccionarlas
        return "authors/form"; // Devolvemos el nombre de la plantilla, en este caso authors/form.html
    }

    // La ruta para guardar un autor /authors/save
    @PostMapping("/save") // Indicamos que se ejecutará cuando se haga una petición POST a la ruta /authors/save (no GET porque lleva datos de formulario)
    public String save(@ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) { // Si hay errores en la validación
            return "authors/form"; // Volvemos a mostrar el formulario
        }
        authorService.save(author); // Guardamos el autor en la base de datos
        return "redirect:/authors"; // Redirigimos a la lista de autores
    }

    // La ruta para mostrar el formulario de edición de un autor /authors/edit/{id}
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) { // Recibimos el id del autor a editar, es un parámetro de la ruta
        Author author = authorService.findById(id); // Buscamos el autor por el id
        if (author == null) { // Si no existe el autor
            return "redirect:/authors"; // Redirigimos a la lista de autores
        }
        model.addAttribute("author", author); // Añadimos al modelo el autor para acceder a la variable author desde la plantilla
        model.addAttribute("publishers", publisherService.findAll()); // Añadimos al modelo todas las editoriales para acceder a la variable publishers desde la plantilla y poder seleccionarlas
        return "authors/form"; // Devolvemos el nombre de la plantilla, en este caso authors/form.html
    }

    // La ruta para eliminar un autor /authors/delete/{id}
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorService.deleteById(id); // Eliminamos el autor por el id
        return "redirect:/authors"; // Redirigimos a la lista de autores, no necesitamos plantilla para borrar, borramos y redirigimos
    }

}

