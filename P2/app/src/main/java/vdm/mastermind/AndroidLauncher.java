package vdm.mastermind;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mastermind.androidengine.AndroidEngine;
import mastermind.engine.INotificationHandler;
import mastermind.engine.Notification;
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

        //if the user enters by a notification
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().containsKey("notification")) {

        }

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
        //handleNotifications();

    }

    private void handleNotifications(){
        INotificationHandler notificationHandler= engine.getNotificationHandler();
        if(notificationHandler!=null){
            ArrayList<Notification> notifications= notificationHandler.getPendingEntries();
            for(Notification n: notifications){

            }

            notifications.clear();
        }

    }
}
