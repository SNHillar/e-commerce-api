package com.example.tp_integrador.dtos.categoria;

import com.example.tp_integrador.entity.Categoria;

public record CategoriaCreate(
        String nombre,
        String descripcion
) {
    public Categoria toEntity(){
        return new Categoria(this.nombre, this.descripcion);
    }
}
