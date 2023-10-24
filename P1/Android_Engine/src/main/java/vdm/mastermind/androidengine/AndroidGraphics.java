package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;

public class AndroidGraphics implements IGraphics {
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
    public IImage newImage(String name) {
        return null;
    }

    @Override
    public IFont newFont(String filename, int size, boolean isBold) {
        return null;
    }

    @Override
    public void clear(int color) {

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

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
