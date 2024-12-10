package org.example.completeapp.controllers;

import org.example.completeapp.entities.Publisher;
import org.example.completeapp.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// La ruta base para todas las rutas de este controlador
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    // Cuando se cree el controlador, automáticamente se inyectará el servicio
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    // La ruta para listar todos los libros /books
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "publishers/list";
    }

    // La ruta para mostrar el formulario de creación de un libro /books/create
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/form";
    }

    // La ruta para guardar un libro /books/save
    @PostMapping("/save")
    public String save(@ModelAttribute Publisher publisher) {
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    // La ruta para mostrar el formulario de edición de un libro /books/edit/{id}
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findById(id));
        return "publishers/form";
    }

    // La ruta para eliminar un libro /books/delete/{id}
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        publisherService.deleteById(id);
        return "redirect:/publishers";
    }
}

