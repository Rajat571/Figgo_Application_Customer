package com.figgo.customer.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.pearlLib.BaseClass
import com.figgo.customer.R

class CityCabActivity : BaseClass() {
    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var prefManager: PrefManager
   /* lateinit var prefManager: PrefManager*/
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
        setContentView(R.layout.drawer_screen2)
        prefManager = PrefManager(this)
        var shareimg=findViewById<ImageView>(R.id.shareimg)
       /* val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout*/
        var menu_naviagtion = findViewById<ImageView>(R.id.menu_naviagtion)
        val navigationView = findViewById<View>(R.id.navView) as NavigationView
        var navView = findViewById<NavigationView>(R.id.navView)



        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        shareimg()
        onBackPress()





        prefManager = PrefManager(this@CityCabActivity)

      /*  menu_naviagtion.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }*/

      /*  toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Change_MPIN, R.string.Rides)
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/

       /* menu_naviagtion.setOnClickListener {
           startActivity(Intent(this,DashBoard::class.java))
        }*/



       /* callicon.setOnClickListener {
            // val callIntent = Intent(Intent.ACTION_CALL)
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:+123")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(callIntent)
        }*/


      /*  shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs")
            intent.setType("text/plain")
            startActivity(Intent.createChooser(intent, "Invite Friends"))
        }*/

        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController=navHostFragment.navController
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.navigation_bar)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }



    override fun onBackPressed() {

        if (prefManager.getCount().equals("vehicle")) {
            startActivity(Intent(this@CityCabActivity, CityCabActivity::class.java))
        } else {
            super.onBackPressed()
            startActivity(Intent(this, DashBoard::class.java))
        }
    }

}