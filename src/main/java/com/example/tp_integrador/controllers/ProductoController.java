package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.dtos.producto.ProductoEdit;
import com.example.tp_integrador.services.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductoDto>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(@Valid @PathVariable Long id, @Valid @RequestBody ProductoEdit productoEdit) {
        ProductoDto prodcutoDto = productoService.update(productoEdit, id);
        return ResponseEntity.ok(prodcutoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<ProductoDto> create(@Valid @RequestBody ProductoCreate productoCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productoCreate));
    }
}