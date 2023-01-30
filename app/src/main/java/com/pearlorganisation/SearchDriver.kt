package com.pearlorganisation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R

class SearchDriver : BaseClass() {

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
        setContentView(R.layout.activity_search_driver)
        var tv_click = findViewById<TextView>(R.id.tv_click)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
        shareimg()
        onBackPress()


        tv_click.setOnClickListener {
            startActivity(Intent(this,Current_Driver_Details_List::class.java))
        }
    }
}