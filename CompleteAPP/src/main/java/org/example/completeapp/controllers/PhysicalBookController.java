package org.example.completeapp.controllers;

import org.example.completeapp.entities.PhysicalBook;
import org.example.completeapp.repositories.BookRepository;
import org.example.completeapp.services.PhysicalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/physicalBooks")
public class PhysicalBookController {
    private final PhysicalBookService physicalBookService;
    private final BookRepository bookRepository;

    public PhysicalBookController(PhysicalBookService physicalBookService, BookRepository bookRepository) {
        this.physicalBookService = physicalBookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public String list(Model model) {
        List<PhysicalBook> physicalBooks = physicalBookService.findAll();
        model.addAttribute("physicalBooks", physicalBooks);
        return "physicalBooks/list";
    }

     @GetMapping("/create")
    public String create(Model model) {
        //TODO: Ejercicio 3
        return "physicalBooks/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute PhysicalBook physicalBook) {
        physicalBookService.save(physicalBook);
        return "redirect:/physicalBooks";
    }

    //TODO: Ejercicio 4. Haz el controlador edit que recibe un id por ruta

}
