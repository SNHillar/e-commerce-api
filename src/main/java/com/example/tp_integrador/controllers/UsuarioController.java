package com.example.tp_integrador.controllers;


import com.example.tp_integrador.dtos.usuario.UsuarioCreate;
import com.example.tp_integrador.dtos.usuario.UsuarioDto;
import com.example.tp_integrador.dtos.usuario.UsuarioEdit;
import com.example.tp_integrador.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<UsuarioDto> create(@Valid @RequestBody UsuarioCreate usuarioCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioCreate));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@Valid @RequestBody UsuarioEdit usuarioEdit, @PathVariable Long id){
        return ResponseEntity.ok(usuarioService.update(usuarioEdit, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
