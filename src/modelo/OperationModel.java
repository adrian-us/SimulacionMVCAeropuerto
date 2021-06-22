package modelo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class OperationModel {

    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos){
            System.out.println(vuelo.getNombre());
        }
    }

    public enum Tipo { CARGA, PASAJEROS, PRIVADO }
    public enum Estado { ENTRANTE, ATERRIZANDO, EMBARCANDO, DESEMBARCANDO, FINALIZADO }
    public enum Pistas { LARGA, CORTA, PRECISION }

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

    private SecureRandom random = new SecureRandom();
    private ArrayList<Vuelo> vuelos = new ArrayList<>();
    private ArrayList<Pista> pistas = new ArrayList<>();
    private ArrayList<Puerta> puertas = new ArrayList<>();


    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public ArrayList<Puerta> getPuertas() {
        return puertas;
    }

    public OperationModel(){
        populatePistas();
        populatePuertas();
    }

    private void populatePistas(){
        int id = 0;
        for (Pistas tipo : Pistas.values()) {
            pistas.add(new Pista(tipo, id));
            id++;
        }
    }

    private void populatePuertas(){
        for(int i = 0; i < 5; i++) {
            puertas.add(new Puerta(i));
        }
    }

    public void agregarVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    public boolean isPuertaDisponible(int id){
        for (Puerta puerta : puertas) {
            if(puerta.getId() == id){
                return !puerta.isOcupada();
            }
        }
        return false;
    }

    public boolean isPistaDisponible(int id){
        for (Pista pista : pistas) {
            if(pista.getId() == id){
                return !pista.isOcupada();
            }
        }
        return false;
    }

    //Los siguientes metodos funcionan para retornar la lista de aviones segun su estado

    public ArrayList<Vuelo> vuelosEntrantes(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.ENTRANTE) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }
    public ArrayList<Vuelo> vuelosAterrizandos(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.ATERRIZANDO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }
    public ArrayList<Vuelo> vuelosEmbarcandos(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.EMBARCANDO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }
    public ArrayList<Vuelo> vuelosDesembarcandos(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.DESEMBARCANDO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }
    public ArrayList<Vuelo> vuelosFinalizandos(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.FINALIZADO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }


    private <T extends Enum<?>> T enumAleatorio(Class<T> clase){
        int x = this.random.nextInt(clase.getEnumConstants().length);
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

    public static String[] getAEROLINEAS() {
        return AEROLINEAS;
    }


}
