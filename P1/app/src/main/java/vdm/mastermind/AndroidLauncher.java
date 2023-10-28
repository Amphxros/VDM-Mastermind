package vdm.mastermind;

import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import vdm.mastermind.androidengine.AndroidEngine;
import vdm.mastermind.logic.Logic;

public class AndroidLauncher extends AppCompatActivity {
    protected AndroidEngine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creamos el SurfaceView que "contendrá" nuestra escena

        SurfaceView renderView = new SurfaceView(this);
        setContentView(renderView);
        engine = new AndroidEngine(renderView, this);
        Logic logic= new Logic(engine);
        engine.setLogic(logic);
        System.out.println("Launched android");

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
