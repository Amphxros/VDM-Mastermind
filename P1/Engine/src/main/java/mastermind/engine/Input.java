package mastermind.engine;

import java.util.LinkedList;
import java.util.List;

public class Input implements IInput{
    protected final LinkedList<TouchEvent> events = new LinkedList<>(); // tambien puede ser una cola
    /**
     * @return Returns a list of all the queued events, clears the queue upon call.
     */
    @Override
    public synchronized LinkedList<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> tmp;

        synchronized (this) {
            // Copy the queued events into a list:
            tmp = new LinkedList<>(events);
            events.clear();
        }

        return tmp;
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
