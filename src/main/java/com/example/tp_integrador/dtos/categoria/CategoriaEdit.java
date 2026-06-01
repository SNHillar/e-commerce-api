package com.example.tp_integrador.dtos.categoria;

import com.example.tp_integrador.entity.Categoria;

public record CategoriaEdit(
        String nombre,
        String descripcion
) {
    public void applyTo(Categoria categoria){
        if(this.nombre != null){
            categoria.setNombre(nombre);
        }
        if (this.descripcion != null){
            categoria.setDescripcion(descripcion);
        }
    }
}
