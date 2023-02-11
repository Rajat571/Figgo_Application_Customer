package com.figgo.customer.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.figgo.customer.R
import com.figgo.customer.databinding.ActivityMainBinding


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