package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase que representa un contenedor vacío con la funcionalidad de contener otros objetos.
 */
public class Container extends GameObject{
    boolean myOpaque = false;

    /**
     * Constructor de la clase Container.
     *
     * @param scene La escena a la que pertenece el contenedor.
     */
    public Container(IScene scene) {
        super(scene);
    }


    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        if(myOpaque) {
            graphics.fillRectangle(getX(),getY(),getWidth(),getHeight());
        }
        //graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        super.render(graphics);
    }

    public void setOpaque(boolean opaque)
    {
        this.myOpaque = opaque;
    }
}
