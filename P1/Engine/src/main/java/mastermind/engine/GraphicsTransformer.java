package mastermind.engine;

/**
 * Clase que gestiona la transformación gráfica para adaptar la escala y posición del contenido en una ventana.
 */
public final class GraphicsTransformer {
    private int width = 600;
    private int height = 800;
    private double ratio = width / (double) height;
    private int contentWidth = 0;
    private int contentHeight = 0;
    private double contentScale = 1;
    private int contentOffsetX = 0;
    private int contentOffsetY = 0;
    private int contentInsetT = 0;
    private int contentInsetL = 0;
    private int contentInsetB = 0;
    private int contentInsetR = 0;

    /**
     * Establece el tamaño de la ventana.
     *
     * @param width  Ancho de la ventana.
     * @param height Altura de la ventana.
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.ratio = width / (double) height;
    }

    /**
     * Obtiene el ancho de la ventana.
     *
     * @return Ancho de la ventana.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Obtiene la altura de la ventana.
     *
     * @return Altura de la ventana.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Establece los márgenes internos del contenido.
     *
     * @param top    Margen superior.
     * @param left   Margen izquierdo.
     * @param bottom Margen inferior.
     * @param right  Margen derecho.
     */
    public void setInset(int top, int left, int bottom, int right) {
        contentInsetT = top;
        contentInsetL = left;
        contentInsetB = bottom;
        contentInsetR = right;
    }

    /**
     * Actualiza las dimensiones del contenido y recalcula la escala y posición.
     *
     * @param contentWidth  Ancho del contenido.
     * @param contentHeight Altura del contenido.
     */
    public void update(int contentWidth, int contentHeight) {
        boolean changed = false;

        if (contentWidth != this.contentWidth) {
            this.contentWidth = contentWidth;
            changed = true;
        }

        if (contentHeight != this.contentHeight) {
            this.contentHeight = contentHeight;
            changed = true;
        }

        if (!changed) return;

        double contentRatio = contentWidth / (double) contentHeight;
        if (ratio >= contentRatio) {
            contentScale = contentWidth / (double) width;
            contentOffsetX = 0;
            contentOffsetY = (int) ((contentHeight - height * contentScale) / 2.0);
        } else {
            contentScale = contentHeight / (double) height;
            contentOffsetX = (int) ((contentWidth - width * contentScale) / 2.0);
            contentOffsetY = 0;
        }
    }

    /**
     * Aplica la transformación al motor gráfico.
     *
     * @param graphics Motor gráfico al que se aplicará la transformación.
     */
    public void transform(IGraphics graphics) {
        graphics.translate(contentInsetL, contentInsetT);
        graphics.translate(contentOffsetX, contentOffsetY);
        graphics.scale(contentScale, contentScale);
    }

    /**
     * Obtiene la coordenada X transformada a partir de la coordenada original.
     *
     * @param x Coordenada X original.
     * @return Coordenada X transformada.
     */
    public int getTransformedX(int x) {
        int out = (int) ((x - contentInsetL - contentOffsetX) / contentScale);
        return out < 0 || out > width ? -1 : out;
    }

    /**
     * Obtiene la coordenada Y transformada a partir de la coordenada original.
     *
     * @param y Coordenada Y original.
     * @return Coordenada Y transformada.
     */
    public int getTransformedY(int y) {
        int out = (int) ((y - contentInsetT - contentOffsetY) / contentScale);
        return out < 0 || out > height ? -1 : out;
    }
}
