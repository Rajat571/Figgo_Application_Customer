package com.figgo.customer.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.*
import com.figgo.customer.Model.BookingAmountList
import com.figgo.customer.UI.Current_Cab_DetailsActivity

class BookingAmountAdapter (var context: Context,private val mList1: List<BookingAmountList>) : RecyclerView.Adapter<BookingAmountAdapter.ViewHolder>()  {

   /* var onItemClick : ((BookingAmountList) -> Unit)? = null
*/
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

        holder.amountpurple.setOnClickListener {
            context.startActivity(Intent(context, Current_Cab_DetailsActivity::class.java))

        }

       /* holder.itemView.setOnClickListener {
            onItemClick?.invoke(BookingAmountList)
        }*/

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


