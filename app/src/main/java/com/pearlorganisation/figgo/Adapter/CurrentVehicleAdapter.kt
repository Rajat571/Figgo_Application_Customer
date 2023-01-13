package com.pearlorganisation.figgo.Adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.CurrentMap.MapsActivity1
import com.pearlorganisation.figgo.CurrentMap.MapsActivity2
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.Model.CurrentVehicleModel
import com.pearlorganisation.figgo.R

import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.util.HashMap

class CurrentVehicleAdapter(var context: Activity, var cablist: ArrayList<CurrentVehicleModel>): RecyclerView.Adapter<CurrentVehicleAdapter.CurrentVehicleHolder>() {
    lateinit var pref: PrefManager
    var row_index = -1

    class CurrentVehicleHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var cab=itemview.findViewById<ImageView>(R.id.cab)
        var ratings=itemview.findViewById<TextView>(R.id.rating)
        var min=itemview.findViewById<TextView>(R.id.min)
        var max=itemview.findViewById<TextView>(R.id.max)
        var linear=itemview.findViewById<LinearLayout>(R.id.linear)

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

    /*    holder.itemView.setOnClickListener {

            pref.setride_id(data.ride_id)
            pref.setVehicleId(data.driver_id)
            row_index = position
            notifyDataSetChanged()
            context.startActivity(Intent(context, MapsActivity1::class.java))
        }*/
        if (row_index === position) {
            holder.linear.setBackgroundColor(context.resources.getColor(R.color.quantum_bluegrey700))
        }

        holder.linear.setOnClickListener {
           // progress?.isVisible = true
         //   ll_location?.isVisible = false
         //   ll_choose_vehicle?.isVisible  =false





           context.startActivity(Intent(context,MapsActivity1::class.java)
               .putExtra("id",data.driver_id)
               .putExtra("ride_id",data.ride_id))
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