package com.example.tp_integrador.dtos.producto;

import com.example.tp_integrador.entity.Categoria;
import com.example.tp_integrador.entity.Producto;

public record ProductoEdit(
        String nombre,
        Double precio,
        String descripcion,
        Integer stock,
        String imagen,
        Long categoriaId
) {
    public void applyTo(Producto producto, Categoria categoria){
        if(this.nombre != null){
            producto.setNombre(this.nombre);
        }
        if (this.precio != null) {
            producto.setPrecio(this.precio);
        }
        if (this.descripcion != null) {
            producto.setDescripcion(this.descripcion);
        }
        if (this.stock != null) {
            producto.setStock(this.stock);
        }
        if (this.imagen != null) {
            producto.setImagen(this.imagen);
        }
        if (categoria != null){
            producto.setCategoria(categoria);
        }
    }
}
