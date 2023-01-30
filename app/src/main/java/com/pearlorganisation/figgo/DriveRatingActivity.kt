package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pearlorganisation.figgo.CurrentMap.EmergencyMapsActivity
import com.pearlorganisation.figgo.CurrentMap.MapsActivity2
import com.pearlorganisation.figgo.UI.CityCabActivity


class DriveRatingActivity : BaseClass() {


lateinit var submitrating:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()

    }
    override fun setLayoutXml() {
        setContentView(R.layout.activity_drive_rating)
    }

    override fun initializeViews() {
         submitrating = findViewById<TextView>(R.id.submitraing)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var ll_back = findViewById<LinearLayout>(R.id.ll_back)
    }

    override fun initializeClickListners() {
        shareimg()
        onBackPress()
        submitrating.setOnClickListener {
            startActivity(Intent(this,CityCabActivity::class.java))
        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {
      
    }
}