package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CabBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cab_book)
        var book_now = findViewById<TextView>(R.id.book_now)

        book_now.setOnClickListener {
           startActivity(Intent(this,PaymentPayActivity::class.java))
        }
    }
}