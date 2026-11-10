package uniquindio.edu.co.poo.Model;

public interface IEmpleado {

    String obtenerCodigo();

    int obtenerMinutosDisponibles();

    void reservarMinutos(int minutos);

    void devolverMinutos(int minutos);
}
