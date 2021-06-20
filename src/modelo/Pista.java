package modelo;

import modelo.OperationModel.Pistas;

public class Pista {
    private final Pistas tipo;
    private boolean ocupada = false;
    private Vuelo avionActual = null;

    public Pista(final Pistas tipo){
        this.tipo = tipo;
    }

    public Pistas getTipo(){ return tipo; }
    public void setAvionActual(Vuelo avion) { this.avionActual = avion; }
    public Vuelo getAvionActual(){ return avionActual; }
    public boolean isOcupada(){ return ocupada; }
    public void setOcupada(boolean ocupada){ this.ocupada = ocupada; }
}
