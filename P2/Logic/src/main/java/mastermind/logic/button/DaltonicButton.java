package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.Scene;
import mastermind.logic.scene.GameScene;

public class DaltonicButton extends Button{
    IImage open, close;
    boolean daltonic= false;
    public DaltonicButton(IScene scene, IImage enable, IImage disable) {
        super(scene);
        this.open=enable;
        this.close=disable;

    }

    @Override
    public void render(IGraphics graphics) {
        if(daltonic){
            graphics.drawImage(open, getX(), getY(), getWidth(), getHeight());
        }
        else{
            graphics.drawImage(close, getX(), getY(), getWidth(), getHeight());
        }

        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        daltonic=!daltonic;
        GameScene scene= (GameScene) getScene();
        if(scene!=null){
            scene.onDaltonicMode(daltonic);
        }

        return daltonic;
    }
}
