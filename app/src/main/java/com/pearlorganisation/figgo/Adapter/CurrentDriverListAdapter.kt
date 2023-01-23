package com.pearlorganisation.figgo.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.CurrentVehicleModel
import com.pearlorganisation.figgo.R

class CurrentDriverListAdapter(var context: Activity, var cablist: ArrayList<CurrentVehicleModel>): RecyclerView.Adapter<CurrentDriverListAdapter.CurrentDriverHolder>() {

    class CurrentDriverHolder(itemview: View): RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentDriverListAdapter.CurrentDriverHolder {
        return CurrentDriverListAdapter.CurrentDriverHolder(LayoutInflater.from(parent.context).inflate(R.layout.driverdetailslist, parent, false))
    }

    override fun onBindViewHolder(holder: CurrentDriverListAdapter.CurrentDriverHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}