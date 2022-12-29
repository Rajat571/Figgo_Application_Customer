package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.pearlorganisation.figgo.UI.Fragments.SupportBottomNav

class EmergencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        var emrgencybtn = findViewById<TextView>(R.id.emrgencybtn)

        emrgencybtn.setOnClickListener {
            startActivity(Intent(this,DriveRatingActivity::class.java))
        }

    }
}