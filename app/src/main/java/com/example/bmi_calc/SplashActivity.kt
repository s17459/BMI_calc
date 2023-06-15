package com.example.bmi_calc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay the start of MainActivity
        Handler().postDelayed({ // Start MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Finish the current activity
            finish()
        }, SPLASH_SCREEN_DELAY)
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY: Long = 3000 // 3 seconds
    }
}