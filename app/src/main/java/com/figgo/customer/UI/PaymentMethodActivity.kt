package com.figgo.customer.UI

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass
import com.razorpay.PaymentResultListener

class PaymentMethodActivity : BaseClass(), PaymentResultListener {
    var transaction_id :String ?= ""
     lateinit var pref: PrefManager

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
        setContentView(R.layout.activity_payment_method)
        var tv_nxt = findViewById<TextView>(R.id.tv_nxt)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        onBackPress()
        shareimg()
        pref = PrefManager(this)




    }

    override fun onPaymentSuccess(s: String?) {

        Toast.makeText(this, "payment successful", Toast.LENGTH_SHORT).show()

        try {
            transaction_id = s

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentError(i: Int, s: String?) {

        Toast.makeText(this, "Payment failed$i", Toast.LENGTH_SHORT).show()
    }


}