package vdm.mastermind.logic;

import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.logic.buttons.GameObject;

public final class Cell  extends GameObject {

    private final CellState currentState = CellState.EMPTY;
    private final Color currentColor = new Color(0,0,0);

    public Cell(IScene scene) {
        super(scene);
    }


    public CellState getState(){
        return currentState;
    }
}
