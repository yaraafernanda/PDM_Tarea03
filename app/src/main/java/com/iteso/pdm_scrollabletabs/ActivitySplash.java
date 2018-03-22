package com.iteso.pdm_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iteso.pdm_scrollabletabs.beans.City;
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

        //PRUEBA
        DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplash.this);
        StoreControl storeControl = new StoreControl();
        storeControl.deleteStore(1, dh);
        if(storeControl.getStores(dh).isEmpty()){
            City city = new City();
            city.setId(2);
            city.setName("Guadalajara");
            Store store1 = new Store();
            store1.setId(0);
            store1.setName("BestBuy");
            store1.setPhone("3338564251");
            store1.setThumbnail(0);
            store1.setLongitude(-534.51);
            store1.setLatitude(344.54);
            store1.setCity(city);
            Store store2 = new Store();
            store2.setId(1);
            store2.setName("Liverpool");
            store2.setPhone("3375689542");
            store2.setThumbnail(0);
            store2.setLongitude(132.51);
            store2.setLatitude(-451.14);
            store2.setCity(city);
            Store store3 = new Store();
            store3.setId(2);
            store3.setName("Mac Store");
            store3.setPhone("3386324571");
            store3.setThumbnail(0);
            store3.setLongitude(123.54);
            store3.setLatitude(163.15);
            store3.setCity(city);
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
