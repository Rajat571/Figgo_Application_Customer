package com.figgo.customer.UI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.figgo.customer.R
import com.figgo.customer.pearlLib.PrefManager

class HelpAndSupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_and_support)
        var number = findViewById<LinearLayout>(R.id.call_us)
        var email = findViewById<LinearLayout>(R.id.email_us)
        //var exit = view.findViewById<Button>(R.id.exit)
        var intent_call = Intent(Intent.ACTION_DIAL)
        var pref = PrefManager(this)
        // var name:String = pref.getDriverName()
        var tv2 = findViewById<TextView>(R.id.textView2)
        tv2.text = "The mission of our company is to provide the most clean and safe cab/taxi services throughout India by ensuring the security of our customers during the whole duration of their trip. We have a mission to provide a comfortable travel/journey experience in the most cost-effective manner possible. We also aim to be known as one of the most renowned and trusted cab/taxi company and become role model when it comes to the concept of operations and giving services to customers."
        intent_call.data = Uri.parse("tel:"+"+919715597855")
        number.setOnClickListener {
            startActivity(intent_call)
        }
        var intent_email = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+"support@figgocabs.com"))
        email.setOnClickListener{
            startActivity(intent_email)
        }
    }
}