package mastermind.engine;

/**
 * Interfaz que define un gestor de anuncios
 */
public interface IAdsManager {
    boolean onAdRewardShown();

    void showRewardAd();

    void showBanner(boolean visible);


}
