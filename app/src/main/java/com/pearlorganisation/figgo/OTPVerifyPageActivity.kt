package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.pearlorganisation.figgo.UI.DashBoard

class OTPVerifyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify_page)
        var book_other = findViewById<TextView>(R.id.book_other)
        var close = findViewById<TextView>(R.id.close)

        book_other.setOnClickListener {
            startActivity(Intent(this,One_Way_OutStationActivity::class.java))
        }

        close.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }


    }
}