package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.dtos.producto.ProductoEdit;
import com.example.tp_integrador.entities.Categoria;
import com.example.tp_integrador.entities.Producto;
import com.example.tp_integrador.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

    Categoria categoria = Categoria.builder()
            .id(1L)
            .nombre("PIZZAS")
            .descripcion("MASA MADRE")
            .build();


    private final Producto PRODUCTO_PREPARED = Producto.builder()
            .id(1L)
            .nombre("Pizza")
            .precio(1500.0)
            .descripcion("Napolitana")
            .stock(10)
            .imagen("example.png")
            .categoria(categoria)
            .detallePedido(new HashSet<>())
            .build();

    private final Producto PRODUCTO_PREPARED_2 = Producto.builder()
            .id(2L)
            .nombre("Hamburguesa")
            .precio(2500.0)
            .descripcion("Cheddar doble")
            .stock(5)
            .imagen("example.png")
            .categoria(categoria)
            .detallePedido(new HashSet<>())
            .build();

    private final ProductoEdit PRODUCTO_EDIT = new ProductoEdit(
            "Pizza",
            2000.0,
            "Calabresa",
            10,
            "example.jpeg",
            1L
    );

    private final ProductoCreate PRODUCTO_CREATE = new ProductoCreate(
            "Empanada",
            1000.0,
            "Carne salada",
            50,
            "empanada.png",
            1L
    );

    @Test
    public void findAll() {
        Mockito.when(productoRepository.findAll()).thenReturn(Arrays.asList(PRODUCTO_PREPARED,  PRODUCTO_PREPARED_2));
        List<ProductoDto> productos = productoServiceImpl.findAll();

        assertEquals(2, productos.size());
        Mockito.verify(productoRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findById() {
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(PRODUCTO_PREPARED));
        ProductoDto productoDto = productoServiceImpl.findById(1L);

        assertEquals(1L, productoDto.id());
        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void update(){
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(PRODUCTO_PREPARED));
        Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenAnswer(i -> i.getArgument(0));

        ProductoDto productoDto = productoServiceImpl.update(PRODUCTO_EDIT, 1L);
        assertEquals(1L, productoDto.id());
        assertEquals("Pizza",productoDto.nombre());
        assertEquals(2000.0,productoDto.precio());
        assertEquals("Calabresa",productoDto.descripcion());
        assertEquals(10,productoDto.stock());

        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(productoRepository, Mockito.times(1)).save(Mockito.any(Producto.class));
    }

    @Test
    void delete() {
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(PRODUCTO_PREPARED));
        Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenAnswer(i -> i.getArgument(0));

        productoServiceImpl.delete(1L);
        assertTrue(PRODUCTO_PREPARED.getEliminado());

        Mockito.verify(productoRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(productoRepository, Mockito.times(1)).save(Mockito.any(Producto.class));
    }

    @Test
    void save(){
        Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenReturn((PRODUCTO_PREPARED));
        productoServiceImpl.save(PRODUCTO_CREATE);

        Mockito.verify(productoRepository, Mockito.times(1)).save(Mockito.any(Producto.class));
    }
}
