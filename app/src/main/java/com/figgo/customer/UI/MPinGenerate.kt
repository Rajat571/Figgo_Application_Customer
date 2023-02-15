package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R

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

        continuetv.setOnClickListener {

            if (mPin.text.toString().length < 4) {

                Toast.makeText(this@MPinGenerate, " 4 digit's Required", Toast.LENGTH_SHORT).show()

            }else {

                if (mPin.text.toString().equals(confirm_mpin.text.toString())) {
                    pref.setMpin(mPin.text.toString())
                    startActivity(Intent(this@MPinGenerate, DashBoard::class.java))
                } else {
                      Toast.makeText(this@MPinGenerate, "PIN not match", Toast.LENGTH_SHORT).show()
                  }
            }
        }



    }
}