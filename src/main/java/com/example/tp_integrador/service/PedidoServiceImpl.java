package com.example.tp_integrador.service;

import com.example.tp_integrador.dtos.detallepedido.DetallePedidoCreate;
import com.example.tp_integrador.dtos.pedido.PedidoDto;
import com.example.tp_integrador.dtos.pedido.PedidoEdit;
import com.example.tp_integrador.entity.Pedido;
import com.example.tp_integrador.entity.Producto;
import com.example.tp_integrador.entity.Usuario;
import com.example.tp_integrador.enums.Estado;
import com.example.tp_integrador.enums.FormaPago;
import com.example.tp_integrador.repository.PedidoRepository;
import com.example.tp_integrador.repository.ProductoRepository;
import com.example.tp_integrador.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository){
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public PedidoDto createPedido(Long usuarioId, List<DetallePedidoCreate> items, String formaPago) {
        // Creamos un nuevo pedido vacio y lo inicializamos con estado pendiente, fecha y la forma de pago que eligio el cliente
        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setFormaPago(FormaPago.valueOf(formaPago.trim().toUpperCase()));

        // buscamos y seteamos el usuario que lo creo al pedido para agregarselo.
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()-> new NullPointerException("No se encontro el usuario con id: " + usuarioId));
        pedido.setUsuario(usuario);
        pedido.setDetalles(new HashSet<>());
        // recorremos la lista de detalles pedidos y validamos/restamos stock
        for (DetallePedidoCreate item : items){
            Producto producto = productoRepository.findById(item.productId()).orElseThrow(() -> new NullPointerException("No se encontro el producto con id: " + item.productId()));
            if (producto.getStock() < item.cantidad()) {
                throw new IllegalArgumentException("No hay suficiente stock para el producto: " + producto.getNombre());
            }
            // si hay stock, se lo restamos al producto y guardamos la nueva cantidad al repositorio
            producto.setStock(producto.getStock() - item.cantidad());
            productoRepository.save(producto);
            // agregamos los detalles pedidos al pedido
            pedido.addDetallePedido(item.cantidad(), producto);
        }
        // calculamos el total con el metodo de la entidad y guardamos el pedido en el repositorio.
        pedido.calcularTotal();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedidoGuardado);
    }

    @Override
    public PedidoDto update(Long id, PedidoEdit pedidoEdit) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el pedido con id: " + id));
        pedidoEdit.applyTo(pedido);
        pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedido);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el pedido con id: " + id));
        return PedidoDto.toDto(pedido);
    }

    @Override
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoDto::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NullPointerException("No se encontro el pedido con id: " + id));
        pedido.setEliminado(true);
        pedidoRepository.save(pedido);
    }
}
