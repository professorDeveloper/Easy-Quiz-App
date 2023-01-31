package com.azamovhudstc.quizapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.local_data.QuizPref;

@SuppressLint("CustomSplashScreen")
public class QuizSplashActivity extends AppCompatActivity {
    QuizPref quizPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        quizPref =QuizPref.getInstance();
        View content = findViewById(android.R.id.content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            content.getViewTreeObserver().addOnDrawListener(() -> {});
        }

        splashAndroid11();
    }
    public  void splashAndroid11(){
        new Handler().postDelayed(() -> {
            System.out.println(quizPref.getName()+" 123123123123123333333333333333");
            if (quizPref.getName().isEmpty()){
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }else {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
            finish();
        },3000);
    }
}