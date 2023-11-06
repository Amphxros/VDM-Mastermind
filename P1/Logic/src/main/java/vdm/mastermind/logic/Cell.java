package vdm.mastermind.logic;

import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.logic.buttons.Button;
import vdm.mastermind.logic.gameobjects.GameObject;

public final class Cell  extends Button implements DaltonicListener {

    private final CellState currentState = CellState.EMPTY;
    private final Color currentColor = new Color(0,0,0);
    private boolean daltonic;
    private int index;
    public Cell(IScene scene) {
        super(scene);
    }


    public CellState getState(){
        return currentState;
    }

    @Override
    public void onDaltonicMode() {

    }

    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic=mode;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.setColor(strokeColor);
        graphics.drawCircle(getX(),getY(),getWidth());

        if(daltonic){
            graphics.drawText(String.valueOf(index),getX(),getY(),getWidth(), HorizontalAlignment.NONE);
        }

    }
}
