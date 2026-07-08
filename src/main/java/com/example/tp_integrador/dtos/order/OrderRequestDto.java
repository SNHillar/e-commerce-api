package com.example.tp_integrador.dtos.order;

import com.example.tp_integrador.dtos.orderdetail.OrderDetailRequestDto;

import java.util.List;

public record OrderRequestDto(
        String paymentMethod,
        List<OrderDetailRequestDto> items
) {

}
