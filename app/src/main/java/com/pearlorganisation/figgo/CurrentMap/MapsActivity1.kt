package com.pearlorganisation.figgo.CurrentMap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.CurrentOneWayKmCountAdapter
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.DashBoard
import com.pearlorganisation.figgo.databinding.ActivityMaps1Binding
import org.json.JSONObject
import java.util.HashMap

class MapsActivity1 : BaseClass(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps1Binding
    lateinit var currentOneWayKmCountAdapter: CurrentOneWayKmCountAdapter
    lateinit var pref: PrefManager
    lateinit var latLng: LatLng
    val mList = ArrayList<OneWayListRatingVehicle>()
    lateinit var fragment: SupportMapFragment
    lateinit var cab_name : String
    lateinit var driver_id : String
    lateinit var pricestring : String
    lateinit var cab_drivers : String
    lateinit var ride : String
    lateinit var year : String
    lateinit var rating : String
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
        binding = ActivityMaps1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        shareimg()
        onBackPress()
        var id = intent.getStringExtra("id")
        var ride_id = intent.getStringExtra("ride_id")
        var driver_id = intent.getStringExtra("driver_id")
        pref  = PrefManager(this)
        var ll_accept = findViewById<LinearLayout>(R.id.ll_accept)
        val onewayvehiclelist = findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var progress = findViewById<ProgressBar>(R.id.progress)
        var backtxt = findViewById<TextView>(R.id.backtxt)

        getcablist(id,ride_id)
        /*getAccept()*/


        backtxt.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }

        backimg.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }



      /*  mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept","min_price","vehicle_detail","year",""))*/

       /* currentOneWayKmCountAdapter= CurrentOneWayKmCountAdapter(this@MapsActivity1,mList)
        binding.onewayvehiclelist.adapter=currentOneWayKmCountAdapter
        binding.onewayvehiclelist.layoutManager=LinearLayoutManager(this@MapsActivity1)*/


       // val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
      //  mapFragment.getMapAsync(this)
    }

    private fun getcablist(id: String?, ride_id: String?) {
        val URL = "https://test.pearl-developer.com/figo/api/ride/select-city-vehicle-type"
        Log.d("SendData", "URL===" + URL)
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("vehicle_type_id", id)
        json.put("ride_id",ride_id)
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
            Response.Listener<JSONObject?>               {
            override fun onResponse(response: JSONObject?) {
                Log.d("SendData", "response===" + response)
                if (response != null) {
                    val status = response.getString("status")
                    if(status.equals("false")){
                        Toast.makeText(this@MapsActivity1, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                    }else{
                        getnxtpage()
                      //  Toast.makeText(this@MapsActivity1, "true", Toast.LENGTH_LONG).show()

                    }
                }

            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("SendData", "error===" + error)
                Toast.makeText(this@MapsActivity1, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers.put("Content-Type", "application/json; charset=UTF-8")
                headers.put("Authorization", "Bearer " + pref.getToken())
                headers.put("Accept", "application/vnd.api+json");
                return headers
            }
        }
        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        queue.add(jsonOblect)


    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val myLocation = LatLng(30.302810, 78.012234)
        mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in India"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng: LatLng) {
                mMap.clear()
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                val location = LatLng(latlng.latitude, latlng.longitude)
                mMap.addMarker(MarkerOptions().position(location))
            }
        })
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

   private fun getnxtpage() {
        val URL = "https://test.pearl-developer.com/figo/api/ride/get-nearby-drivers"
       Log.d("SendData", "URL===" + URL)
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("ride_id",pref.getride_id())
        /*json.put("ride_id","33")*/
      //  json.put("type","current_booking")
       Log.d("SendData", "pref.getToken()===" + pref.getToken())
       Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
            Response.Listener<JSONObject?>               {
            override fun onResponse(response: JSONObject?) {
                Log.d("SendData", "response===" + response)
                if (response != null) {
                    val status = response.getString("status")
                    if (response != null) {
                        val cabs = response.getJSONObject("data").getJSONArray("cabs")
                        val ride = response.getJSONObject("data").getJSONObject("ride").getString("id")

                        for(p2 in 0 until cabs.length()) {
                            val data=response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2)
                            cab_name=data.getString( "cab")
                            pricestring=data.getString( "price")
                              val cab_driversArray = data.getJSONArray("cab_drivers")
                           for (i in 0 until cab_driversArray.length()){

                               val jsonObject = cab_driversArray.getJSONObject(i)
                               driver_id =    jsonObject.getString("driver_id")
                             year =    jsonObject.getString("year")
                              rating=  jsonObject.getJSONObject("driver").getString("rating_avg")

                           }
                            mList.add(OneWayListRatingVehicle(driver_id,cab_name,year,pricestring, rating,ride,))

                        }
                        currentOneWayKmCountAdapter = CurrentOneWayKmCountAdapter(this@MapsActivity1,mList)
                        binding.onewayvehiclelist.layoutManager=LinearLayoutManager(this@MapsActivity1)
                        binding.onewayvehiclelist.adapter=currentOneWayKmCountAdapter
                    }
                }
            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("SendData", "error===" + error)
                Toast.makeText(this@MapsActivity1, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers.put("Content-Type", "application/json; charset=UTF-8")
                headers.put("Authorization", "Bearer " + pref.getToken())
                headers.put("Accept", "application/vnd.api+json");
                return headers
            }
        }
        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        queue.add(jsonOblect)

    }


   /* private fun getAccept() {
        val URL = "https://test.pearl-developer.com/figo/api/ride/select-driver"
        Log.d("SendData", "URL===" + URL)
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("ride_id",pref.getride_id())
        json.put("driver_id",pref.getdriver_id())
        json.put("price",pref.getprice())
        Log.d("SendData", "pref.getToken()===" + pref.getToken())
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
            Response.Listener<JSONObject?>               {
            override fun onResponse(response: JSONObject?) {
                Log.d("SendData", "response===" + response)
                if (response != null) {
                    val status = response.getString("status")
                    if(status.equals("false")){
                        Toast.makeText(this@MapsActivity1, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                    }else{
                        getnxtpage()


                    }
                }

            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("SendData", "error===" + error)
                Toast.makeText(this@MapsActivity1, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers.put("Content-Type", "application/json; charset=UTF-8")
                headers.put("Authorization", "Bearer " + pref.getToken())
                return headers
            }
        }
        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        queue.add(jsonOblect)

    }*/

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DashBoard::class.java))
    }

}


