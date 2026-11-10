package uniquindio.edu.co.poo.Model;

public abstract class Empleado extends Persona {

    private String codigo;
    private int minutosDisponibles;

    public Empleado(String nombre, String documento,
                    String codigo, int minutosDisponibles) {
        super(nombre, documento);
        this.codigo = codigo;
        this.minutosDisponibles = minutosDisponibles;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getMinutosDisponibles() {
        return minutosDisponibles;
    }

    public void descontarMinutos(int minutos) {
        minutosDisponibles -= minutos;
    }

    public void devolverMinutos(int minutos) {
        minutosDisponibles += minutos;
    }
}