package com.pearlorganisation.figgo.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import com.pearlorganisation.figgo.R

class MPinGenerate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpin_generate)

        var mPin = findViewById<EditText>(R.id.mPin)
        var confirm_mpin = findViewById<EditText>(R.id.confirm_mpin)
        var continuetv = findViewById<TextView>(R.id.continuetv)
        mPin.requestFocus();
        mPin.setInputType(InputType.TYPE_CLASS_NUMBER);

        continuetv.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }



    }
}