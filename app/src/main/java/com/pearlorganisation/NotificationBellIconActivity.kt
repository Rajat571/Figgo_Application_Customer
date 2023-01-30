package com.pearlorganisation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.NotificationBellIconAdapter
import com.pearlorganisation.figgo.Model.NotificationModelList
import com.pearlorganisation.figgo.R

class NotificationBellIconActivity : AppCompatActivity() {

    lateinit var notificationBellIconAdapter: NotificationBellIconAdapter
    val belliconlist = ArrayList<NotificationModelList>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_bell_icon)
        var notificationrecyclerview = findViewById<RecyclerView>(R.id.notificationrecyclerview)


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