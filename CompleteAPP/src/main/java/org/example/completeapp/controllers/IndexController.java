package org.example.completeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    // La ruta para mostrar la página de inicio /
    @GetMapping("/")
    public String index() {
        return "index"; // Devolvemos el nombre de la plantilla, en este caso index.html
    }
}
