package com.example.tp_integrador.services.impl;

import com.example.tp_integrador.dtos.category.CategoryRequestDto;
import com.example.tp_integrador.dtos.category.CategoryResponseDto;
import com.example.tp_integrador.dtos.category.CategoryEdit;
import com.example.tp_integrador.entities.Category;
import com.example.tp_integrador.repositories.CategoryRepository;
import com.example.tp_integrador.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDto.toEntity();
        category = categoryRepository.save(category);
        return CategoryResponseDto.toDto(category);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow (() -> new IllegalArgumentException("Category not found with id: " + id));
        return CategoryResponseDto.toDto(category);
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List <Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryResponseDto::toDto).toList();
    }

    @Override
    public CategoryResponseDto update(CategoryEdit categoryEdit, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new IllegalArgumentException("Category not found with id: " + categoryId));
        categoryEdit.applyTo(category);
        category = categoryRepository.save(category);
        return CategoryResponseDto.toDto(category);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
        category.setDeleted(true);
        categoryRepository.save(category);
    }
}
