package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.Tasks.call
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pearlorganisation.figgo.R

class CityCabActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_cab)

        var whatsappicon=findViewById<ImageView>(R.id.whatsappicon)
        var callicon=findViewById<ImageView>(R.id.callicon)
        var shareimg=findViewById<ImageView>(R.id.shareimg)

        whatsappicon.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=7505145405"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        callicon.setOnClickListener {
            // val callIntent = Intent(Intent.ACTION_CALL)
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:+123")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(callIntent)
        }
        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
       /* val navHostFragment=supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController=navHostFragment.navController*/
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.navigation_bar)
       /* NavigationUI.setupWithNavController(bottomNavigationView, navController)*/

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,DashBoard::class.java))
    }
}