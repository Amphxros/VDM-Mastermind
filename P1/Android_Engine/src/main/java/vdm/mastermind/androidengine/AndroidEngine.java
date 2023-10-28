package vdm.mastermind.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.Engine;
import vdm.mastermind.engine.interfaces.ILogic;

public final class AndroidEngine extends Engine implements Runnable {
    Thread thread;
    boolean running;
    public AndroidEngine(SurfaceView view,Context context){
        AndroidGraphics androidGraphics= new AndroidGraphics(view,context);
        AndroidInput androidInput= new AndroidInput();

        view.setOnTouchListener(androidInput);
        setInput(androidInput);
        setGraphics(androidGraphics);

    }

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

        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            handleInput();
            update((float) elapsedTime);
            render();
        }
    }
    private void render() {
        AndroidGraphics graphics = (AndroidGraphics) getGraphics();

        // Waits for an invalid surface
        while (!graphics.surfaceValid()) ;

        graphics.clear(new Color(0,0,0,255));
        getLogic().render(graphics);
        graphics.present();
    }

    private void update(float delta) {
        getLogic().update(delta);
    }

    private void handleInput() {
        getLogic().handleInput(getInput());
    }

    @Override
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

    @Override
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
