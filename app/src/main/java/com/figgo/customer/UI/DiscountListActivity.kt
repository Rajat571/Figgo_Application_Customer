package com.figgo.customer.UI

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Adapter.DiscountAdapter
import com.figgo.customer.Adapter.FiggoAddAdapter
import com.figgo.customer.Model.DicountModelList
import com.figgo.customer.Model.FiggoAdd
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass

class DiscountListActivity : BaseClass() {

    lateinit var discountAdapter: DiscountAdapter

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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount_list)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        var recyclerviewdiscountlist = findViewById<RecyclerView>(R.id.recyclerviewdiscountlist)
        var discountlist = ArrayList<DicountModelList>()

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        shareimg()
        onBackPress()





        discountlist.add(DicountModelList(R.drawable.army))
        discountlist.add(DicountModelList(R.drawable.bpl_img))
        discountlist.add(DicountModelList(R.drawable.oldage_img))
        discountlist.add(DicountModelList(R.drawable.child_img))
        discountlist.add(DicountModelList(R.drawable.student_img))
        discountlist.add(DicountModelList(R.drawable.army))
        discountlist.add(DicountModelList(R.drawable.bpl_img))
        discountlist.add(DicountModelList(R.drawable.oldage_img))
        discountlist.add(DicountModelList(R.drawable.child_img))
        discountlist.add(DicountModelList(R.drawable.student_img))

        recyclerviewdiscountlist.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        discountAdapter= DiscountAdapter(this,discountlist)
        recyclerviewdiscountlist.adapter=discountAdapter


    }
}