package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.NotificationBellIconActivity
import com.pearlorganisation.figgo.Adapter.RideHistoryRowAdapter

class HistoryMapsActivity : BaseClass() {
    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_maps)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)

        shareimg()
        onBackPress()



        iv_bellicon.setOnClickListener {
            startActivity(Intent(this,NotificationBellIconActivity::class.java))
        }

    }
}