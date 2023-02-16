package com.figgo.customer.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Adapter.CardofferAdpter
import com.figgo.customer.Adapter.CurrentWalletAdapter
import com.figgo.customer.Model.CurrentWalletModel
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass

class CurrentWalletActivity : BaseClass() {

    lateinit var currentWalletAdapter: CurrentWalletAdapter
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
        setContentView(R.layout.activity_current_wallet)
        var walletdata = ArrayList<CurrentWalletModel>()
        var recyclerviewwallethistory = findViewById<RecyclerView>(R.id.recyclerviewwallethistory)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        shareimg()
        onBackPress()


        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))
        walletdata.add(CurrentWalletModel("12.1.2023","+ RS.500","From:Mr:xyz","15 minutes 6.25pm","10 km"))

        recyclerviewwallethistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        currentWalletAdapter= CurrentWalletAdapter(this,walletdata)
        recyclerviewwallethistory.adapter=currentWalletAdapter
        recyclerviewwallethistory.adapter

    }
}