package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TotalDistance_Count_Km_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_distance_count_km)
        var exit = findViewById<TextView>(R.id.exit)

        exit.setOnClickListener {
            startActivity(Intent(this,EmergencyActivity::class.java))
        }
    }
}