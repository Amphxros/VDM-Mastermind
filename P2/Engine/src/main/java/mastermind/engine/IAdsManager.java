package mastermind.engine;

public interface IAdsManager {
    boolean onAdRewardShown();

    void showRewardAd();

    void showBanner(boolean visible);
}
