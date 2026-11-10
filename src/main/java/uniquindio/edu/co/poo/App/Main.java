package uniquindio.edu.co.poo.App;

import javax.swing.JOptionPane;
import uniquindio.edu.co.poo.Model.*;
import uniquindio.edu.co.poo.Controller.*;

public class Main {

    private static Panaderia panaderia = new Panaderia();

    private static EmpleadoController empleadoController =
            new EmpleadoController(panaderia);

    private static PedidoController pedidoController =
            new PedidoController(panaderia);

    public static void main(String[] args) {

        String[] opciones = {
                "Registrar empleado",
                "Registrar pedido",
                "Asignar pedido",
                "Cancelar pedido",
                "Consultar empleados",
                "Consultar pedidos",
                "Salir"
        };

        int opcion;

        do {

            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una operacion:",
                    "PANADERIA EL TRIGAL",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            try {

                switch (opcion) {

                    case 0:
                        registrarEmpleado();
                        break;

                    case 1:
                        registrarPedido();
                        break;

                    case 2:
                        asignarPedido();
                        break;

                    case 3:
                        cancelarPedido();
                        break;

                    case 4:
                        consultarEmpleados();
                        break;

                    case 5:
                        consultarPedidos();
                        break;

                    case 6:
                    case -1:
                        JOptionPane.showMessageDialog(
                                null,
                                "Gracias por utilizar Panaderia El Trigal.");
                        break;
                }

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debe ingresar un numero valido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } catch (IllegalArgumentException |
                     IllegalStateException e) {

                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Operacion no realizada",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } while (opcion != 6 && opcion != -1);

        System.exit(0);
    }

    // REGISTRAR EMPLEADO

    private static void registrarEmpleado() {

        String nombre = pedirTexto("Ingrese el nombre:");
        if (nombre == null) return;

        String documento = pedirTexto("Ingrese el documento:");
        if (documento == null) return;

        String codigo = pedirTexto("Ingrese el codigo:");
        if (codigo == null) return;

        String entradaMinutos = pedirTexto(
                "Ingrese los minutos disponibles:");

        if (entradaMinutos == null) return;

        int minutos = Integer.parseInt(entradaMinutos);

        if (minutos < 0) {
            throw new IllegalArgumentException(
                    "Los minutos no pueden ser negativos");
        }

        String[] tipos = {
                "Panadero artesanal",
                "Panadero industrial",
                "Cajero"
        };

        String tipo = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de empleado:",
                "Tipo de empleado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (tipo == null) return;

        String caja = "";

        if (tipo.equals("Cajero")) {

            caja = pedirTexto("Ingrese la caja asignada:");

            if (caja == null) return;
        }

        empleadoController.registrarEmpleado(
                nombre,
                documento,
                codigo,
                minutos,
                tipo,
                caja
        );

        JOptionPane.showMessageDialog(
                null,
                "Empleado registrado correctamente."
        );
    }

    // REGISTRAR PEDIDO

    private static void registrarPedido() {

        String codigo = pedirTexto(
                "Ingrese el codigo del pedido:");

        if (codigo == null) return;

        String cliente = pedirTexto(
                "Ingrese el nombre del cliente:");

        if (cliente == null) return;

        String[] tiposPan = {
                "TRADICIONAL",
                "INTEGRAL"
        };

        String tipoSeleccionado =
                (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el tipo de pan:",
                        "Tipo de pan",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tiposPan,
                        tiposPan[0]
                );

        if (tipoSeleccionado == null) return;

        TipoPan tipoPan = TipoPan.valueOf(tipoSeleccionado);

        String entradaCantidad = pedirTexto(
                "Ingrese la cantidad de panes:");

        if (entradaCantidad == null) return;

        int cantidad = Integer.parseInt(entradaCantidad);

        pedidoController.registrarPedido(
                codigo,
                cliente,
                tipoPan,
                cantidad
        );

        JOptionPane.showMessageDialog(
                null,
                "Pedido registrado correctamente."
        );
    }

    // ASIGNAR PEDIDO

    private static void asignarPedido() {

        String codigo = pedirTexto(
                "Ingrese el codigo del pedido que desea asignar:");

        if (codigo == null) return;

        pedidoController.asignarPedido(codigo);

        Pedido pedido = panaderia.buscarPedido(codigo);

        JOptionPane.showMessageDialog(
                null,
                "Pedido asignado correctamente.\n\n"
                        + "Codigo: " + pedido.getCodigo() + "\n"
                        + "Empleado: " + pedido.getCodigoEmpleado() + "\n"
                        + "Minutos reservados: "
                        + pedido.getMinutosReservados()
        );
    }

    // CANCELAR PEDIDO

    private static void cancelarPedido() {

        String codigo = pedirTexto(
                "Ingrese el codigo del pedido que desea cancelar:");

        if (codigo == null) return;

        pedidoController.cancelarPedido(codigo);

        JOptionPane.showMessageDialog(
                null,
                "Pedido cancelado correctamente.\n"
                        + "Los minutos reservados fueron devueltos."
        );
    }

    // CONSULTAR EMPLEADOS

    private static void consultarEmpleados() {

        if (panaderia.getEmpleados().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay empleados registrados.");

            return;
        }

        StringBuilder informacion = new StringBuilder();

        informacion.append("===== EMPLEADOS =====\n\n");

        for (Empleado empleado : panaderia.getEmpleados()) {

            informacion.append("Nombre: ")
                    .append(empleado.getNombre())
                    .append("\n");

            informacion.append("Documento: ")
                    .append(empleado.getDocumento())
                    .append("\n");

            informacion.append("Codigo: ")
                    .append(empleado.getCodigo())
                    .append("\n");

            informacion.append("Minutos disponibles: ")
                    .append(empleado.getMinutosDisponibles())
                    .append("\n");

            if (empleado instanceof Cajero) {

                Cajero cajero = (Cajero) empleado;

                informacion.append("Caja asignada: ")
                        .append(cajero.getCajaAsignada())
                        .append("\n");

            } else if (empleado instanceof PanaderoArtesanal) {

                informacion.append("Tipo: Panadero artesanal\n");

            } else if (empleado instanceof PanaderoIndustrial) {

                informacion.append("Tipo: Panadero industrial\n");
            }

            informacion.append("-------------------------\n");
        }

        JOptionPane.showMessageDialog(
                null,
                informacion.toString(),
                "Consulta de empleados",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // CONSULTAR PEDIDOS

    private static void consultarPedidos() {

        if (panaderia.getPedidos().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No hay pedidos registrados.");

            return;
        }

        StringBuilder informacion = new StringBuilder();

        informacion.append("===== PEDIDOS =====\n\n");

        for (Pedido pedido : panaderia.getPedidos()) {

            informacion.append("Codigo: ")
                    .append(pedido.getCodigo())
                    .append("\n");

            informacion.append("Cliente: ")
                    .append(pedido.getNombreCliente())
                    .append("\n");

            informacion.append("Tipo de pan: ")
                    .append(pedido.getTipoPan())
                    .append("\n");

            informacion.append("Cantidad: ")
                    .append(pedido.getCantidad())
                    .append("\n");

            informacion.append("Estado: ")
                    .append(pedido.getEstado())
                    .append("\n");

            informacion.append("Empleado asignado: ")
                    .append(pedido.getCodigoEmpleado() == null
                            ? "Sin asignar"
                            : pedido.getCodigoEmpleado())
                    .append("\n");

            informacion.append("Minutos reservados: ")
                    .append(pedido.getMinutosReservados())
                    .append("\n");

            informacion.append("-------------------------\n");
        }

        JOptionPane.showMessageDialog(
                null,
                informacion.toString(),
                "Consulta de pedidos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // SOLICITAR INFORMACION

    private static String pedirTexto(String mensaje) {

        return JOptionPane.showInputDialog(
                null,
                mensaje,
                "Panaderia El Trigal",
                JOptionPane.QUESTION_MESSAGE
        );
    }
}
