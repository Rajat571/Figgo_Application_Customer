package com.figgo.customer.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass


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
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
    }

    override fun initializeClickListners() {
        shareimg()
        onBackPress()

        submitrating.setOnClickListener {
            startActivity(Intent(this,CityCabActivity::class.java))
        }

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {
      
    }
}