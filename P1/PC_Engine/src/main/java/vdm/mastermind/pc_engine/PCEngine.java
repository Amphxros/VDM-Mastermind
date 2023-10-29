package vdm.mastermind.pc_engine;

import javax.swing.JFrame;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.Engine;

public class PCEngine extends Engine implements Runnable {

    private JFrame frame;
    public PCEngine(){
        this.frame=new JFrame("Mastermind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);
        frame.setSize(300,600);

        PCGraphics pcGraphics= new PCGraphics(frame);
        PCInput pcInput= new PCInput(frame);

        frame.addMouseListener(pcInput);
        frame.addKeyListener(pcInput);

        setInput(pcInput);
        setGraphics(pcGraphics);

    }
    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        boolean running=true;
        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            getLogic().handleInput(getInput());
            getLogic().update((float)elapsedTime);

            getGraphics().clear(new Color(250,250,250));
            getLogic().render(getGraphics());
            getGraphics().present();
        }
    }
}