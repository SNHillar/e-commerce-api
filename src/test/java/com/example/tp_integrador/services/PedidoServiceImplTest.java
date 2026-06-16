package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.detallepedido.DetallePedidoCreate;
import com.example.tp_integrador.dtos.pedido.PedidoDto;
import com.example.tp_integrador.dtos.pedido.PedidoEdit;
import com.example.tp_integrador.entities.Pedido;
import com.example.tp_integrador.entities.Producto;
import com.example.tp_integrador.entities.Usuario;
import com.example.tp_integrador.enums.Estado;
import com.example.tp_integrador.enums.FormaPago;
import com.example.tp_integrador.repositories.PedidoRepository;
import com.example.tp_integrador.repositories.ProductoRepository;
import com.example.tp_integrador.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @Mock
    PedidoRepository pedidoRepository;
    @Mock
    ProductoRepository productoRepository;
    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    PedidoServiceImpl pedidoServiceImpl;

    @InjectMocks
    ProductoServiceImpl productoServiceImpl;

    @InjectMocks
    UsuarioServiceImpl usuarioServiceImpl;


    Usuario USER_TEST = Usuario.builder().id(1L).build();

    private final Pedido PEDIDO_PREPARED = Pedido.builder()
            .id(1L)
            .estado(Estado.PENDIENTE)
            .formaPago(FormaPago.TARJETA)
            .detalles(Set.of())
            .usuario(USER_TEST)
            .build();

    private final PedidoEdit PEDIDO_EDIT = new PedidoEdit(
            "cancelado",
            "tarjeta"
    );

    @Test
    void findById() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(PEDIDO_PREPARED));
        PedidoDto pedidoDto = pedidoServiceImpl.findById(1L);

        assertEquals(1L, pedidoDto.id());
        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> pedidoServiceImpl.findById(99L));
    }

    @Test
    void findAll() {
        Mockito.when(pedidoRepository.findAll()).thenReturn(Arrays.asList(PEDIDO_PREPARED));
        List<PedidoDto> pedidoDtos = pedidoServiceImpl.findAll();

        assertEquals(1, pedidoDtos.size());
        Mockito.verify(pedidoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create(){
        Long usuarioId = 1L;
        Long produtoId = 2L;

        DetallePedidoCreate detallePedidoCreate = new DetallePedidoCreate(produtoId, 1L, 2);
        List<DetallePedidoCreate> detallePedidoCreateList = Arrays.asList(detallePedidoCreate);

        Usuario usuario = Usuario.builder()
                .id(usuarioId)
                .nombre("usuario")
                .build();

        Producto producto = Producto.builder()
                .id(produtoId)
                .nombre("producto")
                .precio(1400.0)
                .stock(10)
                .build();

        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        Mockito.when(productoRepository.findById(produtoId)).thenReturn(Optional.of(producto));

        Mockito.when(productoRepository.save(producto)).thenReturn(producto);
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        PedidoDto pedidoDto = pedidoServiceImpl.createPedido(usuarioId, "TARJETA",  detallePedidoCreateList);

        assertNotNull(pedidoDto);
        assertEquals(usuarioId, pedidoDto.usuarioId());
        assertEquals(8, producto.getStock());

        Mockito.verify(usuarioRepository, Mockito.times(1)).findById(usuarioId);
        Mockito.verify(productoRepository, Mockito.times(1)).findById(produtoId);
        Mockito.verify(productoRepository, Mockito.times(1)).save(Mockito.any(Producto.class));
        Mockito.verify(pedidoRepository, Mockito.times(1)).save(Mockito.any(Pedido.class));
    }

    @Test
    void update() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(PEDIDO_PREPARED));
        PedidoDto pedidoDto = pedidoServiceImpl.update(1L, PEDIDO_EDIT);

        assertEquals(1L, pedidoDto.id());
        assertEquals("CANCELADO", pedidoDto.estado());

        Mockito.verify(pedidoRepository, Mockito.times(1)).findById(1L);
    }

}
