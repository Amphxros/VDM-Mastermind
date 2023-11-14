package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.scenes.GameScene;

public class ColoringButton extends Button implements DaltonicListener {

    int index;
    boolean daltonic;
    private final IFont font;
    public ColoringButton(IScene scene, IFont font, int index, Color color) {
        super(scene);
        setStrokeColor(color);
        this.index=index;
        this.font=font;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        System.out.println("AAA" + index);
        GameScene scene= (GameScene) getScene();
        if(scene!=null){
            scene.onColorClicked(index);
        }
        return super.onTouchDown(event);
    }

    @Override
    public void onDaltonicMode() {

    }

    @Override
    public void setDaltonicMode(boolean mode) {
        this.daltonic=mode;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.setColor(strokeColor);
        graphics.fillCircle(getX()+ getWidth()/2,getY() + getHeight()/2,getWidth());
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        if(daltonic){
            graphics.setFont(font);
            graphics.setColor(new Color(0,0,0));
            graphics.drawText(String.valueOf(index+1),getX(),getY() + getHeight(),getWidth(), HorizontalAlignment.NONE);
        }
    }
}
