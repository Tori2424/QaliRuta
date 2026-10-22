package com.utp.qaliruta.controller;

import com.utp.qaliruta.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaginaController {

    private final MenuService menuService;

    public PaginaController(MenuService menuService) {
        this.menuService = menuService;
    }

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
        // Obtenemos los menús directamente de la base de datos MySQL
        model.addAttribute("listaMenus", menuService.listarRecientes());

        // th:if para mostrar u ocultar el distintivo de oro
        boolean usuarioTieneOro = true;
        model.addAttribute("esOro", usuarioTieneOro);
        return "inicio";
    }

    @GetMapping("/crear_menu")
    public String redirigirCrearMenu() {
        return "redirect:/menus/nuevo";
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
