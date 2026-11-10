package uniquindio.edu.co.poo.Model;

public class PanaderoIndustrial extends Panadero {

    public PanaderoIndustrial(String nombre, String documento,
                              String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    @Override
    public int calcularTiempo(TipoPan tipo, int cantidad) {

        int lotes = (cantidad + 29) / 30;

        if (tipo == TipoPan.TRADICIONAL) {
            return 20 + (lotes * 25);
        } else {
            return 20 + (lotes * 35);
        }
    }
}