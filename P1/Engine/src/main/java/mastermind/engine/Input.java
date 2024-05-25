package mastermind.engine;

import java.util.LinkedList;
import java.util.List;

public abstract class Input implements IInput{

    protected int deltaX, deltaY;

    protected final LinkedList<TouchEvent> events = new LinkedList<>(); // tambien puede ser una cola
    /**
     * @return Copia los eventos y los redimensiona a la pantalla logica
     */
    @Override
    public synchronized LinkedList<TouchEvent> getTouchEvents(IGraphics graphics) {
        LinkedList<TouchEvent> tmp;
        synchronized (this) {
            tmp = new LinkedList<>(events);
            events.clear();
            for (TouchEvent t : tmp) {
                t.defineLogicCoordinates(graphics);
            }
        }
        return tmp;
    }

    @Override
    public int getDeltaX() {
        return this.deltaX;
    }

    @Override
    public int getDeltaY() {
        return this.deltaY;
    }

    @Override
    public void setDeltaX(int deltaX) {
        this.deltaX= deltaX;
    }

    @Override
    public void setDeltaY(int deltaY) {
        this.deltaY= deltaY;
    }

    /**
     * Añade un evento de tipo TouchEvent de manera sincronizada a la lista de eventos.
     * @param event El evento de tipo TouchEvent a ser añadido.
     */
    protected synchronized void addEvent(TouchEvent event) {
        // Añade el evento proporcionado a la lista de eventos de manera segura para hilos.
        events.add(event);
    }
}
