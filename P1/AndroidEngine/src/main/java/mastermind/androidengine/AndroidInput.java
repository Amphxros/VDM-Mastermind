package mastermind.androidengine;

import android.os.Handler;
import android.os.Looper;
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
    private float lastX, lastY;

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

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                eventType=EventType.DOWN;

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
                    deltaX = (int) (motionEvent.getX() - lastX);
                    deltaY = (int) (motionEvent.getY() - lastY);
                    // Actualizar las coordenadas anteriores.
                    lastX = motionEvent.getX();
                    lastY = motionEvent.getY();

                    break;

        }
        return t != null;
    }

}
