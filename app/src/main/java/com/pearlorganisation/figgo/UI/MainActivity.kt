package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.pearlorganisation.figgo.Adapter.ViewPager2Adapter
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        var backbutton=findViewById<TextView>(R.id.back_button)
        var nextButton=findViewById<TextView>(R.id.next_button)



        nextButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}