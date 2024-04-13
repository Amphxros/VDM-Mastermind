package mastermind.pcengine;

import javax.swing.JFrame;

import mastermind.engine.Color;
import mastermind.engine.Engine;

public class PCEngine extends Engine implements Runnable {
    private final JFrame renderView;
    public volatile boolean running;

    /**
     * Constructora del motor de PC. Inicializa todos los motores dentro del PC.
     */
    public PCEngine(String _titleGame) {
        renderView = new JFrame(_titleGame);
        renderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderView.pack();
        renderView.setIgnoreRepaint(true);
        renderView.setVisible(true);

        setAudio(new PCAudio());
        setGraphics(new PCGraphics(renderView));
        PCInput input = new PCInput(renderView);
        setInput(input);
        running = true;

        renderView.addMouseListener(input);
        renderView.addKeyListener(input);
    }

    @Override
    public void run() {
        // Obtener el tiempo actual en nanosegundos
        long lastFrameTime = System.nanoTime();

        // Inicializar la lógica del juego
        getLogic().init();

        // Bucle principal mientras la aplicación esté en ejecución
        while (running) {
            long currentTime = System.nanoTime();

            // Calcular el tiempo transcurrido desde el último fotograma en segundos
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            // Manejar eventos de entrada y actualizar la lógica del juego
            getLogic().handleEvents(getInput());
            getLogic().update(elapsedTime);

            // Limpiar el fondo con color blanco
            getGraphics().clear(Color.WHITE.getARGB());

            // Renderizar la lógica del juego en el lienzo
            getLogic().render(getGraphics());

            // Presentar el lienzo para visualizar los cambios
            getGraphics().present();
        }
    }
}