import AplicacionVuelos.Vuelos;
import controlador.OperationController;
import modelo.OperationModel;
import vista.OperationView;

public class MVCAeropuerto {


    public static void main(String[] args) {
        OperationModel modelo = new OperationModel();
        OperationView vistaControlador = new OperationView();

        OperationController controlador = new OperationController(modelo, vistaControlador);

    }
}
