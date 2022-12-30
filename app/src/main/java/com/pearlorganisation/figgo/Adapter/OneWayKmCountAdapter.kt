package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.EmergencyActivity
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CabDetailsActivity
import com.pearlorganisation.figgo.UI.CityCabActivity

class OneWayKmCountAdapter( private val mList: List<OneWayListRatingVehicle>) : RecyclerView.Adapter<OneWayKmCountAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneWayKmCountAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.oneway_count_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OneWayKmCountAdapter.ViewHolder, position: Int) {
        val OneWayListRatingVehicle = mList[position]

        holder.itemView.setOnClickListener {
            when(holder.adapterPosition){
                0->{

                    holder.acceptcountlist.text
//                    holder.cab.setOnClickListener{
//                        recreate(context)
                    //  }
                }
                1-> holder.acceptcountlist.text
                2-> holder.acceptcountlist.text
                3-> holder.acceptcountlist.text
                5-> holder.acceptcountlist.text
            }

        }

        holder.vehiclemodel.text = OneWayListRatingVehicle.vehiclemodel
   //     holder.raingcountlist.text = OneWayListRatingVehicle.raingcountlist
      //  holder.ride_service_rating.text = OneWayListRatingVehicle.ride_service_rating
        holder.reject.text = OneWayListRatingVehicle.reject
        holder.acceptcountlist.text = OneWayListRatingVehicle.acceptcountlist

    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){

        var vehiclemodel:TextView = itemView.findViewById(R.id.vehiclemodel)
        val raingcountlist:TextView = itemView.findViewById(R.id.raingcountlist)
        //   val ride_service_rating:TextView = itemView.findViewById(R.id.ride_service_rating)
        val reject:TextView = itemView.findViewById(R.id.reject)
        val acceptcountlist:TextView = itemView.findViewById(R.id.acceptcountlist)

    }

}