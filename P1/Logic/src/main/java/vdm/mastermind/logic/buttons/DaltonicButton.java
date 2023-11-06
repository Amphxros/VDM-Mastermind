package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.engine.interfaces.objects.ISound;
import vdm.mastermind.logic.scenes.GameScene;

public class DaltonicButton extends Button{
    boolean datonic_mode;

    IImage image_open;
    IImage image_close;

    ISound sound;
    public DaltonicButton(IScene scene, IImage imageopen, IImage imageclose, ISound sound) {
        super(scene);
        this.datonic_mode=false;
        this.image_open=imageopen;
        this.image_close=imageclose;
        this.sound= sound;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        if(datonic_mode){
            graphics.drawImage(image_open, getX(),getY(),getWidth(), getHeight());
        }
        else{
            graphics.drawImage(image_close, getX(),getY(),getWidth(), getHeight());

        }

    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        datonic_mode=!datonic_mode;
        GameScene scene= (GameScene) getScene();
        if(scene!=null){
            scene.onDaltonicMode(datonic_mode);
        }

        return super.onTouchDown(event);
    }


}
