package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Adapter.BookingAmountAdapter
import com.figgo.customer.Model.BookingAmountList
import com.figgo.customer.R

class OneWayBookingActivity : AppCompatActivity() {

    private lateinit var bookingAmountAdapter: BookingAmountAdapter
    private lateinit var bookingAmountList: BookingAmountList
    private lateinit var recyclerView: RecyclerView
    private val mList1 = ArrayList<BookingAmountList>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_booking)
        var one_way = findViewById<TextView>(R.id.one_way)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)

        backimg.setOnClickListener {
            val intent = Intent(this, Driver_Rating_Activity1::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "I am Inviting you to join  Figgo App for better experience to book cabs"
            );
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }



        var recyclerView = findViewById<RecyclerView>(R.id.recyclerviewbookingamount)
        val mList1 = ArrayList<BookingAmountList>()


        mList1.add(
            BookingAmountList(
                "Compact Cab",
                "WegonR, Swift, i-20,similer",
                "Rs. 2555.00",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra",
                "Rs. 2555.00",
                "Rs. 2555.00",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"
            )
        )
        mList1.add(
            BookingAmountList(
                "Compact Cab",
                "WegonR, Swift, i-20,similer",
                "Rs. 2555.00",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra",
                "Rs. 2555.00",
                "Rs. 2555.00",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"
            )
        )
        mList1.add(BookingAmountList("Compact Cab", "WegonR, Swift, i-20,similer", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Rs. 2555.00", "Rs. 2555.00",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra",
                "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"
            )
        )
        mList1.add(BookingAmountList("Compact Cab", "WegonR, Swift, i-20,similer", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Rs. 2555.00", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab", "WegonR, Swift, i-20,similer", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Rs. 2555.00", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))
        mList1.add(BookingAmountList("Compact Cab", "WegonR, Swift, i-20,similer", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Rs. 2555.00", "Rs. 2555.00", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra", "Driver Charge Extra, Toll  State Tax EXTRAParking Charge Extra"))

        recyclerView.adapter = BookingAmountAdapter(this,mList1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookingAmountAdapter = BookingAmountAdapter(this,mList1)
        recyclerView.adapter = bookingAmountAdapter

      /*  bookingAmountAdapter.onItemClick = {
            val intent = Intent(this, VehicleBookActivity::class.java)
            intent.putExtra("vehicleAboutAdapter", it)
            startActivity(intent)
        }*/
    }
}