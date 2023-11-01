package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import vdm.mastermind.androidengine.graphics.AndroidFont;
import vdm.mastermind.androidengine.graphics.AndroidImage;
import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.GraphicsTransformer;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;

public final class AndroidGraphics implements IGraphics {
    protected SurfaceView surfaceView;
    protected SurfaceHolder surfaceHolder;
    protected  Context context;
    protected Canvas canvas=null;
    protected Paint paint;

    protected AssetManager assetManager;
    GraphicsTransformer transformer= new GraphicsTransformer();
    Map<String,AndroidImage> images= new HashMap<>();
    Map<String,AndroidFont> fonts= new HashMap<>();

    public AndroidGraphics(SurfaceView surfaceView, Context context){
        this.surfaceView=surfaceView;
        this.surfaceHolder=surfaceView.getHolder();
        this.context= context;
        this.assetManager= context.getAssets();

        this.paint= new Paint();


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
    public AndroidFont newFont(String filename, int size, boolean isBold, boolean isItalic) {
        AndroidFont font= new AndroidFont(filename,assetManager,size,isBold,isItalic);
        assert(font.getTypeface()!=null);
        fonts.put(filename,font);
        return font;
    }

    @Override
    public void clear(Color color) {
        canvas = surfaceHolder.lockCanvas();
        assert (canvas!=null);
        canvas.drawColor(color.getARGB()); // ARGB
        updateTransformParameters();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    @Override
    public void present() {
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    @Override
    public void translate(int x, int y) {
        canvas.translate(x,y);
    }

    @Override
    public void scale(float x, float y) {
        canvas.scale(x,y);
    }

    @Override
    public void save() {
        canvas.save();
    }

    @Override
    public void restore() {
        canvas.restore();
    }

    @Override
    public void drawImage(IImage image, int x, int y, int w, int h) {

    }

    @Override
    public void drawImage(IImage image, int x, int y) {

    }

    @Override
    public void setColor(Color color) {
        assert (color!=null);
        paint.setColor(color.getARGB());
    }

    @Override
    public void setFont(IFont font) {
        AndroidFont androidFont= (AndroidFont) font;
        assert (androidFont!=null);
        paint.setTypeface(androidFont.getTypeface());

    }

    @Override
    public void fillRectangle(int cx, int cy, int width, int height) {

    }

    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, float arc) {

    }

    @Override
    public void drawRectangle(int cx, int cy, int width, int height) {
        canvas.drawRect(cx,cy,width,height,paint);
    }

    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, float arc) {
       canvas.drawRoundRect(cx,cy,width,height,arc,arc,paint);
    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        canvas.drawLine(initX,initY,endX,endY,paint);
    }

    @Override
    public void drawCircle(float cx, float cy, float radius) {
        canvas.drawCircle(cx,cy,radius,paint);
    }

    @Override
    public void fillCircle(float cx, float cy, float radius) {
        canvas.drawCircle(cx,cy,radius,paint);
    }

    @Override
    public void drawText(String text, int x, int y ,HorizontalAlignment alignment) {
        int outX = x;
        if (alignment == HorizontalAlignment.CENTER) {

        } else if (alignment == HorizontalAlignment.RIGHT) {

        }
        canvas.drawText(text,x,y,paint);
    }

    @Override
    public void setResolution(int width, int height) {
        transformer.setSize(width, height);
    }

    private void updateTransformParameters() {
        int contentW = surfaceView.getWidth();
        int contentH = surfaceView.getHeight();

        transformer.update(contentW, contentH);
        transformer.transform(this);
    }

    public boolean surfaceValid(){
        return surfaceHolder.getSurface().isValid();
    }
    @Override
    public int getWidth() {
        return surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return surfaceView.getHeight();
    }

    @Override
    public int getLogicPointX(int x) {
        return transformer.getTransformedX(x);
    }

    @Override
    public int getLogicPointY(int y) {
        return transformer.getTransformedY(y);
    }
}
