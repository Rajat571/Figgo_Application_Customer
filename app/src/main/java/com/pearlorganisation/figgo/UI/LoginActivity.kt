package com.pearlorganisation.figgo.UI

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType.*
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import org.json.JSONObject


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks {

    val URL = "https://test.pearl-developer.com/figo/api/register"

    private val REQUEST_LOCATION = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var nav_controller:NavController
    lateinit var pref: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = PrefManager(this)
      //  var email = findViewById<TextView>(R.id.email)
      //  var number = findViewById<TextView>(R.id.number)
        var input_number = findViewById<TextView>(R.id.input_number)
        val continuetv = findViewById<TextView>(R.id.continuetv)
        var forgot_account = findViewById<TextView>(R.id.forgot_account)
        var google_login_button = findViewById<TextView>(R.id.google_login_button)
        var cc_number = findViewById<ConstraintLayout>(R.id.cc_number)
        var progress = findViewById<ProgressBar>(R.id.progress)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        mGoogleApiClient = GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this).build()

       /* email.setOnClickListener {
            input_number.setHint("Enter Your Email Id")
            email.setTypeface(Typeface.DEFAULT_BOLD);
            input_number.inputType = TYPE_CLASS_TEXT
            number.setTypeface(Typeface.DEFAULT)


        }
        number.setOnClickListener {
            input_number.setHint("Enter Your Mobile number")
            number.setTypeface(Typeface.DEFAULT_BOLD);
          //  email.setTypeface(Typeface.DEFAULT)
            input_number.inputType = TYPE_CLASS_NUMBER
        }*/

       continuetv.setOnClickListener {
           cc_number.isVisible = false
           progress.isVisible = true
           //var mobile_num=binding.inputNumber.text.toString()
           val URL = "https://test.pearl-developer.com/figo/api/register"
           val queue = Volley.newRequestQueue(this@LoginActivity)
           val json = JSONObject()
           json.put("contact_no", input_number.text.toString())
           Log.d("SendData", "json===" + json)
           val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
                   Response.Listener<JSONObject?> {
                   override fun onResponse(response: JSONObject?) {
                       Log.d("SendData", "response===" + response)
                       if (response != null) {
                           if(pref.getToken().equals("") || pref.getToken().equals("null")){

                               val token = response.getString("token")
                               pref.setToken(token)
                               Toast.makeText(this@LoginActivity,"Login Successfully",Toast.LENGTH_SHORT).show()
                               Log.d("SendData", "token===" + token)
                             //  startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
                               if(pref.getMpin().equals("")){
                                   startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
                               }
                               else{
                                   startActivity(Intent(this@LoginActivity,DashBoard::class.java))
                               }
                               cc_number.isVisible = true
                               progress.isVisible = false
                           }else{
//                               val token = response.getString("token")
//                               pref.setToken(token)
//                               Toast.makeText(this@LoginActivity,"Login Successfully",Toast.LENGTH_SHORT).show()
//                               if(pref.getMpin().equals("") || pref.getMpin().equals("null")){
//                                   startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
//                               }else{
//                                   startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
//                               }
//                               startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
//                               if(pref.getMpin().equals("") || pref.getMpin().equals("null")){
//                                   startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
//                               }
//                               else{
//                                   startActivity(Intent(this@LoginActivity,DashBoard::class.java))
//                               }
                               startActivity(Intent(this@LoginActivity,DashBoard::class.java))
                           }

                       }
                       // Get your json response and convert it to whatever you want.
                   }
               }, object : Response.ErrorListener {
                   override fun onErrorResponse(error: VolleyError?) {
                       // Error
                   }
               }) {
                   /*     @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers: MutableMap<String, String> = HashMap()
                            headers["Authorization"] = "TOKEN" //put your token here
                            return headers
                        }*/
               }
           jsonOblect.setRetryPolicy(
               DefaultRetryPolicy(
                   10000,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
               )
           )
           queue.add(jsonOblect)


           //startActivity(Intent(this,MPinGenerate::class.java))

        }

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }
}