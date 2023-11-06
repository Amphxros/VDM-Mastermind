package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.DaltonicListener;

public class ChooseColors extends Button implements DaltonicListener {

    boolean daltonic = false;
    private int index;
    private Color currentColor;
    public ChooseColors(IScene scene) {
        super(scene);
    }

    @Override
    public void onDaltonicMode() {

    }

    @Override
    public void setDaltonicMode(boolean mode) {

    }

    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.setColor(strokeColor);
        graphics.drawCircle(getX(),getY(),getWidth());

        if(daltonic){
            graphics.drawText(String.valueOf(index),getX(),getY(),getWidth(), HorizontalAlignment.NONE);
        }

    }
}
