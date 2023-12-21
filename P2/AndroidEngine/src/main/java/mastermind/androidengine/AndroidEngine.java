package mastermind.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;

import mastermind.engine.Color;
import mastermind.engine.Engine;
import mastermind.engine.Notification;

public class AndroidEngine extends Engine implements Runnable {
    private Thread thread;
    private volatile boolean running;

    public AndroidEngine(AppCompatActivity activity, SurfaceView surfaceView, AdView adView , Context context) {
        setGraphics(new AndroidGraphics(surfaceView, context));
        setAudio(new AndroidAudio(context));
        setSensorsManager(new SensorsManager(context));
        setAdsManager(new AdsManager(activity,adView,context));
        setFileManager(new AndroidFileManager(context));
        AndroidInput input = new AndroidInput();
        surfaceView.setOnTouchListener(input);
        setInput(input);

        setNotificationHandler(new AndroidNotificationHandler(context));
    }

    @Override
    public int getWidth() {
        return getGraphics().getWidth();
    }

    @Override
    public int getHeight() {
        return getGraphics().getHeight();
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
        getLogic().init();
        getNotificationHandler().add(new Notification("Daily sub","Check in for money","content",20));
        while (running) {
            long currentTime = System.nanoTime();
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
        AndroidGraphics graphics = (AndroidGraphics)getGraphics();

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
