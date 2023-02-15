package com.figgo.customer.UI

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Adapter.NotificationBellIconAdapter
import com.figgo.customer.pearlLib.BaseClass
import com.figgo.customer.Model.NotificationModelList
import com.figgo.customer.R

class NotificationBellIconActivity : BaseClass() {

    lateinit var notificationBellIconAdapter: NotificationBellIconAdapter
    val belliconlist = ArrayList<NotificationModelList>()
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
        setContentView(R.layout.activity_notification_bell_icon)
        var notificationrecyclerview = findViewById<RecyclerView>(R.id.notificationrecyclerview)
        shareimg()
        onBackPress()


        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))
        belliconlist.add(NotificationModelList("Update on Form details verifications","Hello Driver, Your form has been sucessfully verified","6 hours ago"))

        notificationBellIconAdapter= NotificationBellIconAdapter(this,belliconlist)
        notificationrecyclerview.adapter=notificationBellIconAdapter
        notificationrecyclerview.layoutManager= LinearLayoutManager(this)

    }
}