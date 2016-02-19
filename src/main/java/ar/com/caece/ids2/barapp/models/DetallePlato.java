package ar.com.caece.ids2.barapp.models;

/**
 * Created by Sebastian Schepens on 18/2/2016.
 */
public class DetallePlato {
    private Integer code;
    private Integer cantidad;

    public DetallePlato(Integer code, Integer cantidad) {
        this.code = code;
        this.cantidad = cantidad;
    }

    public Integer getCode() {
        return this.code;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }
}
