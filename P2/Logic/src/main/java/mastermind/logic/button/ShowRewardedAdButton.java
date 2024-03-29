package mastermind.logic.button;

import mastermind.engine.IAdsManager;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;

public abstract class ShowRewardedAdButton extends Button{
    public ShowRewardedAdButton(IScene scene) {
        super(scene);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        IAdsManager adsManager=getEngine().getAdsManager();
        if(adsManager!=null){
            adsManager.showRewardAd();
        }
        return super.onTouchDown(event);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(), getHeight(),30);
        super.render(graphics);
    }

    @Override
    public void update(double delta) {
        if(getEngine().getAdsManager()!=null &&
        getEngine().getAdsManager().onAdRewardShown()){
            giveReward();
        }
        super.update(delta);
    }

    protected abstract void giveReward();
}
