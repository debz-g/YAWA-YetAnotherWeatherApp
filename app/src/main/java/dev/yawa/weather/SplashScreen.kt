package dev.yawa.weather

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import android.widget.TextView

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar!!.hide()



        val top= loadAnimation(this,R.anim.top_animation)
        val bottom= loadAnimation(this,R.anim.bottom_animation)
        val m= loadAnimation(this,R.anim.middle_animation)


        val tagline = findViewById<TextView>(R.id.tagLine)
        val name = findViewById<TextView>(R.id.name)
        val couple = findViewById<ImageView>(R.id.couple)

        tagline.startAnimation(bottom)
        name.startAnimation(top)
        couple.startAnimation(m)


        val homeIntent = Intent(this,MainActivity::class.java)
        Handler().postDelayed({
            val pa = android.util.Pair<View,String>(couple,"couple_transition")
            val options = ActivityOptions.makeSceneTransitionAnimation(this,pa)
            startActivity(homeIntent,options.toBundle())
            finish()

        },2700)
    }
}