package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Cuenta {
    List<DetallePlato> detallePlatos = new ArrayList<>();
    List<DetalleBebida> detalleBebidas = new ArrayList<>();
    Long total = 0L;

    public Cuenta(List<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            for (DetallePlato detallePlato : p.getDetallePlatos()) {
                this.detallePlatos.add(detallePlato);
                this.total += Menu.getItemPrice(detallePlato);
            }
            for (DetalleBebida detalleBebida : p.getDetalleBebidas()) {
                this.detalleBebidas.add(detalleBebida);
                this.total += Menu.getItemPrice(detalleBebida);
            }
        }
    }

    public Long getTotal() {
        return total;
    }

    public List<DetallePlato> getDetallePlatos() {
        return detallePlatos;
    }

    public List<DetalleBebida> getDetalleBebidas() {
        return detalleBebidas;
    }
}
