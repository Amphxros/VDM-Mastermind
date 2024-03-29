package mastermind.logic.button;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.ISound;
import mastermind.logic.AnimalID;
import mastermind.logic.PlayerData;


public class SetAnimalButton extends BuyItemButton{
    AnimalID animalID;
    public SetAnimalButton(IScene scene, AnimalID animalID, int price, boolean isLocked, ISound sound) {
        super(scene,price,isLocked,sound);
        this.animalID=animalID;
    }

    @Override
    public void render(IGraphics graphics) {
        PlayerData p= (PlayerData)getEngine().getLogic().getLogicData();
        if(isLocked){
            graphics.setColor(Color.RED);
        }
        else if(p.getCurrentAnimalID()==this.animalID){
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
        PlayerData p= (PlayerData)getEngine().getLogic().getLogicData();
        p.setCurrentAnimalID(this.animalID);
    }

    @Override
    public void unlockElem() {
        PlayerData p= (PlayerData)getEngine().getLogic().getLogicData();
        p.unlockedAnimal(this.animalID.ordinal());

    }

}
