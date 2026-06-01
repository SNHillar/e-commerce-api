package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.usuario.UsuarioCreate;
import com.example.tp_integrador.dtos.usuario.UsuarioDto;
import com.example.tp_integrador.dtos.usuario.UsuarioEdit;

import java.util.List;


public interface UsuarioService {
    public UsuarioDto save(UsuarioCreate usuarioCreate);
    public UsuarioDto findById(Long id);
    public List<UsuarioDto> findAll();
    public UsuarioDto update(UsuarioEdit usuarioEdit, Long id);
    public void delete (Long id);
}
