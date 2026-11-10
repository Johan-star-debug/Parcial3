package uniquindio.edu.co.poo.Model;

public abstract class Panadero extends Empleado {

    public Panadero(String nombre, String documento,
                    String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    public abstract int calcularTiempo(TipoPan tipo, int cantidad);
}