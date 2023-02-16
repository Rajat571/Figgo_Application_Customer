package com.figgo.customer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Model.CurrentWalletModel
import com.figgo.customer.Model.DicountModelList
import com.figgo.customer.R

class DiscountAdapter(var context: Context, var discountlist: ArrayList<DicountModelList>): RecyclerView.Adapter<DiscountAdapter.DiscountHolder>() {

    class DiscountHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var image=itemview.findViewById<ImageView>(R.id.figgo_add1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountAdapter.DiscountHolder {
        return DiscountAdapter.DiscountHolder(LayoutInflater.from(parent.context).inflate(R.layout.discountlistscreen, parent, false))
    }

    override fun onBindViewHolder(holder: DiscountAdapter.DiscountHolder, position: Int) {
        var data=discountlist[position]
        holder.image.setImageResource(data.image)
    }

    override fun getItemCount(): Int {
       return discountlist.size
    }

}