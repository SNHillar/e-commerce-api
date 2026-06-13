package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.categoria.CategoriaCreate;
import com.example.tp_integrador.dtos.categoria.CategoriaDto;
import com.example.tp_integrador.dtos.categoria.CategoriaEdit;
import com.example.tp_integrador.entities.Categoria;
import com.example.tp_integrador.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity <CategoriaDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity <List<CategoriaDto>> findAll(){
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id, @Valid @RequestBody CategoriaEdit categoria){
        CategoriaDto categoriaDto = categoriaService.update(categoria, id);
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create(@Valid @RequestBody CategoriaCreate categoriaCreate){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaCreate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
