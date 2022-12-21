package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.CityCabActivity
import com.pearlorganisation.figgo.UI.MainActivity
import com.pearlorganisation.figgo.UI.Shared_cab_Activity

class CabCategoryAdapter( var context:Context,var cab_category_list:List<CabCategory>):
    Adapter<CabCategoryAdapter.CabCategoryHolder>() {

    class CabCategoryHolder(itemview:View):ViewHolder(itemview){
        var cab_category=itemview.findViewById<ImageView>(R.id.cab_category)
        var cab_name=itemview.findViewById<TextView>(R.id.cab_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabCategoryHolder {
        return CabCategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.cab_category_layout,parent,false))
    }

    override fun onBindViewHolder(holder: CabCategoryHolder, position: Int) {
        var data=cab_category_list[position]
        holder.cab_category.setImageResource(data.cab_category)
        holder.cab_name.text=data.cab_name
        holder.itemView.setOnClickListener {
            when(holder.adapterPosition){
                0->{
                    holder.cab_category.setImageResource(R.drawable.citycab)
                    holder.cab_name.setTextColor(Color.WHITE)
               context.startActivity(Intent(context,CityCabActivity::class.java))
                }
                1->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.intracity)
                }
                2->{
                    holder.cab_name.setTextColor(Color.WHITE)
                holder.cab_category.setImageResource(R.drawable.city_cab)
                    context.startActivity(Intent(context,Shared_cab_Activity::class.java))
                }
                3->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.airportcab)
                }
                4->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.royal)
                }
                5->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.tour)
                }
                6->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.goods)
                }
                7->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.hotel)
                }
                8->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.flight)
                }
                9->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.train)
                }

             /*   adhbgehsabgfehrg*/


                10->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.bus)
                }
                11->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    holder.cab_category.setImageResource(R.drawable.more)
                }

            }
        }
    }

    override fun getItemCount(): Int {
       return cab_category_list.size
    }
}