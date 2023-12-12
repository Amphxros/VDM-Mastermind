package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.ShopScene;

public class ShopButtons extends Button{

    int id;
    public ShopButtons(IScene scene, int id) {
        super(scene);
        this.id=id;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        ShopScene shop= (ShopScene) getScene();
        if(shop!=null){
            shop.onClick(id);
        }
        return super.onTouchDown(event);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(), getHeight(), 20);
        super.render(graphics);
    }
}
