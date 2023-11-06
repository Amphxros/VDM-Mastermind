package vdm.mastermind.logic.gameobjects;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.buttons.ColoringButton;

public class ColorTable extends GameObject implements DaltonicListener {
    int numColors;
    Color[] colors;

   ArrayList<ColoringButton> coloringButtons;
    public ColorTable(IScene scene, int numColors, Color[] colors) {
        super(scene);
        this.numColors=numColors;
        this.coloringButtons= new ArrayList<>();
        this.colors= new Color[this.numColors];

        for(int i=0;i<this.numColors;i++){
            this.colors[i]=colors[i];
        }
    }

    @Override
    public void init() {
        for(int i= 0; i<this.numColors;i++){
            this.coloringButtons.add( createButton(i, this.colors[i], 20 * (i + 1)));
        }
        super.init();
    }

    private ColoringButton createButton(int index, Color c, int posX)
    {
        ColoringButton coloringButton= new ColoringButton(getScene(), index, c);
        coloringButton.setPosition(posX, getY()- getHeight()/2);
        return coloringButton;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(), getY(),getWidth(), getHeight(),50);
        for(ColoringButton c: coloringButtons){
            c.render(graphics);
        }
    }

    @Override
    public void onDaltonicMode() {

    }

    @Override
    public void setDaltonicMode(boolean mode) {
        for(ColoringButton c: coloringButtons){
            c.setDaltonicMode(mode);
        }
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        for(ColoringButton c: coloringButtons){
            if(c.handleInput(event)){
                return true;
            }
        }
        return false;
    }
}
