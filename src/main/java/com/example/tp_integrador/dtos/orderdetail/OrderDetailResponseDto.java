package com.example.tp_integrador.dtos.orderdetail;

import com.example.tp_integrador.dtos.product.ProductResponseDto;
import com.example.tp_integrador.entities.OrderDetail;

public record OrderDetailResponseDto(
        Long id,
        Integer quantity,
        Double subtotal,
        ProductResponseDto productResponseDto
) {
    public static OrderDetailResponseDto toDto(OrderDetail detail){
        if (detail == null) return null ;
        return new OrderDetailResponseDto(
                detail.getId(),
                detail.getQuantity(),
                detail.getSubtotal(),
                ProductResponseDto.toDto(detail.getProduct())
        );
    }
}
