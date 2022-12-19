package com.pearlorganisation.figgo.UI

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pearlorganisation.figgo.R


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks {


    private val REQUEST_LOCATION = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var nav_controller:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var email = findViewById<TextView>(R.id.email)
        var number = findViewById<TextView>(R.id.number)
        var input_number = findViewById<TextView>(R.id.input_number)
        val continuetv:TextView = findViewById<TextView>(R.id.continuetv)
        var forgot_account = findViewById<TextView>(R.id.forgot_account)
        var google_login_button = findViewById<TextView>(R.id.google_login_button)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)


        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        mGoogleApiClient =
            GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build()



       /* binding.email.setOnClickListener{
            binding.inputEmail.isVisible=true
            binding.inputNumber.isVisible=false
        }
        binding.number.setOnClickListener {
            binding.inputNumber.isVisible=true
            binding.inputEmail.isVisible=false
        }
        */


   /*     var nav_host_fragment=supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        nav_controller=nav_host_fragment.navController
        var next_button=findViewById<TextView>(R.id.next_button)*/
    // setupWithNavController(next_button,nav_controller)
       continuetv.setOnClickListener {
           startActivity(Intent(this,MPinGenerate::class.java))

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