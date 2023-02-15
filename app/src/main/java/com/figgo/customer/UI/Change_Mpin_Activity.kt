package com.figgo.customer.UI

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.figgo.customer.pearlLib.BaseClass
import com.figgo.customer.R
import com.figgo.customer.pearlLib.PrefManager

class Change_Mpin_Activity : BaseClass() {
    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_mpin)
        var pref = PrefManager(this)
        var old_pin = findViewById<EditText>(R.id.old_pin)
        var new_pin = findViewById<EditText>(R.id.new_pin)
        var confirm_mpin = findViewById<EditText>(R.id.confirm_mpin)
        var tv_donebtn = findViewById<TextView>(R.id.tv_donebtn)

        old_pin.requestFocus();
        old_pin.setInputType(InputType.TYPE_CLASS_NUMBER);
        if(old_pin.text.toString().equals(confirm_mpin.text.toString())) {
            pref.setMpin(old_pin.text.toString())
        }

        else{

            Toast.makeText(this,"PIN not match", Toast.LENGTH_SHORT).show()
        }
        tv_donebtn.setOnClickListener {
            startActivity(Intent(this, DashBoard::class.java))
        }
    }
}