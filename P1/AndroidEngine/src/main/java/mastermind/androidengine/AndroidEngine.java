package mastermind.androidengine;

import android.content.Context;
import android.view.SurfaceView;

import mastermind.engine.Engine;

public class AndroidEngine extends Engine implements Runnable {
    public AndroidEngine(SurfaceView view, Context context){

    }
    @Override
    public void run() {

    }

    @Override
    public int getWidth() {
        return getGraphics().getWidth();
    }

    @Override
    public int getHeight() {
        return getGraphics().getHeight();
    }
}
