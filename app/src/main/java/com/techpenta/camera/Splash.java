package com.techpenta.camera;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    ImageView rotateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        rotateImage = (ImageView) findViewById(R.id.cameraicon);
        Intent intent = getIntent();
        byte[] imageAsBytes = intent.getByteArrayExtra(HomeScreen.TAG);
        rotateImage.setImageBitmap(
                BitmapFactory.decodeByteArray(imageAsBytes, 0,imageAsBytes.length)
        );

        /*Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        rotateImage.startAnimation(startRotateAnimation);

        getIntent();

        int secondsDelayed = 1;
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, HomeScreen.class));
                finish();
            }
        }, secondsDelayed * 6000);*/
    }
}
