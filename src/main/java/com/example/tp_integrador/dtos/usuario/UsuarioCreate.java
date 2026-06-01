package com.example.tp_integrador.dtos.usuario;

import com.example.tp_integrador.entity.Usuario;
import com.example.tp_integrador.enums.Rol;


public record UsuarioCreate(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String password,
        String rol
) {
    public Usuario toEntity(){
        return Usuario.builder()
                .nombre(this.nombre)
                .apellido(this.apellido)
                .mail(this.mail)
                .celular(this.celular)
                .password(this.password)
                .rol(this.rol != null ? Rol.valueOf(this.rol.trim().toUpperCase()) : null)
                .build();
    }
}
