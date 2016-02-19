package controllers;

import exceptions.TableNotFoundException;
import models.Cuenta;
import models.Mesa;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sebastian Schepens on 17/2/2016.
 */
public class FacturadorControllerFacturaTest {
    private static FacturadorController facturador = new FacturadorController();

    @Test
    public void testCuentaMesa() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.abrirMesa(paulaner.getCode());
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
}
