package com.pearlorganisation.figgo.CurrentMap

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
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
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityMaps2Binding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MapsActivity2 : BaseClass(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps2Binding
    lateinit var pref:PrefManager

    var activavehiclenumber:TextView? = null
    var dl_number:TextView? = null
    var drivername:TextView? = null
    var mobilenumber:TextView? = null
    var activaimg:ImageView? = null
    var  driverimg:ImageView? = null
   lateinit var ll_main1:LinearLayout
    lateinit var  ll_waiting:LinearLayout
    var  driver_id:String? = null
    var  ride_id:String? = null
    var ride_service_rating:RatingBar? = null
    var toLat:String = ""
    var toLong:String = ""
    var fromLat:String = ""
    var fromLong:String = ""


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
        binding = ActivityMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        driver_id = intent.getStringExtra("driver_id")
        ride_id = intent.getStringExtra("ride_id")
        pref = PrefManager(this)
        shareimg()
        onBackPress()

        var accept = findViewById<TextView>(R.id.accept)
        var reject_btn = findViewById<TextView>(R.id.accept)

        activaimg = findViewById<ImageView>(R.id.activaimg)
        activavehiclenumber = findViewById<TextView>(R.id.activavehiclenumber)
        drivername = findViewById<TextView>(R.id.drivername)
        ll_main1 = findViewById(R.id.ll_main1)
        ll_waiting = findViewById(R.id.ll_waiting)

        ride_service_rating = findViewById<RatingBar>(R.id.ride_service_rating)
        dl_number = findViewById<TextView>(R.id.dl_number)
        driverimg = findViewById<ImageView>(R.id.driverimg)


        getmaps()

        accept.setOnClickListener {
            if (ll_main1.isVisible) {
                ll_main1.visibility = View.GONE
                ll_waiting.visibility = View.VISIBLE


                val URL = "https://test.pearl-developer.com/figo/api/ride/select-driver"
                // Log.d("SendData", "URL===" + URL)
                val queue = Volley.newRequestQueue(this)
                val json = JSONObject()
                json.put("ride_id", pref.getRideId())
                json.put("driver_id", driver_id)
                json.put("price", pref.getprice())
                Log.d("SendData", "pref.getToken()===" + pref.getToken())
                Log.d("SendData", "json===" + json)
                val jsonOblect: JsonObjectRequest =
                    object : JsonObjectRequest(Method.POST, URL, json, object :
                        Response.Listener<JSONObject?> {
                        override fun onResponse(response: JSONObject?) {
                            Log.d("SendData", "response===" + response)
                            if (response != null) {
                                val status = response.getString("status")
                                if (status.equals("false")) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Something Went Wrong!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
//                            ll_waiting.visibility= View.VISIBLE
//                            ll_main1.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Searching...",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    /* val bundle = Bundle()
                            bundle.putString("drivername", "test driver")
                            bundle.putString("activavehiclenumber", "test vehicle number")
                            bundle.putString("dl_number", "test dlnumber")
                            val intent = Intent(applicationContext, EmergencyMapsActivity::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)*/

                                }
                            }

                        }
                    }, object : Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError?) {
                            Log.d("SendData", "error===" + error)
                            Toast.makeText(
                                applicationContext,
                                "Something Went Wrong!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }) {
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers: MutableMap<String, String> = java.util.HashMap()
                            headers.put("Content-Type", "application/json; charset=UTF-8")
                            headers.put("Authorization", "Bearer " + pref.getToken())
                            headers.put("Accept", "application/vnd.api+json");
                            return headers
                        }
                    }
                jsonOblect.setRetryPolicy(
                    DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                )

                queue.add(jsonOblect)


                /*val bundle = Bundle()
            bundle.putString("drivername", drivername?.text.toString())
            bundle.putString("activavehiclenumber", activavehiclenumber?.text.toString())
            bundle.putString("dl_number", dl_number?.text.toString())
            val intent = Intent(this, EmergencyMapsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)*/


                /*// Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)*/
            }

            reject_btn.setOnClickListener {
                Toast.makeText(applicationContext, "Searching...", Toast.LENGTH_LONG).show()
            }
        }
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
        json.put("driver_id", driver_id)
        json.put("ride_id", ride_id)
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object : Response.Listener<JSONObject?>{
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {
                    Log.d("SendData", "response===" + response)

                    if (response != null) {
                        progressDialog.hide()
                        val dataobject = response.getJSONObject("data")
                        val driverObject = dataobject.getJSONObject("driver")
                        val cab_detailObject = dataobject.getJSONObject("cab_detail")
                        val rideObject = response.getJSONObject("ride")
                        val to_locationObject = rideObject.getJSONObject("to_location")
                        val from_locationObject = rideObject.getJSONObject("from_location")
                        toLat = to_locationObject.getString("lat")
                        toLong = to_locationObject.getString("lng")
                        fromLat = from_locationObject.getString("lat")
                        fromLong = from_locationObject.getString("lng")
                        val driverName = driverObject.getString("name")
                        val dlNumber = driverObject.getString("dl_number")
                        val rating = driverObject.getString("rating_avg")
                        val vNumber = dataobject.getString("v_number")
                        val docObject = driverObject.getJSONObject("documents")
                        val driver_image = docObject.getString("driver_image")
                        val taxi_image = docObject.getString("taxi_image")

                       // activavehiclenumber?.setText(cab_detailObject.getString("name")+" "+vNumber)
                        activavehiclenumber?.setText(vNumber)
                        drivername?.setText(driverName)
                        dl_number?.setText(dlNumber)
                        ride_service_rating?.rating = rating.toFloat()

                        if(!driver_image.equals("")){
                            Picasso.get().load(driver_image).placeholder(R.drawable.girl_img).into(driverimg)
                        }

                        if(!taxi_image.equals("")){
                            Picasso.get().load(taxi_image).placeholder(R.drawable.blueactiva_img).into(activaimg)
                        }

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
                    headers.put("Content-Type", "application/json; charset=UTF-8")
                    headers.put("Authorization", "Bearer " + pref.getToken())
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }
}





