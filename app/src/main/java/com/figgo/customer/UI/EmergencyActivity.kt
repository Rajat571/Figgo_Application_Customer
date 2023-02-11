package com.figgo.customer.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass

class EmergencyActivity : BaseClass() {
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
        setContentView(R.layout.activity_emergency)
        var emrgencybtn = findViewById<TextView>(R.id.emrgencybtn)

        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var ll_emergency = findViewById<LinearLayout>(R.id.ll_emergency)

        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        shareimg()
        onBackPress()

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        ll_emergency.setOnClickListener {
            startActivity(Intent(this, Driver_Rating_Activity1::class.java))
        }



        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        emrgencybtn.setOnClickListener {
            startActivity(Intent(this, DriveRatingActivity::class.java))
        }

    }
}