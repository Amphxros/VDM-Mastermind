package vdm.mastermind.engine.interfaces;

public interface IAdsManager {
    boolean onAdRewardShown();

    void showRewardAd();

    void showBanner(boolean visible);
}
