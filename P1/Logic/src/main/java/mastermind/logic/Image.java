package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.logic.scene.IScene;

/**
 * Clase Image que representa un objeto de imagen en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para renderizar imágenes en el juego.
 */
public class Image extends GameObject{
    private IImage image; // Objeto IImage que representa la imagen a ser renderizada.

    /**
     * Constructor de la clase Image.
     *
     * @param scene El escenario al que pertenece el objeto de imagen.
     * @param image Objeto IImage que representa la imagen.
     */
    public Image(IScene scene, IImage image) {
        super(scene);
        this.image = image;
    }

    /**
     * Establece la imagen del objeto Image.
     *
     * @param image Nuevo objeto IImage que representa la imagen.
     * @return Una referencia a la instancia actual de Image.
     */
    public Image setImage(IImage image) {
        this.image = image;
        return this;
    }

    /**
     * Método de renderizado que dibuja la imagen en el escenario.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.drawImage(image, getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}
