package com.example.tp_integrador.repository;

import com.example.tp_integrador.entity.Producto;
import com.example.tp_integrador.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
