package com.pearlorganisation.figgo.Adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.CurrentMap.MapsActivity1
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CabDetailsActivity
import com.squareup.picasso.Picasso

class CurrentVehicleAdapter(var context: Activity, var cablist: ArrayList<AdvanceCityCab>): RecyclerView.Adapter<CurrentVehicleAdapter.CurrentVehicleHolder>() {
    lateinit var pref: PrefManager
     var selectedItemPosition: Int = 0

    class CurrentVehicleHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var cab=itemview.findViewById<ImageView>(R.id.cab)
        var ratings=itemview.findViewById<TextView>(R.id.rating)
        var min=itemview.findViewById<TextView>(R.id.min)
        var max=itemview.findViewById<TextView>(R.id.max)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentVehicleAdapter.CurrentVehicleHolder {
        return CurrentVehicleAdapter.CurrentVehicleHolder(LayoutInflater.from(parent.context).inflate(R.layout.current_cab_list, parent, false))
    }

    override fun onBindViewHolder(holder: CurrentVehicleAdapter.CurrentVehicleHolder, position: Int) {
        pref = PrefManager(context)
        var data = cablist[position]
        holder.ratings.text = data.name
        holder.min.text = data.min
        holder.max.text = data.max
        Picasso.get().load(data.image).into(holder.cab)

        holder.cab.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()

            pref.setRideId(data.rideId)
            pref.setVehicleId(data.vehicleId)
            /*context.startActivity(Intent(context, MapsActivity1::class.java))*/
        }
        /* if(selectedItemPosition == position)
            holder.cab.setImageResource(R.color.grey)
        else
            holder.cab.setBackgroundColor(Color.parseColor("#EEEEEE"))*//*
    }*/
    }

    override fun getItemCount(): Int {
       return cablist.size
    }

}