package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Mesa {
    private static final List<Mesa> mesas = Collections.synchronizedList(new ArrayList<>());

    private String name;
    private Integer code;
    private List<Pedido> pedidos;
    private enum State {
        OPEN, CLOSED
    }
    private State state;

    private Mesa(String name) {
        this.name = name;
        this.pedidos = Collections.synchronizedList(new ArrayList<Pedido>());
    }

    public static Mesa getMesa(Integer code) {
        return mesas.get(code);
    }

    public static List<Mesa> getMesas() {
        return mesas;
    }

    public static Mesa createMesa(String name) {
        Mesa mesa = new Mesa(name);
        mesas.add(mesa);
        mesa.code = mesas.indexOf(mesa);
        return mesa;
    }

    public static void destroyMesa(Integer code) {
        mesas.remove(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public boolean isClosed() {
        return !isOpen();
    }

    public void open() {
        state = State.OPEN;
    }

    public List<Cuenta> close() {
        state = State.CLOSED;
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Object pedido) {
        pedidos.remove(pedido);
    }

    public void removePedido(Integer codigo) {
        pedidos.remove(codigo);
    }
}
