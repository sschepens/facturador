package ar.com.caece.ids2.barapp.facturacion.models;

/**
 * Created by Sebastian Schepens on 18/2/2016.
 */
public class DetalleBebida {
    private Integer code;
    private Integer cantidad;

    public DetalleBebida(Integer code, Integer cantidad) {
        this.code = code;
        this.cantidad = cantidad;
    }

    public Integer getCode() {
        return this.code;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
