package ar.com.caece.ids2.barapp.facturacion.controllers;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sebastian Schepens on 17/2/2016.
 */
public class DefaultFacturadorTest {
    private static Facturador facturador = new DefaultFacturador();

    // enviarPedido

    // cuentaMesa
    @Test
    public void testCuentaMesa() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.ocuparMesa(paulaner.getCode());
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertNotNull(cuenta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCuentaFailsOnClosedTable() throws Exception {
        Mesa warsteiner = facturador.crearMesa("Warsteiner");
        facturador.cuentaMesa(warsteiner.getCode());
    }

    @Test(expected = TableNotFoundException.class)
    public void testCuentaFailsOnInexistantTable() throws Exception {
        facturador.cuentaMesa(Integer.MAX_VALUE);
    }

    @Test()
    public void testCuentaReturnsPedidosAndTotal() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.withPlato(detallePlato);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.enviarPedido(paulaner.getCode(), pedido);
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertEquals(cuenta.getDetallePlatos().size(), 1);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), detallePlato);
        Long total = Menu.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);
    }

    @Test()
    public void testCuentaReturnsMultiplePedidosWithMultipleItems() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato1 = new DetallePlato(1, 1);
        DetallePlato detallePlato2 = new DetallePlato(2, 1);
        DetalleBebida detalleBebida1 = new DetalleBebida(3, 1);
        DetalleBebida detalleBebida2 = new DetalleBebida(4, 1);
        Pedido pedido1 = new Pedido(paulaner.getCode());
        pedido1.withPlato(detallePlato1);
        pedido1.withBebida(detalleBebida1);
        Pedido pedido2 = new Pedido(paulaner.getCode());
        pedido2.withPlato(detallePlato2);
        pedido2.withBebida(detalleBebida2);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.enviarPedido(paulaner.getCode(), pedido1);
        facturador.enviarPedido(paulaner.getCode(), pedido2);
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertEquals(cuenta.getDetallePlatos().size(), 2);
        Assert.assertEquals(cuenta.getDetalleBebidas().size(), 2);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), pedido1.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(0), pedido1.getDetalleBebidas().get(0));
        Assert.assertEquals(cuenta.getDetallePlatos().get(1), pedido2.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(1), pedido2.getDetalleBebidas().get(0));
        Long total = Menu.obtenerValorPlato(detallePlato1.getCode()) * detallePlato1.getCantidad() +
                Menu.obtenerValorPlato(detallePlato2.getCode()) * detallePlato2.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida1.getCode()) * detalleBebida1.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida2.getCode()) * detalleBebida2.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);
    }

    // obtenerSaldo
    @Test(expected = TableNotFoundException.class)
    public void testObtenerSaldoFailsOnTableNotFound() throws Exception {
        Long totalMesa = facturador.obtenerSaldo(Integer.MAX_VALUE);
    }

    @Test()
    public void testObtenerSaldoReturnsTotal() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.withPlato(detallePlato);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.enviarPedido(paulaner.getCode(), pedido);
        Long totalMesa = facturador.obtenerSaldo(paulaner.getCode());
        Long total = Menu.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad();
        Assert.assertEquals(totalMesa, total);
    }

    @Test()
    public void testObtenerSaldoReturnsTotalWithMultiplePedidosAndItems() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato1 = new DetallePlato(1, 1);
        DetallePlato detallePlato2 = new DetallePlato(2, 1);
        DetalleBebida detalleBebida1 = new DetalleBebida(3, 1);
        DetalleBebida detalleBebida2 = new DetalleBebida(4, 1);
        Pedido pedido1 = new Pedido(paulaner.getCode());
        pedido1.withPlato(detallePlato1);
        pedido1.withBebida(detalleBebida1);
        Pedido pedido2 = new Pedido(paulaner.getCode());
        pedido2.withPlato(detallePlato2);
        pedido2.withBebida(detalleBebida2);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.enviarPedido(paulaner.getCode(), pedido1);
        facturador.enviarPedido(paulaner.getCode(), pedido2);
        Long totalMesa = facturador.obtenerSaldo(paulaner.getCode());
        Long total = Menu.obtenerValorPlato(detallePlato1.getCode()) * detallePlato1.getCantidad() +
                Menu.obtenerValorPlato(detallePlato2.getCode()) * detallePlato2.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida1.getCode()) * detalleBebida1.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida2.getCode()) * detalleBebida2.getCantidad();
        Assert.assertEquals(totalMesa, total);
    }

    @Test(expected = TableNotFoundException.class)
    public void testFacturaFailsOnInexistantTable() throws Exception {
        facturador.cuentaMesa(Integer.MAX_VALUE);
    }
}
