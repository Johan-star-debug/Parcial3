package uniquindio.edu.co.poo.App;

import uniquindio.edu.co.poo.Model.*;

public class Main {

    public static void main(String[] args) {

        // Crear la panaderia
        Panaderia panaderia = new Panaderia();

        // Crear empleados
        PanaderoArtesanal ana = new PanaderoArtesanal(
                "Ana", "123", "A01", 100
        );

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "456", "I01", 120
        );

        Cajero lucia = new Cajero(
                "Lucia", "789", "C01", 120, "Caja 1"
        );

        // Registrar empleados
        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarEmpleado(lucia);

        // Crear pedidos
        Pedido pedido1 = new Pedido(
                "P01", "Restaurante Central",
                TipoPan.TRADICIONAL, 25
        );

        Pedido pedido2 = new Pedido(
                "P02", "Cafeteria Norte",
                TipoPan.INTEGRAL, 40
        );

        // Registrar pedidos
        panaderia.registrarPedido(pedido1);
        panaderia.registrarPedido(pedido2);

        // Asignar pedidos
        panaderia.asignarPedido("P01");
        panaderia.asignarPedido("P02");

        // Mostrar resultados
        System.out.println("===== PANADERIA EL TRIGAL =====");

        for (Pedido pedido : panaderia.getPedidos()) {

            System.out.println("Codigo: " + pedido.getCodigo());

            System.out.println("Cliente: "
                    + pedido.getNombreCliente());

            System.out.println("Tipo de pan: "
                    + pedido.getTipoPan());

            System.out.println("Cantidad: "
                    + pedido.getCantidad());

            System.out.println("Estado: "
                    + pedido.getEstado());

            System.out.println("Empleado asignado: "
                    + pedido.getCodigoEmpleado());

            System.out.println("Minutos reservados: "
                    + pedido.getMinutosReservados());

            System.out.println("--------------------------");
        }

        // Mostrar disponibilidad de empleados
        System.out.println("\n===== DISPONIBILIDAD =====");

        panaderia.getEmpleados().stream().map(empleado -> empleado.getNombre()
                + " - "
                + empleado.getMinutosDisponibles()
                + " minutos disponibles").forEach(System.out::println);

        // Cancelar un pedido
        System.out.println("\n===== CANCELACION =====");

        panaderia.cancelarPedido("P01");

        System.out.println("Pedido P01 cancelado.");

        System.out.println("Minutos disponibles de Ana: "
                + ana.getMinutosDisponibles());

        System.out.println("Minutos disponibles de Carlos: "
                + carlos.getMinutosDisponibles());
    }
}