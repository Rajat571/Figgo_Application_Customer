package com.pearlorganisation.figgo.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Model.AdvanceCityCabModel
import com.pearlorganisation.figgo.Model.HistoryAdd
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CabDetailsActivity
import com.pearlorganisation.figgo.UI.Fragments.Current_cityCab
import com.squareup.picasso.Picasso


class HistoryAdapter(var context:Activity, var historyList:List<HistoryAdd>): Adapter<HistoryAdapter.HistoryHolder>() {
    lateinit var pref: PrefManager

    class HistoryHolder(itemview: View):ViewHolder(itemview)
    {

        var address = itemview.findViewById<TextView>(R.id.address)

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_layout,parent,false))
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        pref = PrefManager(context)
     var data=historyList[position]
      //  holder.cab.setImageResource(data.cab)
        holder.address.text=data.address
        holder.address.setOnClickListener {
           
            Toast.makeText(context, "Address is click ", Toast.LENGTH_LONG).show()
        }




    }

    override fun getItemCount(): Int {
       return historyList.size
    }
}