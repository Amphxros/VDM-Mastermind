package mastermind.pcengine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import mastermind.engine.GraphicsTransformer;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.ISound;

public class PCGraphics implements IGraphics {
    private final JFrame window;
    private final BufferStrategy buffer;
    private final GraphicsTransformer transformer = new GraphicsTransformer();
    private Graphics2D canvas;
    private HorizontalAlignment textAlignment;

    /**
     * Constructor de la clase PCGraphics que inicializa la representación gráfica para un JFrame dado
     * @param window ventana que le pasas al motor grafico
     */
    public PCGraphics(JFrame window) {
        // Asignar el JFrame proporcionado a la variable de instancia 'window'
        this.window = window;

        // Número de intentos para crear un buffer strategy
        int attempts = 10;

        // Intentar crear un BufferStrategy con un máximo de 2 buffers
        while (attempts > 0) {
            try {
                // Intentar crear un BufferStrategy con 2 buffers
                this.window.createBufferStrategy(2);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Decrementar el número de intentos restantes
            attempts--;
        }

        // Obtener el BufferStrategy creado
        this.buffer = this.window.getBufferStrategy();
        // Obtener el contexto gráfico (Graphics2D) asociado al BufferStrategy
        this.canvas = (Graphics2D) this.buffer.getDrawGraphics();
    }

    /**
     *
     * @param name ruta donde se encuentra el archivo de la imagen
     * @return devuelve una imagen de PC (PCImage)
     */
    @Override
    public PCImage newImage(String name) {
        // Declaración de una BufferedImage para almacenar la imagen
        BufferedImage image;
        try {
            // Intentar leer la imagen desde el archivo en la ruta especificada
            image = ImageIO.read(new File("Assets/" + name));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return new PCImage(image);
    }

    /**
     *
     * @param name ruta donde se encuentra el archivo de la fuente
     * @param size tamaño de la fuente
     * @param isBold boleano para saber si esta en negrita
     * @return devuelve una fuente de PC (PCFont)
     */
    @Override
    public PCFont newFont(String name, int size, boolean isBold) {
        Font font;
        try {
            // Intentar leer la fuente desde el archivo en la ruta especificada
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/" + name));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        // Derivar la fuente con el tamaño y el estilo especificados
        font = font.deriveFont(isBold ? Font.BOLD : Font.PLAIN, (float) size);
        return new PCFont(font);
    }

    /**
     * Sets the text alignment for text.
     *
     * @param alignment The alignment to use.
     */
    @Override
    public void setTextAlignment(HorizontalAlignment alignment) {
        textAlignment = alignment;
    }

    /**
     *
     * @param image The image to draw.
     * @param x     The x-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     * @param y     The y-axis coordinate in the destination canvas at which to place the top left corner of the source `image`.
     */
    @Override
    public void drawImage(IImage image, int x, int y) {
        canvas.drawImage(((PCImage) image).getUnderlyingImage(), x, y, null);
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
        canvas.drawImage(((PCImage) image).getUnderlyingImage(), x, y, width, height, null);
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
            outX -= canvas.getFontMetrics().stringWidth(text) / 2;
        } else if (textAlignment == HorizontalAlignment.RIGHT) {
            outX -= canvas.getFontMetrics().stringWidth(text);
        }

        canvas.drawString(text, outX, y);
    }
    /**
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int side) {
        fillRectangle(x, y, side, side);
    }

    /**
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        canvas.fillRect(x, y, width, height);
    }

    /**
     *
     * @param cx  		The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy		The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width		The width of the rectangle to draw.
     * @param height 	The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, int arc) {
        canvas.fill(new RoundRectangle2D.Double(cx, cy, width, height, arc, arc));
    }

    /**
     *
     * @param cx The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param radius Radius of the circle to be drawn
     */
    @Override
    public void fillCircle(float cx, float cy, float radius) {
        Shape circle = new Ellipse2D.Double(cx - radius, cy - radius, radius * 2.0, radius * 2.0);
        canvas.fill(circle);
    }

    /**
     *
     * @param x    The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y    The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param side The size of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    /**
     *
     * @param x      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param y      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     */
    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        canvas.drawRect(x, y, width, height);
    }

    /**
     *
     * @param cx      The x-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param cy      The y-axis coordinate of the top left corner of the rectangle to draw into the destination canvas.
     * @param width  The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     * @param arc		The roundness of the corners
     */
    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, int arc) {
        canvas.draw(new RoundRectangle2D.Double(cx, cy, width, height, arc, arc));
    }

    /**
     *
     * @param initX The x-axis coordinate of the starting point of the line.
     * @param initY The y-axis coordinate of the starting point of the line.
     * @param endX  The x-axis coordinate of the ending point of the line.
     * @param endY  The y-axis coordinate of the ending point of the line.
     */
    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        canvas.drawLine(initX, initY, endX, endY);
    }

    /**
     *
     * Método que dibuja un círculo en el lienzo.
     *
     * @param cx La coordenada x del centro del círculo.
     * @param cy La coordenada y del centro del círculo.
     * @param radius El radio del círculo.
     */
    @Override
    public void drawCircle(float cx, float cy, float radius) {

        // Se crea un objeto de la clase Shape que representa un círculo utilizando la clase Ellipse2D.Double.
        Shape circle = new Ellipse2D.Double(cx - radius, cy - radius, radius * 2.0, radius * 2.0);
        canvas.draw(circle);
    }

    /**
     * {@link IGraphics}
     * @param width Ancho de la resolución.
     * @param height Altura de la resolución.
     */
    @Override
    public void setResolution(int width, int height) {
        window.setSize(width, height);
        transformer.setSize(width, height);
    }

    /**
     * {@link IGraphics}
     * @param color Sets the current color with a raw RGBA integer.
     */
    @Override
    public void setColor(int color) {
        canvas.setColor(new Color(color, true));
    }

    /**
     * {@link IGraphics}
     * @param color Sets the current color with a {@link Color} instance.
     */
    @Override
    public void setColor(mastermind.engine.Color color) {
        canvas.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }

    /**
     * {@link IGraphics}
     * @param font Objeto que implementa la interfaz IFont y representa la fuente.
     */
    @Override
    public void setFont(IFont font) {
        canvas.setFont(((PCFont) font).getUnderlyingFont().deriveFont((float) font.getSize()));
    }

    /**
     * {@link IGraphics}
     * Presenta el contenido del buffer en pantalla, luego libera el lienzo actual
     * y lo reemplaza con uno actualizado.
     */
    @Override
    public void present() {
        // Muestra el contenido del buffer en pantalla.
        buffer.show();
        // Libera el lienzo actual para evitar pérdidas de recursos.
        canvas.dispose();
        // Reemplaza el lienzo con uno actualizado del buffer.
        canvas = (Graphics2D) buffer.getDrawGraphics();
    }

    /**
     * {@link IGraphics}
     * @param color Color con el que se limpiará el objeto (puede ser un valor hexadecimal o un código de color).
     */
    @Override
    public void clear(int color) {
        setColor(color);
        canvas.fillRect(0, 0, window.getWidth(), window.getHeight());
        updateTransformParameters();
    }

    /**
     * {@link IGraphics}
     * @param x La cantidad de unidades a trasladar en el eje x.
     * @param y La cantidad de unidades a trasladar en el eje y.
     */
    @Override
    public void translate(int x, int y) {
        canvas.translate(x, y);
    }

    /**
     * {@link IGraphics}
     * @param x Factor de escala para el eje x.
     * @param y Factor de escala para el eje y.
     */
    @Override
    public void scale(double x, double y) {
        canvas.scale(x, y);
    }

    @Override
    public void save() {
    }

    @Override
    public void restore() {
    }

    @Override
    public int getWidth() {
        return transformer.getWidth();
    }

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
        Insets insets = window.getInsets();
        int contentW = window.getWidth() - insets.left - insets.right;
        int contentH = window.getHeight() - insets.top - insets.bottom;

        transformer.setInset(insets.top, insets.left, insets.bottom, insets.right);
        transformer.update(contentW, contentH);
        transformer.transform(this);
    }
}
