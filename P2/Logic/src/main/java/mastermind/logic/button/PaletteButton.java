package mastermind.logic.button;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.PlayerData;

public class PaletteButton extends BuyItemButton{
    Color background;
    Color buttons;
    Color font;
    Color tittle;

    public PaletteButton(IScene scene, Color background, Color buttons, Color tittle, Color font, boolean isLocked, ISound sound, int price) {
        super(scene,price,isLocked,sound);
        this.background=background;
        this.buttons=buttons;
        this.font=font;
        this.tittle=tittle;

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

    @Override
    public void setElem() {
        PlayerData p= (PlayerData)getEngine().getLogic().getLogicData();
        p.setBackground(background);
        p.setButtons(buttons);
        p.setFont(font);
        p.setTittle(tittle);
    }

    @Override
    public void unlockElem() {

    }
}
