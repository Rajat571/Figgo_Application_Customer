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
import com.android.volley.DefaultRetryPolicy
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
import com.pearlorganisation.figgo.WelcomeSlider
import org.json.JSONObject


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks {

    val URL = "https://test.pearl-developer.com/figo/api/register"

    private val REQUEST_LOCATION = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var nav_controller:NavController
    lateinit var pref: PrefManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = PrefManager(this)
        if(pref.isValid().equals("true")){

            startActivity(Intent(this, DashBoard::class.java))
        }
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
       // mGoogleApiClient = GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
        //    .addOnConnectionFailedListener(this).build()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("AIzaSyD1HxXgHCXEdsqZlusGqPjkHeJdpAuvnG0")
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        val googleLoginButton = findViewById<TextView>(R.id.google_login_button)
        googleLoginButton.setOnClickListener {
            signIn()
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
           val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
                   Response.Listener<JSONObject?> {
                   override fun onResponse(response: JSONObject?) {
                       Log.d("SendData", "response===" + response)
                       if (response != null) {
                           if(pref.getToken().equals("") || pref.getToken().equals("null")){

                               val token = response.getString("token")
                               pref.setToken(token)
                               pref.setisValid("true")
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
                               pref.setisValid("true")
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


    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
          //  updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
           // Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }
    }
  /*  private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)
            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)
            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)
            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)
            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)
            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)
        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }*/
    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }


    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }

}