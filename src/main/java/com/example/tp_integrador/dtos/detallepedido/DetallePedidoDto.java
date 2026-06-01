package com.example.tp_integrador.dtos.detallepedido;

import com.example.tp_integrador.dtos.pedido.PedidoDto;
import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.entity.DetallePedido;
import com.example.tp_integrador.entity.Pedido;
import com.example.tp_integrador.entity.Producto;

public record DetallePedidoDto(
        Long id,
        Integer cantidad,
        Double subtotal,
        ProductoDto productoDto
) {
    public static DetallePedidoDto toDto(DetallePedido detalle){
        if (detalle == null) return null ;
        return new DetallePedidoDto(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                ProductoDto.toDto(detalle.getProducto())
        );
    }
}
