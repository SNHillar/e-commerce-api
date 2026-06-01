package com.example.tp_integrador.service;

import com.example.tp_integrador.dtos.categoria.CategoriaCreate;
import com.example.tp_integrador.dtos.categoria.CategoriaDto;
import com.example.tp_integrador.dtos.categoria.CategoriaEdit;

import java.util.List;

public interface CategoriaService {
    public CategoriaDto save(CategoriaCreate categoriaCreate);
    public CategoriaDto findById(Long id);
    public List <CategoriaDto> findAll();
    public CategoriaDto update (CategoriaEdit categoriaEdit, Long idCategoria);
    public void deleteById(Long id);
}
