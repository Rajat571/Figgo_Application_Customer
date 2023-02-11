package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.figgo.customer.R

class OTPVerifyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify_page)

        var book_other = findViewById<TextView>(R.id.book_other)
        var close = findViewById<TextView>(R.id.close)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)

        backimg.setOnClickListener {
            val intent = Intent(this, PaymentMethodActivity::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        book_other.setOnClickListener {
            startActivity(Intent(this, One_Way_OutStationActivity::class.java))
        }

        close.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }


    }
}