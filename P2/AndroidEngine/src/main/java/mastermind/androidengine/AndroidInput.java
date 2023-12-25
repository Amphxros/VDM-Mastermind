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
            case MotionEvent.ACTION_DOWN:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                eventType=EventType.DOWN;
                t= new TouchEvent((int)x,(int)y,eventType,false);
                addEvent(t);
                break;
            case MotionEvent.ACTION_UP:
                eventType=EventType.UP;
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                t= new TouchEvent((int)x,(int)y,eventType,true);
                addEvent(t);

                break;
            case MotionEvent.ACTION_MOVE:
                    eventType=EventType.MOVE;
                    deltaX = (int) (motionEvent.getX() - lastX);
                    deltaY = (int) (motionEvent.getY() - lastY);

                // Realizar acciones según el desplazamiento (puedes imprimir o almacenar valores, etc.).
                Log.d("Touch", "DeltaX: " + deltaX + ", DeltaY: " + deltaY);

                t= new TouchEvent((int)motionEvent.getX(),(int)deltaY,eventType,true);
                //scrolling=false;
                addEvent(t);

                // Actualizar las coordenadas anteriores.
                lastX = motionEvent.getX();
                lastY = motionEvent.getY();
                break;

        }
        return t != null;
    }

}
