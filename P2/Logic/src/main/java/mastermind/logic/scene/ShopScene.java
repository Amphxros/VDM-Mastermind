package mastermind.logic.scene;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.logic.Scene;

public class ShopScene extends Scene {
    public ShopScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",30,false);




        super.init();
    }
}
