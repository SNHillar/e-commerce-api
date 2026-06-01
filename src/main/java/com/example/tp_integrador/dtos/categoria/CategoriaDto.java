package com.example.tp_integrador.dtos.categoria;

import com.example.tp_integrador.entities.Categoria;


public record CategoriaDto(
        Long id,
        String nombre,
        String descripcion
) {
    public static CategoriaDto toDto(Categoria categoria){
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}
