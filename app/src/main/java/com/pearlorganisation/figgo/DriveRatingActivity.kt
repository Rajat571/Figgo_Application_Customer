package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DriveRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive_rating)
        var submitraing = findViewById<TextView>(R.id.submitraing)

        submitraing.setOnClickListener {
            startActivity(Intent(this,OneWayBookingActivity::class.java))
        }
    }
}