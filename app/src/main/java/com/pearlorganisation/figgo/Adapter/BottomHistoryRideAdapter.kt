package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.HistoryMapsActivity
import com.pearlorganisation.figgo.R

data class BottomHistoryRideAdapter(var data: List<String>,var x:Int,var context: Context): RecyclerView.Adapter<RideHistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideHistoryHolder {
        return RideHistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.ridehistory_table_adapter,parent,false))

    }

    override fun onBindViewHolder(holder: RideHistoryHolder, position: Int) {
        var str = data[position]
        holder.boxTV.text = str
        if (x == 0) {
            //holder.boxTV.setTextColor(Color.WHITE)
            holder.block.setBackgroundColor(context.getColor(R.color.colornavyblue))
            holder.boxTV.setTextColor(context.getColor(R.color.white))
            holder.boxTV.setTypeface(null, Typeface.BOLD);
        } else if (x % 2 == 0) {
            holder.block.setBackgroundColor(Color.LTGRAY)

        }

        if(str.equals("View")&&x!=0){
            holder.boxTV.setBackgroundColor(context.getColor(R.color.appcolorlight))
            holder.boxTV.setTextColor(context.getColor(R.color.white))

            holder.boxTV.setOnClickListener {
                context.startActivity(Intent(context, HistoryMapsActivity::class.java))
            }

        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

}
class RideHistoryHolder(itemView: View):ViewHolder(itemView){
    var boxTV=itemView.findViewById<TextView>(R.id.tablecontentTV)
    var block = itemView.findViewById<LinearLayout>(R.id.blocklinear)
}
