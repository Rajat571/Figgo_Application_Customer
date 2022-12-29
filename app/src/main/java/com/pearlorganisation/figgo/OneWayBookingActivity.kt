package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.BookingAmountAdapter
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.BookingAmountList
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard

class OneWayBookingActivity : AppCompatActivity() {

    lateinit var bookingAmountAdapter: BookingAmountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_booking)
        var one_way = findViewById<TextView>(R.id.one_way)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)

       /* backimg.setOnClickListener {
            val intent = Intent(this, DriveRatingActivity::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }*/

        one_way.setOnClickListener {
            startActivity(Intent(this,CabBookActivity::class.java))
        }

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerviewbookingamount)
        val mList1 = ArrayList<BookingAmountList>()


        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab","WegonR, Swift, i-20,similer","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Rs. 2555.00","Rs. 2555.00","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra","Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))

        recyclerView.adapter = BookingAmountAdapter(mList1)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}