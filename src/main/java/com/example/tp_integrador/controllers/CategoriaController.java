package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.categoria.CategoriaCreate;
import com.example.tp_integrador.dtos.categoria.CategoriaDto;
import com.example.tp_integrador.dtos.categoria.CategoriaEdit;
import com.example.tp_integrador.entities.Categoria;
import com.example.tp_integrador.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/{id}")
    public CategoriaDto findById(@Valid @PathVariable Long id){
        return categoriaService.findById(id);
    }

    @GetMapping("/all")
    public List <CategoriaDto> findAll(){
        return categoriaService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@Valid @PathVariable Long id, @Valid @RequestBody CategoriaEdit categoria){
        CategoriaDto categoriaDto = categoriaService.update(categoria, id);
        return ResponseEntity.ok(categoriaDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable Long id){
        categoriaService.deleteById(id);
    }

}
