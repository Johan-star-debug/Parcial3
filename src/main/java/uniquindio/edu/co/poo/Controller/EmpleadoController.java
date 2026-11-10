package uniquindio.edu.co.poo.Controller;
import uniquindio.edu.co.poo.Model.*;

public class EmpleadoController {

    private Panaderia panaderia;

    public EmpleadoController(Panaderia panaderia) {
        if (panaderia == null) {
            throw new IllegalArgumentException(
                    "La panadería no puede ser nula");
        }

        this.panaderia = panaderia;
    }

    public void registrarEmpleado(
            String nombre,
            String documento,
            String codigo,
            int minutos,
            String tipo,
            String caja) {

        if (nombre == null || nombre.trim().isEmpty()
                || documento == null || documento.trim().isEmpty()
                || codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Complete todos los datos del empleado");
        }

        if (minutos < 0) {
            throw new IllegalArgumentException(
                    "Los minutos disponibles no pueden ser negativos");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Seleccione un tipo de empleado");
        }

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
                if (caja == null || caja.trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Ingrese la caja asignada al cajero");
                }

                empleado = new Cajero(
                        nombre, documento, codigo, minutos, caja);
                break;

            default:
                throw new IllegalArgumentException(
                        "Seleccione un tipo de empleado válido");
        }

        panaderia.registrarEmpleado(empleado);
    }
}
