package AplicacionControlador;

import java.security.SecureRandom;
import AplicacionControlador.Controlador.Estado;
import AplicacionControlador.Controlador.Tipo;

public class Vuelo {
    private final String nombre;
    private final int id;
    private final boolean atrasado;
    private int tiempoAterrizaje;
    private int tiempoDesembarque;

    //TODO: Hacer que el avion cambie a atrasado cuando se cumple cierto numero por la suma de todos los tiempos
    private int tiempoParaEstarAtrasado;

    private Controlador.Estado estado;
    private final Tipo tipo;
    private static final SecureRandom secureRandom = new SecureRandom();

    public Vuelo(final Tipo tipo,
                 final int id,
                 final String nombre,
                 final int tiempoAterrizaje,
                 final int tiempoDesembarque){
        this.tipo = tipo;
        this.id = id;
        this.nombre = nombre;
        this.estado = Estado.ENTRANTE;
        atrasado = randomBoolean();
        this.tiempoAterrizaje = tiempoAterrizaje;
        this.tiempoDesembarque = tiempoDesembarque;
    }

    private static boolean randomBoolean(){
        return secureRandom.nextBoolean();
    }

    public boolean decrementarTiempoAterrizaje(){
        if (tiempoAterrizaje > 0){
            tiempoAterrizaje--;
            return true;
        } else
            return false;
    }

    public boolean decrementarTiempoDesembarque(){
        if (tiempoDesembarque > 0){
            tiempoDesembarque--;
            return true;
        } else
            return false;
    }

    public void setSiguienteEstado(){
        switch (estado) {
            case ENTRANTE -> setEstado(Estado.ATERRIZANDO);
            case ATERRIZANDO -> setEstado(Estado.DESEMBARCANDO);
            case DESEMBARCANDO -> setEstado(Estado.FINALIZADO);
        }
    }

    private void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombre(){ return nombre; }
    public Tipo getTipo() {return tipo; }
    public int getId() {return id; }
    public boolean isAtrasado() {return atrasado; }
    public int getTiempoAterrizaje() {return tiempoAterrizaje; }
    public int getTiempoDesembarque() {return tiempoDesembarque; }
    public Estado getEstado(){ return estado; }
    public int getEstadoInt(){
        return switch (estado) {
            case ENTRANTE -> 0;
            case ATERRIZANDO -> 1;
            case DESEMBARCANDO -> 2;
            case FINALIZADO -> 3;
        };
    }

}

