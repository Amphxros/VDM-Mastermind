package mastermind.logic.button.GoTo;

import mastermind.engine.IScene;
import mastermind.logic.button.GoTo.GoToScene;
import mastermind.logic.scene.Scene;
import mastermind.logic.scene.ShopScene;

public class GoToShopScene extends GoToScene {
    public GoToShopScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new ShopScene(getEngine());
    }
}
