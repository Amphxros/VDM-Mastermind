package mastermind.logic;

import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;

public class ColouringCell extends Button {

    int value;
    public ColouringCell(IScene scene, int value) {
        super(scene);
        this.value=value;
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        return true;
    }
}
