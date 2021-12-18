package dev.yawa.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val time: Long = 1200

        Handler().postDelayed(Runnable {
            val intent = Intent(splashscreen@this, MainActivity::class.java)
            startActivity(intent)
            finish()

        },time)

    }
}