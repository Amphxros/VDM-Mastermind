package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.PlayerData;

public abstract class BuyItemButton extends Button{

    ISound sound;
    int price;
    public boolean isLocked;
    public BuyItemButton(IScene scene, int price, boolean isLocked, ISound sound) {
        super(scene);
        this.sound=sound;
        this.price=price;
        this.isLocked=isLocked;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        PlayerData p= (PlayerData)getEngine().getLogic().getLogicData();
        if(p.getCoins()>=this.price && isLocked){
            p.setCoins(p.getCoins() - this.price);
            isLocked=false;
            unlockElem();
            System.out.println("buy");
        }
        else{
            setElem();
        }
        return super.onTouchDown(event);
    }

    public abstract void setElem();
    public abstract void unlockElem();
}
