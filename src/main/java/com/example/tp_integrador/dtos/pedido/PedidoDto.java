package com.example.tp_integrador.dtos.pedido;

import com.example.tp_integrador.dtos.detallepedido.DetallePedidoDto;
import com.example.tp_integrador.entity.DetallePedido;
import com.example.tp_integrador.entity.Pedido;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record PedidoDto(
        Long id,
        LocalDateTime fecha,
        String estado,
        String formaPago,
        Long usuarioId,
        Set<DetallePedidoDto>detallePedido
) {
    public static PedidoDto toDto (Pedido pedido){
        return new PedidoDto(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado().toString(),
                pedido.getFormaPago().toString(),
                pedido.getUsuario().getId(),
                pedido.getDetalles().stream()
                        .map(DetallePedidoDto::toDto)
                        .collect(Collectors.toSet())
        );
    }
}
