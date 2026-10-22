package com.utp.qaliruta.service;

import com.utp.qaliruta.model.Menu;
import com.utp.qaliruta.repository.MenuRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public List<Menu> listarTodos() {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Menu> buscarPorId(Long id) {
        return menuRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarPorCategoria(Long categoriaId) {
        return menuRepository.findByCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public List<Menu> listarRecientes() {
        return menuRepository.findTop4ByOrderByIdDesc();
    }

    @Transactional
    public Menu guardar(Menu menu) {
        return menuRepository.save(menu);
    }

    @Transactional
    public void eliminar(Long id) {
        menuRepository.deleteById(id);
    }
}
