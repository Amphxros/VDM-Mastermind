package vdm.mastermind;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import vdm.mastermind.androidengine.AndroidEngine;

public class AndroidLauncher extends AppCompatActivity {
    protected AndroidEngine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creamos el SurfaceView que "contendrá" nuestra escena

        SurfaceView renderView = new SurfaceView(this);
        setContentView(renderView);

        engine = new AndroidEngine(renderView, this);
        //engine.getGraphics().setResolution(400, 600);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
