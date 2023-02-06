package com.pearlorganisation
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.LocationRequest
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.google.gson.Gson
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.DriveRatingActivity
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UTIL.MapUtility
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


class EmergencyRoutedraweActivity : BaseClass(), OnMapReadyCallback {


    private var mMap: GoogleMap? = null
    lateinit var progressDialog:ProgressDialog
    lateinit var pref:PrefManager
     var destination:MarkerOptions? = null
    private var originLatitude: Double = 28.5021359
    private var originLongitude: Double = 77.4054901
    private var destinationLatitude: Double = 28.5151087
    private var destinationLongitude: Double = 77.3932163
    lateinit var geocoder:Geocoder
    var Access_Location_Request_Code:Int=1001
    var iv_activaimg:ImageView? = null
     var tv_activanumber:TextView? = null
    var tv_drivername:TextView?=null
    var tv_dl_number:TextView? = null

    lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    lateinit var context:Context
    val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:Int=1001
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


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_routedrawe)
        geocoder= Geocoder(this)
        pref = PrefManager(this)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        var tv_emrgencybtn = findViewById<TextView>(R.id.tv_emrgencybtn)
        tv_drivername = findViewById<TextView>(R.id.tv_drivername)
        tv_dl_number = findViewById<TextView>(R.id.tv_dl_number)
        tv_activanumber = findViewById(R.id.tv_activanumber)
        shareimg()
        onBackPress()

        getdriverdetails()

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        tv_emrgencybtn.setOnClickListener {
            startActivity(Intent(this, DriveRatingActivity::class.java))
        }




        val locationRequest=LocationRequest.CREATOR



        // Fetching API_KEY which we wrapped
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["api_key"]
        val apiKey = getString(R.string.api_key)

        // Initializing the Places API with the help of our API_KEY
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }



        // Map Fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val gd = findViewById<Button>(R.id.directions)
        gd.setOnClickListener{
            mapFragment.getMapAsync {
                mMap = it
                val originLocation = LatLng(originLatitude, originLongitude)
                mMap!!.addMarker(MarkerOptions().position(originLocation))
                val destinationLocation = LatLng(destinationLatitude, destinationLongitude)
                mMap!!.addMarker(MarkerOptions().position(destinationLocation))
                val urll = getDirectionURL(originLocation, destinationLocation, apiKey)
                GetDirection(urll).execute()
                mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))


            }

        }
    }


    private fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(this)
        if (ConnectionResult.SUCCESS == status) return true else {
            if (googleApiAvailability.isUserResolvableError(status)) Toast.makeText(this,
                "Please Install google play services to use this application", Toast.LENGTH_LONG).show()
        }
        return false
    }



    private fun getDirectionURL(origin:LatLng, dest:LatLng, secret: String) : String{
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}" +
                "&sensor=false" +
                "&mode=driving" +
                "&key=$secret"
    }

    @SuppressLint("StaticFieldLeak")
    private inner class GetDirection(val url : String) : AsyncTask<Void, Void, List<List<LatLng>>>(){
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body!!.string()

            val result =  ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data, BaseClass.Companion.MapData::class.java)
                val path =  ArrayList<LatLng>()
                for (i in 0 until respObj.routes[0].legs[0].steps.size){
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return result
        }



        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()
            for (i in result.indices){
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.GREEN)
                lineoption.geodesic(true)
            }
            mMap?.addPolyline(lineoption)
        }


    }

    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }
        return poly
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val ll = LatLng(23.777176, 90.399452)
        mMap?.let {
            it.addMarker(MarkerOptions().position(ll).title("Marker"))
            it.moveCamera(CameraUpdateFactory.newLatLng(ll))

            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(23.777176, 90.399452))
                .bearing(45f)
                .tilt(90f)
                .zoom(googleMap.cameraPosition.zoom)
                .build()
        }






        /* mMap = p0!!
        val originLocation = LatLng(originLatitude, originLongitude)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(originLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 18F))*/
    }

    private fun getdriverdetails() {
        val progressDialog = ProgressDialog(this)
        progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/check-ride-request-status"
        Log.d("searchDriver", "json===" +URL )
        Log.d("SendData87", pref.getride_id() )
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        //json.put("ride_id", pref.getride_id())
        json.put("ride_id", "1070")
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json,
                Response.Listener<JSONObject?> { response ->
                    if (response != null) {

                        Log.d("Ride Status", "response===" + response)
                        progressDialog.hide()
                        val status = response.getJSONObject("data").getString("status")

                        if (status.equals("true")){
                            val data = response.getJSONObject("data").getJSONObject("ride_driver").getString("name")
                            val name = response.getJSONObject("data").getJSONObject("ride_driver").getString("name")
                            val dlnumber = response.getJSONObject("data").getJSONObject("ride_driver").getString("dl_number")
                            val v_number = response.getJSONObject("data").getJSONObject("ride_driver").getJSONObject("cab").getString("v_number")
                            /*val name1 = response.getJSONObject("data").getJSONObject("cab_details").getString("name")*/

                            val rating_avg = response.getJSONObject("data").getJSONObject("ride_driver").getString("rating_avg")

                            tv_drivername?.setText(name)
                            tv_dl_number?.setText(dlnumber)
                            tv_activanumber?.setText(v_number)

                            /*   if(!full_image.equals("")){
                                   Picasso.get().load(full_image).placeholder(R.drawable.girl_img).into(driverimg)
                               }*/

                            /* if(!taxi_image.equals("")){
                                 Picasso.get().load(taxi_image).placeholder(R.drawable.blueactiva_img).into(activaimg)
                             }*/


                        }else if (status.equals("false")){
                            //  Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
                        }

                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        progressDialog.hide()
                        Log.d("SendData", "error===" + error)
                        //

                        MapUtility.showDialog(error.toString(),this@EmergencyRoutedraweActivity)
                    }
                }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }


}

