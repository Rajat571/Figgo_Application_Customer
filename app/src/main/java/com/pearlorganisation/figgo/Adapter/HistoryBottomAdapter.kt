package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.UI.HistoryMapsActivity
import com.pearlorganisation.figgo.Model.HistoryBottomModelList
import com.pearlorganisation.figgo.R

class HistoryBottomAdapter(var context: Context , var historybottomlist:ArrayList<HistoryBottomModelList>): RecyclerView.Adapter<HistoryBottomAdapter.HistoryBottomHolder>() {

    class HistoryBottomHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_one = itemview.findViewById<TextView>(R.id.tv_one)
        var tv_name = itemview.findViewById<TextView>(R.id.tv_name)
        var tv_tolocation = itemview.findViewById<TextView>(R.id.tv_tolocation)
        var tv_fromlocation = itemview.findViewById<TextView>(R.id.tv_fromlocation)
        var tv_states = itemview.findViewById<TextView>(R.id.tv_states)
        var tv_view1 = itemview.findViewById<TextView>(R.id.tv_view1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBottomAdapter.HistoryBottomHolder {
        return HistoryBottomAdapter.HistoryBottomHolder(LayoutInflater.from(parent.context).inflate(R.layout.historybottomitemlist, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryBottomAdapter.HistoryBottomHolder, position: Int) {
        if (position == 0){

        }else{

        }
        var data = historybottomlist[position]
        holder.tv_name.text=data.tv_name
        holder.tv_tolocation.text=data.tv_tolocation
        holder.tv_fromlocation.text=data.tv_fromlocation
        holder.tv_states.text=data.tv_states
        holder.tv_view1.text=data.tv_view1

        holder.tv_view1.setOnClickListener {
            context.startActivity(Intent(context, HistoryMapsActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
       return historybottomlist.size
    }

}