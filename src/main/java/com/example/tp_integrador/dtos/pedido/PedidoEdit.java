package com.example.tp_integrador.dtos.pedido;

import com.example.tp_integrador.entity.Pedido;
import com.example.tp_integrador.enums.Estado;
import com.example.tp_integrador.enums.FormaPago;

public record PedidoEdit(
        String estado,
        String formaPago
) {
    public void applyTo (Pedido pedido){
        if (estado != null) {
            pedido.setEstado(Estado.valueOf(estado));
        }
        if (formaPago != null) {
            pedido.setFormaPago(FormaPago.valueOf(formaPago));
        }
    }
}
