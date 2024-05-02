package mastermind.androidengine;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import mastermind.engine.EventType;
import mastermind.engine.IInput;
import mastermind.engine.Input;
import mastermind.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener {
    private int x,y; // coordenadas del evento
    EventType eventType; //tipo de evento
    private int lastX = 0, lastY = 0;

    /**
     *
     * @param view The view the touch event has been dispatched to.
     * @param motionEvent The MotionEvent object containing full information about
     *        the event.
     * @return si ha sucedido un evento que nos interesa parsear
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent t = null;
        //deltaX = 0; deltaY = 0;

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                eventType=EventType.DOWN;
                lastX = x;
                lastY = y;
                t= new TouchEvent(x,y,eventType);
                addEvent(t);
                break;
            case MotionEvent.ACTION_UP:
                eventType=EventType.UP;
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                t= new TouchEvent(x,y,eventType);
                addEvent(t);

                break;
            case MotionEvent.ACTION_MOVE:

                int currentX = (int) motionEvent.getX();
                int currentY = (int) motionEvent.getY();

                if (currentY < lastY) {
                    eventType = EventType.DRAG_UP;
                } else {
                    eventType = EventType.DRAG_DOWN;
                }

                // Actualizar las coordenadas anteriores.
                lastX = currentX;
                lastY = currentY;
                t = new TouchEvent(currentX, currentY, eventType);
                addEvent(t);
                break;


        }
        return t != null;
    }

}
