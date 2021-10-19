/*

Welcome screen

 */

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Handler h = new Handler();
    private static int time = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, time);

        Button button = (Button) findViewById(R.id.skip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
                finish();
            }
        });

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
                finish();
            }
        }, 5000);
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}