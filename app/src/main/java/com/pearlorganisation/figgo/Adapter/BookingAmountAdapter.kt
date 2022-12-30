package com.pearlorganisation.figgo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.BookingAmountList
import com.pearlorganisation.figgo.R

class BookingAmountAdapter (private val mList1: List<BookingAmountList>) : RecyclerView.Adapter<BookingAmountAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingAmountAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onewaybookinglist, parent, false)
        return BookingAmountAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingAmountAdapter.ViewHolder, position: Int) {
        val BookingAmountList = mList1[position]

        holder.compactcab.text = BookingAmountList.compactcab
        holder.swiftcab.text = BookingAmountList.swiftcab
        holder.amountyellow.text = BookingAmountList.amountyellow
        holder.driverchange.text = BookingAmountList.driverchange
        holder.amountpurple.text = BookingAmountList.amountpurple
        holder.driverchangepurple.text = BookingAmountList.driverchangepurple

    }

    override fun getItemCount(): Int {
       return mList1.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){

        var compactcab: TextView = itemView.findViewById(R.id.compactcab)
        var swiftcab:TextView = itemView.findViewById(R.id.swiftcab)
        var carimg:ImageView = itemView.findViewById(R.id.carimg)
        var carimg1:ImageView = itemView.findViewById(R.id.carimg1)
        var amountyellow:TextView = itemView.findViewById(R.id.amountyellow)
        var driverchange:TextView = itemView.findViewById(R.id.driverchange)
        var amountpurple:TextView = itemView.findViewById(R.id.amountpurple)
        var driverchangepurple:TextView = itemView.findViewById(R.id.driverchangepurple)

    }
}


