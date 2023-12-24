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
    private int x,y;

    EventType eventType;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent t = null;

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                eventType=EventType.DOWN;
                lastX = (int) motionEvent.getX();
                lastY = (int) motionEvent.getY();
                t= new TouchEvent(x,y,eventType,false);
                addEvent(t);
                break;
            case MotionEvent.ACTION_UP:
                eventType=EventType.UP;
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();

                t= new TouchEvent(x,y,eventType,true);
                addEvent(t);

                break;
                case MotionEvent.ACTION_MOVE:
                float deltaX = motionEvent.getX() - lastX;
                float deltaY = motionEvent.getY() - lastY;

                // Realizar acciones según el desplazamiento (puedes imprimir o almacenar valores, etc.).
                System.out.println("Touch" +"DeltaX: " + deltaX + ", DeltaY: " + deltaY);

                // Actualizar las coordenadas anteriores.
                lastX = (int) motionEvent.getX();
                lastY = (int) motionEvent.getY();
                break;

        }
        return t != null;
    }

}
