package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Adapter.VehicleAboutAdapter
import com.pearlorganisation.figgo.Model.VehicleInfoList


class VehicleAboutActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

   /* private lateinit var vehicleAboutAdapter: VehicleAboutAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleInfoList: VehicleInfoList
    private val vehicle_about_list=ArrayList<VehicleInfoList>()*/
   private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicleaboutactivity)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var accept = findViewById<TextView>(R.id.accept)
       /* var recyclerView = findViewById<RecyclerView>(R.id.vehicleinformation)*/

        accept.setOnClickListener {
            startActivity(Intent(this,EmergencyActivity::class.java))
        }

        backimg.setOnClickListener {
            val intent = Intent(this, OneWay_Km_CountActivity::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fun onMapReady(googleMap: GoogleMap) {

            mMap = googleMap
            val myLocation = LatLng(30.302810, 78.012234)
            mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in India"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
            mMap.uiSettings.isZoomControlsEnabled = true
        }

        fun onMarkerClick(p0: Marker): Boolean {
            TODO("Not yet implemented")
        }

      /*  recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VehicleAboutAdapter(vehicle_about_list)

        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))
        vehicle_about_list.add(VehicleInfoList("Activa - CH-01-2015","DL. No. Cgdjn9857imvc","Ravi Singh","Ravi Singh"))

        recyclerView.adapter = VehicleAboutAdapter(vehicle_about_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        vehicleAboutAdapter = VehicleAboutAdapter(vehicle_about_list)
        recyclerView.adapter = vehicleAboutAdapter

        vehicleAboutAdapter.onItemClick = {
            val intent = Intent(this,EmergencyActivity::class.java)
            intent.putExtra("vehicleAboutAdapter",it)
            startActivity(intent)
        }*/

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }
}