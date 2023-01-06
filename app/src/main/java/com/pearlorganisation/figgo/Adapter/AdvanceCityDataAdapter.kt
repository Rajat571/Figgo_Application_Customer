package com.pearlorganisation.figgo.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.transition.Hold
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.Model.AdvanceCityCabModel
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CabDetailsActivity
import com.pearlorganisation.figgo.UI.Fragments.CabBookFragment
import com.squareup.picasso.Picasso


class AdvanceCityDataAdapter(var context:Activity, var cablist:List<AdvanceCityCabModel>): Adapter<AdvanceCityDataAdapter.AdvanceCityHolder>() {
    lateinit var pref: PrefManager
    class AdvanceCityHolder(itemview: View):ViewHolder(itemview)
    {
        var cab=itemview.findViewById<ImageView>(R.id.cab)
        var name=itemview.findViewById<TextView>(R.id.name)
        var price=itemview.findViewById<TextView>(R.id.price)
      /*  var max=itemview.findViewById<TextView>(R.id.max)*/
        /*var ll_main=itemview.findViewById<LinearLayout>(R.id.ll_main)*/


    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvanceCityHolder {
        return AdvanceCityHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_cab_layout,parent,false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: AdvanceCityHolder, position: Int) {
        pref = PrefManager(context)
     var data=cablist[position]
      //  holder.cab.setImageResource(data.cab)
        /*holder.ratings.text=data.name*/
       /* holder.min.text=data.min
        holder.max.text=data.max*/
        /*Picasso.get().load(data.image).into(holder.cab)*/
        holder.name.text=data.name
        holder.price.text=data.min
        /*holder.max.text=data.max*/
        Picasso.get().load(data.image).into(holder.cab)
        holder.itemView.setOnClickListener {


            pref.setRideId(data.rideId)
            pref.setVehicleId(data.vehicleId)
            /*context.startActivity(Intent(context,CabBookFragment::class.java))*/

           }



    }

    override fun getItemCount(): Int {
       return cablist.size
    }
}