package com.pearlorganisation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.pearlorganisation.figgo.PaymentMethodActivity
import com.pearlorganisation.figgo.R

class Current_Driver_Details_List : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_driver_details_list)
        var accept = findViewById<TextView>(R.id.accept)

        accept.setOnClickListener {
            startActivity(Intent(this,PaymentMethodActivity::class.java))
        }
    }
}