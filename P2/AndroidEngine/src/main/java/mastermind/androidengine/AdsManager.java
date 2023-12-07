package mastermind.androidengine;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import mastermind.engine.IAdsManager;

public final class AdsManager implements IAdsManager {

    AppCompatActivity activity;
    Context context;
    private final AdView adView;
    private final String adToken;
    private final AdRequest adRequest;

    private RewardedAd rewardAd;
    private boolean hasRewardAdShown;


    public AdsManager(AppCompatActivity activity, AdView view, Context context){
        MobileAds.initialize(context);
        this.adView= view;
        this.adToken= this.adView.getAdUnitId();
        this.adRequest= new AdRequest.Builder().build();
        this.adView.loadAd(this.adRequest);
        this.activity=activity;
        this.hasRewardAdShown=false;

    }

    @Override
    public void showBanner() {
        this.adView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBanner() {
        this.adView.setVisibility(View.GONE);
    }

    @Override
    public void launchRewardedAd() {
        if(rewardAd!=null) {
            this.activity.runOnUiThread(new ShowRewardAd(this.activity));
        }
        else{
           System.out.println("Not yet");
        }
    }

    @Override
    public boolean hasRewardAdShown() {
        boolean reward= hasRewardAdShown;
        hasRewardAdShown=false;
        return reward;
    }

    @Override
    public void onRewardAdShown() {

    }

    public void loadRewardAd(){
        RewardedAd.load(this.activity, this.adToken, adRequest,
                new UserRewardedAdLoadCallback());
    }

    private class ShowRewardAd implements Runnable {
        private final AppCompatActivity activity;

        ShowRewardAd(AppCompatActivity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            if (rewardAd == null) {
                System.out.println("Reward ad not set up yet.");
            } else {
                // show ad (we assume one has been preloaded before)
                rewardAd.setFullScreenContentCallback(new UserFullScreenContentCallback());
                rewardAd.show(this.activity, new UserEarnedRewardListener());
                rewardAd = null;
            }

            // load ad for next showing
            loadRewardAd();
        }
    }
    private class UserEarnedRewardListener implements OnUserEarnedRewardListener {
        /**
         * Handle the rewards.
         *
         * @param rewardItem The reward to give.
         */
        @Override
        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            System.out.println("Rewarded granted.");
            hasRewardAdShown = true;
        }
    }

    /**
     *
     */
    private class UserRewardedAdLoadCallback extends RewardedAdLoadCallback {
        /**
         * Handle the error.
         *
         * @param loadAdError The error.
         */
        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

            System.err.println("Rewarded ad not loaded correctly");
            rewardAd = null;
        }

        @Override
        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
            // Obtain data
            System.out.println("Rewarded ad properly loaded");
            rewardAd = rewardedAd;
        }
    }


    // full screen callback
    private class UserFullScreenContentCallback extends FullScreenContentCallback {
        /**
         *  Called when a click is recorded for an ad.
         */
        @Override
        public void onAdClicked() {
            System.out.println("Ad was clicked.");
        }

        /**
         * Called when ad is dismissed.
         */
        @Override
        public void onAdDismissedFullScreenContent() {

            // Set the ad reference to null so you don't show the ad a second time.
            rewardAd = null;
        }

        /**
         * Called when ad fails to show.
         *
         * @param adError The error.
         */
        @Override
        public void onAdFailedToShowFullScreenContent(AdError adError) {

            rewardAd = null;
        }

        /**
         * Called when an impression is recorded for an ad.
         */
        @Override
        public void onAdImpression() {
            System.out.println("Ad recorded an impression.");
        }

        /**
         * Called when ad is shown
         */
        @Override
        public void onAdShowedFullScreenContent() {
            System.out.println("Ad showed fullscreen content.");
        }
    }

}
