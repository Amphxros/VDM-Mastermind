package vdm.mastermind;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import mastermind.androidengine.AndroidEngine;
import mastermind.logic.Logic;

public class AndroidLauncher extends AppCompatActivity{
    AndroidEngine engine;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creamos el SurfaceView que "contendrá" nuestra escena
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout, null, false);
        SurfaceView renderView = layout.findViewById(R.id.surfaceView);
        AdView adView = layout.findViewById(R.id.adView);
        setContentView(layout);



        engine = new AndroidEngine(this,renderView,adView, this);
        engine.getGraphics().setResolution(400, 600);

        Logic logic = new Logic(engine);
        engine.setLogic(logic);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        engine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        engine.pause();
    }
}
