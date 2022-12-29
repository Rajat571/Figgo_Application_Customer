package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.BookingAmountAdapter
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.BookingAmountList
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle

class OneWayBookingActivity : AppCompatActivity() {

    lateinit var bookingAmountAdapter: BookingAmountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_booking)
        var one_way = findViewById<TextView>(R.id.one_way)

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