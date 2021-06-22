package AplicacionVentanaControlador.modelo;

import AplicacionControlador.Controlador.Estado;
import AplicacionControlador.Controlador.Tipo;
import AplicacionControlador.Controlador.Pistas;
import AplicacionControlador.Pista;


public class OperationModel {

    public OperationModel() {}

    /* Metodos */

    public static Tipo decifrarTipoVuelo(int indice){
        return Tipo.class.getEnumConstants()[indice];
    }

    public static Estado decifrarEstadoVuelo(int indice) {
        return Estado.class.getEnumConstants()[indice];
    }

    public static Pista decifrarTipoPista(int indice) {
        return Pista.class.getEnumConstants()[indice];
    }

    public static boolean asignarPista(Pista pista, Tipo tipoAvion) {
        switch (tipoAvion){
            case CARGA:
                if (pista.getTipo() == Pistas.LARGA || pista.getTipo() == Pistas.CORTA)
                    return true;
                break;
            case PASAJEROS:
                if (pista.getTipo() == Pistas.LARGA)
                    return true;
                break;
            case PRIVADO:
                if (pista.getTipo() == Pistas.CORTA || pista.getTipo() == Pistas.PRECISION)
                    return true;
                break;
        }
        return false;
    }


}
