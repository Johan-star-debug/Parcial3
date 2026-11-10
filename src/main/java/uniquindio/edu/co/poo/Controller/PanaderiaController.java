package uniquindio.edu.co.poo.Controller;
import uniquindio.edu.co.poo.Model.Empleado;
import uniquindio.edu.co.poo.Model.Panaderia;
import uniquindio.edu.co.poo.Model.Pedido;

import java.util.ArrayList;

public class PanaderiaController {

    private Panaderia panaderia;

    public PanaderiaController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        return panaderia.getEmpleados();
    }

    public ArrayList<Pedido> obtenerPedidos() {
        return panaderia.getPedidos();
    }
}
