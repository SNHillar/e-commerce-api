package com.example.tp_integrador.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Categoria extends Base{

    @EqualsAndHashCode.Include
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private Set<Producto> productos;

    // CONSTRUCTOR PARA LOS DTOS
    public Categoria(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
