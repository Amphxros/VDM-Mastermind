package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.ISound;
import mastermind.logic.scene.IScene;

public class ContainerScroll extends Container implements IScrollable{

    int limitUP;
    int limitDOWN;

    /**
     * Constructor que asigna la escena al objeto.
     *
     * @param scene La escena a la que pertenece el objeto.
     */
    public ContainerScroll(IScene scene, int limitUp, int LimitDown) {
        super(scene);
        this.limitUP = limitUp;
        this.limitDOWN = LimitDown;
    }

    @Override
    public void onVerticalScroll(float inputY) {
        
        if(((inputY < 0) && (this.getY() > limitDOWN)) || ((inputY > 0) && (this.getY() < limitUP))){
            move(0, (int)inputY);
        }
    }
}
