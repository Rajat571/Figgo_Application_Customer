package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.figgo.customer.R

class RoundAndTourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round_and_tour)
        var oneway = findViewById<LinearLayout>(R.id.oneway)
        var roundandtour = findViewById<LinearLayout>(R.id.roundandtour)

        roundandtour.setOnClickListener {
            startActivity(Intent(this, RoundAndTourActivity::class.java))
        }
    }
}