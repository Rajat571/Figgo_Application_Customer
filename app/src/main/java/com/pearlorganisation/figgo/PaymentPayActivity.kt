package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PaymentPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_pay)

        var pay_now = findViewById<Button>(R.id.pay_now)
        pay_now.setOnClickListener {
           // startActivity(Intent(this,PaymentMethodActivity::class.java))
        }
    }
}