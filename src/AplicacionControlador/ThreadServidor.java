package AplicacionControlador;

import AplicacionControlador.Controlador.Tipo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadServidor extends Thread{
    private final int id;
    private final DataInputStream reader;
    private final DataOutputStream writer;
    private static final boolean running = true;
    public Servidor servidor;
    private final Lock lock = new ReentrantLock(true);


    public ThreadServidor(final Socket socketRef,
                          final Servidor servidor,
                          final int id) throws IOException {
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.servidor = servidor;
        this.id = id;
    }

    public Vuelo crearVueloConDatos(Tipo tipo, int id, String nombre, int tiempoAterrizaje, int  tiempoDesembarque){
        Vuelo vuelo = new Vuelo(tipo, id, nombre, tiempoAterrizaje, tiempoDesembarque);
        return vuelo;
    }

    /* Reservado solamente la conexion Controlador <-> Ventana */
    public void notificarAterrizaje(int identificador) throws IOException {
        if (id == 22){ /* ID '22' = conexion Controlador <-> Ventana */
            lock.lock();
            try {
                writer.writeByte(1);
                writer.writeInt(identificador);
            } catch (Exception e) {

            }
            finally {
                lock.unlock();
            }
        }
    }

    public void notificarDesembarqueCompleto(int identificador) throws IOException {
        if (id == 22){ /* ID '22' = conexion Controlador <-> Ventana */
            lock.lock();
            try {
                writer.writeByte(2);
                writer.writeInt(identificador);
            } catch (Exception e) {

            }
            finally {
                lock.unlock();
            }
        }
    }
    /* ======================================================= */

    @Override
    public void run(){
        byte instruccionID;
        while (running){
            try {
                instruccionID = reader.readByte(); /* Esperar hasta que reciba un Byte */
                switch (instruccionID) {
                    /* ================ Tipo 'A' : 0 : Vuelo entrante listo para aterrizar ================ */
                    case 0:
                        int tipoVuelo = reader.readInt();
                        int id = reader.readInt();
                        String nombre = reader.readUTF();
                        int tiempoAterrizaje = reader.readInt();
                        int tiempoDesembarque = reader.readInt();
                        servidor.agregarVuelo(this.crearVueloConDatos(Controlador.decifrarTipoVuelo(tipoVuelo), id, nombre, tiempoAterrizaje, tiempoDesembarque));
                        lock.lock();
                        try {
                            // notificar 'Ventana Controlador'
                            if (servidor.getConexiones().containsKey(22)) {
                                servidor.getConexiones().get(22).writer.writeByte(0);
                                servidor.getConexiones().get(22).writer.writeUTF(nombre);
                                servidor.getConexiones().get(22).writer.writeInt(id);
                            }
                        } catch (Exception e) {

                        }finally {
                            lock.unlock();
                        }
                        break;

                    case 4: {
                        writer.writeUTF(servidor.obtenerInformacion());
                        break;
                    }
                    /* ==================================================================================== */

                    // todo !!!!!!!!!
                    /* =========== Tipo 'E' : 101 : Ventana pidiendo informacion sobre vuelo ============== */
                    case 101: /*  */
                        System.out.println("Me piden informacion sobre vuelo");
                        String[] arregloInformacionPistas = servidor.controlador.recolectarInformacionVueloPistas(reader.readInt());
                        for (int i = 0; i < arregloInformacionPistas.length; i++){
                            System.out.println(arregloInformacionPistas[i]);
                        }
                        lock.lock();
                        try {
                            for (int i = 0; i < 4; i++) {
                                writer.writeUTF(arregloInformacionPistas[i]);
                            }
                        } catch (Exception e) {

                        }finally {
                            lock.unlock();
                        }
                        break;
                }
                    /* ==================================================================================== */


                    /* =========== Tipo 'E' : 101 : Ventana pidiendo informacion sobre vuelo ============== */
                    //El caso 4 funciona para la ventana de informacion


                    case 102:
                        String[] arregloInformacionPuertas = servidor.controlador.recolectarInformacionVueloPuertas(reader.readInt());
                        lock.lock();
                        try {
                            for (int i = 0; i < 4; i++){
                                writer.writeUTF(arregloInformacionPuertas[i]);
                            }
                        } catch (Exception e){

                        }finally {
                            lock.unlock();
                        }
                        break;
                    /* ==================================================================================== */
                }
            } catch (IOException ignored) {}
        }
    }
}
