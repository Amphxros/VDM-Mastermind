package mastermind.androidengine;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import mastermind.engine.IAdsManager;

public final class AdsManager implements IAdsManager {

    AppCompatActivity activity;
    Context context;
    private final AdView adView;
    private final String adToken;
    private final AdRequest adRequest;


    public AdsManager(AppCompatActivity activity, AdView view, Context context){
        MobileAds.initialize(context);
        this.adView= view;
        this.adToken= this.adView.getAdUnitId();
        this.adRequest= new AdRequest.Builder().build();
        this.adView.loadAd(this.adRequest);
    }
}
