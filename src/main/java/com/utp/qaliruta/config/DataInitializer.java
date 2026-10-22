package com.utp.qaliruta.config;

import com.utp.qaliruta.model.Categoria;
import com.utp.qaliruta.model.Ingrediente;
import com.utp.qaliruta.model.Menu;
import com.utp.qaliruta.repository.CategoriaRepository;
import com.utp.qaliruta.repository.IngredienteRepository;
import com.utp.qaliruta.repository.MenuRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final MenuRepository menuRepository;
    private final IngredienteRepository ingredienteRepository;

    public DataInitializer(
            CategoriaRepository categoriaRepository,
            MenuRepository menuRepository,
            IngredienteRepository ingredienteRepository) {
        this.categoriaRepository = categoriaRepository;
        this.menuRepository = menuRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Inicializar Ingredientes si la tabla está vacía
        if (ingredienteRepository.count() == 0) {
            List<Ingrediente> ingredientes = List.of(
                new Ingrediente("Sangrecita de pollo", 29.5),
                new Ingrediente("Bazo de res", 28.7),
                new Ingrediente("Hígado de res", 8.0),
                new Ingrediente("Relleno / Morcilla criolla", 6.4),
                new Ingrediente("Habas secas", 5.4),
                new Ingrediente("Quinua", 4.6),
                new Ingrediente("Pallar criollo", 4.3),
                new Ingrediente("Frijol negro", 3.6),
                new Ingrediente("Lentejas cocidas", 3.3),
                new Ingrediente("Garbanzos", 2.9),
                new Ingrediente("Espinaca fresca", 2.7),
                new Ingrediente("Acelga", 1.8),
                new Ingrediente("Bonito / Jurel", 1.0),
                new Ingrediente("Arroz blanco", 0.8),
                new Ingrediente("Brócoli", 0.7),
                new Ingrediente("Pepino", 0.3),
                new Ingrediente("Tomate", 0.3)
            );
            ingredienteRepository.saveAll(ingredientes);
        }

        // 2. Inicializar Categorías y Menús si está vacío
        if (categoriaRepository.count() == 0) {
            Categoria catHemo = new Categoria(
                "Alta en Hierro Hemínico",
                "Alimentos de origen animal con máxima absorción de hierro contra la anemia."
            );
            Categoria catMenestras = new Categoria(
                "Menestras y Vegetales",
                "Legumbres ricas en hierro combinadas con facilitadores de absorción."
            );
            Categoria catEspeciales = new Categoria(
                "Especiales Nutritivos",
                "Platos balanceados para niños, madres gestantes y adultos mayores."
            );

            categoriaRepository.save(catHemo);
            categoriaRepository.save(catMenestras);
            categoriaRepository.save(catEspeciales);

            Menu m1 = new Menu("Sangrecita con ensalada", 10.0, 29.5, "Sangrecita criolla con papita dorada y ensalada fresca con limón.", catHemo);
            ingredienteRepository.findByNombre("Sangrecita de pollo").ifPresent(i -> m1.getIngredientes().add(i));
            menuRepository.save(m1);

            Menu m2 = new Menu("Hígado encebollado con arroz", 9.0, 8.8, "Hígado de res encebollado acompañado de arroz blanco y papas.", catHemo);
            ingredienteRepository.findByNombre("Hígado de res").ifPresent(i -> m2.getIngredientes().add(i));
            ingredienteRepository.findByNombre("Arroz blanco").ifPresent(i -> m2.getIngredientes().add(i));
            menuRepository.save(m2);

            Menu m3 = new Menu("Lentejas con espinaca", 8.0, 6.0, "Lentejas guisadas con espinaca fresca y ensalada cítrica.", catMenestras);
            ingredienteRepository.findByNombre("Lentejas cocidas").ifPresent(i -> m3.getIngredientes().add(i));
            ingredienteRepository.findByNombre("Espinaca fresca").ifPresent(i -> m3.getIngredientes().add(i));
            menuRepository.save(m3);

            Menu m4 = new Menu("Chaufa de sangrecita", 11.0, 30.3, "Arroz chaufa nutritivo salteado con sangrecita y cebolla china.", catHemo);
            ingredienteRepository.findByNombre("Sangrecita de pollo").ifPresent(i -> m4.getIngredientes().add(i));
            ingredienteRepository.findByNombre("Arroz blanco").ifPresent(i -> m4.getIngredientes().add(i));
            menuRepository.save(m4);

            Menu m5 = new Menu("Caldo de quinua con bazo", 7.0, 33.3, "Sopa reconstituyente de quinua andina y bazo de res.", catEspeciales);
            ingredienteRepository.findByNombre("Bazo de res").ifPresent(i -> m5.getIngredientes().add(i));
            ingredienteRepository.findByNombre("Quinua").ifPresent(i -> m5.getIngredientes().add(i));
            menuRepository.save(m5);
        }

        // 3. Vincular ingredientes a menús que aún no los tengan asociados en base de datos
        List<Menu> menusSinIngredientes = menuRepository.findAll();
        for (Menu m : menusSinIngredientes) {
            if (m.getIngredientes() == null || m.getIngredientes().isEmpty()) {
                if (m.getNombre().toLowerCase().contains("sangrecita")) {
                    ingredienteRepository.findByNombre("Sangrecita de pollo").ifPresent(i -> m.getIngredientes().add(i));
                }
                if (m.getNombre().toLowerCase().contains("higado") || m.getNombre().toLowerCase().contains("hígado")) {
                    ingredienteRepository.findByNombre("Hígado de res").ifPresent(i -> m.getIngredientes().add(i));
                }
                if (m.getNombre().toLowerCase().contains("bazo")) {
                    ingredienteRepository.findByNombre("Bazo de res").ifPresent(i -> m.getIngredientes().add(i));
                }
                if (m.getNombre().toLowerCase().contains("lenteja")) {
                    ingredienteRepository.findByNombre("Lentejas cocidas").ifPresent(i -> m.getIngredientes().add(i));
                }
                if (m.getNombre().toLowerCase().contains("quinua")) {
                    ingredienteRepository.findByNombre("Quinua").ifPresent(i -> m.getIngredientes().add(i));
                }
                if (m.getNombre().toLowerCase().contains("arroz") || m.getNombre().toLowerCase().contains("chaufa")) {
                    ingredienteRepository.findByNombre("Arroz blanco").ifPresent(i -> m.getIngredientes().add(i));
                }
                // Si es el menú personalizado creado previamente con hierro 33.9 mg
                if (Math.abs(m.getContenidoHierro() - 33.9) < 0.1) {
                    ingredienteRepository.findByNombre("Sangrecita de pollo").ifPresent(i -> m.getIngredientes().add(i));
                    ingredienteRepository.findByNombre("Frijol negro").ifPresent(i -> m.getIngredientes().add(i));
                    ingredienteRepository.findByNombre("Arroz blanco").ifPresent(i -> m.getIngredientes().add(i));
                }
                menuRepository.save(m);
            }
        }
    }
}
