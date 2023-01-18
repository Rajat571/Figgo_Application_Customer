package com.pearlorganisation.figgo.UI.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.AdvanceCityDataAdapter
import com.pearlorganisation.figgo.Adapter.CurrentVehicleAdapter
import com.pearlorganisation.figgo.Adapter.CurrentOneWayKmCountAdapter
import com.pearlorganisation.figgo.IOnBackPressed
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.Model.CurrentModel
import com.pearlorganisation.figgo.Model.CurrentVehicleModel
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityMainBinding
import com.pearlorganisation.figgo.databinding.FragmentCurrentCityCabBinding
import org.json.JSONObject
import java.net.URI.create
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Current_cityCab : Fragment(),IOnBackPressed, OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraMoveCanceledListener,GoogleMap.OnCameraIdleListener  {

    private val REQUEST_CHECK_SETTINGS: Int=101;
    private lateinit var mMap: GoogleMap
    var PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=101;
    private lateinit var fusedLocationClient: FusedLocationProviderClient;
    private lateinit var lastLocation: Location;
    private lateinit var locationRequest: LocationRequest;

    lateinit var binding: FragmentCurrentCityCabBinding
    lateinit var currentVehicleAdapter: CurrentVehicleAdapter
    private lateinit var recyclerView: RecyclerView
    var cablist=ArrayList<CurrentVehicleModel>()
    var mList= ArrayList<CurrentModel>()
    var manualLoc: TextView? = null
    var liveLoc: TextView? = null
    var AUTOCOMPLETE_REQUEST_CODE = -1
    var to_lat :String ?= ""
    var from_lat :String ?= ""
    var to_lng :String ?= ""
    var from_lng :String ?= ""
    lateinit var pref: PrefManager
    var current_loc:TextView? = null
    var destination_loc:TextView? = null
    var to_location_name:String ?= null
    var from_location_name:String? = null
    var linear_des:String ? = " "
    var live_loc:String ? = " "
    var selects : String ?= "";
    lateinit var ll_location : LinearLayout
    lateinit var ll_choose_vehicle : LinearLayout
    var press : String ?= "";
    var datetext: TextView? = null
    var timetext: TextView? = null
    var progress: ProgressBar? = null
    var nxtbtn: Button? = null


    private var currentLocation: Location? = null
    lateinit var locationManager: LocationManager
    private val requestcodes = 2
    private val permissionId = 2
    private var hasGps = false
    private var hasNetwork = false
    private var locationByGps: Location? = null
    private var locationByNetwork: Location? = null
    private var lastKnownLocationByGps: Location? = null

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_current_city_cab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = PrefManager(requireActivity())
        var calenderimg = view.findViewById<LinearLayout>(R.id.calenderimg)
        datetext = view.findViewById<TextView>(R.id.datetext)
        var watchimg = view?.findViewById<LinearLayout>(R.id.watchimg)
        timetext = view?.findViewById<TextView>(R.id.timetext)
        ll_location = view?.findViewById<LinearLayout>(R.id.ll_location)!!
       ll_choose_vehicle = view?.findViewById<LinearLayout>(R.id.ll_choose_vehicle)!!
        manualLoc = view?.findViewById<TextView>(R.id.loc_manual)
        liveLoc = view?.findViewById<TextView>(R.id.live_loc)
       /* nxtbtn = view.findViewById(R.id.nxtbtn)*/
        progress = view.findViewById<ProgressBar>(R.id.progress)
        val onewayvehiclelist = view.findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var locLinear = view?.findViewById<LinearLayout>(R.id.linear_loc)
        var submit = view?.findViewById<Button>(R.id.submit)
        var destLinear = view?.findViewById<LinearLayout>(R.id.linear_des)
        var advance_li = view?.findViewById<LinearLayout>(R.id.adLinear)
        var map_li = view?.findViewById<RelativeLayout>(R.id.mapLinear)
        var set = view?.findViewById<TextView>(R.id.img_marker)
        ll_choose_vehicle?.isVisible = false

        map_li?.isVisible = false
        ll_choose_vehicle?.isVisible = false
        pref.setBookingNo("")
        pref.setOtp("")
        pref.setride_id("")
        pref.setVehicleId("")
        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), apiKey)
        }
        set?.setOnClickListener {
            advance_li?.isVisible = true
            map_li?.isVisible = false
        }
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentDate = LocalDateTime.now().format(formatter)
            val formated = DateTimeFormatter.ofPattern("HH:mm:s")
            val currentTime = LocalDateTime.now().format(formated)
            datetext?.setText(currentDate)
            timetext?.setText(currentTime)
        } else {

        }

        calenderimg.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                        it1,
                        { view, year, monthOfYear, dayOfMonth ->
                            val dat : String
                            if (monthOfYear < 9){
                                dat = (year.toString() + "-0" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
                            }else {
                                dat = (year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
                            }
                            datetext?.setText(dat)
                        },
                        year,
                        month,
                        day
                )
            }

            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
            watchimg?.setOnClickListener {
                val cal = Calendar.getInstance()
                val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    if (timetext != null) {
                        timetext?.text = SimpleDateFormat("HH:mm:s").format(cal.time)
                    }
                }
                TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
            }

        }

        manualLoc?.setOnClickListener {
            press = "manual";
            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field).build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }

        liveLoc?.setOnClickListener {

            press = "live";
            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                    .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }



        submit?.setOnClickListener {
            if (to_lat == ""){
              //  startActivity(Intent(requireActivity(), OneWay_Km_CountActivity::class.java))
                Toast.makeText(requireActivity(), "Please select Start Address", Toast.LENGTH_LONG).show()
            }else if (from_lat == ""){
                Toast.makeText(requireActivity(), "Please select Destination Address", Toast.LENGTH_LONG).show()

            }else {
                submitform()
            }

           /* nxtbtn?.setOnClickListener {
                startActivity(Intent(requireActivity(), MapsActivity1::class.java))
                    *//*vehicle_type_id.setvehicle_type_id("vehicle_type_id")
                    ride_id.setride_id("ride_id")*//*

            }*/

        }
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        locLinear?.setOnClickListener {
            val internet :Boolean = isOnline(requireActivity())
            if(internet == true) {
                mainBinding = ActivityMainBinding.inflate(layoutInflater)
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                selects = "start"
                if (isLocationPermissionGranted()) {
                    advance_li?.isVisible = false
                    map_li?.isVisible = true
                    val mapFragment = getChildFragmentManager()
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                    // startActivity(Intent(requireActivity(), MapsActivity::class.java))
                }else{
                    requestPermissions()
                }
            }else{

                Toast.makeText(requireActivity(), "Please turn on internet", Toast.LENGTH_LONG).show()

            }
        }

        destLinear?.setOnClickListener {
            val internet :Boolean = isOnline(requireActivity())
            if(internet == true) {
                mainBinding = ActivityMainBinding.inflate(layoutInflater)
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                selects = "dest"
                if (isLocationPermissionGranted()) {
                    advance_li?.isVisible = false
                    map_li?.isVisible = true
                    val mapFragment = getChildFragmentManager()
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                }else{
                    requestPermissions()
                }
            }else{
                Toast.makeText(requireActivity(), "Please turn on internet", Toast.LENGTH_LONG).show()

            }
        }


       /* cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))*/
     //   advanceCityAdapter=AdvanceCityAdapter(requireActivity(),cablist)
     //   binding.currentCabList.adapter=advanceCityAdapter

    }

    private fun submitform() {
        progress?.isVisible = true
        ll_location?.isVisible = false
        ll_choose_vehicle?.isVisible  =false
        val URL = "https://test.pearl-developer.com/figo/api/ride/create-city-ride"
        Log.d("SendData", "URL===" + URL)
        val queue = Volley.newRequestQueue(requireContext())
        val json = JSONObject()
        json.put("date", datetext?.text.toString())
        json.put("time", timetext?.text.toString())
        json.put("to_lat", to_lat)
        json.put("to_lng", to_lng)
        json.put("from_lat", from_lat)
        json.put("from_lng", from_lng)
        json.put("to_location_name", manualLoc?.text.toString())
        json.put("from_location_name", liveLoc?.text.toString())
        json.put("type","current_booking")
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
                        Response.Listener<JSONObject?>               {
                    override fun onResponse(response: JSONObject?) {
                        Log.d("SendData", "response===" + response)
                        if (response != null) {
                            val status = response.getString("status")
                            if(status.equals("false")){
                                Toast.makeText(requireActivity(), "Something Went Wrong!", Toast.LENGTH_LONG).show()
                            }else{
                                val data = response.getJSONObject("data")
                                val ride_id = data.getString("ride_id")
                                pref.setride_id(ride_id)
                                val vehicle_types = data.getJSONArray("vehicle_types")
                                for (i in 0 until vehicle_types.length()){
                                    val name = vehicle_types.getJSONObject(i).getString("name")
                                    val image = vehicle_types.getJSONObject(i).getString("full_image")
                                    val id = vehicle_types.getJSONObject(i).getString("id")
                                    val min_price = vehicle_types.getJSONObject(i).getString("min_price")
                                    val max_price = vehicle_types.getJSONObject(i).getString("max_price")
                                    cablist.add(CurrentVehicleModel(name,image,ride_id,id, min_price,max_price))

                                }
                                currentVehicleAdapter= CurrentVehicleAdapter(requireActivity(),cablist)
                               binding.recylerCabList.adapter=currentVehicleAdapter
                               binding.recylerCabList.layoutManager=GridLayoutManager(context,3)
                                progress?.isVisible = false
                                ll_location?.isVisible = false
                                ll_choose_vehicle?.isVisible  =true
                            }
                        }

                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        Log.d("SendData", "error===" + error)
                        Toast.makeText(requireActivity(), "Something Went Wrong!", Toast.LENGTH_LONG).show()
                    }
                }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers: MutableMap<String, String> = HashMap()
                        headers.put("Content-Type", "application/json; charset=UTF-8");
                        headers.put("Authorization", "Bearer " + pref.getToken());
                        headers.put("Accept", "application/vnd.api+json");
                        return headers
                    }
                }
        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        queue.add(jsonOblect)

    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        if (isLocationPermissionGranted()){
            if (hasGps) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    0F,
                    gpsLocationListener
                )
            }
//------------------------------------------------------//
            if (hasNetwork) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000,
                    0F,
                    networkLocationListener)
            }


            val lastKnownLocationByGps =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            // locationByGps = getLastKnownLocation()
            lastKnownLocationByGps?.let {
                locationByGps = lastKnownLocationByGps
            }
            //------------------------------------------------------//


            val lastKnownLocationByNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            lastKnownLocationByNetwork?.let {
                locationByNetwork = lastKnownLocationByNetwork
            }
//------------------------------------------------------//
            //  if (locationByGps != null || locationByNetwork != null) {
            /*  if (locationByGps!!.accuracy > locationByNetwork!!.accuracy) {
                  if (selects.equals("start")) {

                      to_lat = locationByGps?.latitude.toString()
                      to_lng = locationByGps?.longitude.toString()

                      val geocoder: Geocoder
                      val addresses: List<Address>
                      geocoder = Geocoder(requireActivity(), Locale.getDefault())

                      addresses = locationByGps?.let {
                          geocoder.getFromLocation(
                              it.latitude,
                              it.longitude,1
                          )
                      }!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                      val address = addresses[0].getAddressLine(0)
                      liveLoc?.setText(address)
                  }else{

                      from_lat = locationByGps?.latitude.toString()
                      from_lng = locationByGps?.longitude.toString()

                      val geocoder: Geocoder
                      val addresses: List<Address>
                      geocoder = Geocoder(requireActivity(), Locale.getDefault())

                      addresses = locationByGps?.let {
                          geocoder.getFromLocation(
                              it.latitude,
                              it.longitude,1
                          )
                      }!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                      val address = addresses[0].getAddressLine(0)
                      manualLoc?.setText(address)
                  }
                  // use latitude and longitude as per your need
              } else {*/
            if (locationByNetwork == null){
                Toast.makeText(requireActivity(), "No Network", Toast.LENGTH_LONG).show()

            }else {
                if (selects.equals("start")) {


                    to_lat = locationByNetwork?.latitude.toString()
                    to_lng = locationByNetwork?.longitude.toString()
                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(requireActivity(), Locale.getDefault())

                    addresses = locationByNetwork?.let {
                        geocoder.getFromLocation(
                            it.latitude,
                            it.longitude, 1
                        )
                    }!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                    val address = addresses[0].getAddressLine(0)
                    liveLoc?.setText(address)
                } else {

                    from_lat = locationByNetwork?.latitude.toString()
                    from_lng = locationByNetwork?.longitude.toString()

                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(requireActivity(), Locale.getDefault())

                    addresses = locationByNetwork?.let {
                        geocoder.getFromLocation(
                            it.latitude,
                            it.longitude, 1
                        )
                    }!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                    val address = addresses[0].getAddressLine(0)
                    manualLoc?.setText(address)
                }
            }
            // use latitude and longitude as per your need
            // }
            // }
        }else{
            requestPermissions()
        }


        /* if (ActivityCompat.checkSelfPermission(
                 requireActivity(),
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                 requireActivity(),
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             // TODO: Consider calling
             //    ActivityCompat#requestPermissions
             // here to request the missing permissions, and then overriding
             //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
             //                                          int[] grantResults)
             // to handle the case where the user grants the permission. See the documentation
             // for ActivityCompat#requestPermissions for more details.
             return
         }else {
             requestPermissions()
         }checkLocationService
         mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                     val location: Location? = task.result

                     if (location != null) {
                         val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                         val list: List<Address> =
                             geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                         mainBinding.apply {
                             if (selects.equals("start")) {
                                 //   tvLatitude.text = "Latitude\n${list[0].latitude}"
                                  to_lat = "${list[0].latitude}"
                                 to_lng = "${list[0].longitude}"
                                 // tvCountryName.text = "Country Name\n${list[0].countryName}"
                                 var location: String? = "${list[0].getAddressLine(0)}"
                                 liveLoc!!.text = location?.replace("133", "")?.replace(",","")
                                 //tvAddress.text = "Address\n${list[0].getAddressLine(0)}"
                             }else{
                                     from_lat  = "${list[0].latitude}"
                                  from_lng = "${list[0].longitude}"


                                 var location: String? = "${list[0].getAddressLine(0)}"
                                 manualLoc!!.text = location?.replace("133", "")?.replace(",","")
                                 //tvAddress.text = "Address\n${list[0].getAddressLine(0)}"
                             }
                         }
                     }else{
                         getLocation()
                     }
                 }*/
    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                if (press.equals("manual")) {
                    manualLoc!!.setText(place.address)
                    from_lat = place.latLng.latitude.toString()
                    from_lng = place.latLng.longitude.toString()
                }else if (press.equals("live")){
                    to_lat = place.latLng.latitude.toString()
                    to_lng = place.latLng.longitude.toString()
                    liveLoc!!.setText(place.address)
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                val status = Autocomplete.getStatusFromIntent(
                    data!!
                )
            } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
            }
        }else if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data!!.getStringExtra("result")
                fetchCurrentLocation();
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }

    @SuppressLint("MissingPermission")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                    return true
                }
            }
        }
        return false
    }

    override fun onBackPressed(): Boolean {

        ll_location?.isVisible = true
        ll_choose_vehicle?.isVisible  =false

        return true
    }

    override fun Any.onBackPressed(): Boolean {

        ll_location?.isVisible = true
        ll_choose_vehicle?.isVisible  =false

        return true
    }





    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestcodes
            )
            false
        } else {
            true
        }
    }
    val gpsLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            locationByGps = location
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}

    }
    //------------------------------------------------------//
    val networkLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            locationByNetwork= location
            // locationByNetwork= location
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        locationManager = requireActivity().getSystemService(
            LOCATION_SERVICE
        ) as LocationManager

        val providers: List<String> = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l: Location = locationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                // Found best last known location: %s", l);
                bestLocation = l
            }
        }
        return bestLocation
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {

            mMap.isMyLocationEnabled = true;
            mMap.uiSettings.isMapToolbarEnabled = true;
            mMap.uiSettings.isMyLocationButtonEnabled = true;
            checkLocationService();
        }

        mMap.setOnCameraMoveStartedListener (this)
        mMap.setOnCameraIdleListener (this)
        mMap.setOnCameraMoveListener  (this)

    }
    override fun openSomeActivityForResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled=true;
            mMap.uiSettings.isMapToolbarEnabled=true;
            mMap.uiSettings.isMyLocationButtonEnabled=true;
            checkLocationService();
        }
    }

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {

                if (selects.equals("start")){
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    to_lat = location.latitude.toString()
                    to_lng = location.longitude.toString()

                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(requireActivity(), Locale.getDefault())

                    addresses = lastLocation?.let {
                        geocoder.getFromLocation(
                            it.latitude,
                            it.longitude, 1
                        )
                    }!! // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                    val address = addresses[0].getAddressLine(0)
                    liveLoc?.setText(address)

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }else{
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    from_lat = location.latitude.toString()
                    from_lng = location.longitude.toString()


                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(requireActivity(), Locale.getDefault())

                    addresses = lastLocation?.let {
                        geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    }!!


                    val address = addresses[0].getAddressLine(0)
                    manualLoc?.setText(address)

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }

            }
        }
    }
    fun checkLocationService() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(2 * 1000);


        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        // builder.setAlwaysShow(true);
        val client = LocationServices.getSettingsClient(requireActivity())
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener(requireActivity()) {it->
            it.locationSettingsStates;
            fetchCurrentLocation();
        }

        task.addOnFailureListener(requireActivity()) { e ->
            if (e is ResolvableApiException) {

                try {

                    e.startResolutionForResult(requireActivity(), REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

            }
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.v("Onmove start","Onmove "+p0);
        mMap.clear()
    }

    override fun onCameraMove() {

        Log.v("Onmove ","Onmove ");
    }

    override fun onCameraMoveCanceled() {
        Log.v("Onmove cancel","Onmove ");
    }

    override fun onCameraIdle() {
        Log.v("Onmove Idle","Onmove ");
        val markerOptions = MarkerOptions().position(mMap.cameraPosition.target)

        mMap.addMarker(markerOptions)
        val position: LatLng = markerOptions.getPosition()


        if (selects.equals("start")){

            to_lat = position.latitude.toString()
            to_lng = position.longitude.toString()

            val geocoder: Geocoder
            val addresses: List<Address>
            geocoder = Geocoder(requireActivity(), Locale.getDefault())

            addresses = position?.let {
                geocoder.getFromLocation(it.latitude, it.longitude, 1)
            }!!


            val address = addresses[0].getAddressLine(0)
            liveLoc?.setText(address)


        }else{

            from_lat = position.latitude.toString()
            from_lng = position.longitude.toString()


            val geocoder: Geocoder
            val addresses: List<Address>
            geocoder = Geocoder(requireActivity(), Locale.getDefault())

            addresses = position?.let {
                geocoder.getFromLocation(it.latitude, it.longitude, 1)
            }!!


            val address = addresses[0].getAddressLine(0)
            manualLoc?.setText(address)

        }
    }

}