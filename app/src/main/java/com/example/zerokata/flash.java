package com.example.zerokata;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class flash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;// 4 second



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        ImageView s=(ImageView) findViewById(R.id.imageView2);

        s.animate().scaleX(1.5f).scaleY(1.5f).setDuration(3000);
        s.animate().alpha(1f).setDuration(2500);

        ImageView t=(ImageView) findViewById(R.id.imageView3);
        ImageView l=(ImageView) findViewById(R.id.imageView4);

        ImageView r=(ImageView) findViewById(R.id.imageView5);

        ImageView b=(ImageView) findViewById(R.id.imageView6);

        /*t.setTranslationY(-1000f).setDu;
        l.setTranslationX(-1000f);
        r.setTranslationX(1000f);
        b.setTranslationY(1000f);*/

        t.animate().translationYBy(750f).setDuration(2000);
        l.animate().translationXBy(750f).setDuration(2000);
        r.animate().translationXBy(-770f).setDuration(2000);
        b.animate().translationYBy(-770f).setDuration(2000);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent homeIntent = new Intent(flash.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
