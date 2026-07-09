package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.product.ProductRequestDto;
import com.example.tp_integrador.dtos.product.ProductResponseDto;
import com.example.tp_integrador.dtos.product.ProductEdit;

import java.util.List;


public interface ProductService {
    public ProductResponseDto save(ProductRequestDto productRequestDto);
    public ProductResponseDto findById(Long id);
    public List<ProductResponseDto> findAll();
    public ProductResponseDto update(ProductEdit productEdit, Long id);
    public void delete(Long id);
}
