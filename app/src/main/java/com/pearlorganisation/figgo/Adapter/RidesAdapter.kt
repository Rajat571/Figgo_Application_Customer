package com.pearlorganisation.figgo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.RidesModel
import com.pearlorganisation.figgo.R

class RidesAdapter(var data:List<RidesModel>): RecyclerView.Adapter<ViewHolder>()



{ override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


var inflater = LayoutInflater.from(parent.context)
    val view =inflater.inflate(R.layout.active_ride_layout,parent,false)
return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.date.text = data[position].date
            holder.to.text = data[position].to
            holder.from.text = data[position].from
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
var date = itemView.findViewById<TextView>(R.id.date)
var from = itemView.findViewById<TextView>(R.id.from);
    var to = itemView.findViewById<TextView>(R.id.to)
}