package uniquindio.edu.co.poo.Model;

public interface IPedido {

    String obtenerCodigoPedido();

    String obtenerCliente();

    TipoPan obtenerTipoPan();

    int obtenerCantidad();

    EstadoPedido obtenerEstado();

    void modificarEstado(EstadoPedido estado);
}
