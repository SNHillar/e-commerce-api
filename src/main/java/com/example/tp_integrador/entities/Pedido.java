package com.example.tp_integrador.entities;

import com.example.tp_integrador.enums.Estado;
import com.example.tp_integrador.enums.FormaPago;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
public class Pedido extends Base implements Calculable{

    @DateTimeFormat
    private LocalDateTime fecha;

    @EnumeratedValue
    private Estado estado;

    @EnumeratedValue
    private FormaPago formaPago;

    @EqualsAndHashCode.Include
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true) // si se borra un pedido, se borran sus detalles. Si se borra un detalle, no se borra el pedido.
    private Set<DetallePedido> detalles;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Override
    public void calcularTotal() {
        detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto){
        return this.detalles.stream()
                .filter(detalle -> detalle.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public void addDetallePedido(Integer cantidad, Producto producto){
        DetallePedido detallePedido = findDetallePedidoByProducto(producto);
        if(detallePedido != null){
            int nuevaCantidad = detallePedido.getCantidad() + cantidad;
            detallePedido.setCantidad(nuevaCantidad);
            detallePedido.setSubtotal(nuevaCantidad * producto.getPrecio());
        } else {
            DetallePedido nuevoDetalle = DetallePedido.builder()
                    .cantidad(cantidad)
                    .subtotal(cantidad * producto.getPrecio())
                    .pedido(this)
                    .producto(producto)
                    .build();
            this.detalles.add(nuevoDetalle);
        }
    }

    public void deleteDetallePedidoByProducto(Producto producto){
        DetallePedido detallePedido = findDetallePedidoByProducto(producto);
        if(detallePedido != null){
            this.detalles.remove(detallePedido);
        } else {
            throw new NullPointerException("No se encontro el detalle del pedido para el producto: " + producto.getNombre());
        }
    }
}
