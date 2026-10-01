package com.utp.qaliruta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String mostrarInicio(Model model) {
        // lista simulada de menus recientes
        List<String> menusRecientes = Arrays.asList(
        "Sangrecita con ensalada", 
            "Higado encebollado con arroz", 
            "Lentejas con pollo"
        );

        // se envia la lista al HTML con el nombre de listaMenus
        model.addAttribute("listaMenus", menusRecientes);

        //th:if para mostrar u ocultar el bloque de oro
        boolean usuarioTieneOro = true; 
        model.addAttribute("esOro", usuarioTieneOro);
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

    @PostMapping("/enviar-contacto")
    public String procesarContacto() {
        return "contacto_exito";
    }

    
}