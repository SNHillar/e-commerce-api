package com.example.tp_integrador.dtos.usuario;

import com.example.tp_integrador.entities.Usuario;
import com.example.tp_integrador.enums.Rol;

public record UsuarioEdit(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String password,
        String rol
) {
    public void applyTo(Usuario usuario){
        if(nombre != null){
            usuario.setNombre(nombre);
        }
        if (apellido != null) {
            usuario.setApellido(apellido);
        }
        if (mail != null) {
            usuario.setMail(mail);
        }
        if (celular != null) {
            usuario.setCelular(celular);
        }
        if (password != null) {
            usuario.setPassword(password);
        }
        if (rol != null) {
            usuario.setRol(Rol.valueOf(rol.trim().toUpperCase()));
        }
    }
}
