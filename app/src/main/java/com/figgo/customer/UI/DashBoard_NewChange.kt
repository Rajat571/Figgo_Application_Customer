package com.figgo.customer.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R

class DashBoard_NewChange : AppCompatActivity() {


    lateinit var prefManager: PrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContentView(R.layout.activity_dash_board_new_change)*/
        prefManager = PrefManager(this)
        var recyclerView = findViewById<RecyclerView>(R.id.rcv_cardoffer)




    }
}