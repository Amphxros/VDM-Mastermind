package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.AnimalID;


public class SetAnimalButton extends Button{
    IImage image;
    AnimalID animalID;
    public SetAnimalButton(IScene scene, IImage image, AnimalID animalID) {
        super(scene);
        this.image=image;
        this.animalID=animalID;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.drawImage(image,getX(), getY(), getWidth(), getHeight());
        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {

        return super.onTouchDown(event);
    }
}
