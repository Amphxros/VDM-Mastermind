package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.logic.GameObject;

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
        graphics.setColor(strokeColor);
        if(cellState==CellState.Empty) {
            graphics.setColor(Color.BLACK);
            graphics.drawCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else if(cellState==CellState.Misplaced){
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else if(cellState==CellState.Correct){
            graphics.setColor(Color.GREEN);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else{
            graphics.setColor(Color.RED);
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }

        super.render(graphics);
    }
}
