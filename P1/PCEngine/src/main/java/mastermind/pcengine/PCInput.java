package mastermind.pcengine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import mastermind.engine.EventType;
import mastermind.engine.Input;
import mastermind.engine.TouchEvent;

/**
 * Clase que implementa la interfaz Input y los listeners MouseInputListener y KeyListener
 * para manejar la entrada desde el ratón y el teclado en un entorno de PC.
 */
public class PCInput extends Input implements MouseInputListener, KeyListener {


    // Variable que indica si la aplicación se encuentra en modo pantalla completa.
    private final JFrame frame;

    // Variable que indica si la aplicación se encuentra en modo pantalla completa.
    private boolean fullscreen = false;

    /**
     * Constructor de la clase PCInput.
     *
     * @param frame El JFrame asociado a la entrada.
     */
    public PCInput(JFrame frame) {
        this.frame = frame;
    }

    // Implementación de los métodos de la interfaz MouseInputListener:
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.DOWN));
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // No se realiza ninguna acción al presionar el botón del ratón.
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.UP));
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // No se realiza ninguna acción al entrar en el área del componente.
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // No se realiza ninguna acción al salir del área del componente.
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

    // Implementación de los métodos de la interfaz KeyListener:
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // No se realiza ninguna acción al escribir una tecla.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Escucha la tecla "F" del teclado y realiza la alternancia entre modo pantalla completa y modo normal.
        if (e.getKeyCode() == KeyEvent.VK_F) {
            // Obtén los dispositivos de pantalla y establece la ventana como pantalla completa
            // si hay al menos un dispositivo, de lo contrario, desactívalo.
            GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            if (devices.length != 0) {
                devices[0].setFullScreenWindow(fullscreen ? null : frame);
                fullscreen = !fullscreen;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // No se realiza ninguna acción al soltar una tecla.
    }
}