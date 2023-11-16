package vdm.mastermind.logic;

import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.buttons.Button;
import vdm.mastermind.logic.gameobjects.GameObject;

public final class Cell  extends Button implements DaltonicListener {

    private CellState currentState = CellState.EMPTY;
    private boolean daltonic;
    private int index=-1; //value of the index
    IFont font;
    IImage image;
    public Cell(IScene scene, IFont font) {
        super(scene);
        this.font=font;
    }


    public CellState getState(){
        return currentState;
    }
    public void setState(CellState newState) { currentState = newState; };

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
        graphics.fillCircle(getX(),getY(),getWidth());

        if(daltonic && currentState!=CellState.EMPTY){
            graphics.setFont(font);
            graphics.setColor(new Color(0,0,0));
            graphics.drawText(String.valueOf(index +1),getX()-getWidth()/2,getY() + getHeight()/2,getWidth(), HorizontalAlignment.NONE);
        }

    }

    public void setIndex(int index) {
        if(index!=-1){
            currentState=CellState.FILLED;
        }
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
