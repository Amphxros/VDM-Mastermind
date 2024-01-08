package mastermind.androidengine;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import mastermind.engine.EventType;
import mastermind.engine.Input;
import mastermind.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener {
    private float x,y, lastX, lastY;
    EventType eventType;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent t = null;

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN: //bajar dedo
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                eventType=EventType.DOWN;
                t= new TouchEvent((int)x,(int)y,eventType);
                lastX = motionEvent.getX();
                lastY = motionEvent.getY();
                addEvent(t);
                break;
            case MotionEvent.ACTION_UP: //levantar dedo
                eventType=EventType.UP;
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                lastX = motionEvent.getX();
                lastY = motionEvent.getY();
                t= new TouchEvent((int)x,(int)y,eventType);
                addEvent(t);

                break;
            case MotionEvent.ACTION_MOVE: //evento de scroll
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
