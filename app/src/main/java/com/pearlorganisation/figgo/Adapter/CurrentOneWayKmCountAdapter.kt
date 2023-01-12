package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.CurrentMap.MapsActivity1
import com.pearlorganisation.figgo.CurrentMap.MapsActivity2
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R

class CurrentOneWayKmCountAdapter(var context: Context, private val mList: List<OneWayListRatingVehicle>) : RecyclerView.Adapter<CurrentOneWayKmCountAdapter.ViewHolder>() {

    lateinit var pref: PrefManager
   /* var onItemClick : ((OneWayListRatingVehicle) -> Unit)? = null*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentOneWayKmCountAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.oneway_count_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrentOneWayKmCountAdapter.ViewHolder, position: Int) {
        val OneWayListRatingVehicle = mList[position]

        holder.vehicleprice.text = mList.get(position).rating
        holder.vehiclname.text = mList.get(position).name
        holder.vehiclemodel.text = mList.get(position).year
       /* holder.raing.text = mList.get(position).rating*/

        holder.ll_main.setOnClickListener {
            context.startActivity(Intent(context, MapsActivity1::class.java))
        }
        pref.setride_id(OneWayListRatingVehicle.ride_id)
        pref.setdriver_id(OneWayListRatingVehicle.driver_id)


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){

        var vehiclname:TextView = itemView.findViewById(R.id.vehiclname)
        var vehiclemodel:TextView = itemView.findViewById(R.id.vehiclemodel)
        var vehicleprice:TextView = itemView.findViewById(R.id.vehicleprice)
        val raing:TextView = itemView.findViewById(R.id.raingcountlist)
        val ll_main:LinearLayout = itemView.findViewById(R.id.ll_main)

    }

}