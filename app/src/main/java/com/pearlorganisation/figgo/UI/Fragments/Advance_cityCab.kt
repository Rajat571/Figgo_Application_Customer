package com.pearlorganisation.figgo.UI.Fragments

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.BassBoost
import android.media.audiofx.EnvironmentalReverb
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.androidgamesdk.gametextinput.Settings

import com.pearlorganisation.figgo.Adapter.AdvanceCityAdapter
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityMainBinding
import com.pearlorganisation.figgo.databinding.FragmentAdvanceCityCabBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest


class Advance_cityCab : Fragment() {

    lateinit var binding: FragmentAdvanceCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var cablist=ArrayList<AdvanceCityCab>()
    var manualLoc: TextView? = null
    var locText: TextView? = null
    var AUTOCOMPLETE_REQUEST_CODE = -1
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_advance_city_cab, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var calenderimg = view.findViewById<ImageView>(R.id.calenderimg)
        var datetext = view.findViewById<TextView>(R.id.datetext)
        var watchimg = view?.findViewById<ImageView>(R.id.watchimg)
        var timetext = view?.findViewById<TextView>(R.id.timetext)
        manualLoc = view?.findViewById<TextView>(R.id.loc_manual)
         locText = view?.findViewById<TextView>(R.id.live_loc)
        var locLinear = view?.findViewById<LinearLayout>(R.id.linear_loc)
        var submit = view?.findViewById<Button>(R.id.submit)
        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), apiKey)
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
                            dat = (dayOfMonth.toString() + "-0" + (monthOfYear + 1) + "-" + year)
                        }else {
                             dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        }
                        datetext.setText(dat)
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
                        timetext.text = SimpleDateFormat("HH:mm").format(cal.time)
                    }
                }
                TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
            }

        }
        manualLoc?.setOnClickListener {
            val field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS,Place.Field.LAT_LNG)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                .build(requireActivity())
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }

        submit?.setOnClickListener {

        }


        binding.recylerCabList.layoutManager=GridLayoutManager(context,4)
        cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))
        cablist.add(AdvanceCityCab(R.drawable.ola_mini,"256-420"))
        cablist.add(AdvanceCityCab(R.drawable.ola_auto,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_bike,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_lux,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_mini,"25-40"))
        advanceCityAdapter=AdvanceCityAdapter(requireActivity(),cablist)
        binding.recylerCabList.adapter=advanceCityAdapter

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())



        locLinear?.setOnClickListener {

            val internet :Boolean = isOnline(requireActivity())
            if(internet == true) {
                getLocation()
            }else{
                Toast.makeText(requireActivity(), "Please turn on internet", Toast.LENGTH_LONG).show()

            }
        }

        locLinear?.setOnClickListener {

            val internet :Boolean = isOnline(requireActivity())
            if(internet == true) {
                getLocation()
            }else{
                Toast.makeText(requireActivity(), "Please turn on internet", Toast.LENGTH_LONG).show()

            }
        }
       // Initialize Places.

    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        return@addOnCompleteListener;
                    }
                    if (location != null) {
                        val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                        mainBinding.apply {
                         //   tvLatitude.text = "Latitude\n${list[0].latitude}"
                          //  tvLongitude.text = "Longitude\n${list[0].longitude}"
                           // tvCountryName.text = "Country Name\n${list[0].countryName}"
                            var location: String?  =  "${list[0].getAddressLine(0)}"
                            locText!!.text = location?.replace("133","")
                            //tvAddress.text = "Address\n${list[0].getAddressLine(0)}"
                        }
                    }
                }
            } else {
                Toast.makeText(requireActivity(), "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
               android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
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
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                manualLoc!!.setText(place.address)
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


}
