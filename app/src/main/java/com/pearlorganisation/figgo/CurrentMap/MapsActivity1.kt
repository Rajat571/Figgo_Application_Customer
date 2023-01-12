package com.pearlorganisation.figgo.CurrentMap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.isVisible
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
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.DashBoard
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.databinding.ActivityMaps1Binding
import org.json.JSONObject
import java.util.HashMap

class MapsActivity1 : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps1Binding
    lateinit var currentOneWayKmCountAdapter: CurrentOneWayKmCountAdapter
    lateinit var pref: PrefManager
    lateinit var latLng: LatLng
    val mList = ArrayList<OneWayListRatingVehicle>()
    lateinit var fragment: SupportMapFragment
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
        var backtxt = findViewById<TextView>(R.id.backtxt)
        var nxtscreen = findViewById<Button>(R.id.nxtscreen)
        getnxtpage()

        nxtscreen.setOnClickListener {
            startActivity(Intent(this,MapsActivity2::class.java))
        }

        backtxt.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }

        backimg.setOnClickListener {
            startActivity(Intent(this,DashBoard::class.java))
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs")
            intent.setType("text/plain")
            startActivity(Intent.createChooser(intent, "Invite Friends"));
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


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val myLocation = LatLng(30.302810, 78.012234)
        mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in India"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng: LatLng) {
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
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
        json.put("type","current_booking")
       Log.d("SendData", "pref.getToken()===" + pref.getToken())
       Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
            Response.Listener<JSONObject?>               {
            override fun onResponse(response: JSONObject?) {
                Log.d("SendData", "response===" + response)
                if (response != null) {
                    val status = response.getString("status")
                    if (response != null) {
                        val cabs = response.getJSONObject("data").getJSONArray("cabs").length()
                        Log.d("SendData", "size===" + cabs)
                      //  val price = response.getJSONObject("data").getJSONArray("cabs")
                       /* val cab_drivers = response.getJSONObject("data").getJSONArray("cab_drivers")*/
                        val ride = response.getJSONObject("data").getString("ride")
                        /*val cab_drivers = response.getJSONObject("data").getJSONArray("cab_drivers")*/

                        for(p2 in 0 until cabs) {

                            val data=response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2)
                            val cab=data.getString( "cab")
                            Log.d("SendData", "cab===" + cab)
                            Toast.makeText(this@MapsActivity1, "try after some time", Toast.LENGTH_LONG).show()
                            val id = response.getJSONObject("data").getString("id")
                          //  val cab = response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2).getString("cab")
                            var pricestring = response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2).getString("price")
                            var cab_drivers = response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2).getString("cab_drivers")
                            val year = response.getJSONObject("data").getJSONArray("cabs").getJSONObject(p2).getString("year")
                            Log.d("SendData", "list===" + id+"\n"+cab+"\n"+pricestring+"\n"+cab_drivers+"\n"+year)

                            /*val price =  String.format("$ %.2f",pricestring )*/
                           /* Log.d("JsonObjectRequest","response==="+cab+"\n"+price+"\n"+yearArray)*/
                           /* var driver_id: String? = null
                            var year: String? = null
                            var rating: String? = null*/
                            /*for (i in 0 until yearArray.length()){
                                 year = yearArray.getJSONObject(i).getString("year")
                                 driver_id = yearArray.getJSONObject(i).getString("driver_id")
                                rating = yearArray.getJSONObject(i).getJSONObject("driver").getString("rating_avg")
                                Log.d("JsonObjectRequest","response==="+cab+"\n\n"+rating)
                            }*/

                            mList.add(OneWayListRatingVehicle(cab, id,pricestring,cab_drivers,ride,year))

                        }
                        currentOneWayKmCountAdapter = CurrentOneWayKmCountAdapter(this@MapsActivity1,mList)
                        binding.onewayvehiclelist.layoutManager=LinearLayoutManager(this@MapsActivity1)
                        binding.onewayvehiclelist.adapter=currentOneWayKmCountAdapter



                    }

                     /* val size = response.getJSONObject("data").getJSONArray("vehicle_types").length()
                      val ride_id = response.getJSONObject("data").getString("ride_id")

                      for(p2 in 0 until size) {
                          val name = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("name")
                          val image = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("full_image")
                          val ride_id = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("ride_id")
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DashBoard::class.java))
    }

}


