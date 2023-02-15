package com.figgo.customer.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.figgo.customer.UI.CurrentMap.MapsActivity1
import com.figgo.customer.Model.AdvanceCityCab
import com.figgo.customer.R



class AdvanceCityAdapter(var context:Activity, var cablist: ArrayList<AdvanceCityCab>): Adapter<AdvanceCityAdapter.AdvanceCityHolder>() {

    class AdvanceCityHolder(itemview: View):ViewHolder(itemview)
    {
        var cab=itemview.findViewById<ImageView>(R.id.cab)
        var ratings=itemview.findViewById<TextView>(R.id.rating)
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvanceCityHolder {
        return AdvanceCityHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_cab_layout,parent,false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: AdvanceCityHolder, position: Int) {
     var data=cablist[position]
        /*holder.cab.setImageResource(data.cab)
        holder.ratings.text=data.rating*/
        holder.itemView.setOnClickListener {
            when(holder.adapterPosition)
            {
                0->{
                    holder.cab.setImageResource(R.drawable.ola_auto_active)
//                    holder.cab.setOnClickListener{
//                        recreate(context)
                  //  }
                }
                1->holder.cab.setImageResource(R.drawable.ola_bike_active)
                2->holder.cab.setImageResource(R.drawable.ola_e_rick_active)
                3->holder.cab.setImageResource(R.drawable.ola_lux_active)
                5->holder.cab.setImageResource(R.drawable.ola_bike_active)
                6-> holder.cab.setImageResource(R.drawable.ola_auto_active)
                7->holder.cab.setImageResource(R.drawable.ola_bike_active)
                8->holder.cab.setImageResource(R.drawable.ola_bike_active)
                9->holder.cab.setImageResource(R.drawable.ola_bike_active)
            }
         context.startActivity(Intent(context, MapsActivity1::class.java))
        }
    }

    override fun getItemCount(): Int {
       return cablist.size
    }
}