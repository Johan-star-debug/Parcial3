package uniquindio.edu.co.poo.Model;

import java.util.ArrayList;

public class Panaderia {

    private ArrayList<Empleado> empleados;
    private ArrayList<Pedido> pedidos;

    public Panaderia() {
        empleados = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void registrarPedido(Pedido pedido) {

        if (pedido.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        for (Pedido p : pedidos) {
            if (p.getCodigo().equals(pedido.getCodigo())) {
                throw new IllegalArgumentException(
                        "El codigo del pedido ya existe"
                );
            }
        }

        pedidos.add(pedido);
    }

    public void asignarPedido(String codigoPedido) {

        Pedido pedido = buscarPedido(codigoPedido);

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "El pedido no existe"
            );
        }

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException(
                    "El pedido no esta pendiente"
            );
        }

        Panadero mejorPanadero = null;
        int menorTiempo = Integer.MAX_VALUE;

        for (Empleado empleado : empleados) {

            if (empleado instanceof Panadero) {

                Panadero panadero = (Panadero) empleado;

                int tiempo = panadero.calcularTiempo(
                        pedido.getTipoPan(),
                        pedido.getCantidad()
                );

                if (tiempo <= panadero.getMinutosDisponibles()
                        && tiempo < menorTiempo) {

                    menorTiempo = tiempo;
                    mejorPanadero = panadero;
                }
            }
        }

        if (mejorPanadero == null) {
            throw new IllegalStateException(
                    "No hay panaderos disponibles"
            );
        }

        mejorPanadero.descontarMinutos(menorTiempo);

        pedido.asignar(
                mejorPanadero.getCodigo(),
                menorTiempo
        );
    }

    public void cancelarPedido(String codigoPedido) {

        Pedido pedido = buscarPedido(codigoPedido);

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "El pedido no existe"
            );
        }

        if (pedido.getEstado() == EstadoPedido.CANCELADO) {
            throw new IllegalStateException(
                    "El pedido ya esta cancelado"
            );
        }

        if (pedido.getEstado() == EstadoPedido.ASIGNADO) {

            for (Empleado empleado : empleados) {

                if (empleado.getCodigo().equals(
                        pedido.getCodigoEmpleado())) {

                    empleado.devolverMinutos(
                            pedido.getMinutosReservados()
                    );

                    break;
                }
            }
        }

        pedido.cancelar();
    }

    public Pedido buscarPedido(String codigo) {

        for (Pedido pedido : pedidos) {

            if (pedido.getCodigo().equals(codigo)) {
                return pedido;
            }
        }

        return null;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}
