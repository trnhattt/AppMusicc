package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appmusic.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        handler.postDelayed(new Runnable(){
            @Override
                public void run(){
                    Intent intent = null;
                    if (mAuth.getCurrentUser() != null){
                        intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    }
                    else {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
        },  3000);
    }
}