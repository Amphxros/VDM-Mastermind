package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
import mastermind.logic.scene.GameScene;

public class ColouringCell extends Button implements DaltonicListener{

    int value;
    boolean daltonic_mode=false;
    public ColouringCell(IScene scene, int value) {
        super(scene);
        this.value=value;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        GameScene scene= (GameScene) getScene();
        if(scene!=null)
            scene.onColouringCellSelected(strokeColor,value);
        return true;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillCircle(getX() + getWidth()/2,getY()+ getHeight()/2, getWidth()/2);
        //graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        drawDaltonicInfo(graphics);
        super.render(graphics);
    }

    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic_mode=mode;
    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        if(daltonic_mode){
            graphics.setColor(Color.BLACK);
            graphics.drawText(String.valueOf(value + 1),getX(), getY() + getHeight()/2);
        }
    }
}