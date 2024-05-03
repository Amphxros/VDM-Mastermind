package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase HintElem que representa un elemento de pista en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para visualizar pistas asociadas con las celdas del juego.
 */
public class HintElem extends GameObject {
    private CellState cellState; // Estado del elemento de pista (Empty, Misplaced, Correct, Wrong).

    /**
     * Constructor de la clase HintElem.
     *
     * @param scene El escenario al que pertenece el elemento de pista.
     */
    public HintElem(IScene scene) {
        super(scene);
        this.cellState=CellState.Empty;
    }

    /**
     * Establece el estado del elemento de pista.
     *
     * @param cellState Nuevo estado del elemento de pista.
     */
    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    /**
     * Obtiene el estado actual del elemento de pista.
     *
     * @return El estado actual del elemento de pista.
     */
    public CellState getCellState(){return this.cellState;}

    /**
     * Método de renderizado que dibuja el elemento de pista según su estado.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics) {

        // Dibuja el elemento de pista según su estado.
        graphics.setColor(strokeColor);
        if(cellState==CellState.Empty) {
            graphics.setColor(Color.GRAY);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else if(cellState==CellState.Misplaced){

            graphics.setColor(Color.WHITE);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
            graphics.setColor(Color.BLACK);
            graphics.drawCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);

        }
        else if(cellState==CellState.Correct){
            graphics.setColor(Color.BLACK);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else{ // CellState.WRONG
            graphics.setColor(Color.GRAY);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }

        super.render(graphics);
    }
}
