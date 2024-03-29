package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
/**
 * Clase HintElem que representa un elemento de pista en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para visualizar pistas asociadas con las celdas del juego.
 */
public class HintElem extends GameObject {
    private CellState cellState;
    public HintElem(IScene scene) {
        super(scene);
        this.cellState=CellState.Empty;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState(){return this.cellState;}
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        if(cellState==CellState.Empty) {
            graphics.drawCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else if(cellState==CellState.Misplaced){
            graphics.setColor(Color.YELLOW);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else if(cellState==CellState.Correct){
            graphics.setColor(Color.GREEN);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else{
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }

        super.render(graphics);
    }
}
