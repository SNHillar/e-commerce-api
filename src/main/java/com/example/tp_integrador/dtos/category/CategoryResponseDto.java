package com.example.tp_integrador.dtos.category;

import com.example.tp_integrador.entities.Category;


public record CategoryResponseDto(
        Long id,
        String name,
        String description
) {
    public static CategoryResponseDto toDto(Category category){
        return new CategoryResponseDto(category.getId(), category.getName(), category.getDescription());
    }
}
