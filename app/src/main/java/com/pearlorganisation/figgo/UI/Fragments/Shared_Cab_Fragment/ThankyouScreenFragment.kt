package com.pearlorganisation.figgo.UI.Fragments.Shared_Cab_Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CityCabActivity
import com.pearlorganisation.figgo.UI.DashBoard
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.razorpay.Checkout
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class ThankyouScreenFragment : Fragment() {
    lateinit var pref: PrefManager
    var booking_id: TextView? = null
    var otpText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thankyou_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var next_button = view.findViewById<TextView>(R.id.next_button)
        var book_other = view.findViewById<TextView>(R.id.book_other)
        var close = view.findViewById<TextView>(R.id.close)
        booking_id = view.findViewById<TextView>(R.id.booking_id)
        otpText = view.findViewById<TextView>(R.id.otp)
        pref = PrefManager(requireActivity())

        var backtxt =view.findViewById<TextView>(R.id.backtxt)
        var backimg =view.findViewById<ImageView>(R.id.backimg)
        var shareimg = view.findViewById<ImageView>(R.id.shareimg)
        otpText?.setText("Otp -"+pref.getOtp()+"")
        booking_id?.setText("Booking No -"+pref.getBookingNo()+"")


        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs")
            intent.setType("text/plain")
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }
        backimg.setOnClickListener {

            startActivity(Intent(requireActivity(), DashBoard::class.java))

        }
        backtxt.setOnClickListener {
            startActivity(Intent(requireActivity(), DashBoard::class.java))
        }


        next_button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_thankyouScreenFragment_to_vehicleNumberFragment)
        }

        book_other.setOnClickListener {

            startActivity(Intent(requireActivity(), CityCabActivity::class.java))

        }
        close.setOnClickListener {
            startActivity(Intent(requireActivity(), DashBoard::class.java))
        }



    }



}