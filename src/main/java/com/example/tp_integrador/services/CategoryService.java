package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.category.CategoryRequestDto;
import com.example.tp_integrador.dtos.category.CategoryResponseDto;
import com.example.tp_integrador.dtos.category.CategoryEdit;

import java.util.List;

public interface CategoryService {
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto findById(Long id);
    public List <CategoryResponseDto> findAll();
    public CategoryResponseDto update (CategoryEdit categoryEdit, Long idCategoria);
    public void deleteById(Long id);
}
