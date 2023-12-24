package mastermind.logic.button;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.ISound;

public class BackgroundButton extends BuyItemButton{
    public BackgroundButton(IScene scene, int price, boolean isLocked, ISound sound) {
        super(scene, price, isLocked, sound);
    }

    @Override
    public void setElem() {

    }

    @Override
    public void unlockElem() {

    }

    @Override
    public void render(IGraphics graphics) {
        if(isLocked){
            graphics.setColor(Color.RED);
        }
        else{
            graphics.setColor(Color.GREEN);
        }

        graphics.fillRoundRectangle(getX(), getY(), getWidth(), getHeight(),30);

        super.render(graphics);
    }
}
