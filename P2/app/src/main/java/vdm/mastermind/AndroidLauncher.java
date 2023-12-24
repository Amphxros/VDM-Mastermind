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
    Logic logic;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "MySharedPreferences";

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

        logic = new Logic(engine);
        engine.setLogic(logic);

        //if the user enters by a notification
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().containsKey("notification")) {
            logic.onNotificationClicked();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        engine.resume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        // Editor object is mandatory for the changes on the SharedPreferences object
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        // PREFERENCES SAVING
        preferencesEditor.apply(); // APPLIES ALL CHANGED PREFERENCES
        logic.onApplicationExit();
        engine.pause();

        handleNotifications();
    }

    private void handleNotifications(){
        INotificationHandler notificationHandler= engine.getNotificationHandler();
        if(notificationHandler!=null){
            ArrayList<Notification> notifications= notificationHandler.getPendingEntries();
            for(Notification n: notifications){
                Data input = new Data.Builder()
                        .putString(NotificationWorker.INPUT_CHANNEL_ID, notificationHandler.getChannelID())
                        .putString(NotificationWorker.INPUT_TITLE, n.getTitle())
                        .putString(NotificationWorker.INPUT_CONTENT, n.getContent())
                        .putString(NotificationWorker.INPUT_BIGGER_TEXT, n.getSubtitle())
                        .putBoolean(NotificationWorker.INPUT_AUTO_CANCEL, true)
                        .build();

                OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInitialDelay(n.getDelay(), TimeUnit.SECONDS)
                        .setInputData(input)
                        .build();
                WorkManager.getInstance(this).enqueue(notificationWork);
            }

            notifications.clear();
        }

    }
}
