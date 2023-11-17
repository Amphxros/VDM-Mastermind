package vdm.mastermind;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import mastermind.androidengine.AndroidEngine;
import mastermind.logic.Logic;

public class AndroidLauncher extends AppCompatActivity{
    AndroidEngine engine;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creamos el SurfaceView que "contendrá" nuestra escena
        SurfaceView renderView = new SurfaceView(this);
        setContentView(renderView);

        engine = new AndroidEngine(renderView, this);
        engine.getGraphics().setResolution(400, 600);

        Logic logic = new Logic(engine);
        engine.setLogic(logic);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
    }
}
