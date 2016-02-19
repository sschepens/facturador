package controllers;

import exceptions.DuplicateTableException;
import exceptions.TableNotFoundException;
import models.*;

import java.util.List;

/**
 * Created by Sebastian Schepens on 16/2/2016.
 */
public interface Facturador {
    /**
     * Agrega, modifica o quita un pedido de una mesa de acuerdo al estado del pedido.
     * Lanza {@link IllegalArgumentException} si el {@link Pedido} es invalido.
     */
    public void enviarPedido(Integer mesa, Pedido pedido) throws TableNotFoundException, IllegalArgumentException;

    // Retorna una Cuenta para la mesa especificada
    public Cuenta cuentaMesa(Integer code) throws TableNotFoundException;

    public List<Factura> facturarMesa(Integer code, long limit, float tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException;

    public void ocuparMesa(Integer code) throws TableNotFoundException;

    public void liberarMesa(Integer code) throws TableNotFoundException;

    public Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException;

    public long obtenerSaldo(Integer code) throws TableNotFoundException;

    public List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException;

    public List<Mesa> obtenerMesas() throws TableNotFoundException;

    public Mesa crearMesa(String name) throws DuplicateTableException;

    public Mesa modificarMesa(Integer code, String newName) throws TableNotFoundException, DuplicateTableException;

    public void quitarMesa(Integer code) throws TableNotFoundException;
}
