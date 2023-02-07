package com.pearlorganisation.figgo.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.CurrentVehicle_itemListAdapter

class WalletAdapter(var context: Activity, var cardoffer_list: ArrayList<CurrentVehicle_itemListAdapter>): RecyclerView.Adapter<WalletAdapter.CurrentWaletAdapter>() {

    class CurrentWaletAdapter(itemview: View): RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletAdapter.CurrentWaletAdapter {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WalletAdapter.CurrentWaletAdapter, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}