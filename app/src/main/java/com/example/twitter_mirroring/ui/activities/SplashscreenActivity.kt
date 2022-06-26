package com.example.twitter_mirroring.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.twitter_mirroring.MainActivity
import com.example.twitter_mirroring.R

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //Adding animation to Twitter's Logo when starting app
        val onStartAppAnimation = AnimationUtils.loadAnimation(this, R.anim.animation)
        ivTwitterLogo.startAnimation(onStartAppAnimation)

        //Adding action when animation ends to sent user to Main Activity screen
        val intent = Intent(this, MainActivity::class.java)

        //Implementing functions depending on animation's behavior
        onStartAppAnimation.setAnimationListener(object : Animation.AnimationListener
        {
            //Nothing will be implemented here yet
            override fun onAnimationStart(animation: Animation?){}

            //When animation ends, send user to Main activity and close current activity
            override fun onAnimationEnd(animation: Animation?)
            {
                startActivity(intent)
                finish()
            }

            //Nothing will be implemented here yet
            override fun onAnimationRepeat(animation: Animation?){}
        })
    }
}