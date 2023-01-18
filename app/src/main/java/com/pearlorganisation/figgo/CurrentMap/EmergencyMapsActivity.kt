package com.pearlorganisation.figgo.CurrentMap

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.pearlorganisation.Driver_Rating_Activity1
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.DriveRatingActivity
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityEmergencyMapsBinding
import de.hdodenhof.circleimageview.CircleImageView

class EmergencyMapsActivity : BaseClass(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityEmergencyMapsBinding
    lateinit var dlnumber:TextView
    lateinit var driver_name:TextView
    lateinit var activanumber:TextView

    lateinit var pref:PrefManager
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

        pref  = PrefManager(this)
        binding = ActivityEmergencyMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var emrgencybtn = findViewById<TextView>(R.id.emrgencybtn)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var ll_back = findViewById<LinearLayout>(R.id.ll_back)
        var crl_driverimg = findViewById<CircleImageView>(R.id.crl_driverimg)
        activanumber = findViewById<TextView>(R.id.activanumber)
        driver_name = findViewById<TextView>(R.id.driver_name)
        dlnumber = findViewById<TextView>(R.id.dlnumber)
        activanumber.setText(pref.getactivavehiclenumber())
        driver_name.setText(pref.getdrivername())
        dlnumber.setText(pref.getdl_number())
        shareimg()
        onBackPress()

        pref.getactivavehiclenumber().toString()
        pref.getdrivername().toString()
        pref.getdl_number().toString()


        val bundle = intent.extras
        if (bundle != null){
            driver_name.text = "drivername  ${bundle.getString("drivername")}"
            activanumber.text = "activa  ${bundle.getString("activavehiclenumber")}"
            dlnumber.text = "dlNumber  ${bundle.getString("dlNumber")}"
        }
        val driver_name = intent.getStringExtra("driver_name")
        val activanumber = intent.getStringExtra("activanumber")
        val dlnumber = intent.getStringExtra("dlnumber")



        emrgencybtn.setOnClickListener {
            startActivity(Intent(this, Driver_Rating_Activity1::class.java))
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
}