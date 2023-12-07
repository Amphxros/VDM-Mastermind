package mastermind.engine;

public interface IAdsManager {
    void showBanner();
    void hideBanner();

    void launchRewardedAd();
    boolean hasRewardAdShown();
    void onRewardAdShown();


}
