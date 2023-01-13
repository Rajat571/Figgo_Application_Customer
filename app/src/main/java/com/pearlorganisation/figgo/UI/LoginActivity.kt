package com.pearlorganisation.figgo.UI

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import org.json.JSONObject


class LoginActivity : AppCompatActivity(){

    val URL = "https://test.pearl-developer.com/figo/api/register"

    private val REQUEST_LOCATION = 1
    private val RC_SIGN_IN = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var nav_controller:NavController
    lateinit var pref: PrefManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
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
        var window = window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION
        )
        // mGoogleApiClient = GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
        //    .addOnConnectionFailedListener(this).build()


        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this@LoginActivity, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this@LoginActivity)
        //updateUI(account)

// Set the dimensions of the sign-in button.
        // Set the dimensions of the sign-in button.
        val signInButton = findViewById<TextView>(R.id.google_login_button)

        signInButton.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
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
                                        this@LoginActivity,
                                        "Login Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d("SendData", "token===" + token)
                                    //  startActivity(Intent(this@LoginActivity,MPinGenerate::class.java))
                                    if (pref.getMpin().equals("")) {
                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                MPinGenerate::class.java
                                            )
                                        )
                                    } else {
                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                DashBoard::class.java
                                            )
                                        )
                                    }
                                    cc_number.isVisible = true
                                    progress.isVisible = false
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
                                    startActivity(Intent(this@LoginActivity, DashBoard::class.java))
                                }

                            }
                            // Get your json response and convert it to whatever you want.
                        }
                    }, object : Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError?) {
                            Toast.makeText(this@LoginActivity, "Something went wrong!", Toast.LENGTH_LONG).show()
                        }
                    }) {
                        /*     @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers: MutableMap<String, String> = HashMap()
                            headers["Authorization"] = "TOKEN" //put your token here
                            return headers
                        }*/
                    }

                queue.add(jsonOblect)


                //startActivity(Intent(this,MPinGenerate::class.java))

            }

    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }


       private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account = completedTask.getResult(ApiException::class.java)
                Log.d("Account ", "" + account.account)

                pref.setAccountDetails(
                    account.account.toString(),
                    account.displayName.toString(),
                    account.photoUrl.toString()
                )
                // prefManager.setToken("")
                Toast.makeText(
                    this@LoginActivity,
                    "Signed In :" + account.account.toString(),
                    Toast.LENGTH_LONG
                ).show()
                if (pref.getMpin().equals("") || pref.getMpin().equals("null")) {
                    startActivity(Intent(this,MPinGenerate::class.java))
                } else {
                    startActivity(Intent(this,DashBoard::class.java))
                }

                // Signed in successfully, show authenticated UI.
                // updateUI(account)
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.d("SignIN = ", "signInResult:failed code=" + e.statusCode)
                //updateUI(null)
            }
        }


}