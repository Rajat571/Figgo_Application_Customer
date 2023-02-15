package com.figgo.customer.UI
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R

class SplashActivity : AppCompatActivity() {


    var prefManager: PrefManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        setContentView(R.layout.activity_splash)
        prefManager = PrefManager(this)
        Handler().postDelayed({

          //  if(prefManager!!.isValid().equals("true")){

             //   startActivity(Intent(this, DashBoard::class.java))
           // }else{
                startActivity(Intent(this, WelcomeSlider::class.java))
        //    }

        },3000)
        var become_the_luxury=findViewById<TextView>(R.id.become_the_luxury)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_side)
        become_the_luxury.startAnimation(slideAnimation)
    }
}