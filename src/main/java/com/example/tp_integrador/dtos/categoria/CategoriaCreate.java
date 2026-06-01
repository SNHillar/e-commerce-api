package com.example.tp_integrador.dtos.categoria;

import com.example.tp_integrador.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCreate(
        @NotBlank
        @Size(min = 3, max = 20, message = "Ingrese un nombre válido.")
        String nombre,
        @NotBlank
        @Size(min = 10, max = 1000, message = "Ingrese una descripción válida.")
        String descripcion
) {
    public Categoria toEntity(){
        return new Categoria(this.nombre, this.descripcion);
    }
}
