package com.figgo.customer.UI

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.pearlLib.BaseClass
import com.figgo.customer.R
import com.figgo.customer.Fragments.Shared_Cab_Fragment.ThankyouScreenFragment
import com.figgo.customer.Util.MapUtility
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.util.*


class CabDetailsActivity : BaseClass(), PaymentResultListener {
    lateinit var nav_controller: NavController
    var transaction_id :String ?= ""
    var thankyouScreenFragment = ThankyouScreenFragment()
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
        setContentView(R.layout.activity_cab_details)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        var nav_host_fragment=supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        nav_controller=nav_host_fragment.navController
        pref = PrefManager(this@CabDetailsActivity)
//        var book_now=findViewById<TextView>(R.id.book_now)
//        book_now.setOnClickListener {
//            Toast.makeText(this,"your cab is book successfully",Toast.LENGTH_LONG)
//
//        }
    }

    override fun onPaymentSuccess(s: String?) {
        Toast.makeText(this@CabDetailsActivity, "payment successful", Toast.LENGTH_SHORT).show()

              try {
                 transaction_id = s
                  getOtp()
              } catch (e: Exception) {
                  Log.e(TAG, "Exception in onPaymentSuccess", e)
              }

        }

    override fun onPaymentError(i: Int, s: String?) {
        Toast.makeText(this@CabDetailsActivity, "Payment failed$i", Toast.LENGTH_SHORT).show()

    }
    private fun getOtp() {
        val progressDialog = ProgressDialog(this@CabDetailsActivity)
        progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/update-city-ride-payment-status"
        val queue = Volley.newRequestQueue(this@CabDetailsActivity)
        val json = JSONObject()
        json.put("transaction_id", transaction_id.toString())
        json.put("payment_type", "card")
        json.put("ride_id", pref.getRideId())
        Log.d("transac",transaction_id.toString())
        Log.d("rides",pref.getride_id())
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        try {

                            progressDialog.hide()
                            val booking_no = response.getJSONObject("ride").getString("booking_id")
                            val otp = response.getInt("otp")

                            pref.setOtp(otp.toString())
                            pref.setBookingNo(booking_no)
                            supportFragmentManager.beginTransaction().apply {
                                replace(R.id.nav_controller, thankyouScreenFragment)
                                commit()
                            }
                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),this@CabDetailsActivity)

                            }
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    progressDialog.hide()
                    //Toast.makeText(this@CabDetailsActivity, "Something went wrong!", Toast.LENGTH_LONG).show()
                    MapUtility.showDialog(error.toString(),this@CabDetailsActivity)
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }


}