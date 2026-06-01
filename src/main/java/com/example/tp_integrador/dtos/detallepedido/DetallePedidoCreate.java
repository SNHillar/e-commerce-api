package com.example.tp_integrador.dtos.detallepedido;

import com.example.tp_integrador.entities.DetallePedido;
import com.example.tp_integrador.entities.Pedido;
import com.example.tp_integrador.entities.Producto;

public record DetallePedidoCreate (
        Long productId,
        Long pedidoId,
        Integer cantidad
){
    public DetallePedido toEntity(){
        return DetallePedido.builder()
                .producto(Producto.builder().id(productId).build())
                .pedido(Pedido.builder().id(pedidoId).build())
                .cantidad(cantidad)
                .build();
    }
}
