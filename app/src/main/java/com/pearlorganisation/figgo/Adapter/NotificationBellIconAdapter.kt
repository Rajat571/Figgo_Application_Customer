package com.pearlorganisation.figgo.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.pearlLib.PrefManager
import com.pearlorganisation.figgo.Model.CurrentVehicleModel
import com.pearlorganisation.figgo.Model.NotificationModelList
import com.pearlorganisation.figgo.R

class NotificationBellIconAdapter(var context: Context, var belliconlist: ArrayList<NotificationModelList>): RecyclerView.Adapter<NotificationBellIconAdapter.CurrentNotificationHolder>() {
    lateinit var pref: PrefManager
    var row_index = -1

    class CurrentNotificationHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_bellnotifaction = itemview.findViewById<TextView>(R.id.tv_bellnotifaction)
        var tv_customer = itemview.findViewById<TextView>(R.id.tv_customer)
        var tv_hour = itemview.findViewById<TextView>(R.id.tv_hour)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationBellIconAdapter.CurrentNotificationHolder {
        return NotificationBellIconAdapter.CurrentNotificationHolder(LayoutInflater.from(parent.context).inflate(R.layout.notificationlist, parent, false))
    }

    override fun onBindViewHolder(holder: NotificationBellIconAdapter.CurrentNotificationHolder, position: Int) {
        pref = PrefManager(context)
        var data = belliconlist[position]
        holder.tv_bellnotifaction.text=data.tv_bellnotifaction
        holder.tv_customer.text=data.tv_customer
        holder.tv_hour.text=data.tv_hour
    }

    override fun getItemCount(): Int {
        return belliconlist.size
    }
}

