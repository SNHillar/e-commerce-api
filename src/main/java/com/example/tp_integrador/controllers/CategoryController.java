package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.category.CategoryRequestDto;
import com.example.tp_integrador.dtos.category.CategoryResponseDto;
import com.example.tp_integrador.dtos.category.CategoryEdit;
import com.example.tp_integrador.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity <CategoryResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity <List<CategoryResponseDto>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id, @Valid @RequestBody CategoryEdit category){
        CategoryResponseDto categoryResponseDto = categoryService.update(category, id);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
