package com.pearlorganisation.figgo.UI


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.appindexing.Action
import com.google.android.gms.appindexing.AppIndex
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PolylineOptions
import com.pearlorganisation.figgo.R
import org.json.JSONException
import org.json.JSONObject


class LocationDisActivity : FragmentActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerDragListener,
    GoogleMap.OnMapLongClickListener {
    //Our Map
    private var mMap: GoogleMap? = null

    //To store longitude and latitude from map
    private var longitude = 0.0
    private var latitude = 0.0

    //From -> the first coordinate from where we need to calculate the distance
    private var fromLongitude = 0.0
    private var fromLatitude = 0.0

    //To -> the second coordinate to where we need to calculate the distance
    private var toLongitude = 0.0
    private var toLatitude = 0.0
    var myLocation : LatLng ?= null
    //Google ApiClient
    private lateinit var googleApiClient: GoogleApiClient

    //Our buttons
    private val buttonSetTo: Button? = null
    private val buttonSetFrom: Button? = null
    private val buttonCalcDistance: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_distance)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        //Initializing googleapi client
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .addApi(AppIndex.API).build()

        fromLatitude =  30.3244
        fromLongitude = 78.0418
        toLatitude = 30.316496
        toLongitude = 78.032188


         myLocation = LatLng(fromLatitude, fromLongitude)
        direction
    }

    override fun onStart() {
        googleApiClient!!.connect()
        super.onStart()
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        val viewAction = Action.newAction(
            Action.TYPE_VIEW,  // TODO: choose an action type.
            "Maps Page",  // TODO: Define a title for the content shown.
            // TODO: If you have web page content that matches this app activity's content,
            // make sure this auto-generated web page URL is correct.
            // Otherwise, set the URL to null.
            Uri.parse("http://host/path"),  // TODO: Make sure this auto-generated app deep link URI is correct.
            Uri.parse("android-app://com.pearlorganisation.figgo/http/host/path")
        )
        AppIndex.AppIndexApi.start(googleApiClient, viewAction)

          }

    override fun onStop() {
        googleApiClient!!.disconnect()
        super.onStop()
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        val viewAction = Action.newAction(
            Action.TYPE_VIEW,  // TODO: choose an action type.
            "Maps Page",  // TODO: Define a title for the content shown.
            // TODO: If you have web page content that matches this app activity's content,
            // make sure this auto-generated web page URL is correct.
            // Otherwise, set the URL to null.
            Uri.parse("http://host/path"),  // TODO: Make sure this auto-generated app deep link URI is correct.
            Uri.parse("android-app://com.pearlorganisation.figgo/http/host/path")
        )
       AppIndex.AppIndexApi.end(googleApiClient, viewAction)
    }//Getting longitude and latitude

    //moving the map to location
//Creating a location object
    //Getting current location
    private val currentLocation: Unit
        private get() {
            mMap!!.clear()
            //Creating a location object
            @SuppressLint("MissingPermission") val location =
                LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
            if (location != null) {
                //Getting longitude and latitude
                longitude = location.longitude
                latitude = location.latitude

                //moving the map to location
                moveMap()
            }
        }

    //Function to move the map
    private fun moveMap() {
        //Creating a LatLng Object to store Coordinates
       // val latLng = LatLng(latitude, longitude)

        //Adding marker to map
       // mMap!!.addMarker(
        //    MarkerOptions()
        //        .position(latLng) //setting position
        //        .draggable(true) //Making the marker draggable
        //        .title("Current Location")
      //  ) //Adding a title

        //Moving the camera
      //  mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))

        //Animating the camera
      //  mMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f))
    }

    fun makeURL(sourcelat: Double, sourcelog: Double, destlat: Double, destlog: Double): String {
        val urlString = StringBuilder()
        urlString.append("https://maps.googleapis.com/maps/api/directions/json")
        urlString.append("?origin=") // from
        urlString.append(java.lang.Double.toString(sourcelat))
        urlString.append(",")
        urlString
            .append(java.lang.Double.toString(sourcelog))
        urlString.append("&destination=") // to
        urlString
            .append(java.lang.Double.toString(destlat))
        urlString.append(",")
        urlString.append(java.lang.Double.toString(destlog))
        urlString.append("&sensor=false&mode=driving&alternatives=true")
        urlString.append("&key=AIzaSyCZT-YdJMsLTC2J6ssQeytY3zJfjeoIUVE")
        return urlString.toString()
    }//Calling the method drawPath to draw the path

    //Adding the request to request queue
    //Getting the URL
    private val direction: Unit

    //Showing a dialog till we get the route

        //Creating a string request
        private get() {
            //Getting the URL
            val url = makeURL(fromLatitude, fromLongitude, toLatitude, toLongitude)

            //Showing a dialog till we get the route
            val loading = ProgressDialog.show(this, "Getting Current", "Please wait...", false, false)

            //Creating a string request
            val stringRequest = StringRequest(url,
                { response ->
                    loading.dismiss()
                    //Calling the method drawPath to draw the path
                    drawPath(response)
                }
            ) { loading.dismiss() }

            //Adding the request to request queue
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }

    //The parameter is the server response
    fun drawPath(result: String?) {
        //Getting both the coordinates
        val from = LatLng(fromLatitude, fromLongitude)
        val to = LatLng(toLatitude, toLongitude)

        //Calculating the distance in meters
        //val distance = SphericalUtil.computeDistanceBetween(from, to)
        val results = FloatArray(1)
        val distance2 = Location.distanceBetween(fromLatitude,fromLongitude,toLatitude,toLongitude,results)
        val distance = results[0]
        //Displaying the distance
        Toast.makeText(this, "$distance Meters", Toast.LENGTH_SHORT).show()
        try {
            //Parsing json
            val json = JSONObject(result)
            val routeArray = json.getJSONArray("routes")
            val routes = routeArray.getJSONObject(0)
            val overviewPolylines = routes.getJSONObject("overview_polyline")
            val encodedString = overviewPolylines.getString("points")
            val list = decodePoly(encodedString)

            val options = PolylineOptions().width(5f).color(Color.BLUE).geodesic(true)
            for (z in 0 until list.size) {
                val point = list[z]
                options.add(point)
            }
            val line = mMap?.addPolyline(options)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }












    private fun decodePoly(encoded: String): List<LatLng> {
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
            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }
        return poly
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
      //  val latLng = LatLng(-34.00, 151.00)
      //  mMap!!.addMarker(MarkerOptions().position(latLng).draggable(true))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(myLocation!!))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f))
       // mMap!!.setOnMarkerDragListener(this)
     //   mMap!!.setOnMapLongClickListener(this)
    }

    override fun onConnected(bundle: Bundle?) {
        currentLocation
    }

    override fun onConnectionSuspended(i: Int) {}
    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
    override fun onMapLongClick(latLng: LatLng) {
        //Clearing all the markers
      //  mMap!!.clear()
        //Adding a new marker to the current pressed position
      //  mMap!!.addMarker(
       //     MarkerOptions()
       //         .position(latLng)
       //         .draggable(true)
       // )
       // latitude = latLng.latitude
      //  longitude = latLng.longitude
    }

    override fun onMarkerDragStart(marker: Marker) {}
    override fun onMarkerDrag(marker: Marker) {}
    override fun onMarkerDragEnd(marker: Marker) {
        //Getting the coordinates
      //  latitude = marker.position.latitude
       // longitude = marker.position.longitude

        //Moving the map
        moveMap()
    } /* @Override
    public void onClick(View v) {
        if(v == buttonSetFrom){
            fromLatitude = latitude;
            fromLongitude = longitude;
            Toast.makeText(this,"From set",Toast.LENGTH_SHORT).show();
        }

        if(v == buttonSetTo){
            toLatitude = latitude;
            toLongitude = longitude;
            Toast.makeText(this,"To set",Toast.LENGTH_SHORT).show();
        }

        if(v == buttonCalcDistance){
            getDirection();
        }
    }
}*/


}