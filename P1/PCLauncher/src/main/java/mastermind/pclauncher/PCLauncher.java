package mastermind.pclauncher;

import mastermind.logic.Logic;
import mastermind.pcengine.PCEngine;

/**
 * Clase PCLauncher que contiene el método main para iniciar la aplicación.
 */
public class PCLauncher {
    /**
     * Método principal (main) que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Crea una instancia de la clase PCEngine, que representa el motor de la aplicación.
        PCEngine engine = new PCEngine("Mastermind");
        // Establece la resolución gráfica del motor a 400x600 píxeles.
        engine.getGraphics().setResolution(400, 600);
        // Crea una instancia de la clase Logic, que representa la lógica de la aplicación,
        // y le pasa la referencia al motor como parámetro.
        Logic logic = new Logic(engine);
        // Establece la lógica creada como la lógica asociada al motor.
        engine.setLogic(logic);
        // Inicia la ejecución del motor, lo que iniciará el bucle principal de la aplicación.
        engine.run();
    }
}