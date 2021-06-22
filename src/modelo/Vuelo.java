package modelo;

import java.security.SecureRandom;
import modelo.OperationModel.Estado;
import modelo.OperationModel.Tipo;


public class Vuelo {
    private final String nombre;
    private final int id;
    private final boolean atrasado;
    private int tiempoLlegada;
    private int tiempoAterrizaje;
    private int tiempoEmbarque;
    private int tiempoDesembarque;

    //TODO: Hacer que el avion cambie a atrasado cuando se cumple cierto numero por la suma de todos los tiempos
    private int tiempoParaEstarAtrasado;

    private Estado estado;
    private final Tipo tipo;
    private static final SecureRandom secureRandom = new SecureRandom();

    public Vuelo(final Tipo tipo,
                 final int id,
                 final String nombre,
                 final int tiempoLlegada,
                 final int tiempoAterrizaje,
                 final int tiempoEmbarque,
                 final int tiempoDesembarque){
        this.tipo = tipo;
        this.id = id;
        this.nombre = nombre;
        this.estado = Estado.ENTRANTE;
        atrasado = randomBoolean();
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoAterrizaje = tiempoAterrizaje;
        this.tiempoEmbarque = tiempoEmbarque;
        this.tiempoDesembarque = tiempoDesembarque;
    }

    private static boolean randomBoolean(){
        return secureRandom.nextBoolean();
    }

    public boolean decrementarTiempoLlegada(){
        if (tiempoLlegada > 0){
            tiempoLlegada--;
            return true;
        } else
            return false;
    }

    public boolean decrementarTiempoAterrizaje(){
        if (tiempoAterrizaje > 0){
            tiempoAterrizaje--;
            return true;
        } else
            return false;
    }

    public boolean decrementarTiempoEmbarque(){
        if (tiempoEmbarque > 0){
            tiempoEmbarque--;
            return true;
        } else
            return false;
    }

    public boolean decrementarTiempoDesembarque(){
        if (tiempoDesembarque > 0) {
            tiempoDesembarque--;
            return true;
        } else
            return false;
    }

    public void setSiguienteEstado(){
        switch (estado) {
            case ENTRANTE -> setEstado(Estado.ATERRIZANDO);
            case ATERRIZANDO -> setEstado(Estado.EMBARCANDO);
            case EMBARCANDO -> setEstado(Estado.DESEMBARCANDO);
            case DESEMBARCANDO -> setEstado(Estado.FINALIZADO);
        }
    }

    public String getNombre(){ return nombre; }
    public Tipo getTipo() {return tipo; }
    public int getId() {return id; }
    public boolean isAtrasado() {return atrasado; }
    public int getTiempoLlegada() {return tiempoLlegada; }
    public int getTiempoAterrizaje() {return tiempoAterrizaje; }
    public int getTiempoEmbarque() {return tiempoEmbarque; }
    public int getTiempoDesembarque() {return tiempoDesembarque; }
    public Estado getEstado(){ return estado; }

    @Override
    public String toString() {
        return "Vuelo" +
                nombre +
                "/" + Boolean.toString(atrasado) +
                "/" + Integer.toString(tiempoLlegada) +
                "/" + Integer.toString(tiempoAterrizaje) +
                "/" + Integer.toString(tiempoDesembarque) +
                "/" + estado +
                "/" + tipo
                ;
    }

    public void setEstado(Estado estado){ this.estado = estado; }
}
