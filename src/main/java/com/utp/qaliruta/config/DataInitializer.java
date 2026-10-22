package com.utp.qaliruta.config;

import com.utp.qaliruta.model.Categoria;
import com.utp.qaliruta.model.Menu;
import com.utp.qaliruta.repository.CategoriaRepository;
import com.utp.qaliruta.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final MenuRepository menuRepository;

    public DataInitializer(CategoriaRepository categoriaRepository, MenuRepository menuRepository) {
        this.categoriaRepository = categoriaRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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

            menuRepository.save(new Menu(
                "Sangrecita con ensalada",
                10.0,
                29.5,
                "Sangrecita criolla con papita dorada y ensalada fresca con limón.",
                catHemo
            ));

            menuRepository.save(new Menu(
                "Hígado encebollado con arroz",
                9.0,
                8.1,
                "Hígado de res encebollado acompañado de arroz blanco y papas.",
                catHemo
            ));

            menuRepository.save(new Menu(
                "Lentejas con pollo",
                8.0,
                5.4,
                "Lentejas guisadas con trozos de pollo y ensalada cítrica.",
                catMenestras
            ));

            menuRepository.save(new Menu(
                "Chaufa de sangrecita",
                11.0,
                18.5,
                "Arroz chaufa nutritivo salteado con sangrecita y cebolla china.",
                catHemo
            ));

            menuRepository.save(new Menu(
                "Caldo de quinua con bazo",
                7.0,
                12.3,
                "Sopa reconstituyente de quinua andina y bazo de res.",
                catEspeciales
            ));
        }
    }
}
