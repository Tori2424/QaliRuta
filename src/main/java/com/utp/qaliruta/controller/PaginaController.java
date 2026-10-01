package com.utp.qaliruta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio";
    }

    @GetMapping("/crear_menu")
    public String mostrarCrearMenu() {
        return "crear_menu";
    }

    @GetMapping("/menus")
    public String mostrarMenus() {
        return "menus";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }



    
}