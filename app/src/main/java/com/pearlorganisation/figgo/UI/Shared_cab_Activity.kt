package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pearlorganisation.figgo.R

class Shared_cab_Activity : AppCompatActivity() {
    lateinit var nav_controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_cab)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        var nav_host_fragment=supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        nav_controller=nav_host_fragment.navController

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,DashBoard::class.java))
    }

}