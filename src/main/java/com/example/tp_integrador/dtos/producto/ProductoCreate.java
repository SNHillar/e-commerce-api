package com.example.tp_integrador.dtos.producto;

import com.example.tp_integrador.entities.Categoria;
import com.example.tp_integrador.entities.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductoCreate(
        @NotBlank
        String nombre,
        @NotBlank
        Double precio,
        String descripcion,
        @NotBlank @Size (min = 1)
        Integer stock,
        String imagen,
        @NotBlank
        Long categoriaId
) {
    public Producto toEntity(){
        return Producto.builder()
                .nombre(this.nombre)
                .precio(this.precio)
                .descripcion(this.descripcion)
                .stock(this.stock)
                .imagen(this.imagen)
                .categoria(Categoria.builder().id(this.categoriaId).build()).build();
    }
}
