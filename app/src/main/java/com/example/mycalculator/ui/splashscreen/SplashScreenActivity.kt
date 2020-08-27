package com.example.mycalculator.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mycalculator.ui.main.MainActivity
import com.example.mycalculator.R

class SplashScreenActivity : AppCompatActivity(), SplashScreenContact.View {
    private val presenter: SplashScreenContact.Presenter

    init {
        presenter = SplashScreenPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }, 500)
    }
}