package vdm.mastermind.pc_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import vdm.mastermind.engine.classes.Input;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.enums.EventType;

public class PCInput extends Input implements MouseListener, KeyListener {

    private final JFrame frame;

    public PCInput (JFrame frame){
        this.frame=frame;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.DOWN));
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.UP));
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
