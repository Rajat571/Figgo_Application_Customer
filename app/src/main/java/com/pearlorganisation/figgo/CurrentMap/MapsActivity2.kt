package com.pearlorganisation.figgo.CurrentMap

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityMaps2Binding
import org.json.JSONObject

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding
    lateinit var pref:PrefManager

    var activavehiclenumber:TextView? = null
    var vehiclenumber:TextView? = null
    var drivername:TextView? = null
    var mobilenumber:TextView? = null
    var activaimg:ImageView? = null
    var  driverimg:ImageView? = null
    var ride_service_rating:RatingBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        pref  = PrefManager(this)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var accept = findViewById<TextView>(R.id.accept)
        var backtxt = findViewById<TextView>(R.id.backtxt)
        var mobilenumber = findViewById<TextView>(R.id.mobilenumber)
        var activaimg = findViewById<ImageView>(R.id.activaimg)
        var activavehiclenumber = findViewById<TextView>(R.id.activavehiclenumber)
        var drivername = findViewById<TextView>(R.id.drivername)
        var ride_service_rating = findViewById<RatingBar>(R.id.ride_service_rating)
        var vehiclenumber = findViewById<TextView>(R.id.vehiclenumber)
        var driverimg = findViewById<ImageView>(R.id.driverimg)


        getmaps()

        accept.setOnClickListener {
            startActivity(Intent(this, EmergencyMapsActivity::class.java))
        }

        backtxt.setOnClickListener {
            startActivity(Intent(this, MapsActivity1::class.java))
        }

        backimg.setOnClickListener {
            val intent = Intent(this, MapsActivity1::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val myLocation = LatLng(30.302810, 78.012234)
        mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    private fun getmaps() {
        val progressDialog = ProgressDialog(this)
        progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/get-driver"
        Log.d("SendData", "URL===" + URL)
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("driver_id", pref.getdriver_id())
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object : Response.Listener<JSONObject?>{
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {
                    Log.d("SendData", "response===" + response)

                    if (response != null) {
                        val id = response.getJSONObject("data").getString("id")
                        val driver_id = response.getJSONObject("data").getString("driver_id")
                        val year = response.getJSONObject("data").getString("year")

                        mobilenumber?.setText(year)


                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    Toast.makeText(this@MapsActivity2, "Something went wrong!", Toast.LENGTH_LONG).show()

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
}