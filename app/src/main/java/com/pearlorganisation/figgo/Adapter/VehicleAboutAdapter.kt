package com.pearlorganisation.figgo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.Model.VehicleInfoList
import com.pearlorganisation.figgo.R

class VehicleAboutAdapter(private val vehicle_about_list:ArrayList<VehicleInfoList>) : RecyclerView.Adapter<VehicleAboutAdapter.VehicleViewHolder>(){

    var onItemClick : ((VehicleInfoList) -> Unit)? = null

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var activavehiclenumber:TextView = itemView.findViewById(R.id.activavehiclenumber)
        var vehiclenumber:TextView = itemView.findViewById(R.id.vehiclenumber)
        var drivername:TextView = itemView.findViewById(R.id.drivername)
        var activaimg:ImageView = itemView.findViewById(R.id.activaimg)
        var driverimg:ImageView = itemView.findViewById(R.id.driverimg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleAboutAdapter.VehicleViewHolder {

       val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicleinfolist, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleAboutAdapter.VehicleViewHolder, position: Int) {

        val VehicleInfoList = vehicle_about_list[position]

        holder.vehiclenumber.text=VehicleInfoList.vehiclenumber
        holder.activaimg.setImageResource(R.drawable.blueactiva_img)
        holder.driverimg.setImageResource(R.drawable.girl_img)
        holder.activavehiclenumber.text=VehicleInfoList.activavehiclenumber

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(VehicleInfoList)
        }

    }

    override fun getItemCount(): Int {

        return vehicle_about_list.size
    }

}