package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.VehicleInfoList
import com.pearlorganisation.figgo.R

class VehicleInformationAdapter(var context: Context, var vehicle_info_list:List<VehicleInfoList>):
    RecyclerView.Adapter<VehicleInformationAdapter.VehicleInfoHolder>() {

    class VehicleInfoHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var vehiclemodel = itemview.findViewById<TextView>(R.id.vehiclemodel)
        var ravisingh = itemview.findViewById<TextView>(R.id.ravisingh)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleInformationAdapter.VehicleInfoHolder {

        return VehicleInformationAdapter.VehicleInfoHolder(LayoutInflater.from(parent.context).inflate(R.layout.vehicleinfolist, parent, false))

    }

    override fun onBindViewHolder(holder: VehicleInformationAdapter.VehicleInfoHolder, position: Int) {
        var data=vehicle_info_list[position]


    }

    override fun getItemCount(): Int {
       return vehicle_info_list.size
    }
}