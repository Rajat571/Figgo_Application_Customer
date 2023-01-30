package com.pearlorganisation.figgo.UI

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import org.json.JSONObject

class VerifyOtp : AppCompatActivity() {

    val URL = "https://test.pearl-developer.com/figo/api/register"

    private val REQUEST_LOCATION = 1
    private val RC_SIGN_IN = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var nav_controller: NavController
    lateinit var pref: PrefManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var cc_number: ConstraintLayout
    lateinit var enteredOTP: EditText
    lateinit var resend : TextView
    lateinit var otp_Verify_button: Button
    lateinit var progress: ProgressBar
    lateinit var cTimer : CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_verify_number)
        pref = PrefManager(this)




        // startTimer()
    }



    private fun verifyOtp(){
        val URL = " https://test.pearl-developer.com/figo/api/otp/check-otp"
        val queue = Volley.newRequestQueue(this@VerifyOtp)
        val json = JSONObject()
        json.put("type", "user")
        json.put("type_id", pref.getUserId())
        json.put("otp", enteredOTP.text.toString())
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {
                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        if (pref.getToken().equals("") || pref.getToken().equals("null")) {

                            val token = response.getString("token")
                            pref.setToken(token)
                            pref.isValidLogin()
                            Toast.makeText(
                                this@VerifyOtp,
                                "Login Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            //  Log.d("SendData", "token===" + token)
                            //  startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
                            if (pref.getMpin().equals("")) {
                                startActivity(
                                    Intent(
                                        this@VerifyOtp,
                                        MPinGenerate::class.java
                                    )
                                )
                            } else {
                                startActivity(
                                    Intent(
                                        this@VerifyOtp,
                                        DashBoard::class.java
                                    )
                                )
                            }


                        } else {
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
                            startActivity(Intent(this@VerifyOtp, DashBoard::class.java))
                        }

                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(this@VerifyOtp, "Something went wrong!", Toast.LENGTH_LONG).show()
                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers.put("Accept", "application/vnd.api+json") //put your token here
                    return headers
                }
            }

        queue.add(jsonOblect)


        //startActivity(Intent(this,MPinGenerate::class.java))

    }

}
