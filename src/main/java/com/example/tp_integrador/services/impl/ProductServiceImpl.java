package com.example.tp_integrador.services.impl;

import com.example.tp_integrador.dtos.product.ProductRequestDto;
import com.example.tp_integrador.dtos.product.ProductResponseDto;
import com.example.tp_integrador.dtos.product.ProductEdit;
import com.example.tp_integrador.entities.Product;
import com.example.tp_integrador.repositories.ProductRepository;
import com.example.tp_integrador.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        product = productRepository.save(product);
        return ProductResponseDto.toDto(product);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow( () -> new NullPointerException("Product not found with id:" + id));
        return ProductResponseDto.toDto(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponseDto::toDto).toList();
    }

    @Override
    public ProductResponseDto update(ProductEdit productEdit, Long id) {
        Product product = productRepository.findById(id).orElseThrow( () -> new NullPointerException("Product not found with id: " + id));
        productEdit.applyTo(product, product.getCategory());
        productRepository.save(product);
        return ProductResponseDto.toDto(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("Product not found with id: " + id));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
