package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.dtos.producto.ProductoEdit;
import com.example.tp_integrador.services.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/all")
    public void findAll() {
        productoService.findAll();
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Long id) {
        productoService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(@Valid @PathVariable Long id, @Valid @RequestBody ProductoEdit productoEdit) {
        ProductoDto prodcutoDto = productoService.update(productoEdit, id);
        return ResponseEntity.ok(prodcutoDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable Long id) {
        productoService.delete(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductoDto> create(@Valid @RequestBody ProductoCreate productoCreate) {
        ProductoDto productoDto = productoService.save(productoCreate);
        return ResponseEntity.ok(productoDto);
    }
}