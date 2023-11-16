package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.scenes.Scene;
import vdm.mastermind.logic.scenes.ShopScene;

public class GoToShopScene extends GoToSceneButton{
    IImage image;
    public GoToShopScene(IScene scene, IImage image) {
        super(scene);
        this.image=image;
    }

    @Override
    protected Scene createScene() {
        return new ShopScene(getEngine());
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);
        if(image!=null)
            graphics.drawImage(this.image, getX(),getY(),getWidth(),getHeight());
        else{
            graphics.setColor(strokeColor);
            graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        }
    }
}
