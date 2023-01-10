package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.pearlorganisation.figgo.CurrentMap.EmergencyMapsActivity
import com.pearlorganisation.figgo.CurrentMap.MapsActivity2
import com.pearlorganisation.figgo.UI.CityCabActivity


class DriveRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive_rating)
        var submitraing = findViewById<TextView>(R.id.submitraing)

        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var backtxt = findViewById<TextView>(R.id.backtxt)

        backtxt.setOnClickListener {
            startActivity(Intent(this, EmergencyMapsActivity::class.java))
        }

        backimg.setOnClickListener {
            val intent = Intent(this, EmergencyMapsActivity::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        submitraing.setOnClickListener {
            startActivity(Intent(this,CityCabActivity::class.java))
        }
    }
}