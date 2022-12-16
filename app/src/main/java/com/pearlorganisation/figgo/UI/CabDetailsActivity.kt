package com.pearlorganisation.figgo.UI

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import com.pearlorganisation.figgo.R


class CabDetailsActivity : AppCompatActivity() {
    lateinit var nav_controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cab_details)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        var nav_host_fragment=supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        nav_controller=nav_host_fragment.navController

//        var book_now=findViewById<TextView>(R.id.book_now)
//        book_now.setOnClickListener {
//            Toast.makeText(this,"your cab is book successfully",Toast.LENGTH_LONG)
//
//        }
    }
}