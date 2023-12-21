package mastermind.pcengine;

import javax.swing.JFrame;

import mastermind.engine.Color;
import mastermind.engine.Engine;

public class PCEngine extends Engine implements Runnable {
    private final JFrame renderView;
    public volatile boolean running;

    public PCEngine() {
        renderView = new JFrame("Mastermind");
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
    public int getWidth() {
        return renderView.getWidth();
    }

    @Override
    public int getHeight() {
        return renderView.getHeight();
    }

    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();

        getLogic().init();
        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            getLogic().handleEvents(getInput());
            getLogic().update(elapsedTime);

            getGraphics().clear(Color.WHITE.getARGB());
            getLogic().render(getGraphics());
            getGraphics().present();
        }
    }
}