package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.CurrentMap.EmergencyMapsActivity
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
        pref = PrefManager(context)
        val OneWayListRatingVehicle = mList[position]

        holder.vehicleprice.text = mList.get(position).price
        holder.vehiclname.text = mList.get(position).name
        holder.vehiclemodel.text = mList.get(position).year
        holder.rating.text = mList.get(position).rating

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, MapsActivity2::class.java))
        }

        holder.acceptbutton.setOnClickListener {
            Toast.makeText(context,"Accepted Driver",Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, EmergencyMapsActivity::class.java))


        }

        holder.reject.setOnClickListener {
           Toast.makeText(context,"Rejected",Toast.LENGTH_SHORT).show()
        }

        pref.setride_id(OneWayListRatingVehicle.ride_id)
        pref.setdriver_id(OneWayListRatingVehicle.driver_id)
        pref.setprice(OneWayListRatingVehicle.price)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        var vehiclname:TextView = itemView.findViewById(R.id.vehiclname)
        var vehiclemodel:TextView = itemView.findViewById(R.id.vehiclemodel)
        var vehicleprice:TextView = itemView.findViewById(R.id.vehicleprice)
        var acceptbutton:TextView = itemView.findViewById(R.id.acceptbutton)
        var reject:TextView = itemView.findViewById(R.id.reject)
        val rating:TextView = itemView.findViewById(R.id.rating)
        val ll_main:LinearLayout = itemView.findViewById(R.id.ll_main)

    }

}