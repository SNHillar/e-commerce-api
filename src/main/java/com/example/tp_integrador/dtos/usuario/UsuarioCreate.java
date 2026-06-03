package com.example.tp_integrador.dtos.usuario;

import com.example.tp_integrador.entities.Usuario;
import com.example.tp_integrador.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record UsuarioCreate(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank @Email
        String mail,
        String celular,
        @NotBlank @Size (min = 6) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "La contraseña debe contener al menos una letra y un número")
        String password,
        @NotBlank
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
