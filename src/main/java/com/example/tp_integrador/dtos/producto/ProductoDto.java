package com.example.tp_integrador.dtos.producto;

import com.example.tp_integrador.dtos.categoria.CategoriaDto;
import com.example.tp_integrador.entity.Categoria;
import com.example.tp_integrador.entity.Producto;

public record ProductoDto(
        Long id,
        String nombre,
        Double precio,
        String descripcion,
        Integer stock,
        String imagen,
        CategoriaDto categoriaDto
) {
    public static ProductoDto toDto(Producto producto) {
        return new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getImagen(),
                producto.getCategoria() != null ? CategoriaDto.toDto(producto.getCategoria()) : null
        );
    }
}