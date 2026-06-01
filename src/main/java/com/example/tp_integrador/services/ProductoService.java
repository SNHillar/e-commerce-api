package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.dtos.producto.ProductoEdit;

import java.util.List;


public interface ProductoService {
    public ProductoDto save(ProductoCreate productoCreate);
    public ProductoDto findById(Long id);
    public List<ProductoDto> findAll();
    public ProductoDto update(ProductoEdit productoEdit, Long id);
    public void delete(Long id);
}
