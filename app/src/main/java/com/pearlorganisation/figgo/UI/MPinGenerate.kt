package com.pearlorganisation.figgo.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R

class MPinGenerate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpin_generate)

        var mPin = findViewById<EditText>(R.id.mPin)
        var pref = PrefManager(this)
        var confirm_mpin = findViewById<EditText>(R.id.confirm_mpin)
        var continuetv = findViewById<TextView>(R.id.continuetv)
        mPin.requestFocus();
        mPin.setInputType(InputType.TYPE_CLASS_NUMBER);
        if(mPin.text.toString().equals(confirm_mpin.text.toString())) {
            pref.setMpin(mPin.text.toString())
        }

        else{

            Toast.makeText(this,"PIN not match", Toast.LENGTH_SHORT).show()
        }
        continuetv.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }



    }
}