package com.utp.qaliruta.controller;

import com.utp.qaliruta.model.Menu;
import com.utp.qaliruta.service.CategoriaService;
import com.utp.qaliruta.service.IngredienteService;
import com.utp.qaliruta.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;
    private final CategoriaService categoriaService;
    private final IngredienteService ingredienteService;

    public MenuController(
            MenuService menuService,
            CategoriaService categoriaService,
            IngredienteService ingredienteService) {
        this.menuService = menuService;
        this.categoriaService = categoriaService;
        this.ingredienteService = ingredienteService;
    }

    // LISTAR TODOS (READ)
    @GetMapping
    public String listarMenus(Model model) {
        model.addAttribute("listaMenus", menuService.listarTodos());
        return "menus";
    }

    // FORMULARIO DE NUEVO MENÚ (CREATE)
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        model.addAttribute("listaIngredientes", ingredienteService.listarTodos());
        model.addAttribute("modoEdicion", false);
        return "crear_menu";
    }

    // FORMULARIO DE EDICIÓN (UPDATE - LECTURA DE DATOS A EDITAR)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        var menuOpt = menuService.buscarPorId(id);
        if (menuOpt.isEmpty()) {
            flash.addFlashAttribute("error", "El menú solicitado no existe.");
            return "redirect:/menus";
        }
        model.addAttribute("menu", menuOpt.get());
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        model.addAttribute("listaIngredientes", ingredienteService.listarTodos());
        model.addAttribute("modoEdicion", true);
        return "crear_menu";
    }

    // GUARDAR O ACTUALIZAR (CREATE / UPDATE con VALIDACIONES)
    @PostMapping("/guardar")
    public String guardarMenu(
            @Valid @ModelAttribute("menu") Menu menu,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("listaCategorias", categoriaService.listarTodas());
            model.addAttribute("listaIngredientes", ingredienteService.listarTodos());
            model.addAttribute("modoEdicion", menu.getId() != null);
            return "crear_menu";
        }

        boolean esEdicion = menu.getId() != null;
        menuService.guardar(menu);

        if (esEdicion) {
            flash.addFlashAttribute("mensajeExito", "Menú actualizado correctamente.");
        } else {
            flash.addFlashAttribute("mensajeExito", "Menú registrado con éxito.");
        }

        return "redirect:/menus";
    }

    // ELIMINAR REGISTRO (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarMenu(@PathVariable("id") Long id, RedirectAttributes flash) {
        try {
            menuService.eliminar(id);
            flash.addFlashAttribute("mensajeExito", "Menú eliminado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el menú.");
        }
        return "redirect:/menus";
    }
}
