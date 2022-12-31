package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard

class OneWay_Km_CountActivity : AppCompatActivity() {

    lateinit var oneWayKmCountAdapter: OneWayKmCountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_km_count)
        /*var nextscreen = findViewById<TextView>(R.id.nextscreen)*/
        var ll_accept = findViewById<LinearLayout>(R.id.ll_accept)
        val recyclerview = findViewById<RecyclerView>(R.id.onewayvehiclelist)
        val mList = ArrayList<OneWayListRatingVehicle>()

        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)

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

        recyclerview.adapter = OneWayKmCountAdapter(mList)
        recyclerview.layoutManager = LinearLayoutManager(this)
        oneWayKmCountAdapter = OneWayKmCountAdapter(mList)
        recyclerview.adapter = oneWayKmCountAdapter

        oneWayKmCountAdapter.onItemClick = {
            val intent = Intent(this,VehicleAboutActivity::class.java)
            intent.putExtra("oneWayKmCountAdapter",it)
            startActivity(intent)
        }
    }

}


