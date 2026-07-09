package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.orderdetail.OrderDetailRequestDto;
import com.example.tp_integrador.dtos.order.OrderResponseDto;
import com.example.tp_integrador.dtos.order.OrderEdit;

import java.util.List;

public interface OrderService {

    public OrderResponseDto createOrder(Long userId, String paymentMethod, List<OrderDetailRequestDto> items);
    public OrderResponseDto update(Long id, OrderEdit orderEdit);
    public OrderResponseDto findById(Long id);
    public List<OrderResponseDto> findAll();
    public void delete(Long id);
}
