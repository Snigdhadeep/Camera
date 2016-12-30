package com.techpenta.camera;

import com.firebase.client.Firebase;

/**
 * Created by Snikdha on 9/11/2016.
 */
public class CrowdWeather extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
