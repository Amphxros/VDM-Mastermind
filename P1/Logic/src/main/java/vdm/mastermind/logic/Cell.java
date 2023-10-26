package vdm.mastermind.logic;

import vdm.mastermind.logic.CellState;
import vdm.mastermind.engine.classes.Color;

public final class Cell  extends  GameObject{

    private final CellState currentState = CellState.EMPTY;
    private final Color currentColor = new Color(0,0,0);

    public Cell(){

    }

    public CellState getState(){
        return currentState;
    }
}
