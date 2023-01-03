package com.pearlorganisation.figgo.UI.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class PayFragment : Fragment() ,PaymentResultListener {
    var psg_name: EditText? = null
    var psg_contact: EditText? = null
    var pick_address: EditText? = null
    var landmark: EditText? = null
    var pick_location: TextView? = null
    var msg: EditText? = null
    var AUTOCOMPLETE_REQUEST_CODE = -1
    var lat :String ?= ""
    var lng :String ?= ""
    lateinit var pref: PrefManager
    var transaction_id :String ?= ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pay_now = view.findViewById<Button>(R.id.pay_now)

        psg_name = view.findViewById<EditText>(R.id.psg_name)
        psg_contact = view.findViewById<EditText>(R.id.psg_contact_no)
        pick_address = view.findViewById<EditText>(R.id.pick_address)
        landmark = view.findViewById<EditText>(R.id.landmark)
        pick_location = view.findViewById<TextView>(R.id.pick_location)
        msg = view.findViewById<EditText>(R.id.msg)
        pref = PrefManager(requireActivity())
        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), apiKey)
        }

        pick_location?.setOnClickListener {


            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }



        pay_now.setOnClickListener {



            if (psg_name?.text.toString().equals("")){
                Toast.makeText(requireActivity(), "Please type Passenger Name", Toast.LENGTH_LONG).show()

            }else if (psg_contact?.text.toString().equals("")){
                Toast.makeText(requireActivity(), "Please type Passenger Name", Toast.LENGTH_LONG).show()

            }else if (pick_address?.text.toString().equals("")){
                Toast.makeText(requireActivity(), "Please type Pick Up Address", Toast.LENGTH_LONG).show()

            }else if (landmark?.text.toString().equals("")){
                Toast.makeText(requireActivity(), "Please type Landmark", Toast.LENGTH_LONG).show()

            }else if (lat.equals("")){
                Toast.makeText(requireActivity(), "Please select Pick Up Location", Toast.LENGTH_LONG).show()

            }else {
                getPayNow();
            }

        }

    }

    private fun getPayNow() {

        val URL ="https://test.pearl-developer.com/figo/api/ride/enter-passenger-details"
        val queue = Volley.newRequestQueue(requireContext())
        val json = JSONObject()
        json.put("name", psg_name?.text.toString())
        json.put("contact", psg_contact?.text.toString())
        json.put("lat", lat)
        json.put("lng", lng)
        json.put("pickup_address", pick_address?.text.toString())
        json.put("landmark", landmark?.text.toString())
        json.put("ride_id", pref.getRideId())
        if (msg?.text.toString().equals("")){
            msg?.text = null
        }
        json.put("additional_message ", msg?.text.toString())




        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {

                       val amt = "1"
                       val amount = Math.round(amt.toFloat() * 100).toInt()
                      val checkout = Checkout()
                    checkout.setKeyID("rzp_test_LrVNPY8Z5MQgNE")
                       checkout.setImage(R.drawable.appicon)
                       val obj = JSONObject()
                       try {
                          obj.put("name", "Geeks for Geeks")
                           obj.put("description", "Test payment")
                           obj.put("theme.color", "")
                           obj.put("send_sms_hash", true)
                           obj.put("allow_rotation", true)
                           obj.put("currency", "INR")
                          obj.put("amount", amount)
                           val preFill = JSONObject()
                           preFill.put("email", "a@gmail.com")
                           preFill.put("contact", "91" + "1234567098")
                           obj.put("prefill", preFill)
                           checkout.open(requireActivity(), obj)
                           } catch (e: JSONException) {
                           Toast.makeText(getApplicationContext(), "Error in payment: " + e.message, Toast.LENGTH_SHORT).show();
                           e.printStackTrace()
                            }
                      //  view?.let { Navigation.findNavController(it).navigate(R.id.action_payFragment_to_paymentWayFragment) }



                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    Toast.makeText(requireActivity(), "Something went wrong!", Toast.LENGTH_LONG).show()

                }
            }) {
                @SuppressLint("SuspiciousIndentation")
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    return headers
                }
            }

        queue.add(jsonOblect)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)

                pick_location!!.setText(place.address)
                    lat = place.latLng.latitude.toString()
                   lng = place.latLng.longitude.toString()

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                val status = Autocomplete.getStatusFromIntent(
                    data!!
                )
            } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
            }
        }
    }
   override fun onPaymentSuccess(s: String?) {
       Toast.makeText(requireActivity(), "payment successful", Toast.LENGTH_SHORT).show()
     //  startActivity(Intent(requireActivity(), UOrderPlaced::class.java))
       view?.let { Navigation.findNavController(it).navigate(R.id.thankyouScreenFragment) }
       try {
           transaction_id = s
           val c: Calendar = Calendar.getInstance()
           @SuppressLint("SimpleDateFormat") val dateformat =
               SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa")
           val currentTime: String = dateformat.format(c.getTime())
         //  Log.d("transaction_id", transaction_id)
           /*sendorderdetails(
               transaction_id,
               order_id,
               java.lang.String.valueOf(total_samount),
               1,
               currentTime
           )*/
       } catch (e: Exception) {
         //  Log.e(TAG, "Exception in onPaymentSuccess", e)
       }
   }
      override fun onPaymentError(i: Int, s: String?) {
          try {
              Toast.makeText(requireActivity(), "Payment failed$i", Toast.LENGTH_SHORT).show()
              val c: Calendar = Calendar.getInstance()
              @SuppressLint("SimpleDateFormat") val dateformat =
                  SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa")
              val currentTime: String = dateformat.format(c.getTime())
             // sendorderdetails("0", order_id, java.lang.String.valueOf(total_samount), 0, currentTime)
          } catch (e: Exception) {
              //Log.e(TAG, "Exception in onPaymentError", e)
          }
       }

}


