package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase que representa un contenedor vacío con la funcionalidad de contener otros objetos.
 */
public class Container extends GameObject{
    /**
     * Constructor de la clase Container.
     *
     * @param scene La escena a la que pertenece el contenedor.
     */
    public Container(IScene scene) {
        super(scene);
    }

    /**
     * Constructor de la clase Container.
     *
     * @param scene La escena a la que pertenece el contenedor.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        super.render(graphics);
    }


}
