package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.categoria.CategoriaDto;
import com.example.tp_integrador.dtos.categoria.CategoriaEdit;
import com.example.tp_integrador.entities.Categoria;
import com.example.tp_integrador.entities.Producto;
import com.example.tp_integrador.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    private CategoriaServiceImpl categoriaServiceImpl;

    private Producto PRODUCTO_TEST = Producto.builder()
            .id(1L)
            .nombre("Napolitana")
            .descripcion("con tomates")
            .build();

    private Producto PRODUCTO_TEST_2 = Producto.builder()
            .id(2L)
            .nombre("Calabresa")
            .descripcion("con tomates")
            .build();

    private final Categoria CATEGORIA_PREPARED = Categoria.builder()
            .id(1L)
            .nombre("Pizzas")
            .descripcion("Masa Madre")
            .productos(Set.of(PRODUCTO_TEST, PRODUCTO_TEST_2))
            .build();

    private final Categoria CATEGORIA_PREPARED_2 = Categoria.builder()
            .id(2L)
            .nombre("Hamburguesas")
            .descripcion("Pan de papa")
            .productos(Set.of(PRODUCTO_TEST))
            .build();

    private final CategoriaEdit CATEGORIA_EDIT = new CategoriaEdit(
            "Empanadas",
            "Criollas"
    );

    @Test
    public void findAll() {
        Mockito.when(categoriaRepository.findAll()).thenReturn(Arrays.asList(CATEGORIA_PREPARED,  CATEGORIA_PREPARED_2));
        List<CategoriaDto> categorias = categoriaServiceImpl.findAll();

        assertEquals(2, categorias.size());
        Mockito.verify(categoriaRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findById() {
        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(CATEGORIA_PREPARED));
        CategoriaDto categoria = categoriaServiceImpl.findById(1L);

        assertEquals(1L, categoria.id());
        Mockito.verify(categoriaRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void save() {

    }

    @Test
    public void update() {
        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(CATEGORIA_PREPARED));
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(CATEGORIA_PREPARED);

        CategoriaDto categoriaDto = categoriaServiceImpl.update(CATEGORIA_EDIT, 1L);

        assertEquals(1L, categoriaDto.id());
        assertEquals("Empanadas", categoriaDto.nombre());
        assertEquals("Criollas", categoriaDto.descripcion());

        Mockito.verify(categoriaRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(categoriaRepository, Mockito.times(1)).save(Mockito.any(Categoria.class));
    }
}
