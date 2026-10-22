package com.utp.qaliruta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del ingrediente es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El contenido de hierro es obligatorio")
    @DecimalMin(value = "0.0", message = "El contenido de hierro no puede ser negativo")
    @Column(name = "contenido_hierro", nullable = false)
    private Double contenidoHierro;

    public Ingrediente() {
    }

    public Ingrediente(String nombre, Double contenidoHierro) {
        this.nombre = nombre;
        this.contenidoHierro = contenidoHierro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getContenidoHierro() {
        return contenidoHierro;
    }

    public void setContenidoHierro(Double contenidoHierro) {
        this.contenidoHierro = contenidoHierro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
