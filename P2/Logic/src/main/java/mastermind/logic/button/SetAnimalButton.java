package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.AnimalID;


public class SetAnimalButton extends Button{
    AnimalID animalID;
    public SetAnimalButton(IScene scene, AnimalID animalID) {
        super(scene);
        this.animalID=animalID;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(), getY(), getWidth(), getHeight(),30);
        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {

        return super.onTouchDown(event);
    }
}
