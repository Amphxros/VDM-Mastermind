package vdm.mastermind.engine.interfaces;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.objects.*;

public interface IGraphics {
    IImage newImage(String name);
    IFont newFont(String filename, int size, boolean isBold, boolean isItalic);
    void clear(Color color);
    void present();

    //Métodos de control de la transformación sobre el canvas
    void translate(int x,int y);
    void scale(float x,float y);
    void save();
    void restore();
    // Las operaciones de dibujado se verán afectadas por la transformación establecida.
    void drawImage(IImage image,int x, int y, int w, int h);
    void drawImage(IImage image,int x, int y);

    void setColor(Color color);
    void setFont(IFont font);
    void fillRectangle(int cx, int cy, int width, int height);
    void fillRoundRectangle(int cx, int cy, int width, int height, float arc);
    void drawRectangle(int cx,int cy,int width,int height);
    void drawRoundRectangle(int cx,int cy,int width,int height,float arc);
    void drawLine(int initX,int initY,int endX,int endY);
    void drawCircle(float cx, float cy, float radius);
    void fillCircle(float cx, float cy, float radius);
    void drawText(String text,int x,int y, HorizontalAlignment alignment);
    // Dimensiones de la ventana

    void setResolution(int width, int height);
    int getWidth();
    int getHeight();

    /** Transforms the window X-axis point into a value within 0 and {@link #getWidth()}, returns -1
     * if the resulting value is out of bounds.
	 *
     * @param x The window X-axis point to transform.
     * @return The scene X-axis point, -1 if invalid.
	 */
    int getLogicPointX(int x);

    /**
     * Transforms the window Y-axis point into a value within 0 and {@link #getHeight()}, returns -1
     * if the resulting value is out of bounds.
     *
     * @param y The window Y-axis point to transform.
     * @return The scene Y-axis point, -1 if invalid.
     */
    int getLogicPointY(int y);


}
