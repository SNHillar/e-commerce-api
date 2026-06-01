package com.example.tp_integrador.entities;

import com.example.tp_integrador.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true) @ToString
public class Usuario extends Base{

    private String nombre;
    private String apellido;
    @EqualsAndHashCode.Include
    private String mail; // IDENTIDAD

    private String celular;
    private String password;

    @EnumeratedValue
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private Set<Pedido> pedidos;

}
