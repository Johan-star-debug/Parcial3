package uniquindio.edu.co.poo.Model;

public class PanaderoArtesanal extends Panadero {

    public PanaderoArtesanal(String nombre, String documento,
                             String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    @Override
    public int calcularTiempo(TipoPan tipo, int cantidad) {

        int bandejas = (cantidad + 11) / 12;

        if (tipo == TipoPan.TRADICIONAL) {
            return bandejas * 30;
        } else {
            return bandejas * 40;
        }
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