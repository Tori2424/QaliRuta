package com.utp.qaliruta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.utp.qaliruta.model.Categoria;
import com.utp.qaliruta.model.Menu;
import com.utp.qaliruta.service.CategoriaService;
import com.utp.qaliruta.service.MenuService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QalirutaApplicationTests {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MenuService menuService;

    @Test
    @DisplayName("Contexto levanta correctamente")
    void contextLoads() {
        assertNotNull(categoriaService);
        assertNotNull(menuService);
    }

    @Test
    @DisplayName("Operaciones CRUD de Categoría y Menú con Clave Foránea")
    void testCrudOperations() {
        // 1. READ inicial (datos semilla)
        List<Categoria> categorias = categoriaService.listarTodas();
        assertFalse(categorias.isEmpty(), "Deben existir categorías creadas por el DataInitializer");

        List<Menu> menus = menuService.listarTodos();
        assertFalse(menus.isEmpty(), "Deben existir menús creados por el DataInitializer");

        // 2. CREATE (Inserción)
        Categoria nuevaCat = new Categoria("Sopas y Caldos", "Caldos reconstituyentes altos en nutrientes");
        Categoria catGuardada = categoriaService.guardar(nuevaCat);
        assertNotNull(catGuardada.getId(), "La categoría debe tener ID generado por MySQL");

        Menu nuevoMenu = new Menu(
            "Caldo de carnero con quinua",
            12.0,
            14.2,
            "Caldo tradicional con carne de carnero, quinua y hierbas.",
            catGuardada
        );
        Menu menuGuardado = menuService.guardar(nuevoMenu);
        assertNotNull(menuGuardado.getId(), "El menú debe tener ID generado por MySQL");
        assertEquals(catGuardada.getId(), menuGuardado.getCategoria().getId(), "La clave foránea debe coincidir");

        // 3. UPDATE (Actualización)
        menuGuardado.setPrecio(13.5);
        menuGuardado.setContenidoHierro(15.0);
        Menu menuActualizado = menuService.guardar(menuGuardado);
        assertEquals(13.5, menuActualizado.getPrecio());
        assertEquals(15.0, menuActualizado.getContenidoHierro());

        // 4. DELETE (Eliminación)
        Long menuId = menuActualizado.getId();
        menuService.eliminar(menuId);
        assertTrue(menuService.buscarPorId(menuId).isEmpty(), "El menú eliminado ya no debe existir");
    }
}
