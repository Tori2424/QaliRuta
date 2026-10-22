package com.utp.qaliruta.controller;

import com.utp.qaliruta.model.Categoria;
import com.utp.qaliruta.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // LISTAR TODAS (READ)
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "categorias";
    }

    // FORMULARIO DE NUEVA CATEGORÍA (CREATE)
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("modoEdicion", false);
        return "categoria_form";
    }

    // FORMULARIO DE EDICIÓN (UPDATE - LECTURA)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        var catOpt = categoriaService.buscarPorId(id);
        if (catOpt.isEmpty()) {
            flash.addFlashAttribute("error", "La categoría solicitada no existe.");
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", catOpt.get());
        model.addAttribute("modoEdicion", true);
        return "categoria_form";
    }

    // GUARDAR O ACTUALIZAR (CREATE / UPDATE con VALIDACIONES)
    @PostMapping("/guardar")
    public String guardarCategoria(
            @Valid @ModelAttribute("categoria") Categoria categoria,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", categoria.getId() != null);
            return "categoria_form";
        }

        boolean esEdicion = categoria.getId() != null;
        categoriaService.guardar(categoria);

        if (esEdicion) {
            flash.addFlashAttribute("mensajeExito", "Categoría actualizada correctamente.");
        } else {
            flash.addFlashAttribute("mensajeExito", "Categoría registrada con éxito.");
        }

        return "redirect:/categorias";
    }

    // ELIMINAR (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id, RedirectAttributes flash) {
        try {
            categoriaService.eliminar(id);
            flash.addFlashAttribute("mensajeExito", "Categoría eliminada correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar la categoría porque tiene menús asociados.");
        }
        return "redirect:/categorias";
    }
}
