package mastermind.engine;

public interface IGraphics {

    /**
     * @param name ruta donde se encuentra el archivo de la imagen
     * @return Interfaz de una Image para las diferentes plataformas.
     */
    IImage newImage(String name);

    /**
     *
     * @param name ruta donde se encuentra el archivo de la fuente
     * @param size tamaño de la fuente
     * @param isBold boleano para saber si esta en negrita
     * @return
     */
    IFont newFont(String name, int size, boolean isBold);

    /**
     * Sets the text alignment for text.
     *
     * @param alignment The alignment to use.
     */
    void setTextAlignment(HorizontalAlignment alignment);

    /**
     * Draws an image with the original size at a point of the canvas.
     *
     * @param image The image to draw.
     * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     */
    void drawImage(IImage image, int x, int y);

    /**
     * Draws an image with the specified size at a point of the canvas.
     *
     * @param image  The image to draw.
     * @param x      The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y      The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param width  The width to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     * @param height The height to raw the `image` in the destination canvas. This allows scaling of the drawn image.
     */
    void drawImage(IImage image, int x, int y, int width, int height);

    /**
     * Draws a text using the active font.
     *
     * @param text The text to draw in the destination canvas.
     * @param x    The x-axis coordinates from where to draw the text.
     * @param y    The y-axis coordinates from where to draw the text.
     * @see IGraphics#setFont
     */
    void drawText(String text, int x, int y);

    /**
     * Draws a filled square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    void fillRectangle(int x, int y, int side);

    /**
     * Draws a filled rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    void fillRectangle(int x, int y, int width, int height);

    /**
     * Draws a filled rounded rectangle
     * @param cx  		The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy		The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width		The width of the rectangle to draw.
     * @param height 	The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    void fillRoundRectangle(int cx, int cy, int width, int height, int arc);

    /**
     *
     * @param cx The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param radius Radius of the circle to be drawn
     */
    void fillCircle(float cx, float cy, float radius);

    /**
     * Draws a square.
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    void drawRectangle(int x, int y, int side);

    /**
     * Draws a rectangle.
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    void drawRectangle(int x, int y, int width, int height);

    /**
     * Draws a rectangle.
     *
     * @param cx      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    void drawRoundRectangle(int cx, int cy, int width, int height, int arc);
    /**
     * Draws a line from (initX, initY) to (endX, endY).
     *
     * @param initX The x-axis coordinate of the starting point of the line.
     * @param initY The y-axis coordinate of the starting point of the line.
     * @param endX  The x-axis coordinate of the ending point of the line.
     * @param endY  The y-axis coordinate of the ending point of the line.
     */
    void drawLine(int initX, int initY, int endX, int endY);

    /**
     *
     * @param cx
     * @param cy
     * @param radius
     */
    void drawCircle(float cx, float cy, float radius);


    /**
     * 
     * @param width
     * @param height
     */
    void setResolution(int width, int height);

    /**
     * @param color Sets the current color with a raw RGBA integer.
     */
    void setColor(int color);

    /**
     * @param color Sets the current color with a {@link Color} instance.
     */
    void setColor(mastermind.engine.Color color);

    void setFont(IFont font);

    void present();


    void clear(int color);

    void translate(int x, int y);

    void scale(double x, double y);

    void save();

    void restore();

    int getWidth();

    int getHeight();

    /**
     * Transforms the window X-axis point into a value within 0 and {@link #getWidth()}, returns -1
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
