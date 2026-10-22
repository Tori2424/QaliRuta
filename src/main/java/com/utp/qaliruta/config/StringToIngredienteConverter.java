package com.utp.qaliruta.config;

import com.utp.qaliruta.model.Ingrediente;
import com.utp.qaliruta.repository.IngredienteRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredienteConverter implements Converter<String, Ingrediente> {

    private final IngredienteRepository ingredienteRepository;

    public StringToIngredienteConverter(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public Ingrediente convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(source.trim());
            return ingredienteRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
