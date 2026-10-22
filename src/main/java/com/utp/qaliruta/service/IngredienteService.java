package com.utp.qaliruta.service;

import com.utp.qaliruta.model.Ingrediente;
import com.utp.qaliruta.repository.IngredienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }
}
