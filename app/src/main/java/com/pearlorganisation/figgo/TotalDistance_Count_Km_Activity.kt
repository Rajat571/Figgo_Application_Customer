package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Adapter.VehicleInformationAdapter
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.Model.VehicleInfoList
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard

class TotalDistance_Count_Km_Activity : AppCompatActivity() {

    lateinit var vehicleInformationAdapter: VehicleInformationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_distance_count_km)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var vehicleinformation = findViewById<RecyclerView>(R.id.vehicleinformation)
        val vehicle_info_list=ArrayList<VehicleInfoList>()

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






    }
}