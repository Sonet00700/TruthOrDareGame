package com.sonetsagortruthordaregame.sagor.truthordare;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView imgTop,imgBottom;
    private int time_out = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgTop=findViewById(R.id.topbgid);
        imgBottom=findViewById(R.id.downbgid);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imgTop.setAnimation(topAnim);
        imgBottom.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        }, time_out);
    }
}
