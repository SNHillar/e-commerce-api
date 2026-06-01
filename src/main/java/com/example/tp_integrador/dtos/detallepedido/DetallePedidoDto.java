package com.example.tp_integrador.dtos.detallepedido;

import com.example.tp_integrador.dtos.producto.ProductoDto;
import com.example.tp_integrador.entities.DetallePedido;

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
