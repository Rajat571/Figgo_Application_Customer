package com.pearlorganisation.figgo.CurrentMap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.databinding.ActivityMaps1Binding

class MapsActivity1 : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMaps1Binding
    lateinit var oneWayKmCountAdapter: OneWayKmCountAdapter
    lateinit var pref: PrefManager
    val mList = ArrayList<OneWayListRatingVehicle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaps1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var ll_accept = findViewById<LinearLayout>(R.id.ll_accept)
        val recyclerview = findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var progress = findViewById<ProgressBar>(R.id.progress)

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

        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))

        recyclerview.adapter = OneWayKmCountAdapter(this,mList)
        recyclerview.layoutManager = LinearLayoutManager(this)
        oneWayKmCountAdapter = OneWayKmCountAdapter(this,mList)
        recyclerview.adapter = oneWayKmCountAdapter
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

}