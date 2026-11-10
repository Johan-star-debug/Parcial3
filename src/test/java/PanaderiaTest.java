import org.junit.jupiter.api.Test;
import uniquindio.edu.co.poo.Model.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanaderiaTest {
/**
 * Clase para Probar que el registro se haga correctamente
 * @author Johan Sanhcez estudiante de la UQ Quindio
 * @since 2026-11
 *Licencia GNU/GPL V3.0
*/
    @Test
    public void testRegistrarPedidoCorrectamente() {

        Panaderia panaderia = new Panaderia();

        PanaderoArtesanal ana = new PanaderoArtesanal(
                "Ana", "123", "A01", 100
        );

        panaderia.registrarEmpleado(ana);

        Pedido pedido = new Pedido(
                "P01", "Restaurante Central",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido);

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(25, pedido.getCantidad());
        assertEquals(100, ana.getMinutosDisponibles());
        assertEquals(1, panaderia.getPedidos().size());
    }
    /**
     * Clase para Probar registro de pedido con codigo repetido
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testRegistrarPedidoConCodigoRepetido() {

        Panaderia panaderia = new Panaderia();

        Pedido pedido1 = new Pedido(
                "P01", "Cliente 1",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido1);

        panaderia.cancelarPedido("P01");

        Pedido pedido2 = new Pedido(
                "P01", "Cliente 2",
                TipoPan.INTEGRAL, 15
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> panaderia.registrarPedido(pedido2)
        );

        assertEquals(1, panaderia.getPedidos().size());

        assertEquals(
                EstadoPedido.CANCELADO,
                panaderia.buscarPedido("P01").getEstado()
        );
    }
    /**
     * Clase para registar un pedido sin productos
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testRegistrarPedidoConCantidadCero() {

        Panaderia panaderia = new Panaderia();

        Pedido pedido = new Pedido(
                "P01", "Cliente 1",
                TipoPan.TRADICIONAL, 0
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> panaderia.registrarPedido(pedido)
        );

        assertEquals(0, panaderia.getPedidos().size());
    }

    /**
     * Clase para asignar a un panadero que tarda menos
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testAsignarAlPanaderoQueTardaMenos() {

        Panaderia panaderia = new Panaderia();

        PanaderoArtesanal ana = new PanaderoArtesanal(
                "Ana", "123", "A01", 100
        );

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "456", "I01", 45
        );

        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(carlos);

        Pedido pedido = new Pedido(
                "P01", "Restaurante Central",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido);

        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstado());

        assertEquals("I01", pedido.getCodigoEmpleado());

        assertEquals(45, pedido.getMinutosReservados());

        assertEquals(0, carlos.getMinutosDisponibles());

        assertEquals(100, ana.getMinutosDisponibles());
    }
    /**
     * Clase para enpaquetar respetando el orden de pedido
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testAsignarRespetandoOrdenEnEmpate() {

        Panaderia panaderia = new Panaderia();

        PanaderoArtesanal ana = new PanaderoArtesanal(
                "Ana", "123", "A01", 80
        );

        PanaderoArtesanal diana = new PanaderoArtesanal(
                "Diana", "456", "A02", 80
        );

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "789", "I01", 54
        );

        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(diana);
        panaderia.registrarEmpleado(carlos);

        Pedido pedido = new Pedido(
                "P01", "Restaurante Central",
                TipoPan.INTEGRAL, 13
        );

        panaderia.registrarPedido(pedido);

        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstado());

        assertEquals("A01", pedido.getCodigoEmpleado());

        assertEquals(80, pedido.getMinutosReservados());

        assertEquals(0, ana.getMinutosDisponibles());

        assertEquals(80, diana.getMinutosDisponibles());

        assertEquals(54, carlos.getMinutosDisponibles());
    }
    /**
     * Clase para asignar al un empleado con tiempo insuficiente
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testAsignarSinTiempoSuficiente() {

        Panaderia panaderia = new Panaderia();

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "123", "I01", 44
        );

        Cajero lucia = new Cajero(
                "Lucia", "456", "C01", 120, "Caja 1"
        );

        panaderia.registrarEmpleado(carlos);
        panaderia.registrarEmpleado(lucia);

        Pedido pedido = new Pedido(
                "P01", "Restaurante Central",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido);

        assertThrows(
                IllegalStateException.class,
                () -> panaderia.asignarPedido("P01")
        );

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());

        assertEquals(44, carlos.getMinutosDisponibles());

        assertEquals(120, lucia.getMinutosDisponibles());
    }

    /**
     * Clase para cancelar un pedido hecho
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */

    @Test
    public void testCancelarPedidoAsignado() {

        Panaderia panaderia = new Panaderia();

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "123", "I01", 120
        );

        panaderia.registrarEmpleado(carlos);

        Pedido pedido1 = new Pedido(
                "P01", "Cliente 1",
                TipoPan.TRADICIONAL, 25
        );

        Pedido pedido2 = new Pedido(
                "P02", "Cliente 2",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido1);
        panaderia.registrarPedido(pedido2);

        panaderia.asignarPedido("P01");
        panaderia.asignarPedido("P02");

        assertEquals(30, carlos.getMinutosDisponibles());

        panaderia.cancelarPedido("P01");

        assertEquals(EstadoPedido.CANCELADO, pedido1.getEstado());

        assertEquals(EstadoPedido.ASIGNADO, pedido2.getEstado());

        assertEquals(75, carlos.getMinutosDisponibles());
    }
    /**
     * Clase para cancelar un pedido pendiente
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testCancelarPedidoPendiente() {

        Panaderia panaderia = new Panaderia();

        PanaderoArtesanal ana = new PanaderoArtesanal(
                "Ana", "123", "A01", 100
        );

        panaderia.registrarEmpleado(ana);

        Pedido pedido = new Pedido(
                "P01", "Cliente 1",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido);

        panaderia.cancelarPedido("P01");

        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());

        assertEquals(100, ana.getMinutosDisponibles());

        assertEquals(1, panaderia.getPedidos().size());
    }
    /**
     * Clase para Probar la cancelación de un pedido que se hizo 2 veces
     * @author Johan Sanhcez estudiante de la UQ Quindio
     * @since 2026-11
     *Licencia GNU/GPL V3.0
     */
    @Test
    public void testCancelarPedidoDosVeces() {

        Panaderia panaderia = new Panaderia();

        PanaderoIndustrial carlos = new PanaderoIndustrial(
                "Carlos", "123", "I01", 120
        );

        panaderia.registrarEmpleado(carlos);

        Pedido pedido = new Pedido(
                "P01", "Cliente 1",
                TipoPan.TRADICIONAL, 25
        );

        panaderia.registrarPedido(pedido);

        panaderia.asignarPedido("P01");

        assertEquals(75, carlos.getMinutosDisponibles());

        panaderia.cancelarPedido("P01");

        assertEquals(120, carlos.getMinutosDisponibles());

        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());

        assertThrows(
                IllegalStateException.class,
                () -> panaderia.cancelarPedido("P01")
        );

        assertEquals(120, carlos.getMinutosDisponibles());

        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
    }
}