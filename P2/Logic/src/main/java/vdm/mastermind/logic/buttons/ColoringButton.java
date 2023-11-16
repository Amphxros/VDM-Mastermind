package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.enums.HorizontalAlignment;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.scenes.GameScene;

public class ColoringButton extends Button implements DaltonicListener {

    int index;
    boolean daltonic;
    private final IFont font;
    IImage image;
    public ColoringButton(IScene scene, IFont font, int index, IImage image) {
        super(scene);
        this.image=image;
        this.index=index;
        this.font=font;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        GameScene scene= (GameScene) getScene();
        if(scene!=null){

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
        if(daltonic){
            graphics.setFont(font);
            graphics.setColor(new Color(0,0,0));
            graphics.drawText(String.valueOf(index+1),getX(),getY() + getHeight(),getWidth(), HorizontalAlignment.NONE);
        }
    }
}
