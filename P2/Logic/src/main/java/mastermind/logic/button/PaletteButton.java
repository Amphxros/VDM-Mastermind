package mastermind.logic.button;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.PaletteID;
import mastermind.logic.PlayerData;

public class PaletteButton extends BuyItemButton{
    PaletteID paletteID;

    public PaletteButton(IScene scene, PaletteID paletteID, ISound sound, int price, boolean isLocked) {
        super(scene,price,isLocked,sound);
        this.paletteID=paletteID;

    }

    @Override
    public void render(IGraphics graphics) {
        PlayerData playerData= (PlayerData) getScene().getLogicData();

        if(isLocked){
            graphics.setColor(Color.RED);
        }
        else if(playerData.getCurrentPalette()==paletteID){
            graphics.setColor(Color.YELLOW);
        }
        else{
            graphics.setColor(Color.GREEN);
        }

        graphics.fillRoundRectangle(getX(), getY(), getWidth(), getHeight(),30);

        super.render(graphics);

    }

    @Override
    public void setElem() {
        PlayerData p= (PlayerData) getScene().getLogicData();
        p.setCurrentPalette(this.paletteID);

    }

    @Override
    public void unlockElem() {
        PlayerData p= (PlayerData) getScene().getLogicData();

    }
}
