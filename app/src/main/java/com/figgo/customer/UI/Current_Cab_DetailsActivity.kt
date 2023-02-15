package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.figgo.customer.UI.CurrentMap.MapsActivity2
import com.figgo.customer.Model.VehicleBookPayList
import com.figgo.customer.R

class Current_Cab_DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.current_cab_details_activity)
        var book_now = findViewById<TextView>(R.id.book_now)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        val vehicle_book_pay_list = ArrayList<VehicleBookPayList>()
        /*var recyclerview = findViewById<RecyclerView>(R.id.vehiclebooknow)*/

        backimg.setOnClickListener {
            val intent = Intent(this, OneWayBookingActivity::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        book_now.setOnClickListener {
           startActivity(Intent(this, MapsActivity2::class.java))
        }

    }
}