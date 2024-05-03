package mastermind.androidengine;

import mastermind.engine.Color;
import mastermind.engine.GraphicsTransformer;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;
import java.util.HashMap;



public final class AndroidGraphics implements IGraphics {
    private final SurfaceView surfaceView;
    private final SurfaceHolder surfaceHolder;
    private final Paint paint;
    private final HashMap<String, AndroidImage> loadedImages = new HashMap<>();
    private final HashMap<String, IFont> loadedFonts = new HashMap<>();
    private final AssetManager assetManager;
    private final GraphicsTransformer transformer = new GraphicsTransformer();
    private Canvas canvas = null;
    private HorizontalAlignment textAlignment = HorizontalAlignment.NONE;

    public AndroidGraphics(SurfaceView surfaceView, Context context) {
        this.surfaceView = surfaceView;
        this.surfaceHolder = surfaceView.getHolder();
        this.paint = new Paint();
        this.assetManager = context.getAssets();
        setColor(Color.WHITE);
    }

    public SurfaceView getView() {
        return surfaceView;
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
    public void clear(int color) {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(color); // ARGB
        updateTransformParameters();
    }

    public void present() {
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public boolean surfaceValid() {
        return surfaceHolder.getSurface().isValid();
    }

    @Override
    public AndroidImage newImage(String name) {
        Bitmap image;
        try {
            InputStream in = assetManager.open(name);
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        AndroidImage aImage = new AndroidImage(image);
        loadedImages.put(name, aImage);
        return aImage;
    }

    @Override
    public AndroidFont newFont(String name, int size, boolean isBold) {
        AndroidFont aFont = new AndroidFont(name, assetManager, size, isBold);
        loadedFonts.put(name, aFont);
        return aFont;
    }

    /**
     * Sets the text alignment for text.
     *
     * @param alignment The alignment to use.
     */
    @Override
    public void setTextAlignment(HorizontalAlignment alignment) {
        this.textAlignment = alignment;
    }

    /**
     *
     * @param image The image to draw.
     * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     */
    @Override
    public void drawImage(IImage image, int x, int y) {
        canvas.drawBitmap(((AndroidImage) image).getUnderlyingImage(), x, y, paint);
    }

    /**
     *
     * @param image  The image to draw.
     * @param x      The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y      The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param width  The width to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     * @param height The height to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     */
    @Override
    public void drawImage(IImage image, int x, int y, int width, int height) {
        Rect src = new Rect(0, 0, image.getWidth(), image.getHeight());
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(((AndroidImage) image).getUnderlyingImage(), src, dst, paint);
    }

    /**
     *
     * @param text The text to draw in the destination canvas.
     * @param x    The x-axis coordinates from where to draw the text.
     * @param y    The y-axis coordinates from where to draw the text.
     */
    @Override
    public void drawText(String text, int x, int y) {
        int outX = x;
        if (textAlignment == HorizontalAlignment.CENTRE) {
            Rect result = new Rect();
            paint.getTextBounds(text, 0, text.length(), result);
            outX -= result.centerX();
        } else if (textAlignment == HorizontalAlignment.RIGHT) {
            Rect result = new Rect();
            paint.getTextBounds(text, 0, text.length(), result);
            outX -= result.width();
        }
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, outX, y, paint);
    }

    /**
     * Dibuja un cuadrado relleno
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int side) {
        paint.setStyle(Paint.Style.FILL);
        fillRectangle(x, y, side, side);
    }

    /**
     * Dibuja un rectangulo relleno
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    /**
     * Dibuja un retangulo relleno con esquinas redondeadas
     * @param cx  		The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy		The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width		The width of the rectangle to draw.
     * @param height 	The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, int arc) {
        assert (paint!=null);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(cx,cy,cx+ width,cy + height,arc,arc,paint);
    }

    /**
     * Dibuja un circulo
     * @param cx centro x del circulo
     * @param cy centro y del circulo
     * @param radius radio del circulo
     */
    @Override
    public void fillCircle(float cx, float cy, float radius) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx,cy,radius,paint);
    }

    /**
     * Dibuja el contorno de un cuadrado
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    /**
     * dibuja el contorno del rectangulo
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    /**
     * dibuja el contorno de un rectangulo redondeado
     * @param cx      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, int arc) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(cx,cy,cx+ width,cy + height,arc,arc,paint);
        //paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Dibuja una linea de un punto init a un punto end
     * @param initX The x-axis coordinate of the starting point of the line.
     * @param initY The y-axis coordinate of the starting point of the line.
     * @param endX  The x-axis coordinate of the ending point of the line.
     * @param endY  The y-axis coordinate of the ending point of the line.
     */
    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        canvas.drawLine(initX, initY, endX, endY, paint);
    }

    /**
     * Dibuja una circunferencia
     * @param cx centro en x
     * @param cy centro en y
     * @param radius radio de la circunferencia
     */
    @Override
    public void drawCircle(float cx, float cy, float radius) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx,cy,radius,paint);

    }

    /**
     * Pone la dimension de la pantalla logica
     *
     * @param width Ancho de la resolución.
     * @param height Altura de la resolución.
     */
    @Override
    public void setResolution(int width, int height) {
        transformer.setSize(width, height);
    }

    /**
     * Pone el color al que vamos a pintar con un int ARGB
     * @param color Sets the current color with a raw ARGB integer.
     */
    @Override
    public void setColor(int color) {
        paint.setColor(Color.rgbaToARGB(color));
    }

    /**
     * Pone el color a pintar
     * @param color Sets the current color with a {@link Color} instance.
     */
    @Override
    public void setColor(Color color) {
        paint.setColor(color.getARGB());
    }

    /**
     * Pone la tipografia que vamos a usar
     *
     * @param font Objeto que implementa la interfaz IFont y representa la fuente.
     */
    @Override
    public void setFont(IFont font) {
        AndroidFont aFont = (AndroidFont) font;
        paint.setTypeface(aFont.getFont());
        paint.setTextSize(aFont.getSize());
    }

    /**
     * Mueve el canvas
     * @param x posicion en x
     * @param y posicion en y
     */
    @Override
    public void translate(int x, int y) {
        canvas.translate(x, y);
    }

    /**
     * Escala el canvas
     * @param x posicion en x
     * @param y posicion en y
     */
    @Override
    public void scale(double x, double y) {
        canvas.scale((float) x, (float) y);
    }

    @Override
    public void save() {
        canvas.save();
    }

    @Override
    public void restore() {
        canvas.restore();
    }

    /**
     * @return El ancho del objeto.
     */
    @Override
    public int getWidth() {
        return transformer.getWidth();
    }

    /**
     * @return El alto del objeto.
     */
    @Override
    public int getHeight() {
        return transformer.getHeight();
    }

    /**
     * Transforms the window X-axis point into a value within 0 and {@link #getWidth()}, returns -1
     * if the resulting value is out of bounds.
     *
     * @param x The window X-axis point to transform.
     * @return The scene X-axis point, -1 if invalid.
     */
    @Override
    public int getLogicPointX(int x) {
        return transformer.getTransformedX(x);
    }

    /**
     * Transforms the window Y-axis point into a value within 0 and {@link #getHeight()}, returns -1
     * if the resulting value is out of bounds.
     *
     * @param y The window Y-axis point to transform.
     * @return The scene Y-axis point, -1 if invalid.
     */
    @Override
    public int getLogicPointY(int y) {
        return transformer.getTransformedY(y);
    }

    /**
     * Updates the transform parameters, internally calls {@link #translate(int, int)} and
     * {@link #scale(double, double)} internally.
     */
    private void updateTransformParameters() {
        int contentW = surfaceView.getWidth();
        int contentH = surfaceView.getHeight();

        transformer.update(contentW, contentH);
        transformer.transform(this);
    }
}
