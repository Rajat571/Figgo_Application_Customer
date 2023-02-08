package com.pearlorganisation.figgo.UI
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.location.*
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
<<<<<<< HEAD:app/src/main/java/com/pearlorganisation/figgo/UI/EmergencyRoutedraweActivity.kt
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.pearlLib.BaseClass
import com.pearlorganisation.figgo.pearlLib.PrefManager
=======
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.DriveRatingActivity
import com.pearlorganisation.figgo.R
>>>>>>> f277e8a4a95ac1c84ea1662c6fdef3be7abd4924:app/src/main/java/com/pearlorganisation/EmergencyRoutedraweActivity.kt
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class EmergencyRoutedraweActivity : BaseClass(), OnMapReadyCallback, LocationListener {

    lateinit var pref: PrefManager
    private var mMap: GoogleMap? = null
     var destination:MarkerOptions? = null
    private var originLatitude: Double = 28.5021359
    private var originLongitude: Double = 77.4054901
    private var destinationLatitude: Double = 28.5151087
    private var destinationLongitude: Double = 77.3932163
    lateinit var geocoder:Geocoder
    var to_lat :Double ?= 0.0
    var from_lat :Double ?= 0.0
    var to_lng :Double ?= 0.0
    var from_lng :Double ?= 0.0
    var Access_Location_Request_Code:Int=1001
    lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    lateinit var context:Context
    val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:Int=1001
    var pickupLocation: LatLng? = null
    var dropLocation: LatLng? = null
    var pontos: List<LatLng> = java.util.ArrayList()
    var polyline: Polyline? = null
    var line: Polyline? = null
    private var provider: String? = null
    private var locationManager: LocationManager? = null
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
        pref = PrefManager(this@EmergencyRoutedraweActivity)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        var tv_emrgencybtn = findViewById<TextView>(R.id.tv_emrgencybtn)
        var tv_vehiclenumber = findViewById<TextView>(R.id.tv_vehiclenumber)
        var iv_drivername = findViewById<TextView>(R.id.iv_drivername)


        var tv_activanumber = findViewById<TextView>(R.id.tv_activanumber)
        var tv_drivername = findViewById<TextView>(R.id.tv_drivername)
        var tv_dl_number = findViewById<TextView>(R.id.tv_dl_number)
        var iv_call = findViewById<ImageView>(R.id.iv_call)

     //   var tv_emrgencybtn = findViewById<TextView>(R.id.tv_emrgencybtn)
        val profileName=intent.getStringExtra("name")
        val dl_number=intent.getStringExtra("dl_number")
        val veh_number=intent.getStringExtra("veh_number")
        val price=intent.getStringExtra("price")


        tv_activanumber.setText(veh_number)
        tv_drivername.setText(profileName)
        tv_dl_number.setText(dl_number)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        val criteria = Criteria()
        provider = locationManager!!.getBestProvider(criteria, false)

        shareimg()
        onBackPress()

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        tv_emrgencybtn.setOnClickListener {
            startActivity(Intent(this,DriveRatingActivity::class.java))
        }

        iv_call.setOnClickListener {
            
            var intent_call = Intent(Intent.ACTION_DIAL)
            intent_call.data = Uri.parse("tel:"+"+919715597855")
            startActivity(intent_call)
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
               // GetDirection(urll).execute()
                mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))


            }

        }
    }
    override fun onLocationChanged(location: Location) {
        val curLat: Double = location.getLatitude() //current latitude
        val curLong: Double = location.getLongitude() //current longitude

        mMap?.clear()

        pickupLocation = LatLng(curLat, curLong)
        dropLocation = LatLng(from_lat!!.toDouble() , from_lng!!.toDouble())
        val height = 100
        val width = 100
        val bitmapdraw = resources.getDrawable(R.drawable.carmove_img) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
        mMap?.addMarker(MarkerOptions().position(pickupLocation!!).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).title("Current Location"))

        val bitmapdraw2 = resources.getDrawable(R.drawable.drop_location) as BitmapDrawable
        val b2 = bitmapdraw2.bitmap
        val smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false)
        mMap?.addMarker(MarkerOptions().position(dropLocation!!).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).title("dropoff"))

        val source = "" + curLat + "," + curLong
        val destination = "" + from_lat!!.toDouble() + "," + from_lng!!.toDouble()
     //   Log.e("Origin ", "$source\n Destination $destination")
        if (polyline.toString().equals("null") || polyline.toString().equals("")){

        }else {
            polyline!!.remove()
        }
        GetDirection().execute(source, destination)

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



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        to_lat = pref.getToLatLC().toDouble()
        to_lng = pref.getToLngLC().toDouble()
        from_lat =  pref.getToLatMC().toDouble()
        from_lng =  pref.getToLngMC().toDouble()
        pickupLocation = LatLng(to_lat!!, to_lng!!)
        dropLocation = LatLng(from_lat!!, from_lng!!)
        val height = 100
        val width = 100
        val bitmapdraw = resources.getDrawable(R.drawable.pic_location) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
        mMap?.addMarker(
            MarkerOptions().position(pickupLocation!!)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title("pickup"))

        val bitmapdraw2 = resources.getDrawable(R.drawable.drop_location) as BitmapDrawable
        val b2 = bitmapdraw2.bitmap
        val smallMarker2 = Bitmap.createScaledBitmap(b2, width, height, false)
        mMap?.addMarker(MarkerOptions().position(dropLocation!!).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).title("dropoff"))

        val source = "" + to_lat + "," + to_lng
        val destination = "" + from_lat + "," + from_lng
      //  Log.e("Origin ", "$source\n Destination $destination")
        GetDirection().execute(source, destination)


    }

    inner class GetDirection :
        AsyncTask<String?, String?, String?>() {



        override fun onPreExecute() {
            super.onPreExecute()
        }

        protected override fun doInBackground(vararg params: String?): String? {
            var stringUrl = ""


            stringUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=" + params[0] + "&destination=" + params[1] + "&key=" + "AIzaSyCbd3JqvfSx0p74kYfhRTXE7LZghirSDoU" + "&sensor=false"
           // Log.e("URL : ", "" + stringUrl)
            val response = StringBuilder()
            try {
                val url = URL(stringUrl)
                val httpconn = url.openConnection() as HttpURLConnection
                if (httpconn.responseCode == HttpURLConnection.HTTP_OK) {
                    val input = BufferedReader(InputStreamReader(httpconn.inputStream), 8192)
                    var strLine: String? = null
                    while (input.readLine().also { strLine = it } != null) {
                        response.append(strLine)
                    }
                    input.close()
                }
                val jsonOutput = response.toString()
                val jsonObject = JSONObject(jsonOutput)

                // routesArray contains ALL routes
                val routesArray = jsonObject.getJSONArray("routes")
                // Grab the first route
                val route = routesArray.getJSONObject(0)
                val poly = route.getJSONObject("overview_polyline")
                val polyline = poly.getString("points")
                pontos = decodePoly(polyline)!!
                val legs = route.getJSONArray("legs")
                var steps: JSONObject
                var distance: JSONObject? = null
                var totalDistance = 0f
                for (i in 0 until legs.length()) {
                    steps = legs.getJSONObject(i)
                    distance = steps.getJSONObject("distance")
                    val total = distance.getString("text").split(" ".toRegex()).toTypedArray()
                    totalDistance += total[0].replace(",", "").toFloat()
                }
                //distanceString = "$totalDistance Km"
               // Log.e("Total Distance : ", "" + distanceString)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        private fun decodePoly(encoded: String): List<LatLng>? {
            val poly: MutableList<LatLng> = ArrayList()
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
                val p = LatLng(lat.toDouble() / 1E5, lng.toDouble() / 1E5)
                poly.add(p)
            }
            return poly
        }


        override fun onPostExecute(file_url: String?) {
            var src1: LatLng? = null
            var dest: LatLng? = null
            for (i in 0 until pontos.size - 1) {
               // Log.e("call poly ", "loop = $i")
                val src: LatLng = pontos.get(i)
                if (i == 0) {
                    src1 = src
                }
                dest = pontos.get(i + 1)

                try {
                    polyline = mMap?.addPolyline(
                        PolylineOptions().add(
                            LatLng(src.latitude, src.longitude),
                            LatLng(dest.latitude, dest.longitude)
                        ).width(7f).color(
                            Color.GREEN
                        ).geodesic(true))
                } catch (e: NullPointerException) {
                    Log.e("Error", "NullPointerException onPostExecute: $e")
                } catch (e2: Exception) {
                    Log.e("Error", "Exception onPostExecute: $e2")
                }
            }
            try {
                val builder = LatLngBounds.Builder()
                builder.include(src1!!)
                builder.include(dest!!)
                val bounds = builder.build()
                //  mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                val padding = 250 // offset from edges of the map in pixels
                val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
                mMap?.moveCamera(cu)
                this@EmergencyRoutedraweActivity.runOnUiThread {
                    // kmsTxt?.setText("" + distanceString)
                }
            } catch (e: Exception) {
            }
        }
    }


    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()


        locationManager?.requestLocationUpdates(provider!!, 400L, 1F, this@EmergencyRoutedraweActivity)


    }

}

