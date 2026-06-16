package com.example.tp_integrador.dtos.product;

import com.example.tp_integrador.dtos.category.CategoryDto;
import com.example.tp_integrador.entities.Product;

public record ProductDto(
        Long id,
        String name,
        Double price,
        String description,
        Integer stock,
        String image,
        CategoryDto categoryDto
) {
    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock(),
                product.getImage(),
                product.getCategory() != null ? CategoryDto.toDto(product.getCategory()) : null
        );
    }
}