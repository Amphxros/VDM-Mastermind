package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;
import mastermind.logic.scene.ShopScene;

public class GoToShopScene extends GoToScene{
    public GoToShopScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new ShopScene(getEngine());
    }
}
