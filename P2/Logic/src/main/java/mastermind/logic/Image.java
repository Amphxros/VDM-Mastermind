package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
/**
 * Clase Image que representa un objeto de imagen en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para renderizar una imagen en el juego.
 * Similar a lo que actualmente conocemos como Sprites
 */
public class Image extends GameObject{
    private IImage image;

    public Image(IScene scene, IImage image) {
        super(scene);
        this.image = image;
    }

    public Image setImage(IImage image) {
        this.image = image;
        return this;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.drawImage(image, getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}
