package com.figgo.customer.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R


class SupportBottomNav : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        return inflater.inflate(R.layout.fragment_support_bottom_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var number = view.findViewById<LinearLayout>(R.id.call_us)
        var email = view.findViewById<LinearLayout>(R.id.email_us)
        //var exit = view.findViewById<Button>(R.id.exit)
        var intent_call = Intent(Intent.ACTION_DIAL)
        var pref = PrefManager(requireContext())
       // var name:String = pref.getDriverName()
        var tv2 = view.findViewById<TextView>(R.id.textView2)
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