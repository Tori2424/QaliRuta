package com.utp.qaliruta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del menú es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.50", message = "El precio mínimo debe ser S/ 0.50")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El contenido de hierro es obligatorio")
    @DecimalMin(value = "0.1", message = "El contenido de hierro debe ser al menos 0.1 mg Fe")
    @Column(name = "contenido_hierro", nullable = false)
    private Double contenidoHierro;

    @NotBlank(message = "La descripción o detalle de ingredientes es obligatorio")
    @Size(min = 5, max = 300, message = "La descripción debe tener entre 5 y 300 caracteres")
    @Column(nullable = false, length = 300)
    private String descripcion;

    @NotNull(message = "Debe seleccionar una categoría")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Menu() {
    }

    public Menu(String nombre, Double precio, Double contenidoHierro, String descripcion, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.contenidoHierro = contenidoHierro;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getContenidoHierro() {
        return contenidoHierro;
    }

    public void setContenidoHierro(Double contenidoHierro) {
        this.contenidoHierro = contenidoHierro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
