package uniquindio.edu.co.poo.Controller;

import uniquindio.edu.co.poo.Model.*;

public class EmpleadoController {

    private Panaderia panaderia;

    public EmpleadoController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public void registrarEmpleado(
            String nombre,
            String documento,
            String codigo,
            int minutos,
            String tipo,
            String caja) {

        Empleado empleado;

        switch (tipo) {
            case "Panadero artesanal":
                empleado = new PanaderoArtesanal(
                        nombre, documento, codigo, minutos);
                break;

            case "Panadero industrial":
                empleado = new PanaderoIndustrial(
                        nombre, documento, codigo, minutos);
                break;

            case "Cajero":
                empleado = new Cajero(
                        nombre, documento, codigo, minutos, caja);
                break;

            default:
                throw new IllegalArgumentException(
                        "Seleccione un tipo de empleado");
        }

        panaderia.registrarEmpleado(empleado);
    }
}