package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.EventType;
import mastermind.engine.IFont;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.IScene;

public class ContainerScroll extends Container implements IScrollable{

    int limitUP;
    int limitDOWN;

    int vel;

    /**
     * Constructor que asigna la escena al objeto.
     *
     * @param scene La escena a la que pertenece el objeto.
     */
    public ContainerScroll(IScene scene, int limitUp, int LimitDown) {
        super(scene);
        this.limitUP = limitUp;
        this.limitDOWN = LimitDown;
        this.vel = 5;
    }

    @Override
    public void onVerticalScroll(float inputY) {

        move(0, (int)inputY);
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        if (event.getType() == EventType.DRAG_UP) {
            onVerticalScroll(-vel);
        }else if(event.getType() == EventType.DRAG_DOWN){
            onVerticalScroll(vel);
        }
        return super.handleInput(event);
    }
}
