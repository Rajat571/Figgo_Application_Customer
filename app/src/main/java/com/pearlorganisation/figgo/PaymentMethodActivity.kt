package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        var nextpage = findViewById<TextView>(R.id.nextpage)

        nextpage.setOnClickListener {
            startActivity(Intent(this,OTPVerifyPageActivity::class.java))
        }
    }
}