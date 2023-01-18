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
        /*shareimg()
        onBackPress()*/
        setContentView(R.layout.activity_drive_rating)
        var submitrating = findViewById<TextView>(R.id.submitraing)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var ll_back = findViewById<LinearLayout>(R.id.ll_back)




        submitrating.setOnClickListener {
            startActivity(Intent(this,CityCabActivity::class.java))
        }
    }
}