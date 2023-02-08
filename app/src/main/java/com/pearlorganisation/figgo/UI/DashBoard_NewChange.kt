package com.pearlorganisation.figgo.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.pearlLib.PrefManager
import com.pearlorganisation.figgo.R

class DashBoard_NewChange : AppCompatActivity() {


    lateinit var prefManager: PrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContentView(R.layout.activity_dash_board_new_change)*/
        prefManager = PrefManager(this)
        var recyclerView = findViewById<RecyclerView>(R.id.rcv_cardoffer)




    }
}