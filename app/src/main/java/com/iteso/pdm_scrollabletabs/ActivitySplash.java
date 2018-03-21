package com.iteso.pdm_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iteso.pdm_scrollabletabs.beans.Store;
import com.iteso.pdm_scrollabletabs.beans.User;
import com.iteso.pdm_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm_scrollabletabs.database.StoreControl;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    public static final String USER_PREFERENCES = "com.iteso.USER_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                Intent mainIntent;
                if(user.isLogged())
                    mainIntent = new Intent().setClass(ActivitySplash.this, ActivityMain.class);
                else
                    mainIntent = new Intent().setClass(ActivitySplash.this, ActivityLogin.class);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);

        DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplash.this);
        Store store1 = new Store();
        Store store2 = new Store();
        Store store3 = new Store();
        StoreControl storeControl = new StoreControl();
        storeControl.getStores(dh);
        if(storeControl.getStores(dh).isEmpty()){
            storeControl.addStore(store1, dh);
            storeControl.addStore(store2, dh);
            storeControl.addStore(store3, dh);
        }
    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences =
                getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("USER", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }

}
