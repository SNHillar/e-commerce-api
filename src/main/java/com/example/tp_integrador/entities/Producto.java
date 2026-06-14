package com.example.tp_integrador.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
public class Producto extends Base{

    @EqualsAndHashCode.Include
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;
    private String imagen;

    @Builder.Default
    private Boolean disponible = true;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private Set<DetallePedido> detallePedido;

    // Constructor DTO.
    public Producto(String nombre, Double precio, String descripcion, Integer stock, String imagen, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.categoria = categoria;
    }
}
