package com.figgo.customer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.figgo.customer.Model.FiggoAdd
import com.figgo.customer.R

class FiggoAddAdapter(var figgo_add_list:List<FiggoAdd>):
    Adapter<FiggoAddAdapter.Figgo_Add_Holder>() {


    class Figgo_Add_Holder(view:View):ViewHolder(view){
        var image=view.findViewById<ImageView>(R.id.figgo_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Figgo_Add_Holder {
       return Figgo_Add_Holder(LayoutInflater.from(parent.context).inflate(R.layout.figgo_add_layout,parent,false))
    }

    override fun onBindViewHolder(holder: Figgo_Add_Holder, position: Int) {
       var data=figgo_add_list[position]
        holder.image.setImageResource(data.image)
    }

    override fun getItemCount(): Int {
        return figgo_add_list.size
    }
}