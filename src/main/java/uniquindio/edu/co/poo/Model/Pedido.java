package uniquindio.edu.co.poo.Model;

public class Pedido {

    private String codigo;
    private String nombreCliente;
    private TipoPan tipoPan;
    private int cantidad;
    private EstadoPedido estado;

    private String codigoEmpleado;
    private int minutosReservados;

    public Pedido(String codigo, String nombreCliente,
                  TipoPan tipoPan, int cantidad) {

        this.codigo = codigo;
        this.nombreCliente = nombreCliente;
        this.tipoPan = tipoPan;
        this.cantidad = cantidad;
        this.estado = EstadoPedido.PENDIENTE;

        this.codigoEmpleado = null;
        this.minutosReservados = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public TipoPan getTipoPan() {
        return tipoPan;
    }

    public int getCantidad() {
        return cantidad;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public int getMinutosReservados() {
        return minutosReservados;
    }

    public void asignar(String codigoEmpleado, int minutos) {
        this.codigoEmpleado = codigoEmpleado;
        this.minutosReservados = minutos;
        this.estado = EstadoPedido.ASIGNADO;
    }

    public void cancelar() {
        this.estado = EstadoPedido.CANCELADO;
    }
}
