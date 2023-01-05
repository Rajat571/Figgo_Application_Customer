package com.pearlorganisation.figgo.UI.Fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.AdvanceCityDataAdapter
import com.pearlorganisation.figgo.IOnBackPressed
import com.pearlorganisation.figgo.Model.AdvanceCityCabModel
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CabDetailsActivity
import com.pearlorganisation.figgo.databinding.ActivityMainBinding
import com.pearlorganisation.figgo.databinding.FragmentAdvanceCityCabBinding
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Advance_cityCab : Fragment(), IOnBackPressed {

    lateinit var binding: FragmentAdvanceCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityDataAdapter
    var cablist=ArrayList<AdvanceCityCabModel>()
    var manualLoc: TextView? = null
    var liveLoc: TextView? = null
    var AUTOCOMPLETE_REQUEST_CODE = -1
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
     var to_lat :String ?= ""
    var from_lat :String ?= ""
    var to_lng :String ?= ""
    var from_lng :String ?= ""
    lateinit var pref: PrefManager
    var selects : String ?= "";
    lateinit var ll_location : LinearLayout
    lateinit var ll_choose_vehicle : LinearLayout
    private var currentLocation: Location? = null
    lateinit var locationManager: LocationManager
    private val requestcode = 2
    private var hasGps = false
    private var hasNetwork = false
    private var locationByGps: Location? = null
    private var locationByNetwork: Location? = null
    private var lastKnownLocationByGps: Location? = null


    var press : String ?= "";
    var datetext: TextView? = null
    var timetext: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_advance_city_cab, container, false)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = PrefManager(requireActivity())
        var calenderimg = view.findViewById<LinearLayout>(R.id.calenderimg)
         datetext = view.findViewById<TextView>(R.id.datetext)
        var watchimg = view?.findViewById<LinearLayout>(R.id.watchimg)
         timetext = view?.findViewById<TextView>(R.id.timetext)
         ll_location = view?.findViewById<LinearLayout>(R.id.ll_location)!!
         ll_choose_vehicle = view?.findViewById<LinearLayout>(R.id.ll_choose_vehicle)!!
        manualLoc = view?.findViewById<TextView>(R.id.loc_manual)
         liveLoc = view?.findViewById<TextView>(R.id.live_loc)
        var locLinear = view?.findViewById<LinearLayout>(R.id.linear_loc)
        var submit = view?.findViewById<Button>(R.id.submit)
        var next = view?.findViewById<Button>(R.id.next)
        var destLinear = view?.findViewById<LinearLayout>(R.id.linear_des)
        ll_choose_vehicle?.isVisible = false
        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), apiKey)
        }

        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

         hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//------------------------------------------------------//
         hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentDate = LocalDateTime.now().format(formatter)
            val formated = DateTimeFormatter.ofPattern("HH:mm:s")
            val currentTime = LocalDateTime.now().format(formated)

           datetext?.setText(currentDate)
           timetext?.setText(currentTime)
        } else {
            TODO("VERSION.SDK_INT < O")
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
                        val datApi : String
                        if (monthOfYear < 9){
                           // dat = (dayOfMonth.toString() + "-0" + (monthOfYear + 1) + "-" + year.toString())
                            dat = (year.toString() + "-0" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
                        }else {
                             //dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year.toString())
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
            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS,Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }

        liveLoc?.setOnClickListener {

            press = "live";
            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS,Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }

        submit?.setOnClickListener {



            if (to_lat == ""){
                Toast.makeText(requireActivity(), "Please select Start Address", Toast.LENGTH_LONG).show()
            }else if (from_lat == ""){
                Toast.makeText(requireActivity(), "Please select Destination Address", Toast.LENGTH_LONG).show()

            }else {
                submitform()
            }

        }
        next?.setOnClickListener {


         startActivity(Intent(requireActivity(), CabDetailsActivity::class.java))



        }



        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())


        binding.recylerCabList.layoutManager=GridLayoutManager(context,3)
      //  cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
       // cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
       // cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
      //  cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))
       // cablist.add(AdvanceCityCab(R.drawable.ola_mini,"256-420"))




        locLinear?.setOnClickListener {

            val internet :Boolean = isOnline(requireActivity())
            if(internet == true) {
                mainBinding = ActivityMainBinding.inflate(layoutInflater)
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

                selects = "start"
                if (isLocationPermissionGranted()) {
                    getLocation()
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
                    getLocation()
                }else{
                    requestPermissions()
                }
            }else{
                Toast.makeText(requireActivity(), "Please turn on internet", Toast.LENGTH_LONG).show()

            }
        }




       // Initialize Places.

    }

    private fun submitform() {
        val progressDialog = ProgressDialog(requireActivity())
        progressDialog.show()
        val URL = "https://test.pearl-developer.com/figo/api/ride/create-city-ride"
        val queue = Volley.newRequestQueue(requireContext())
        val json = JSONObject()
        json.put("date", datetext?.text.toString())
        json.put("time", timetext?.text.toString())
        json.put("to_lat", to_lat)
        json.put("to_lng", to_lng)
        json.put("from_lat", from_lat)
        json.put("from_lng", from_lng)
        json.put("type", "advance_booking")
        json.put("to_location_name", manualLoc?.text.toString())
        json.put("from_location_name", liveLoc?.text.toString())




        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {

                        progressDialog.hide()
                        ll_location?.isVisible = false
                       ll_choose_vehicle?.isVisible  =true

                        val size = response.getJSONObject("data").getJSONArray("vehicle_types").length()
                       val rideId = response.getJSONObject("data").getString("ride_id")

                        for(p2 in 0 until size) {



                        val name = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("name")
                            val image = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("full_image")


                            val vehicle_id = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("id")
                            val min = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("min_price")
                            val max = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("max_price")

                            cablist.add(AdvanceCityCabModel(name,image,rideId,vehicle_id,min,max))
                            }

                        advanceCityAdapter= AdvanceCityDataAdapter(requireActivity(),cablist)
                        binding.recylerCabList.adapter=advanceCityAdapter

                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    Toast.makeText(requireActivity(), "Something went wrong!", Toast.LENGTH_LONG).show()

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
                    networkLocationListener
                )
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
        }
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
                requestcode
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

}