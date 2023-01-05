package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.CurrentMap.MapsActivity2
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R

class OneWayKmCountAdapter(var context: Context, private val mList: List<OneWayListRatingVehicle>) : RecyclerView.Adapter<OneWayKmCountAdapter.ViewHolder>() {

    lateinit var pref: PrefManager
   /* var onItemClick : ((OneWayListRatingVehicle) -> Unit)? = null*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneWayKmCountAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.oneway_count_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OneWayKmCountAdapter.ViewHolder, position: Int) {
        val OneWayListRatingVehicle = mList[position]

        holder.vehiclemodel.text = OneWayListRatingVehicle.vehiclemodel
        holder.reject.text = OneWayListRatingVehicle.reject
        holder.acceptcountlist.text = OneWayListRatingVehicle.acceptcountlist

        holder.acceptcountlist.setOnClickListener {
            context.startActivity(Intent(context, MapsActivity2::class.java))

        }
       /* pref.setvehicle_type_id(OneWayListRatingVehicle.vehicle_type_id)
        pref.setride_id(OneWayListRatingVehicle.ride_id)
*/
      /*  holder.itemView.setOnClickListener {
            onItemClick?.invoke(OneWayListRatingVehicle)
        }*/

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){

        var vehiclemodel:TextView = itemView.findViewById(R.id.vehiclemodel)
        val raingcountlist:TextView = itemView.findViewById(R.id.raingcountlist)
        //   val ride_service_rating:TextView = itemView.findViewById(R.id.ride_service_rating)
        val reject:TextView = itemView.findViewById(R.id.reject)
        val acceptcountlist:TextView = itemView.findViewById(R.id.acceptcountlist)

    }

}