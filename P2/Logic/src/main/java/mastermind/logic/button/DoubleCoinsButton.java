package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.logic.PlayerData;

public class DoubleCoinsButton extends ShowRewardedAdButton{
   int amount;
    public DoubleCoinsButton(IScene scene, int amount) {
        super(scene);
        this.amount=amount;
    }

    @Override
    protected void giveReward() {

    }
}
