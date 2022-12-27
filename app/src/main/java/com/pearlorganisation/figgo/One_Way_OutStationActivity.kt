package com.pearlorganisation.figgo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class One_Way_OutStationActivity : AppCompatActivity() {
    lateinit var nav_controller: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_one_way_out_station)

        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
      //  var nav_host_fragment=supportFragmentManager.findFragmentById(R.id.navigation_bar2) as NavHostFragment
      //  nav_controller=nav_host_fragment.navController


    }
}