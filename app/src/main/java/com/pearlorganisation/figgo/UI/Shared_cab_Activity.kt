package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R

class Shared_cab_Activity : BaseClass() {
    lateinit var nav_controller: NavController
    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }



    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

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