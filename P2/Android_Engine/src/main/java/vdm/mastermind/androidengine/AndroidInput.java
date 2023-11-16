package vdm.mastermind.androidengine;

import android.view.MotionEvent;
import android.view.View;

import vdm.mastermind.engine.classes.Input;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.enums.EventType;

public final class AndroidInput extends Input implements View.OnTouchListener {
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
      }
        return t != null;
    }
}
