package com.example.tp_integrador;

import com.example.tp_integrador.dtos.categoria.CategoriaCreate;
import com.example.tp_integrador.dtos.producto.ProductoCreate;
import com.example.tp_integrador.dtos.usuario.UsuarioCreate;
import com.example.tp_integrador.dtos.detallepedido.DetallePedidoCreate;
import com.example.tp_integrador.services.PedidoService;
import com.example.tp_integrador.services.ProductoService;
import com.example.tp_integrador.services.UsuarioService;
import com.example.tp_integrador.services.CategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    @Override
    public void run(String... args) throws Exception {
        // instanciamos 3 categorias
        categoriaService.save(new CategoriaCreate("Tecnología", "Dispositivos electrónicos"));
        categoriaService.save(new CategoriaCreate("Hogar", "Cosas para la casa"));
        categoriaService.save(new CategoriaCreate("Deportes", "Artículos deportivos"));

        //instanciamos 10 productos y le asignamos categorias
        productoService.save(new ProductoCreate("Teclado Mecánico", 45000.0, "RGB Switch Red", 10, "url_img", 1L));
        productoService.save(new ProductoCreate("Mouse Gamer", 25000.0, "Wireless 16000 DPI", 15, "url_img", 1L));
        productoService.save(new ProductoCreate("Monitor 24'", 180000.0, "144Hz IPS", 5, "url_img", 1L));
        productoService.save(new ProductoCreate("Auriculares", 35000.0, "HyperX Cloud", 8, "url_img", 1L));
        productoService.save(new ProductoCreate("Lámpara LED", 8000.0, "Inteligente RGB", 20, "url_img", 2L));
        productoService.save(new ProductoCreate("Cafetera", 95000.0, "Espresso automática", 4, "url_img", 2L));
        productoService.save(new ProductoCreate("Silla de Escritorio", 120000.0, "Ergonómica", 6, "url_img", 2L));
        productoService.save(new ProductoCreate("Pelota de Fútbol", 22000.0, "N° 5 Reglamentaria", 12, "url_img", 3L));
        productoService.save(new ProductoCreate("Gorra Deportiva", 12000.0, "Protección UV", 14, "url_img", 3L));
        productoService.save(new ProductoCreate("Mochila Trekking", 55000.0, "50 Litros impermeable", 7, "url_img", 3L));

        //creamos 2 usuarios
        usuarioService.save(new UsuarioCreate("Juan", "Pérez", "juan@mail.com", "11223344", "pass123", "USER"));
        usuarioService.save(new UsuarioCreate("Saul", "Gomez", "saul@mail.com", "55667788", "admin456", "ADMIN"));
        // instanciamos 2 pedidos a partir de los detalles
        List<DetallePedidoCreate> itemsPedido1 = List.of(
                new DetallePedidoCreate(1L, 1L, 1), // 1 Teclado
                new DetallePedidoCreate(2L, 2L, 2)  // 2 Mouses
        );
        pedidoService.createPedido(1L, itemsPedido1, "TARJETA");

        List<DetallePedidoCreate> itemsPedido2 = List.of(
                new DetallePedidoCreate(3L, 1L, 1), // 1 Monitor
                new DetallePedidoCreate(4L, 1L, 1)  // 1 Auricular
        );
        pedidoService.createPedido(2L, itemsPedido2, "EFECTIVO");

        List<DetallePedidoCreate> itemsPedido3 = List.of(
                new DetallePedidoCreate(8L, 2L, 2), // 2 Pelotas
                new DetallePedidoCreate(9L, 1L, 1)  // 1 Gorra
        );
        pedidoService.createPedido(1L, itemsPedido3, "TRANSFERENCIA");
    }
}