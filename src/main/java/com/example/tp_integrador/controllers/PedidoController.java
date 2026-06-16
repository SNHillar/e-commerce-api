package com.example.tp_integrador.controllers;

import com.example.tp_integrador.dtos.detallepedido.DetallePedidoCreate;
import com.example.tp_integrador.dtos.pedido.PedidoDto;
import com.example.tp_integrador.dtos.pedido.PedidoEdit;
import com.example.tp_integrador.entities.Pedido;
import com.example.tp_integrador.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(@PathVariable Long id, @RequestBody PedidoEdit pedidoEdit){
        PedidoDto pedidoDto = pedidoService.update(id, pedidoEdit);
        return ResponseEntity.ok(pedidoDto);
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<PedidoDto> create(
            @PathVariable Long usuarioId,
            @RequestParam String formaPago,
            @RequestBody List<DetallePedidoCreate> items
    ){
        PedidoDto pedidoDto = pedidoService.createPedido(usuarioId, formaPago, items);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDto);
    }
}


