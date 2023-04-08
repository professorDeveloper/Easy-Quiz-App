package com.azamovhudstc.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.azamovhudstc.quizapp.local_data.QuizPref

class SplashScreen : AppCompatActivity() {
    private lateinit var quizPref: QuizPref
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        quizPref = QuizPref.getInstance()
        splashAndroid11()
    }

    fun splashAndroid11() {
        Handler().postDelayed({
            println(quizPref.getName() + " 123123123123123333333333333333")
            if (quizPref.getName().isEmpty()) {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
            finish()
        }, 1000)
    }

}