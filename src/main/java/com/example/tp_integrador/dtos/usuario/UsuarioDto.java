package com.example.tp_integrador.dtos.usuario;

import com.example.tp_integrador.entities.Usuario;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Boolean eliminado,
        String rol
) {
    public static UsuarioDto toDto(Usuario usuario){
        if (usuario == null) return null;
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getMail(),
                usuario.getCelular(),
                usuario.getEliminado(),
                usuario.getRol().toString()
        );
    }
}
