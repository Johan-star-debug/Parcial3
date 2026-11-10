package uniquindio.edu.co.poo.Model;

public class Cajero extends Empleado {

    private String cajaAsignada;

    public Cajero(String nombre, String documento,
                  String codigo, int minutosDisponibles,
                  String cajaAsignada) {

        super(nombre, documento, codigo, minutosDisponibles);
        this.cajaAsignada = cajaAsignada;
    }

    public String getCajaAsignada() {
        return cajaAsignada;
    }

    @Override
    public String obtenerCodigo() {
        return "";
    }

    @Override
    public int obtenerMinutosDisponibles() {
        return 0;
    }

    @Override
    public void reservarMinutos(int minutos) {

    }
}
