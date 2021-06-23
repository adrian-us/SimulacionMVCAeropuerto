package AplicacionControlador;

import AplicacionVentanaControlador.controlador.OperationController;

import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
    public enum Tipo {CARGA, PASAJEROS, PRIVADO}
    public enum Estado {ENTRANTE, ATERRIZANDO, DESEMBARCANDO, FINALIZADO}
    public enum Pistas {LARGA, CORTA, PRECISION}
    private static final ArrayList<Vuelo> vuelos = new ArrayList<>();
    private final ArrayList<Pista> pistas = new ArrayList<>();
    private final ArrayList<Puerta> puertas = new ArrayList<>();
    Servidor servidor;
    ThreadNotificador hiloNotificador;

    public static final class ThreadNotificador extends Thread{
        Servidor refServidor;

        public ThreadNotificador(Servidor refServidor){
            this.refServidor = refServidor;
        }

        @Override
        public void run(){
            boolean running = true;
            while (running){
                try {
                    for (Vuelo vueloAterrizando : vuelosAterrizando()){
                        if (!vueloAterrizando.decrementarTiempoAterrizaje()){
                            try {
                                refServidor.notificarAterrizaje(vueloAterrizando.getId());
                            } catch (IOException ioException) { ioException.printStackTrace(); }
                        }
                    }
                    for (Vuelo vueloDesembarcando : vuelosDesembarcando()){
                        if (!vueloDesembarcando.decrementarTiempoDesembarque()){
                            try {
                                refServidor.notificarDesembarqueCompleto(vueloDesembarcando.getId());
                            } catch (IOException ioException) { ioException.printStackTrace(); }
                        }
                    }
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Controlador(){
        populatePistas();
        populatePuertas();
        servidor = new Servidor(this);
        hiloNotificador = new ThreadNotificador(servidor);
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
        System.out.println("Vuelo recibido : " + vuelo.getNombre() + " [ " + vuelo.getId() + " ]" );
    }


    public static Tipo decifrarTipoVuelo(int indice){
        return Tipo.class.getEnumConstants()[indice];
    }


    public static Estado decifrarEstadoVuelo(int indice) {
        return Estado.class.getEnumConstants()[indice];
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


    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos){
            System.out.println(vuelo.getNombre());
        }
    }

    public String datosVuelos() {
        String respuesta = "";
        ArrayList<Vuelo> vuelos = this.getVuelos();
        for(Vuelo vuelo: vuelos){
            respuesta += vuelo.toString();
        }
        return respuesta;
    }


    public String[] getInformacionEspecificaPistas(int identificador){
        System.out.println("Recopilando informacion de : " + identificador);
        String[] arregloInformacion = new String[4];
        for (Vuelo vuelo : vuelos){
            if (vuelo.getId() == identificador){
                arregloInformacion[0] = vuelo.getNombre();
                arregloInformacion[1] = String.valueOf(vuelo.getId());
                arregloInformacion[2] = vuelo.getTipo().name();
                arregloInformacion[3] = "[ ";
                for (Pista pista : pistas){
                    if (!pista.isOcupada())
                        arregloInformacion[3] += "Pista : " + pista.getId() + ", ";
                }
                arregloInformacion[3] += " ]";
            }
        }
        for (int i = 0; i < arregloInformacion.length; i++){
            System.out.println(arregloInformacion[i]);
        }
        return arregloInformacion;
    }


    public String[] getInformacionEspecificaPuertas(int identificador){
        String[] arregloInformacion = new String[4];
        for (Vuelo vuelo : vuelos){
            if (vuelo.getId() == identificador){
                arregloInformacion[0] = vuelo.getNombre();
                arregloInformacion[1] = String.valueOf(vuelo.getId());
                arregloInformacion[2] = vuelo.getTipo().name();
                arregloInformacion[3] = "[ ";
                for (Puerta puerta : puertas){
                    if (!puerta.isOcupada())
                        arregloInformacion[3] += "Puerta : " + puerta.getId() + ", ";
                }
                arregloInformacion[3] += " ]";
            }
        }
        return arregloInformacion;
    }

    public Pista getPista(int id){
        for (Pista pista : pistas) {
            if (pista.getId() == id)
                return pista;
        }
        return null;
    }

    public Puerta getPuerta(int id){
        for (Puerta puerta : puertas){
            if (puerta.getId() == id)
                return puerta;
        }
        return null;
    }

    public void asignarPista(int id, int idAvion){
        Pista pistaAOcupar = getPista(id);
        if (!pistaAOcupar.isOcupada()){
            pistaAOcupar.setOcupada(true);
            pistaAOcupar.setAvionActual(getVuelos().get(idAvion)); /* ID avion = indice */
        }
    }

    public void desocuparPista(int id){
        getPista(id).setAvionActual(null);
        getPista(id).setOcupada(false);
    }

    public void asignarPuerta(int id, int idAvion){
        Puerta puertaAOcupar = getPuerta(id);
        if (!puertaAOcupar.isOcupada()){
            puertaAOcupar.setOcupada(true);
            puertaAOcupar.setAvionActual(getVuelos().get(idAvion));
        }
    }

    public void desocuparPuerta(int id){
        getPuerta(id).setAvionActual(null);
        getPuerta(id).setOcupada(false);
    }

    /* Getters & Setters */

    public ArrayList<Vuelo> getVuelos() { return vuelos; }

    public ArrayList<Pista> getPistas() { return pistas; }

    public ArrayList<Puerta> getPuertas() { return puertas; }

    //Los siguientes metodos funcionan para retornar la lista de aviones segun su estado

    public ArrayList<Vuelo> vuelosEntrantes(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.ENTRANTE) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }


    public static ArrayList<Vuelo> vuelosAterrizando(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.ATERRIZANDO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }


    public static ArrayList<Vuelo> vuelosDesembarcando(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.DESEMBARCANDO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }


    public ArrayList<Vuelo> vuelosFinalizados(){
        ArrayList<Vuelo> vuelosARetornar = new ArrayList<>();
        for(Vuelo vuelo : vuelos){
            if(vuelo.getEstado() == Estado.FINALIZADO) vuelosARetornar.add(vuelo);
        }
        return vuelosARetornar;
    }

    public String[] recolectarInformacionVueloPistas(int readInt) {
        return new String[4];
    }

    public String[] recolectarInformacionVueloPuertas(int readInt) {
        return new String[4];
    }


    public static void main(String[] args) throws IOException {
        Controlador controlador = new Controlador();
        controlador.hiloNotificador.start();
        controlador.servidor.correrServidor();
    }

}
