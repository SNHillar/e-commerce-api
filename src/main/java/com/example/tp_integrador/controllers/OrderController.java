package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.order.OrderRequestDto;
import com.example.tp_integrador.dtos.order.OrderResponseDto;
import com.example.tp_integrador.dtos.order.OrderEdit;
import com.example.tp_integrador.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody OrderEdit orderEdit){
        OrderResponseDto orderResponseDto = orderService.update(id, orderEdit);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderResponseDto> create(
            @PathVariable Long userId,
            @RequestBody OrderRequestDto orderRequestDto
            ){
        OrderResponseDto orderResponseDto = orderService.createOrder(
                userId,
                orderRequestDto.paymentMethod(),
                orderRequestDto.items()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }
}


