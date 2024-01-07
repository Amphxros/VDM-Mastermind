package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
import mastermind.logic.scene.GameScene;

/**
 * Clase que representa una casilla coloreable que implementa la interfaz DaltonicListener.
 * Rellena la casilla con un valor asociado cuando es seleccionada.
 */
public class ColouringCell extends Button implements DaltonicListener{

    int value; // Valor asociado a la casilla.
    boolean daltonic_mode=false; // Indica si la casilla está en modo daltónico.

    /**
     * Constructor de la clase ColouringCell.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param value El valor asociado a la casilla.
     */
    public ColouringCell(IScene scene, int value) {
        super(scene);
        this.value=value;
    }

    /**
     * Maneja el evento de toque en la casilla y notifica a la escena correspondiente.
     *
     * @param event Evento de toque recibido.
     * @return true para indicar que el evento fue manejado.
     */
    @Override
    public boolean onTouchDown(TouchEvent event) {
        GameScene scene= (GameScene) getScene();
        if(scene!=null)
            scene.onColouringCellSelected(strokeColor,value);
        return true;
    }

    /**
     * Maneja el evento de toque en la casilla y notifica a la escena correspondiente.
     *
     * @param event Evento de toque recibido.
     * @return true para indicar que el evento fue manejado.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillCircle(getX() + getWidth()/2,getY()+ getHeight()/2, getWidth()/2);
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        drawDaltonicInfo(graphics);
        super.render(graphics);
    }

    /**
     * Establece el modo daltónico para la casilla.
     *
     * @param mode Valor del modo daltónico.
     */
    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic_mode=mode;
    }

    /**
     * Dibuja información daltónica en la casilla si el modo daltónico está activado.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        if(daltonic_mode){
            graphics.setColor(Color.BLACK);
            graphics.drawText(String.valueOf(value + 1),getX() + getWidth()/2, getY()+ 3*getHeight()/4);
        }
    }
}
