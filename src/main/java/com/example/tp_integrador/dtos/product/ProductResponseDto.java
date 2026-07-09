package com.example.tp_integrador.dtos.product;

import com.example.tp_integrador.dtos.category.CategoryResponseDto;
import com.example.tp_integrador.entities.Product;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        Double price,
        String description,
        Integer stock,
        String image,
        Boolean deleted,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime created_at,
        CategoryResponseDto categoryResponseDto
) {
    public static ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock(),
                product.getImage(),
                product.getDeleted(),
                product.getCreatedAt(),
                product.getCategory() != null ? CategoryResponseDto.toDto(product.getCategory()) : null
        );
    }
}