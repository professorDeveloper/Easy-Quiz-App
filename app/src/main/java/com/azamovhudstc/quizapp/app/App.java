package com.azamovhudstc.quizapp.app;

import android.app.Application;
import android.content.Context;

import com.azamovhudstc.quizapp.local_data.QuizPref;
import com.azamovhudstc.quizapp.local_data.source.Base;
import com.azamovhudstc.quizapp.local_data.source.LocalPref;

public class App extends Application {
    public static   Context cnt;
    @Override
    public void onCreate() {
        super.onCreate();
        cnt =this;
        Base.init(this);
        QuizPref.init(this);
    }
}
