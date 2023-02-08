package com.pearlorganisation.figgo.UI.Fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.payu.base.models.ErrorResponse
import com.payu.base.models.PayUPaymentParams
import com.payu.checkoutpro.PayUCheckoutPro
import com.payu.checkoutpro.utils.PayUCheckoutProConstants
import com.payu.ui.model.listeners.PayUCheckoutProListener
import com.payu.ui.model.listeners.PayUHashGenerationListener
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.Shared_Cab_Fragment.ThankyouScreenFragment
import com.pearlorganisation.figgo.UTIL.MapUtility
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CabBookFragment : Fragment() {
    lateinit var pref: PrefManager

    var created_at: TextView? = null
    var updated_at: TextView? = null
    var dis1: TextView? = null
    var dis2: TextView? = null
    var to_loc: TextView? = null
    var from_loc: TextView? = null
    var fare: TextView? = null
    var image: ImageView? = null
    var transaction_id :String ?= ""
    var thankyouScreenFragment = ThankyouScreenFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_cab_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var book_self=view.findViewById<TextView>(R.id.book_self)
        var book_other=view.findViewById<TextView>(R.id.book_other)
        created_at = view.findViewById<TextView>(R.id.created_at)
        updated_at = view.findViewById<TextView>(R.id.updated_at)
        dis1 = view.findViewById<TextView>(R.id.dis)
        dis2 = view.findViewById<TextView>(R.id.dis2)
        to_loc = view.findViewById<TextView>(R.id.to_loc)
        from_loc = view.findViewById<TextView>(R.id.from_loc)
        fare = view.findViewById<TextView>(R.id.fare)
        var ll_back = view.findViewById<LinearLayout>(R.id.ll_back)
        var shareimg = view.findViewById<ImageView>(R.id.shareimg)
        var iv_bellicon = view.findViewById<ImageView>(R.id.iv_bellicon)
        pref = PrefManager(requireActivity())

        getCabBookData()

        iv_bellicon.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_cabBookFragment_to_notificationBellIconActivity)

        }

        ll_back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_cabBookFragment_to_dashBoard)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"))
        }

        book_other.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_cabBookFragment_to_payFragment)


            // startActivity(Intent(requireActivity(), PaymentPayActivity::class.java))


        }

        book_self.setOnClickListener {
            val amt = "1"
            val amount = Math.round(amt.toFloat() * 100).toInt()
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_capDM1KlnUhj5f")
            checkout.setImage(R.drawable.appicon)
            val obj = JSONObject()
            try {
                obj.put("name", "Figgo")
                obj.put("description", "Payment")
                obj.put("theme.color", "")
                obj.put("send_sms_hash", true)
                obj.put("allow_rotation", true)
                obj.put("currency", "INR")
                obj.put("amount", amount)
                val preFill = JSONObject()
                preFill.put("email", "support@figgocabs.com")
                preFill.put("contact", "91" + "9715597855")
                obj.put("prefill", preFill)
                checkout.open(requireActivity(), obj)
            } catch (e: JSONException) {
                Toast.makeText(requireActivity(), "Error in payment: " + e.message, Toast.LENGTH_SHORT).show();
                e.printStackTrace()
            }

        }

    }

    private fun getCabBookData() {
        val progressDialog = ProgressDialog(requireActivity())
        progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/select-city-vehicle-type"
        val queue = Volley.newRequestQueue(requireContext())
        val json = JSONObject()
        json.put("ride_id", pref.getRideId())
        json.put("vehicle_type_id", pref.getVehicleId())
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {

                        progressDialog.hide()
                        try {

                            val distance = response.getJSONObject("data").getString("distance")
                            val createdAt = response.getJSONObject("data").getJSONObject("ride")
                                .getString("created_at")
                            val updatedAt = response.getJSONObject("data").getJSONObject("ride")
                                .getString("updated_at")
                            val full_image = response.getJSONObject("data").getJSONObject("vehicle")
                                .getString("full_image")
                            val max_price = response.getJSONObject("data").getJSONObject("vehicle")
                                .getString("max_price")
                            val min_price = response.getJSONObject("data").getJSONObject("vehicle")
                                .getString("min_price")
                            val to_location = response.getJSONObject("data").getJSONObject("ride")
                                .getJSONObject("to_location").getString("name")
                            val from_location = response.getJSONObject("data").getJSONObject("ride")
                                .getJSONObject("from_location").getString("name")


                            //  Picasso.get().load(full_image).into(image)
                            created_at?.setText(createdAt)
                            updated_at?.setText(updatedAt)
                            dis1?.setText(distance)
                            dis2?.setText(distance)
                            to_loc?.setText(to_location)
                            from_loc?.setText(from_location)
                            fare?.setText(
                                "Approx.. fare Rs. " + min_price + " to " + max_price + ",\n                for this ride\nwithout waiting  parking charge\nFinal Price is differ from Approx. "

                            )
                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),requireActivity())

                        }
                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    progressDialog.hide()

                    MapUtility.showDialog(error.toString(),requireActivity())
                  //  Toast.makeText(requireActivity(), "Something went wrong!", Toast.LENGTH_LONG).show()

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
    /*override fun onPaymentSuccess(s: String?) {
        Toast.makeText(requireActivity(), "payment successful", Toast.LENGTH_SHORT).show()
        //  startActivity(Intent(requireActivity(), UOrderPlaced::class.java))
        view?.let { Navigation.findNavController(it).navigate(R.id.thankyouScreenFragment) }
        try {
            transaction_id = s
            getOtp()
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
    }*/




}