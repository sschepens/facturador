package ar.com.caece.ids2.barapp.controllers;

import ar.com.caece.ids2.barapp.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.models.*;

import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class DefaultFacturador implements Facturador {
    @Override
    public void enviarPedido(Integer mesa, Pedido pedido) throws TableNotFoundException, IllegalArgumentException {

    }

    @Override
    public Cuenta cuentaMesa(Integer code) throws TableNotFoundException {
        return null;
    }

    @Override
    public List<Factura> facturarMesa(Integer code, long limit, float tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException {
        return null;
    }

    @Override
    public void ocuparMesa(Integer code) throws TableNotFoundException {

    }

    @Override
    public void liberarMesa(Integer code) throws TableNotFoundException {

    }

    @Override
    public Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException {
        return null;
    }

    @Override
    public long obtenerSaldo(Integer code) throws TableNotFoundException {
        return 0;
    }

    @Override
    public List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException {
        return null;
    }

    @Override
    public List<Mesa> obtenerMesas() throws TableNotFoundException {
        return null;
    }

    @Override
    public Mesa crearMesa(String name) throws DuplicateTableException {
        return null;
    }

    @Override
    public Mesa modificarMesa(Integer code, String newName) throws TableNotFoundException, DuplicateTableException {
        return null;
    }

    @Override
    public void quitarMesa(Integer code) throws TableNotFoundException {

    }
}
