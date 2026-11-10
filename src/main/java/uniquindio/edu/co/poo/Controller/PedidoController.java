package uniquindio.edu.co.poo.Controller;

import uniquindio.edu.co.poo.Model.Panaderia;
import uniquindio.edu.co.poo.Model.Pedido;
import uniquindio.edu.co.poo.Model.TipoPan;

public class PedidoController {

    private Panaderia panaderia;

    public PedidoController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public void registrarPedido(
            String codigo,
            String cliente,
            TipoPan tipo,
            int cantidad) {

        Pedido pedido = new Pedido(
                codigo,
                cliente,
                tipo,
                cantidad
        );

        panaderia.registrarPedido(pedido);
    }

    public void asignarPedido(String codigo) {
        panaderia.asignarPedido(codigo);
    }

    public void cancelarPedido(String codigo) {
        panaderia.cancelarPedido(codigo);
    }
}