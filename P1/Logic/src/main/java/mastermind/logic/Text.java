package mastermind.logic;

import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase Text que representa un objeto de texto en un escenario (scene) de juego.
 * Extiende la clase GameObject e implementa la interfaz renderizable.
 */
public final class Text extends GameObject{
    private final IFont font; // Fuente del texto.
    private String text; // Contenido del texto.

    private HorizontalAlignment alignment; // Alineación horizontal del texto.

    /**
     * Constructor de la clase Text.
     *
     * @param scene      El escenario al que pertenece el objeto de texto.
     * @param text       El contenido del texto.
     * @param font       La fuente utilizada para el texto.
     */
    public Text(IScene scene, String text, IFont font) {
        //La alineación horizontal del texto (predeterminado: CENTRE).
        this(scene, text, font, HorizontalAlignment.CENTRE);
    }

    /**
     * Constructor de la clase Text que permite especificar la alineación horizontal.
     *
     * @param scene      El escenario al que pertenece el objeto de texto.
     * @param text       El contenido del texto.
     * @param font       La fuente utilizada para el texto.
     * @param alignment  La alineación horizontal del texto.
     */
    public Text(IScene scene, String text, IFont font, HorizontalAlignment alignment) {
        super(scene);
        this.text = text;
        this.font = font;
        this.alignment = alignment;
    }

    /**
     * Establece el contenido del texto.
     *
     * @param text El nuevo contenido del texto.
     * @return Una referencia a la instancia actual de Text.
     */
    public Text setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Establece la alineación horizontal del texto.
     *
     * @param alignment La nueva alineación horizontal.
     */
    public void setAlignment(HorizontalAlignment alignment){
        this.alignment=alignment;
    }

    /**
     * Método de renderizado que dibuja el texto en el escenario.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        // Configura el color del trazo, la fuente y la alineación antes de dibujar el texto.
        graphics.setColor(strokeColor);
        graphics.setFont(font);
        graphics.setTextAlignment(alignment);

        // Dibuja el texto en las coordenadas del objeto Text.
        graphics.drawText(text, getX(), getY());
    }
}
