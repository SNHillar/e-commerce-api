package com.example.tp_integrador.services;

import com.example.tp_integrador.dtos.detallepedido.DetallePedidoCreate;
import com.example.tp_integrador.dtos.pedido.PedidoDto;
import com.example.tp_integrador.dtos.pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {

    public PedidoDto createPedido (Long usuarioId, String formaPago, List<DetallePedidoCreate> items);
    public PedidoDto update(Long id, PedidoEdit pedidoEdit);
    public PedidoDto findById(Long id);
    public List<PedidoDto> findAll();
    public void delete(Long id);
}
