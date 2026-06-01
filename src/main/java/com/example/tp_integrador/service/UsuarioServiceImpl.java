package com.example.tp_integrador.service;

import com.example.tp_integrador.dtos.usuario.UsuarioCreate;
import com.example.tp_integrador.dtos.usuario.UsuarioDto;
import com.example.tp_integrador.dtos.usuario.UsuarioEdit;
import com.example.tp_integrador.entity.Usuario;
import com.example.tp_integrador.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDto save(UsuarioCreate usuarioCreate) {
        Usuario usuario = usuarioCreate.toEntity();
        usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el usuario con id: " + id));
        usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDto::toDto).toList();
    }

    @Override
    public UsuarioDto update(UsuarioEdit usuarioEdit, Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el usuario con id: " + id));
        usuarioEdit.applyTo(usuario);
        usuarioRepository.save(usuario);
        return UsuarioDto.toDto(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el usuario con id: " + id));
        usuario.setEliminado(true);
        usuarioRepository.save(usuario);
    }
}
