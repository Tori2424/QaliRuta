package com.utp.qaliruta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String mostrarIndex() {
        // Esto le dice a Spring Boot que busque "index.html" en la carpeta templates
        return "index"; 
    }
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; 
    }
    
}