package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.logic.Logic;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.Scene;

/**
 * Clase abstracta que representa un botón para cambiar a una escena específica.
 */
public abstract class GoToScene extends Button{

    /**
     * Constructor de la clase GoToScene.
     *
     * @param scene La escena actual a la que pertenece el botón.
     */
    public GoToScene(IScene scene) {
        super(scene);
    }

    /**
     * Maneja el evento de liberación del botón y cambia la escena a la nueva escena creada.
     *
     * @param event El evento táctil asociado a la liberación del botón.
     * @return Devuelve true para indicar que el evento fue manejado.
     */
    @Override
    public boolean onTouchUp(TouchEvent event) {
        Logic l= (Logic)getEngine().getLogic();
        l.setScene(createScene());
        return true;
    }

    /**
     * Renderiza el botón con una forma redondeada y llama al método render de la clase base.
     *
     * @param graphics El motor gráfico utilizado para renderizar.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        super.render(graphics);

    }

    /**
     * Método abstracto para crear y devolver la nueva escena cuando se activa el botón.
     *
     * @return La nueva instancia de la escena específica.
     */
    protected abstract Scene createScene();
}
