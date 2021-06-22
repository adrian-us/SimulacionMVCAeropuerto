package modelo;

public class Puerta {
    private boolean ocupada = false;
    private Vuelo avionActual = null;
    private int id;

    public Puerta(int id){
        this.id = id;
    }

    public void setAvionActual(Vuelo avionActual) {this.avionActual = avionActual;}
    public void setOcupada(boolean ocupada) {this.ocupada = ocupada;}
    public boolean isOcupada() {return ocupada;}
    public Vuelo getAvionActual() {return avionActual;}

    public int getId() {
        return this.id;
    }

}
