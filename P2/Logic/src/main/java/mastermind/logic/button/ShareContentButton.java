package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.IShareContentManager;
import mastermind.engine.TouchEvent;

public class ShareContentButton extends Button{
    public ShareContentButton(IScene scene) {
        super(scene);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        super.render(graphics);


    }

    @Override
    public boolean onTouchUp(TouchEvent event) {
        IShareContentManager shareContentManager= getEngine().getShareContentManager();

        if(shareContentManager!=null){
            shareContentManager.share();
        }

        return super.onTouchUp(event);
    }
}
