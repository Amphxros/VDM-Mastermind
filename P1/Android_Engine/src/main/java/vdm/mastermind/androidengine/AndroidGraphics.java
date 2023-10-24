package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

import vdm.mastermind.androidengine.graphics.AndroidFont;
import vdm.mastermind.androidengine.graphics.AndroidImage;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;

public final class AndroidGraphics implements IGraphics {
    protected SurfaceView surfaceView;
    protected SurfaceHolder surfaceHolder;
    protected  Context context;
    protected Canvas canvas;
    protected Paint paint;

    protected AssetManager assetManager;

    public AndroidGraphics(SurfaceView surfaceView, Context context){
        this.surfaceView=surfaceView;
        this.context= context;


    }
    @Override
    public AndroidImage newImage(String name) {
        Bitmap image=null;
        try {
            InputStream in = assetManager.open(name);
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        AndroidImage aImage = new AndroidImage(image);
        assert(aImage!=null);
        return aImage;
    }

    @Override
    public AndroidFont newFont(String filename, int size, boolean isBold) {
        return null;
    }

    @Override
    public void clear(int color) {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(color); // ARGB
        updateTransformParameters();
    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(float x, float y) {

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

    @Override
    public void drawImage(IImage image, int x, int y, int w, int h) {

    }

    @Override
    public void drawImage(IImage image, int x, int y) {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void fillRectangle(int cx, int cy, int width, int height) {

    }

    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, float arc) {

    }

    @Override
    public void drawRectangle(int cx, int cy, int width, int height) {

    }

    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, float arc) {

    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {

    }

    @Override
    public void drawCircle(float cx, float cy, float radius) {

    }

    @Override
    public void fillCircle(float cx, float cy, float radius) {

    }

    @Override
    public void drawText(String text, int x, int y) {

    }

    private void updateTransformParameters() {
        int contentW = surfaceView.getWidth();
        int contentH = surfaceView.getHeight();


    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
