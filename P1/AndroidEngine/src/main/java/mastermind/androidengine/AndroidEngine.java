package mastermind.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import mastermind.engine.Color;
import mastermind.engine.Engine;

public class AndroidEngine extends Engine implements Runnable {
    private Thread thread; //Thread de la app
    private volatile boolean running; //bool para el bucle principal

    public AndroidEngine(SurfaceView surfaceView, Context context) {
        this.setGraphics(new AndroidGraphics(surfaceView, context));
        this.setAudio(new AndroidAudio(context));

        AndroidInput input = new AndroidInput();
        surfaceView.setOnTouchListener(input);
        this.setInput(input);
    }

    @Override
    public AndroidGraphics getGraphics() {
        return (AndroidGraphics) super.getGraphics();
    }

    /**
     * Bucle principal del hilo
     */
    @Override
    public void run() {
        if (thread != Thread.currentThread()) {
            // (Defensive Programming)
            // Makes it so runnable can only be called from this class
            throw new RuntimeException("run() should not be called directly");
        }

        // Waits for the view to be initialized (The thread could be faster than the initialization)
        while (running && getGraphics().getWidth() == 0) ;

        long lastFrameTime = System.nanoTime();
        //inicializamos la logica una vez tengamos la vista inicializada
        getLogic().init();

        while (running) {
            long currentTime = System.nanoTime(); //tiempo en nano secs
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            handleEvents();
            update(elapsedTime);
            render();
        }
    }

    private void render() {
        AndroidGraphics graphics = getGraphics();
        // Waits for an invalid surface
        while (!graphics.surfaceValid()) ;
        graphics.clear(Color.WHITE.getARGB());
        getLogic().render(graphics);
        graphics.present();
    }

    private void update(double delta) {
        getLogic().update(delta);
    }

    private void handleEvents() {
        getLogic().handleEvents(getInput());
    }

    public void resume() {
        if (!running) {
            // Only if we weren't doing anything yet
            // (Defensive programming at its best)
            running = true;
            // run() is "running" in a new thread
            thread = new Thread(this);
            thread.start();
        }
    }

    public void pause() {
        if (running) {
            running = false;
            while (true) {
                try {
                    thread.join();
                    thread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Something went REALLY wrong
                }
            }
        }
    }
}
