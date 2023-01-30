package com.pearlorganisation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CityCabActivity

class Driver_Rating_Activity1 : BaseClass() {
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
        setContentView(R.layout.activity_driver_rating1)
        shareimg()
      onBackPress()
        /*setContentView(R.layout.screenwaiting)*/
        var submitrating = findViewById<TextView>(R.id.submitraing)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var ll_back = findViewById<LinearLayout>(R.id.ll_back)


        submitrating.setOnClickListener {
            startActivity(Intent(this, CityCabActivity::class.java))
        }
    }
}