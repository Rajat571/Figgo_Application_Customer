package com.figgo.customer.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.Model.CabCategory
import com.figgo.customer.R
import com.figgo.customer.UI.Shared_cab_Activity

class CabCategoryAdapter( var context:Context,var cab_category_list:List<CabCategory>): Adapter<CabCategoryAdapter.CabCategoryHolder>() {
    lateinit var pref: PrefManager
    var row_index = -1

    class CabCategoryHolder(itemview:View):ViewHolder(itemview) {
        var cab_category=itemview.findViewById<ImageView>(R.id.cab_category)
        var cab_name=itemview.findViewById<TextView>(R.id.cab_name)
        var ll_lnr=itemview.findViewById<LinearLayout>(R.id.ll_lnr1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabCategoryHolder {
        return CabCategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.cab_category_layout,parent,false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CabCategoryHolder, position: Int) {
        var data=cab_category_list[position]
        holder.cab_category.setImageResource(data.cab_category)
        holder.cab_name.text=data.cab_name

        holder.itemView.setOnClickListener {

            context.startActivity(Intent(context,Shared_cab_Activity::class.java))
            holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))
            when(holder.adapterPosition){
                0->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    /*holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                   /* holder.cab_category.setImageResource(R.drawable.citycab)*/
              /* context.startActivity(Intent(context,CityCabActivity::class.java))*/

                }
                1->{
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                    holder.cab_name.setTextColor(Color.WHITE)
                    /*holder.cab_category.setImageResource(R.drawable.intracity)*/
                    /*context.startActivity(Intent(context,One_Way_OutStationActivity::class.java))*/
                }
                2->{
                  /*  holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/

                    holder.cab_name.setTextColor(Color.WHITE)
                /*holder.cab_category.setImageResource(R.drawable.city_cab)*/

                }
                3->{
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/

                    holder.cab_name.setTextColor(Color.WHITE)
                   /* holder.cab_category.setImageResource(R.drawable.airportcab)*/
                }
                4->{
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/

                    holder.cab_name.setTextColor(Color.WHITE)
                    /*holder.cab_category.setImageResource(R.drawable.royal)*/
                }
                5->{
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/

                    holder.cab_name.setTextColor(Color.WHITE)
                    /*holder.cab_category.setImageResource(R.drawable.tour)*/
                }
                6->{
                    holder.cab_name.setTextColor(Color.WHITE)
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                   /* holder.cab_category.setImageResource(R.drawable.goods)*/
                }
                7->{
                    holder.cab_name.setTextColor(Color.WHITE)
                    /*holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                    /*holder.cab_category.setImageResource(R.drawable.hotel)*/
                }
                8->{
                    holder.cab_name.setTextColor(Color.WHITE)
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                    /*holder.cab_category.setImageResource(R.drawable.flightwhite)*/
                }
                9->{
                    holder.cab_name.setTextColor(Color.WHITE)
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                    /*holder.cab_category.setImageResource(R.drawable.train)*/
                }

                10->{
                    holder.cab_name.setTextColor(Color.WHITE)
                   /* holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                   /* holder.cab_category.setImageResource(R.drawable.bus)*/
                }
                11->{
                    holder.cab_name.setTextColor(Color.WHITE)
                  /*  holder.ll_lnr.setBackgroundColor(Color.parseColor("#A79ECD"))*/
                    /*holder.cab_category.setImageResource(R.drawable.more)*/
                }

            }
        }
    }

    override fun getItemCount(): Int {
       return cab_category_list.size
    }
}