package ar.com.caece.ids2.barapp.models;

import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Factura {
    List<Pedido> pedidos;
    Long total;
    public Factura(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.total = 0L; // TODO: calcular total
    }

    public Factura withPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }
}
