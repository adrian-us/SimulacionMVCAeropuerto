package modelo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class OperationModel {
    protected enum Tipo { CARGA, PASAJEROS, PRIVADO }
    protected enum Estado { ENTRANTE, ATERRIZANDO, EMBARCANDO, DESEMBARCANDO, FINALIZADO }
    protected enum Pistas { LARGA, CORTA, PRECISION }
    private static final String[] AEROLINEAS = {
            "Allegiant Air",
            "American Airlines",
            "Delta",
            "InterJet",
            "JetBlue",
            "Jetstar",
            "Qatar Airways",
            "Spirit",
            "Volaris"
    };
    private static final SecureRandom random = new SecureRandom();
    private static final ArrayList<Vuelo> vuelos = new ArrayList<>();

    public OperationModel(){}

    private static <T extends Enum<?>> T enumAleatorio(Class<T> clase){
        int x = random.nextInt(clase.getEnumConstants().length);
        return clase.getEnumConstants()[x];
    }

    public static Tipo decifrarTipoVuelo(int indice){
        return Tipo.class.getEnumConstants()[indice];
    }

    public static Estado decifrarEstadoVuelo(int indice) {
        return Estado.class.getEnumConstants()[indice];
    }

    public static boolean asignarPista(Pistas pista, Tipo tipoAvion){
        switch (tipoAvion){
            case CARGA:
                if (pista == Pistas.LARGA || pista == Pistas.CORTA)
                    return true;
                break;
            case PASAJEROS:
                if (pista == Pistas.LARGA)
                    return true;
                break;
            case PRIVADO:
                if (pista == Pistas.CORTA || pista == Pistas.PRECISION)
                    return true;
                break;
        }
        return false;
    }


    //todo asignar puerta booleano

    public static String[] getAerolineas(){
        return AEROLINEAS;
    }

}
