package com.example.tp_integrador.service;

import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.dtos.producto.ProductoEdit;
import com.example.tp_integrador.entity.Producto;
import com.example.tp_integrador.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDto save(ProductoCreate productoCreate) {
        Producto producto = productoCreate.toEntity();
        producto = productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow( () -> new NullPointerException("No se encontro el producto con id:" + id));
        return ProductoDto.toDto(producto);
    }

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(ProductoDto::toDto).toList();
    }

    @Override
    public ProductoDto update(ProductoEdit productoEdit, Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow( () -> new NullPointerException("No se encontro el producto con id: " + id));
        productoEdit.applyTo(producto, producto.getCategoria());
        productoRepository.save(producto);
        return ProductoDto.toDto(producto);
    }

    @Override
    public void delete(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el producto con id: " + id));
        producto.setEliminado(true);
        productoRepository.save(producto);
    }
}
