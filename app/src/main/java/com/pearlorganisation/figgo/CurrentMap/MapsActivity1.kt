package com.pearlorganisation.figgo.CurrentMap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
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
import com.pearlorganisation.figgo.Adapter.CurrentVehicleAdapter
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.databinding.ActivityMaps1Binding
import org.json.JSONObject
import java.util.HashMap

class MapsActivity1 : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps1Binding
    lateinit var currentOneWayKmCountAdapter: CurrentOneWayKmCountAdapter
    lateinit var pref: PrefManager
    val mList = ArrayList<OneWayListRatingVehicle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaps1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        pref  = PrefManager(this)
        var ll_accept = findViewById<LinearLayout>(R.id.ll_accept)
        val onewayvehiclelist = findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var progress = findViewById<ProgressBar>(R.id.progress)

        getnxtpage()

        backimg.setOnClickListener {
            val intent = Intent(this, HomeDashboard::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        /*mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012 ","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012 ","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012 ","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012","raingcountlist","ride_service_rating","Reject","Accept"))*/


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val myLocation = LatLng(30.302810, 78.012234)
        mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in India"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    private fun getnxtpage() {
        /*progress?.isVisible = true
        ll_location?.isVisible = false
        ll_choose_vehicle?.isVisible  =false*/
        val URL = "https://test.pearl-developer.com/figo/api/ride/get-nearby-drivers"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("ride_id",pref.getride_id())
        json.put("type","current_booking")
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
                        val data = response.getJSONObject("data")
                        //val price = response.getJSONObject("price")
                        val id = data.getString("id")
                        val booking_id = data.getString("booking_id")
                        val user_id = data.getString("user_id")
                        val vehicle_detail = data.getJSONObject("vehicle_detail")
                        val cabs = vehicle_detail.getJSONArray("cabs")
                        for (i in 0 until cabs.length()){

                            val id = cabs.getJSONObject(i).getString("id")
                            val year = cabs.getJSONObject(i).getString("year")
                            val name = vehicle_detail.getJSONObject(i.toString()).getString("name")
                           // val from = cabs.getJSONObject(i).getString("from")
                            val from = "25"
//                            val upto = cabs.getJSONObject(i).getString("upto")
                           val upto = "40"
                            val v_modal = cabs.getJSONObject(i).getString("v_modal")

                            mList.add(OneWayListRatingVehicle(id,year,from,upto,booking_id,user_id,v_modal,v_modal,name))
                        }
                        currentOneWayKmCountAdapter=
                            CurrentOneWayKmCountAdapter(this@MapsActivity1,mList)
                        binding.onewayvehiclelist.adapter=currentOneWayKmCountAdapter
                        binding.onewayvehiclelist.layoutManager=LinearLayoutManager(this@MapsActivity1)

                    }

                    /*

                      val size = response.getJSONObject("data").getJSONArray("vehicle_types").length()
                      val ride_id = response.getJSONObject("data").getString("ride_id")

                      for(p2 in 0 until size) {
                          val name = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("name")
                          val image = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("full_image")
                         *//* val ride_id = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("ride_id")*//*
                                val ride_id = response.getJSONObject("data").getString("ride_id")
                                val vehicle_id = response.getJSONObject("data").getString("vehicle_id")

                                cablist.add(AdvanceCityCabModel(name,image,vehicle_id,ride_id))
                            }
                            advanceCityAdapter= AdvanceCityDataAdapter(requireActivity(),cablist)
                            binding.currentCabList.adapter=advanceCityAdapter*/

                }
                // Get your json response and convert it to whatever you want.
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
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Authorization", "Bearer " + pref.getToken());
                return headers
            }
        }
        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        queue.add(jsonOblect)

    }

}