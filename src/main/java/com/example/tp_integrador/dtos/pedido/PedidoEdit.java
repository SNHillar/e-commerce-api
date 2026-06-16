package com.example.tp_integrador.dtos.pedido;

import com.example.tp_integrador.entities.Pedido;
import com.example.tp_integrador.enums.Estado;
import com.example.tp_integrador.enums.FormaPago;

public record PedidoEdit(
        String estado,
        String formaPago
) {
    public void applyTo (Pedido pedido){
        if (estado != null) {
            pedido.setEstado(Estado.valueOf(this.estado.trim().toUpperCase()));
        }
        if (formaPago != null) {
            pedido.setFormaPago(FormaPago.valueOf(this.formaPago.trim().toUpperCase()));
        }
    }
}
