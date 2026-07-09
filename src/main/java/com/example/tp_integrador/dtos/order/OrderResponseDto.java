package com.example.tp_integrador.dtos.order;

import com.example.tp_integrador.dtos.orderdetail.OrderDetailResponseDto;
import com.example.tp_integrador.entities.Order;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderResponseDto(
        Long id,
        LocalDateTime date,
        String status,
        String paymentMethod,
        Long userId,
        Set<OrderDetailResponseDto> orderDetails
) {
    public static OrderResponseDto toDto (Order order){
        return new OrderResponseDto(
                order.getId(),
                order.getDate(),
                order.getStatus().toString(),
                order.getPayment().toString(),
                order.getUser().getId(),
                order.getDetails().stream()
                        .map(OrderDetailResponseDto::toDto)
                        .collect(Collectors.toSet())
        );
    }
}
